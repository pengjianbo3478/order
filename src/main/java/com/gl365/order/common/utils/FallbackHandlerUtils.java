package com.gl365.order.common.utils;

import com.gl365.order.common.ResultCodeEnum;
import com.gl365.order.dto.PageDto;
import com.gl365.order.dto.PageResultDto;
import com.gl365.order.dto.ResultDto;

/**
 * < 熔断器公用低配处理工具 >
 * 
 * @author hui.li 2017年4月22日 - 下午2:57:14
 * @Since 1.0
 */
public class FallbackHandlerUtils {

	/**
	 * 超时处理
	 * 
	 * @return
	 */
	public static ResultDto<?> timeOutResult() {
		return new ResultDto<>(ResultCodeEnum.System.SYSTEM_TIME_OUT);
	}

	/**
	 * 超时处理_分页
	 * 
	 * @return
	 */
	public static PageResultDto<?> timeOutResult(int curPage) {
		PageDto<Object> page = new PageDto<>(0, 0, curPage, null);
		return new PageResultDto<>(ResultCodeEnum.System.SYSTEM_TIME_OUT, page);
	}

	/**
	 * 空值处理
	 * 
	 * @return
	 */
	public static ResultDto<?> emptyValueResult() {
		return new ResultDto<>(ResultCodeEnum.System.SYSTEM_TIME_OUT);
	}

	/**
	 * 系统异常处理
	 * 
	 * @return
	 */
	public static ResultDto<?> systemErrorResult() {
		return new ResultDto<>(ResultCodeEnum.System.SYSTEM_TIME_OUT);
	}

}
