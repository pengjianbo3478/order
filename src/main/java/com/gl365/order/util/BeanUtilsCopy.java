package com.gl365.order.util;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/** 
 * 重写 org.springframework.beans.BeanUtils 的 copyProperties(Object source, Object target) 方法 
 * 从 source复制属性到target中时, source中为null的属性不复制target中  
 */  
public abstract class BeanUtilsCopy extends BeanUtils {
	
	/** 
     * 从org.springframework.beans.BeanUtils类中直接复制过来 
     * @param source 
     * @param target 
     * @throws BeansException 
     */  
    public static void copyProperties(Object source, Object target) throws BeansException {  
        copyProperties(source, target, null, (String[]) null);  
    }  
    
    /** 
     * 从org.springframework.beans.BeanUtils类中直接复制过来,修改部分代码 
     * @param source 
     * @param target 
     * @param editable 
     * @param ignoreProperties 
     * @throws BeansException 
     */  
    private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties)  
            throws BeansException {  
  
        Assert.notNull(source, "Source must not be null");  
        Assert.notNull(target, "Target must not be null");  
  
        Class<?> actualEditable = target.getClass();  
        if (editable != null) {  
            if (!editable.isInstance(target)) {  
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() +  
                        "] not assignable to Editable class [" + editable.getName() + "]");  
            }  
            actualEditable = editable;  
        }  
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);  
        List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;  
  
        for (PropertyDescriptor targetPd : targetPds) {  
            Method writeMethod = targetPd.getWriteMethod();  
            if (writeMethod != null && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {  
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());  
                if (sourcePd != null) {  
                    Method readMethod = sourcePd.getReadMethod();  
                    if (readMethod != null &&  
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {  
                        try {  
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {  
                                readMethod.setAccessible(true);  
                            }  
                            Object value = readMethod.invoke(source);  
                            // 判断被复制的属性是否为null, 如果不为null才复制  
                            if (value != null) {  
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {  
                                    writeMethod.setAccessible(true);  
                                }  
                                writeMethod.invoke(target, value);  
                            }  
                        }  
                        catch (Throwable ex) {  
                            throw new FatalBeanException(  
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);  
                        }  
                    }  
                }  
            }  
        }  
    }  
      
}  

