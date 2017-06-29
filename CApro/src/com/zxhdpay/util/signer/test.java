package com.zxhdpay.util.signer;

import java.util.HashMap;
import java.util.Map;

import com.zxhdpay.util.local.Base64;
import com.zxhdpay.util.local.Signer;
import com.zxhdpay.util.local.Verifier;

public class test {
	 public static void main(String[] args) {
		try {
			String paramString = "/CPCN/MyKey";
			SignatureFactory.initialize(paramString, "jason.pfx", "zpx.cer");
			String pasw = "12355qq";
			//加密
			byte[] arrayOfByte1 = pasw.getBytes("UTF-8");
		    String requestMessage = new String(Base64.encode(arrayOfByte1));
		    System.out.println(requestMessage);
		    byte[] arrayOfByte2 = Base64.decode(requestMessage);
		    String responsePlainText = new String(arrayOfByte2, "UTF-8");
			System.out.println(responsePlainText);
			Signer signer = SignatureFactory.getSigner();
			String pwd = signer.sign(pasw);
			System.out.println(pwd);
			Verifier verifier = SignatureFactory.getVerifier();
			boolean flag = verifier.verify("12355qq",pwd);
			System.out.println(flag);
			Map<String, String> map  = new HashMap<String, String>();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
