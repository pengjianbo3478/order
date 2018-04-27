package com.gl365.order.fft.smzf.httpclient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gl365.order.fft.core.CommonSecurity;
import com.gl365.order.fft.smzf.bo.FftResponseMessage;

/**
 * 数据加解密处理
 *@author summer
 *@date:2017年6月15日 下午12:06:53
 */
@Component
public class FftHttpMessageSecurity extends CommonSecurity{
	public static final Logger LOG = LoggerFactory.getLogger(FftHttpMessageSecurity.class);
	
	/**
	 * 给乐RSA私钥
	 */
	@Value("${api.fft.smzf.cooperatorPriKey}")
	private String cooperatorPriKey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC3c+icx7no0IQx\rGzb61UuGqyhXHcw8E0ViM1oSIZQ5Nv83Gr5I8KFS/MoJ41t6/xS4Q1GPJXHHXtj7\raqeXWCAHKbO4TfV0HQZ9AqzcFsrAA5zRIcyelwcRv5SOvoHKoIwv7U5ETuEsCHhd\rIoPhjDr8x9plbqkrdLJHMf9unVLT0xVN0DGVNJtWfYX9oNO/JFAcmMi/GuoaiKu7\reerFW/5eJWh7k2KARX6mFru4agGhUwGgck9Db9HQQdywyh/TRy3Zvih75NYTMGKy\rrZ609rBB8F64UBVDtlcR34SXTsfEHOcwXd8J752ST49NI3VModPLj/bSN2gw7yey\rgTvpL9hZAgMBAAECggEARPegVYCIlcv+Rm/SxY32bNRfwBCFvXgJN5fSnspc7P2O\rCOtW6hj8rTQ3yGve8mh/I567RLNxCODRdgNsqesJb2fwFrPIOTTIQasFzWAMLQ+u\rnri0MlLY54U8lkVsif69dS8jo258HI+6oFjfd6W+b1W69zMZCis+8qELREyE3Pj0\rxBt3XSykrmGdtKWXNvIwdP+ZT6FtT26SUya0AYreDGwP8rxy9WhMz+ch6aEuBscN\riEEmLre1MOeMtXKTgqf31F7/yJ0tRPdEI07hbN9ycoYwYs3vgs6n6RwiieUw4ogb\rzfuk8U3Aq4TnAgzq3R1wz3zFL9VYRY4f69TyQHrJmQKBgQDpAdqeBsbe9z69jZfv\r90U1k0IGbXwrXdD+Z5LoRRaBrtavUSsGRnuY5Ad7vOeAP2podGyfr04SOivmu4nU\rQqgeLtjeXaKRQ7jYIu+I5ybixdUpuward+YEoBbsn+1BZsbfFdtOJzpFdEcbo8Ek\rqdLJP2QS8fWeWljswpA+zBI63wKBgQDJjjoorGpZnEvlEiy3rZcAB0qZWM14t6Xu\rxQsytdCT8i3oCgGPbm81E6ANn1XJ2I5tP5HpL0iBdz09nvnryy/2H9I7WNSL+8l9\rXBIS8FHbTeyrSOe4DKCvS8ZaWcK1hPR2OHtYafwfzwH2cmC0ki5NOU0x5uwQ65zt\rV4EfzZOLxwKBgQCCRZAV5BISHMfOMLju6B+guYkjkNjoYhZz9jJzfBT+QmMzN71H\rT+bQb0Uj0FTaJhp29gi5KskKdc2PvfBsZSwbW/PSNyWp8+QHq1ijASGDjjoEj9VF\rzs4ibeMDPtLmSvELTuwDAPmaVU1gTaixt+TqsW9hDDhX5YlsLcd55JyYnwKBgQC0\rJPGoTsnTA9X1jHP5SyaN+PZc7rje701YhjbHASfGQ4tyPBc+6hZtGvhPlJyphmtu\rC4ZPI1qrlDZhMAjyDa31TmWpc3CtGMLyz2q8EJ0o5JHmWYf5wReQ9an4MQfNE2Mn\rkr3zvO6CQ5PegEYcvhayIMf1FQ7i5KNionYfRa9dGwKBgQCC9e4UhRJEhy2TaB8L\rAWuZ/iBq9RwgBTyXg2OdoYv/BPUqF+5LHIdlqWfgDSIb0S7YsIcZDzNUezWO4lZV\rfak0JTw0oAPaxybVAqYFY6HOWff7liZsXHy6ha4awsRgfSwCvOyRBgUMD8LEfmJz\rJd2XGyQHr+3HxH7mnWvaNglLPA==";

	
	/**
	 * 给乐RSA公钥
	 */
	@Value("${api.fft.smzf.cooperatorPubKey}")
	private String cooperatorPubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt3PonMe56NCEMRs2+tVL\rhqsoVx3MPBNFYjNaEiGUOTb/Nxq+SPChUvzKCeNbev8UuENRjyVxx17Y+2qnl1gg\rBymzuE31dB0GfQKs3BbKwAOc0SHMnpcHEb+Ujr6ByqCML+1ORE7hLAh4XSKD4Yw6\r/MfaZW6pK3SyRzH/bp1S09MVTdAxlTSbVn2F/aDTvyRQHJjIvxrqGoiru3nqxVv+\rXiVoe5NigEV+pha7uGoBoVMBoHJPQ2/R0EHcsMof00ct2b4oe+TWEzBisq2etPaw\rQfBeuFAVQ7ZXEd+El07HxBznMF3fCe+dkk+PTSN1TKHTy4/20jdoMO8nsoE76S/Y\rWQIDAQAB";
	
