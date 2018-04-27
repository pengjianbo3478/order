package com.gl365.order.util;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

import org.springframework.security.crypto.codec.Base64;
public class RSAUtils {
	/** */
	/** 
	* 加密算法RSA 
	*/
	public static final String KEY_ALGORITHM = "RSA";
	/** */
	/** 
	* 签名算法 
	*/
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
	
	/**
	 * RSA加密模式
	 */
	public static final String RSA_ALGORITHM ="RSA/ECB/PKCS1Padding";
	
	/** */
	/** 
	* 获取公钥的key 
	*/
	private static final String PUBLIC_KEY = "RSAPublicKey";
	/** */
	/** 
	* 获取私钥的key 
	*/
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	/** */
	/** 
	* RSA最大加密明文大小 
	*/
	private static final int MAX_ENCRYPT_BLOCK = 117;
	/** */
	/** 
	* RSA最大解密密文大小 
	*/
	private static final int MAX_DECRYPT_BLOCK = 128;

	/** */
	/** 
	* <p> 
	* 生成密钥对(公钥和私钥) 
	* </p> 
	*  
	* @return 
	* @throws Exception 
	*/
	public static Map<String, Object> genKeyPair() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}

	/** */
	/** 
	* <p> 
	* 用私钥对信息生成数字签名 
	* </p> 
	*  
	* @param data 已加密数据 
	* @param privateKey 私钥(BASE64编码) 
	*  
	* @return 
	* @throws Exception 
	*/
	public static String sign(byte[] data, String privateKey) throws Exception {
		byte[] keyBytes = Base64.decode(privateKey.toString().getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64Utils.encode(signature.sign());
	}

	/** */
	/** 
	* <p> 
	* 校验数字签名 
	* </p> 
	*  
	* @param data 已加密数据 
	* @param publicKey 公钥(BASE64编码) 
	* @param sign 数字签名 
	*  
	* @return 
	* @throws Exception 
	*  
	*/
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		PublicKey publicK = getPublicKey(publicKey);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(Base64.decode(sign.getBytes()));
	}

	/** */
	/** 
	* <P> 
	* 私钥解密 
	* </p> 
	*  
	* @param encryptedData 已加密数据 
	* @param privateKey 私钥(BASE64编码) 
	* @return 
	* @throws Exception 
	*/
	public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
		PrivateKey prikey = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(2, prikey);
		 byte[] deBytes = cipher.doFinal(encryptedData);
		 return deBytes;
//		int inputLen = encryptedData.length;
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		int offSet = 0;
//		byte[] cache;
//		int i = 0;
//		// 对数据分段解密
//		while (inputLen - offSet > 0) {
//			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
//			} else {
//				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
//			}
//			out.write(cache, 0, cache.length);
//			i++;
//			offSet = i * MAX_DECRYPT_BLOCK;
//		}
//		byte[] decryptedData = out.toByteArray();
//		out.close();
//		return decryptedData;

	}
	
	public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
		byte [] data = Base64.decode(encryptedData.getBytes());
		byte[] bytes = decryptByPrivateKey(data, privateKey);
		return new String(bytes);
	}
	/** */
	/** 
	* <p> 
	* 公钥解密 
	* </p> 
	*  
	* @param encryptedData 已加密数据 
	* @param publicKey 公钥(BASE64编码) 
	* @return 
	* @throws Exception 
	*/
	public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
		PublicKey publicK = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			}
			else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/** */
	/** 
	* <p> 
	* 公钥加密 
	* </p> 
	* @param data 源数据 
	* @param publicKey 公钥(BASE64编码) 
	* @return 
	* @throws Exception 
	*/
	public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
		PublicKey publicK = getPublicKey(publicKey);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			}
			else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/** */
	/** 
	* <p> 
	* 私钥加密 
	* </p> 
	*  
	* @param data 源数据 
	* @param privateKey 私钥(BASE64编码) 
	* @return 
	* @throws Exception 
	*/
	public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
		PrivateKey prikey = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, prikey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			}
			else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}
	
	/** */
	/** 
	* <p> 
	* 获取私钥 
	* </p> 
	*  
	* @param keyMap 密钥对 
	* @return 
	* @throws Exception 
	*/
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return Base64Utils.encode(key.getEncoded());
	}

	
	/**base64字符串转换为公钥
	 *summer
	 *2017年6月7日 上午10:45:41
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception {
		byte[] keyBytes = Base64.decode(publicKey.getBytes());
		  X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		  PublicKey pubkey = keyFactory.generatePublic(keySpec);
		  return pubkey;
	 }
	
	/**base64字符串转换为私钥
	 *summer
	 *2017年6月7日 上午10:45:17
	 */
	public static PrivateKey getPrivateKey(String privateKey) throws Exception{
		byte[] keyBytes = Base64.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		return privateK;
	}
	
	/**读取文件获取公钥
	 *summer
	 *2017年6月7日 上午10:44:44
	 */
	public static PublicKey getPublicKeyByFile(String path) throws Exception {
		return getPublicKey(readPemFile(path));
	}
	
	/**读取文件获取私钥
	 *summer
	 *2017年6月7日 上午10:44:31
	 */
	public static PrivateKey getPrivateKeyByFile(String path) throws Exception{
		return getPrivateKey(readPemFile(path));
	}
	
	/**通过公钥文件对数据RSA加密,然后base64编码
	 *summer
	 *2017年6月7日 上午10:51:11
	 */
	public static String encryptByPrivateKeyFile(String data,String pubkeypath) throws Exception{
		String pubkey = readPemFile(pubkeypath);
		byte[] bytes = encryptByPublicKey(data.getBytes("UTF-8"), pubkey);
		return new String(Base64.encode(bytes));
	}
	
	/**对base64数据先解码，再通过私钥文件解密
	 *summer
	 *2017年6月7日 上午10:57:49
	 */
	public static String decryptByPrivateKeyFile(String data,String prikeypath) throws Exception{
		String prikey = readPemFile(prikeypath);
		byte[] bytes = Base64.decode(data.getBytes());
		byte[] rb = decryptByPrivateKey(bytes, prikey);
		return new String(rb,"UTF-8");
	}
	
	/**读取文件获取base64字符串
	 *summer
	 *2017年6月7日 上午10:46:27
	 */
	private static String readPemFile(String path) throws Exception{
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
	
	
	/** */
	/** 
	* <p> 
	* 获取公钥 
	* </p> 
	*  
	* @param keyMap 密钥对 
	* @return 
	* @throws Exception 
	*/
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return Base64Utils.encode(key.getEncoded());
	}
}
