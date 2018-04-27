package com.gl365.order.fft.core;

public interface SecurityHandler {

	String getEncryptData(String xmlRequestMessage) throws Exception ;

	String getCooperator() throws Exception ;

	String getSignData(String xmlRequestMessage) throws Exception ;

	String getRespAesKey(String encryptKey) throws Exception ;

	String getRespData(String aesKey, String encryptData) throws Exception ;

	boolean checkSignFromBase64(String xmlResponseData, String signData) throws Exception ;

	String getEncryptKey() throws Exception ;

}
