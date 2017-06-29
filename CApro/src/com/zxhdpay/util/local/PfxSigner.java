package com.zxhdpay.util.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Enumeration;

import com.zxhdpay.util.common.StringUtils;

public class PfxSigner implements Signer {

	private PrivateKey privateKey;
	private String algorithm = "SHA1withRSA";
	private String pass = "zxhd123";
	public PfxSigner() {
		FileInputStream fi1;
		// 证书地址
		String pfxPath = "/CPCN/MyKey/jason.pfx"; 
		try {
			fi1 = new FileInputStream(pfxPath);
			KeyStore localKeyStore = KeyStore.getInstance("PKCS12","BC");
			localKeyStore.load(fi1, pass.toCharArray());
			fi1.close();
			Enumeration localEnumeration = localKeyStore.aliases();
			String str = (String) localEnumeration.nextElement();
			this.privateKey = (PrivateKey) localKeyStore.getKey("atlas",
					pass.toCharArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PfxSigner(String path,String certname){
		FileInputStream fi1;
		try {
			fi1 = new FileInputStream(path + File.separatorChar + certname);
			KeyStore localKeyStore = KeyStore.getInstance("PKCS12","BC");
			localKeyStore.load(fi1, pass.toCharArray());
			fi1.close();
			Enumeration localEnumeration = localKeyStore.aliases();
			String str = (String) localEnumeration.nextElement();
			this.privateKey = (PrivateKey) localKeyStore.getKey("atlas",
					pass.toCharArray());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String sign(String paramString) throws Exception {
		Signature localSignature = Signature.getInstance(this.algorithm);
		localSignature.initSign(this.privateKey);
		localSignature.update(paramString.getBytes("UTF-8"));
		return StringUtils.bytes2hex(localSignature.sign());
	}

	@Override
	public byte[] sign(byte[] paramArrayofByte) throws Exception {
		Signature localSignature = Signature.getInstance(this.algorithm);
		localSignature.initSign(this.privateKey);
		localSignature.update(paramArrayofByte);
		return localSignature.sign();
	}

}
