package com.gl365.order.fft.core;

public interface SecretKeyConfig {

	/**
	 *商户密钥
	 */
	public String getCooperatorPriKey() ;

	/**
	 *商户公钥
	 */
	public String getCooperatorPubKey() ;

	/**
	 *商户对称秘钥
	 */
	public String getCooperatorAESKey();

	/**
	 *平台公钥（付费通）
	 */
	public String getSmzfPubKey() ;

	/**
	 *商户号
	 */
	public String getCooperator() ;

	
}
