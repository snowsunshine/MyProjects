package com.zxhdpay.util.local;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import com.zxhdpay.util.common.StringUtils;

public class CertificateVerifier implements Verifier {
	private PublicKey publicKey;
	private String algorithm = "SHA1withRSA";

	public CertificateVerifier() {
		String certPath = "/CPCN/MyKey/zpx.cer"; 
		try {
			FileInputStream fi = new FileInputStream(certPath);
			CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
			this.publicKey = localCertificateFactory.generateCertificate(fi).getPublicKey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CertificateVerifier(String path,String certname){
		try {
			FileInputStream fi = new FileInputStream(path + File.separatorChar + certname);
			CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
			this.publicKey = localCertificateFactory.generateCertificate(fi).getPublicKey();
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean verify(String paramString1, String paramString2)throws Exception {
		 byte[] arrayOfByte = StringUtils.hex2bytes(paramString2);
		 Signature localSignature = Signature.getInstance(this.algorithm);
		 localSignature.initVerify(this.publicKey);
		 localSignature.update(paramString1.getBytes("UTF-8"));
		 return localSignature.verify(arrayOfByte);
	}

	@Override
	public boolean verify(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)throws Exception {
		 Signature localSignature = Signature.getInstance(this.algorithm);
		 localSignature.initVerify(this.publicKey);
		 localSignature.update(paramArrayOfByte1);
		 return localSignature.verify(paramArrayOfByte2);
	}
	public static void main(String[] args) {
		CertificateVerifier cfv = new CertificateVerifier();
		System.err.println(cfv.publicKey);
	}
}