	/**
	 * 给乐对称秘钥
	 */
	@Value("${api.fft.smzf.cooperatorAESKey}")
	private String cooperatorAESKey="SDFPIM683MD63E7J";
	
	/**
	 * 付费通公钥
	 */
	@Value("${api.fft.agency.smzfPubKey}")
	private String smzfPubKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyrJ4WlkefUPl1t1cCdYb\rODaupj9QcZOWeogDjxrUX+YKyJQ9mHunYKrUi4iw/yOYNmXsgG6I3cvn4UWQOeCW\r6Et7PxJUvbJR3y0s3bDewp8gVnWB7OVLhlPDpp3QjKmNrosv6vowKS2bmNIU32Sc\rzLvGQptX47+x92Sg17GTu72ymA9LF+JePFK2l8X+kVGoo7fd886gd87ANN1m3162\rPeomaY58RFr5kq1uTH5m5J3WwgVpb5PtvTH6JnFPOgP+YwcBDRzzyqKtPGZXkKj6\rg0HzvnxtZZUpeW9vDtemNnfsBphkMJkxocgRooA4cY3lEkxn5s2WWzm6q2zSAlDO\rHQIDAQAB";
	
	
	/**
	 * 给乐商户号
	 */
	@Value("${api.fft.agency.cooperator}")
	private String cooperator="805024000000203";

	public String getCooperatorPriKey() {
		return cooperatorPriKey;
	}

	public String getCooperatorPubKey() {
		return cooperatorPubKey;
	}

	public String getSmzfPubKey() {
		return smzfPubKey;
	}
	
	public String getCooperator() {
		return cooperator;
	}

	@Override
	public String getCooperatorAESKey() {
		return cooperatorAESKey;
	}
	
	/**
	 * <p>获取FFT AES key</p>
	 * @param fftResponseMessage
	 * @return String
	 * @throws Exception
	 */
	public String getRespAesKey(FftResponseMessage fftResponseMessage) throws Exception {
		String base64EncryptKeyData = fftResponseMessage.getEncryptKey();
		String encryptKey = getRespAesKey(base64EncryptKeyData);
		LOG.debug("FFT返回AES密钥={}", encryptKey);
		return encryptKey;
	}

	
	
}
