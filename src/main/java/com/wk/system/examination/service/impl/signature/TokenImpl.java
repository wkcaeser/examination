package com.wk.system.examination.service.impl.signature;

import com.wk.system.examination.service.bs.signature.TokenBs;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class TokenImpl implements TokenBs {
	private MessageDigest md5;
	private char[] hexDigits;
	private Base64.Encoder base64Encoder;

	public TokenImpl() {
		try {
			this.md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("没有MD5算法");
		}
		hexDigits = "0123456789ABCDEF".toCharArray();
		base64Encoder = Base64.getEncoder();
	}

	/**
	 * 对用户名进行md5编码，然后再进行base64处理
	 * @param username 用户名
	 * @return token
	 */
	@Override
	public String getToken(String username) {
		byte[] md5Bytes = md5.digest(username.getBytes(Charset.forName("UTF-8")));
		String md5Str = toHex(md5Bytes);
		return base64Encoder.encodeToString(md5Str.getBytes(Charset.forName("UTF-8")));
	}

	private String toHex(byte[] bytes) {
		StringBuilder hexStr = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			hexStr.append(hexDigits[b >> 4 & 0X0F]);
			hexStr.append(hexDigits[b & 0X0F]);
		}
		return hexStr.toString();
	}

	@Test
	public void testMd5() {
		TokenBs instance = new TokenImpl();
		System.out.println(instance.getToken("wk"));
	}
}
