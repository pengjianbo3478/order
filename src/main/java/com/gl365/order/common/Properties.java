package com.gl365.order.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	
	@Value("${query.sum}")
	private  int querySum;
	
	@Value("${order.fft.url}")
	private  String fftUrl;

	public  String getFftUrl() {
		return fftUrl;
	}

	public void setFftUrl(String fftUrl) {
		this.fftUrl = fftUrl;
	}

	public int getQuerySum() {
		return querySum;
	}
	
	

}
