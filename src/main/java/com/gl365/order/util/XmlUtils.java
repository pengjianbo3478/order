package com.gl365.order.util;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.util.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtils {
	
	 /** 
     * 将对象直接转换成String类型的 XML输出 
     *  
     * @param obj 
     * @return 
     */  
    public static String convertToXml(Object obj) {  
        // 创建输出流  
        StringWriter sw = new StringWriter();  
        try {  
            // 利用jdk中自带的转换类实现  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
  
            Marshaller marshaller = context.createMarshaller();  
            // 格式化xml输出的格式  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,  
                    Boolean.TRUE);  
            // 将对象转换成输出流形式的xml  
            marshaller.marshal(obj, sw);  
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return sw.toString();  
    }  
	
	
    /** 
     * 将String类型的xml转换成对象 
     */  
    @SuppressWarnings("unchecked")
	public static <T>T convertXmlStrToObject(Class<T>clazz, String xmlStr) {  
        T xmlObject = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);  
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);  
            Object obj = unmarshaller.unmarshal(sr);  
            xmlObject = (T) obj;
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return xmlObject;  
    }  
    
    /** 
     * 将String类型的xml转换成对象 
     */  
    @SuppressWarnings("unchecked")
	public static <T,R>T convertXmlStrToObject(Class<T>clazz ,Class<R> cls, String xmlStr) {  
        T xmlObject = null;  
        try {  
        	Type objectType = type(clazz, clazz);
            JAXBContext context = JAXBContext.newInstance(clazz,cls);  
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);  
            Object obj = unmarshaller.unmarshal(sr);  
            xmlObject = (T) obj;
        } catch (JAXBException e) {  
            e.printStackTrace();  
        }  
        return xmlObject;  
    }  
  
    
	public static String beanToXml(Object body) {
		if (body == null)
			throw new IllegalArgumentException("The body must not be null");
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		XStream xstream = new XStream();
		xstream.registerConverter(new XStreamDateConverter());
		xstream.aliasSystemAttribute(null, "class");
		xstream.processAnnotations(body.getClass());
		String result = xstream.toXML(body);
		sb.append(result);
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, Class<T> clazz) {
		if (StringUtils.isEmpty(xml))
			throw new IllegalArgumentException("The xml must not be null");
		if (clazz == null)
			throw new IllegalArgumentException("The clazz must not be null");
		XStream xstream = new XStream(new DomDriver());
		xstream.ignoreUnknownElements();
		xstream.registerConverter(new XStreamDateConverter());
		xstream.processAnnotations(clazz);
		xstream.alias("message", clazz);
		T t = (T) xstream.fromXML(xml);
		return t;
	}
	
	static ParameterizedType type(final Class<?> raw, final Type... args) {
		return new ParameterizedType() {
			public Type getRawType() {
				return raw;
			}

			public Type[] getActualTypeArguments() {
				return args;
			}

			public Type getOwnerType() {
				return null;
			}
		};
	}
	
}


class XStreamDateConverter extends AbstractSingleValueConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return type.equals(LocalDateTime.class);
	}

	@Override
	public Object fromString(String str) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return LocalDateTime.parse(str, formatter);
	}

	@Override
	public String toString(Object obj) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime localDateTime = (LocalDateTime) obj;
		return localDateTime.format(formatter);
	}
}