package com.chenxq.ca.X509;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;



public class GenerateCa {
	private static String certPath = "d:/CPCN/MyKeypay/jason.cer";  
	private static String pfxPath = "d:/CPCN/MyKeypay/zpx.pfx";
	
    public static void main(String[] args) {  
        BaseCert baseCert = new BaseCert();  
        X509Certificate cert = baseCert.generateCert("jason");  
        System.out.println(cert.toString());  
        try {  
            FileOutputStream fos = new FileOutputStream(certPath);  
            fos.write(cert.getEncoded());  
            fos.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (CertificateEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        KeyStore store;
        String pass = "zxhd123";
		try {
			store = baseCert.generatePKCS12(pass); 
		    FileOutputStream fout =new FileOutputStream(pfxPath);  
		    store.store(fout, pass.toCharArray());         
		    fout.close();    
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
          
    }  
}
