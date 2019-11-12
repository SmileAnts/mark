package com.smile.common;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * RSA加密
 */
public class RasMethod {
	private static Map<Integer, String> keyMap = new HashMap<>(); // 用于封装随机产生的公钥和私钥

	public static void main(String[] args) {
		// 生成公钥和私钥
		getKeyDair();
		// 加密字符串
		String message = "df723820";
		System.out.println("随机生成的公钥为:" + keyMap.get(0));
		System.out.println("随机生成的私钥为:" + keyMap.get(1));
		String messageEn = encrypt(message, keyMap.get(0));
		System.out.println("加密后的字符串为:" + messageEn);
		String messageDe = decrypt(messageEn, keyMap.get(1));
		System.out.println("还原后的字符串为:" + messageDe);
	}

	public static void getKeyDair() {
		KeyPairGenerator keyPair = null;
		try {
			keyPair = KeyPairGenerator.getInstance("RSA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 初始化秘钥对生成器，秘钥大小为96-1024位
		assert keyPair != null;
		keyPair.initialize(1024, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair key = keyPair.generateKeyPair();
		RSAPrivateCrtKey privateKey = (RSAPrivateCrtKey) key.getPrivate();// 得到私钥
		RSAPublicKey publicKey = (RSAPublicKey) key.getPublic(); // 得到公钥
		// 得到公钥字符串
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
		// 将公钥和私钥保存到Map
		keyMap.put(0, publicKeyString); // 0表示公钥
		keyMap.put(1, privateKeyString); // 1表示私钥
	}

	/**
	 * RSA公钥加密
	 * 
	 * @param str       加密字符串
	 * @param publicKey 公钥
	 * @return 密文
	 */
	public static String encrypt(String str, String publicKey) {
		// base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = null;
		String outStr = null;

		try {
			pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
		} catch (InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException
				| NoSuchPaddingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// RSA加密
		return outStr;
	}

	/**
	 * RSA私钥解密
	 * 
	 * @param str        加密字符串
	 * @param privateKey 私钥
	 * @return 铭文
	 */
	public static String decrypt(String str, String privateKey) {
		// 64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
		// base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = null;
		// RSA解密
		Cipher cipher = null;
		String outStr = null;

		try {
			priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			outStr = new String(cipher.doFinal(inputByte));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
				| IllegalBlockSizeException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return outStr;
	}

	/**
	 * 前端用crypto-js进行加密， npm i jsencrypt， 然后页面头引入import JSEncrypt from 'jsencrypt';
	 * const encrypt = new JSEncrypt(); encrypt.setPublicKey('你的公钥'); password =
	 * encrypt.encrypt(‘你的密码’);// 加密后的字符串
	 */
}
