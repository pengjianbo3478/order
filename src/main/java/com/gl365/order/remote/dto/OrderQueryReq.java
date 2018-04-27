package com.gl365.order.remote.dto;

import java.io.Serializable;

import com.gl365.order.util.JsonUtil;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DELL on 2017/9/13.
 */
public class OrderQueryReq implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="商户订单号")
    private String mchOrderNo;

    @ApiModelProperty(value = "开始时间",hidden = true)
    private String beginTime;

    @ApiModelProperty(value = "结束时间",hidden = true)
    private String endTime;

    @ApiModelProperty(value = "查询页面",hidden = true)
    private int pageNum = 1;


    public String getMchOrderNo() {
        return mchOrderNo;
    }

    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    

	@Override
	public String toString() {
		return JsonUtil.fromObject(this);
	}

}
