package com.gl365.order.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
public class AESUtils {
	/** 密钥算法 */
	private static final String KEY_ALGORITHM = "AES";
	private static final int KEY_SIZE = 128;
	/** 加密/解密算法/工作模式/填充方法 */
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 获取密钥
	 * @return
	 * @throws Exception
	 */
	public static Key getKey() throws Exception {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// AES 要求密钥长度为128位、192位或256位
		kg.init(KEY_SIZE);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey;
	}

	/**
	 * 转化密钥
	 * @param key 密钥
	 * @return Key 密钥
	 * @throws Exception
	 */
	public static Key codeToKey(String key) throws Exception {
		byte[] keyBytes = Base64.decodeBase64(key);
		SecretKey secretKey = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	private static String decrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化，设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return new String(cipher.doFinal(data), "UTF-8");
	}

	/**
	 * 解密
	 * @param data 待解密数据
	 * @param key 密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		return decrypt(Base64.decodeBase64(data), key.getBytes());
	}

	/**
	 * 加密
	 * @param data 待加密数据
	 * @param key 密钥
	 * @return bytes[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 还原密钥
		Key k = new SecretKeySpec(key, KEY_ALGORITHM);
		/**
		 * 实例化
		 * 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	public static String encrypt(String data, String key) throws Exception {
		byte[] dataBytes = data.getBytes();
		return Base64.encodeBase64String(encrypt(dataBytes, key.getBytes()));
	}

	/**
	 * 初始化密钥
	 * @return
	 * @throws Exception
	 */
	public static String getKeyStr() throws Exception {
		return Base64.encodeBase64String(getKey().getEncoded());
	}
	
//	public static void main(String[] args) throws Exception {
//		String aeskey ="51839158BC07EC5E";
//		String key = encrypt("123456", aeskey);
//		System.out.println(key);
////		String result = decrypt("sK07EfQ3rBHGchbd9qzdjjt4HP6/L77A7DZ3UcE6HOKJCG9girKkJ8lwj7iXJx3cqJRHyxWrZs7furljDD3+RpXsJDZndlGiEbxRE8Dg4f8uWojIar4oN+pzurUvWBBj9sP3Oe4rbTYpQyYD1saVqbpWVARsrGmnfghV0faLFgk71APp6oemgAu61WV/8+t5Jnz8uHEMGaG93HOLgCbFGlz8CWSVQgffok6Wa0uuwzdFAlNo8ToTBU6F6a/aOiccvMKER7HG8XwK71G/m7mAr0GNq5SQNIjku1i8JtRxlw9C5BG8HbgisJdYJ2xdsQNPX6fL5qreTc6g4TEpqnVyCVZCSSRz0yBKaVdiI/OGE2SWxIU9ksZJwE1qLMsfVt5Y9VItNYpt3zJoA9vLCLeUm0anI3jQzc8SrVI1YbqpzHXCcpoS+FQjM+byplcmQeRF", "51839158BC07EC5E");
//		String result = decrypt(key, aeskey);
//		System.out.println(result);
//	}
}
