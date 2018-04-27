package com.gl365.order.fft.core;

import java.io.BufferedReader;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Base64;

import com.gl365.order.util.AESUtils;
import com.gl365.order.util.RSAUtils;


public abstract class CommonSecurity implements SecurityHandler,SecretKeyConfig{
	public static final Logger LOG = LoggerFactory.getLogger(CommonSecurity.class);
	
	/**
	 * <p>用合作方(FFT)RSA公钥验证签名</p>
	 * @return false/true
	 * @throws Exception 
	 */
	public boolean checkSignFromBase64(String xmlData, String signData) throws Exception {
		boolean result = RSAUtils.verify(xmlData.getBytes(), getSmzfPubKey(), signData);
		LOG.debug("验证签明结果={}", result);
		return result;
	}


	/**
	 * <p>用fft返回aeskey解密数据</p>
	 * @param aesKey
	 * @return String
	 * @throws Exception
	 */
	public String getRespData(String aesKey, String encryptData) throws Exception {
		// 用FFT返回AES密钥解密返回数据
		String result = AESUtils.decrypt(encryptData,aesKey );
		LOG.debug("AESkey解密 FFT返回后数据=\n{}", result);
		return result;
	}

	/**
	 * <p>获取FFT AES key</p>
	 * @param fftResponseMessage
	 * @return String
	 * @throws Exception
	 */
	public String getRespAesKey(String encryptKey) throws Exception {
//		String base64EncryptKeyData = fftResponseMessage.getEncryptKey();
		// 用给乐RSA私钥解密encryptKey
		String aesKey = RSAUtils.decryptByPrivateKey(encryptKey, getCooperatorPriKey());
		LOG.debug("FFT返回AES密钥={}", encryptKey);
		return aesKey;
	}


	/**
	 * <p>RSA加密xmlRequestMessage</p>
	 * @param xmlRequestMessage
	 * @return String
	 * @throws Exception
	 */
	public String getEncryptData(String xmlRequestMessage) throws Exception {
		return AESUtils.encrypt(xmlRequestMessage, getCooperatorAESKey());
	}

	

	/**
	 * <p>用FFT公钥加密给乐AESKey<p>
	 * @param xmlRequestMessage
	 * @return String
	 * @throws Exception
	 */
	public String getEncryptKey() throws Exception {
		return getEncryptKey(getCooperatorAESKey(),getSmzfPubKey());
	}

	private String getEncryptKey(String data,String key) throws Exception{
		LOG.debug("用FFT公钥加密给乐AESKey开始");
		byte[] bytes = data.getBytes();
		byte[] encodedData = RSAUtils.encryptByPublicKey(bytes, key);
		String encode = new String(Base64.encode(encodedData));
		LOG.debug("用FFT公钥加密给乐AESKey完成");
		return encode;
	}
	
	/**
	 * <p>RSA签名 xmlRequestMessage</p>
	 * @param xmlRequestMessage
	 * @return String
	 * @throws Exception
	 */
	public String getSignData(String xmlRequestMessage) throws Exception {
		LOG.debug("RSA签名开始....");
		byte[] data = xmlRequestMessage.getBytes();
		String signData = RSAUtils.sign(data, getCooperatorPriKey());
		LOG.debug("RSA签名完成....");
		return signData;
	}

	
	
	public String readPemFile(String path) throws Exception{
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try{
			br = new BufferedReader(new FileReader(path));
			String s = "";
			while((s=br.readLine())!= null){
				if((s.charAt(0))=='-'){
					continue;
				}
				sb.append(s);
				sb.append("\r");
			}
		}finally{
			if(br != null){
				br.close();
			}
		}
		return sb.toString();
	}
}
