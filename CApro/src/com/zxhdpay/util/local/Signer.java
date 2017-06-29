package com.zxhdpay.util.local;

public interface Signer {
	public abstract String  sign(String paramString) throws Exception;
	public abstract byte[] sign(byte[] paramArrayofByte) throws Exception;
}
