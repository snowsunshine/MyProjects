package com.zxhdpay.util.signer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.zxhdpay.util.local.CertificateVerifier;
import com.zxhdpay.util.local.PfxSigner;
import com.zxhdpay.util.local.Signer;
import com.zxhdpay.util.local.Verifier;

public class SignatureFactory {
	private static Map<String, Signer> signerMap = new HashMap<String, Signer>();
	private static Map<String, Verifier> verifierMap = new HashMap<String, Verifier>();
	private static String defaultSigner = "signer";
	private static String defaultVerifier = "verifier";

	public static void initialize(String paramString ,String pfxcretname,String certname)
		    throws Exception{
		addSigner(defaultSigner, new PfxSigner(paramString, pfxcretname));
		addVerifier(defaultVerifier, new CertificateVerifier(paramString, certname));
	}
	public static  void addSigner(String paramString, Signer paramSigner){
		signerMap.put(paramString, paramSigner);
	    defaultSigner = paramString;
	}

	public static  void addVerifier(String paramString, Verifier paramVerifier) {
	    verifierMap.put(paramString, paramVerifier);
	    defaultVerifier = paramString;
	}

	public static  Signer getSigner() {
		return (Signer)signerMap.get(defaultSigner);
	}

	public static Signer getSigner(String paramString) {
		return (Signer) signerMap.get(paramString);
	}

	public static Verifier getVerifier() {
		return (Verifier) verifierMap.get(defaultVerifier);
	}

	public static  Verifier getVerifier(String paramString) {
		return (Verifier) verifierMap.get(paramString);
	}

	public  void clearVerifier() {
		verifierMap.clear();
	}

	
}