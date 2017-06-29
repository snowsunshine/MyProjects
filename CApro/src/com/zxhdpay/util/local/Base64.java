
package com.zxhdpay.util.local;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zxhdpay.util.common.StringUtils;

public class Base64{

	 private static final Base64Encoder encoder = new Base64Encoder();

	  public static byte[] encode(byte[] paramArrayOfByte)
	  {
	    int i = (paramArrayOfByte.length + 2) / 3 * 4;
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
	    try
	    {
	      encoder.encode(paramArrayOfByte, 0, paramArrayOfByte.length, localByteArrayOutputStream);
	    } catch (IOException localIOException) {
	      throw new RuntimeException("exception encoding base64 string: " + localIOException);
	    }

	    return localByteArrayOutputStream.toByteArray();
	  }

	  public static int encode(byte[] paramArrayOfByte, OutputStream paramOutputStream)
	    throws IOException
	  {
	    return encoder.encode(paramArrayOfByte, 0, paramArrayOfByte.length, paramOutputStream);
	  }

	  public static int encode(byte[] paramArrayOfByte, int paramInt1, int paramInt2, OutputStream paramOutputStream)
	    throws IOException
	  {
	    return encoder.encode(paramArrayOfByte, paramInt1, paramInt2, paramOutputStream);
	  }

	  public static byte[] decode(byte[] paramArrayOfByte)
	  {
	    int i = paramArrayOfByte.length / 4 * 3;
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
	    try
	    {
	      encoder.decode(paramArrayOfByte, 0, paramArrayOfByte.length, localByteArrayOutputStream);
	    } catch (IOException localIOException) {
	      throw new RuntimeException("exception decoding base64 string: " + localIOException);
	    }

	    return localByteArrayOutputStream.toByteArray();
	  }

	  public static byte[] decode(String paramString)
	  {
	    int i = paramString.length() / 4 * 3;
	    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(i);
	    try
	    {
	      encoder.decode(paramString, localByteArrayOutputStream);
	    } catch (IOException localIOException) {
	      throw new RuntimeException("exception decoding base64 string: " + localIOException);
	    }

	    return localByteArrayOutputStream.toByteArray();
	  }

	  public static int decode(String paramString, OutputStream paramOutputStream)
	    throws IOException
	  {
	    return encoder.decode(paramString, paramOutputStream);
	  }

	  public static String encode(String paramString1, String paramString2)
	    throws UnsupportedEncodingException
	  {
	    if (StringUtils.isEmpty(paramString1)) {
	      return "";
	    }
	    return new String(encode(paramString1.getBytes(paramString2)), paramString2);
	  }

	  public static String decode(String paramString1, String paramString2)
	    throws UnsupportedEncodingException
	  {
	    if (StringUtils.isEmpty(paramString1)) {
	      return "";
	    }
	    return new String(decode(paramString1.getBytes(paramString2)), paramString2);
	  }

	  public static boolean matchBase64(String paramString)
	  {
	    String str = "[A-Za-z0-9,/,=,+]+";
	    Pattern localPattern = Pattern.compile(str);
	    Matcher localMatcher = localPattern.matcher(paramString);
	    if (localMatcher.matches())
	      return true;
	    return false;
	  }
}