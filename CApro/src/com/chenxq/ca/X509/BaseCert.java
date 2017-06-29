package com.chenxq.ca.X509;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/*import org.bouncycastle.jce.provider.BouncyCastleProvider;  
import org.bouncycastle.x509.X509V3CertificateGenerator; */


import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.x509.X509V3CertificateGenerator;

import com.chenxq.ca.util.CAConfig;
import com.chenxq.ca.util.DateUtil;

public class BaseCert {
	protected static KeyPairGenerator kpg = null;  
	// 公钥  
    public PublicKey pubKey ;  
    // 私钥  
    public PrivateKey priKey ; 
	public BaseCert() {
		try {  
            // 采用 RSA 非对称算法加密  
            kpg = KeyPairGenerator.getInstance("RSA");  
            // 初始化为 1023 位  
            kpg.initialize(1024);  
            KeyPair keyPair = this.kpg.generateKeyPair();  
            // 公钥  
            this.pubKey = keyPair.getPublic();  
            // 私钥  
            this.priKey = keyPair.getPrivate(); 
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
	}
	
	 /** 
     * 生成 X509 证书 
     * @param user 
     * @return 
     */  
    public X509Certificate generateCert(String user) {  
        X509Certificate cert = null;  
        try {  
            X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();  
            // 设置序列号  
            certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));  
            // 设置颁发者  
            certGen.setIssuerDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
            // 设置有效期  
            Date now = new Date();
            certGen.setNotBefore(now);  
            certGen.setNotAfter(DateUtil.getAnyYearByNo(now, 10));  
            // 设置使用者  
            certGen.setSubjectDN(new X500Principal(CAConfig.CA_DEFAULT_SUBJECT + user));  
            // 公钥  
            certGen.setPublicKey(pubKey);  
            // 签名算法  
            certGen.setSignatureAlgorithm(CAConfig.CA_SHA);  
            cert = certGen.generateX509Certificate(priKey, "BC");  
        } catch (Exception e) {  
            System.out.println(e.getClass() + e.getMessage());  
        }  
        return cert;  
    }  
    public KeyStore generatePKCS12(String pass) { 
    	KeyStore store = null;  
         try {  
        	 // 创建KeyStore  
        	 store = KeyStore.getInstance("PKCS12","BC");  
             store.load(null, null);  
             X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();  
             // 设置序列号  
             certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));  
             // 设置颁发者  
             certGen.setIssuerDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
             // 设置有效期  
             Date now = new Date();
             certGen.setNotBefore(now);  
             certGen.setNotAfter(DateUtil.getAnyYearByNo(now, 10));  
             // 设置使用者  
             certGen.setSubjectDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
             // 公钥  
             certGen.setPublicKey(pubKey);  
             // 签名算法  
             certGen.setSignatureAlgorithm(CAConfig.CA_SHA);  
             X509Certificate cert = certGen.generateX509Certificate(priKey); //获取私钥
             System.out.println(cert.toString());  
           
             store.setKeyEntry("atlas", priKey,     
                     pass.toCharArray(), new Certificate[] { cert });  
       
         } catch (Exception e) {  
             System.out.println(e.getClass() + e.getMessage());  
         }  
         return store;  
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException,
    NoSuchProviderException, SecurityException, SignatureException, KeyStoreException, CertificateException,
    IOException {  
    	String certPath = "d:/CPCN/MyKey/lee.cer";  
//        BaseCert baseCert = new BaseCert();  
//        X509Certificate cert = baseCert.generateCert("Lee"); 
    	 KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");  
    	 KeyPair keyPair = kpg.generateKeyPair();  
         // 公钥  
         PublicKey pubKey = keyPair.getPublic();  
         // 私钥  
         PrivateKey priKey = keyPair.getPrivate(); 

         X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();  
         // 设置序列号  
         certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));  
         // 设置颁发者  
         certGen.setIssuerDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
         // 设置有效期  
         Date now = new Date();
         certGen.setNotBefore(now);  
         certGen.setNotAfter(DateUtil.getAnyYearByNo(now, 1));  
         // 设置使用者  
         certGen.setSubjectDN(new X500Principal(CAConfig.CA_DEFAULT_SUBJECT + "lee"));  
         // 公钥  
         certGen.setPublicKey(pubKey);  
         // 签名算法  
         certGen.setSignatureAlgorithm(CAConfig.CA_SHA);  
         X509Certificate cert = certGen.generateX509Certificate(priKey, "BC");  
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
        
        String pfxPath = "d:/CPCN/MyKey/jason.pfx";  
        String pass="123465";
        KeyStore store = KeyStore.getInstance("PKCS12","BC");  
        store.load(null, null);  
        // 设置使用者  
        certGen.setSubjectDN(new X500Principal(CAConfig.CA_ROOT_ISSUER));  
        X509Certificate cert1 = certGen.generateX509Certificate(priKey); //获取私钥
        System.out.println(cert1.toString());  
        //System.out.println(keyPair.getPrivate());  
        //store.setCertificateEntry(alias, cert);  
      
        store.setKeyEntry("atlas", keyPair.getPrivate(),     
        		pass.toCharArray(), new Certificate[] { cert1 });  
  
        FileOutputStream fout =new FileOutputStream(pfxPath);  
        store.store(fout, pass.toCharArray());         
        fout.close();    
    }  
}
