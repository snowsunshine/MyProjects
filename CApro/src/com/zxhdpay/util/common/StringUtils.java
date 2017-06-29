package com.zxhdpay.util.common;

public class StringUtils {
	 public static void main(String[] args) {
			for (; ; )System.out.println("Iiiiiiii");
	}
	public static String bytes2hex(byte[] paramArrayOfByte) {
	    String str1 = "";
	    String str2 = "";
	    if (null == paramArrayOfByte) {
	      return null;
	    }
	    for (int i = 0; i < paramArrayOfByte.length; i++) {
	      str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
	      if (str2.length() == 1) {
	        str2 = new StringBuilder().append("0").append(str2).toString();
	      }
	      str1 = new StringBuilder().append(str1).append(str2).toString();
	    }
	    return str1.toUpperCase();
	  }
	public static byte[] hex2bytes(String paramString) {
		byte[] res = new byte[paramString.length()/2];  
        char[] chs = paramString.toCharArray();  
        int[] b = new int[2];  
  
        for(int i=0,c=0; i<chs.length; i+=2,c++){              
            for(int j=0; j<2; j++){  
                if(chs[i+j]>='0' && chs[i+j]<='9'){  
                    b[j] = (chs[i+j]-'0');  
                }else if(chs[i+j]>='A' && chs[i+j]<='F'){  
                    b[j] = (chs[i+j]-'A'+10);  
                }else if(chs[i+j]>='a' && chs[i+j]<='f'){  
                    b[j] = (chs[i+j]-'a'+10);  
                }  
            }   
              
            b[0] = (b[0]&0x0f)<<4;  
            b[1] = (b[1]&0x0f);  
            res[c] = (byte) (b[0] | b[1]);  
        }  
          
        return res;  
	}
	public static byte[] hex2bytes1(String paramString)
	  {
	    paramString = paramString.toUpperCase();

	    char[] arrayOfChar = paramString.toCharArray();
	    byte[] arrayOfByte = new byte[arrayOfChar.length / 2];

	    int i = 0;

	    for (int j = 0; j < arrayOfChar.length; j += 2) {
	      byte k = 0;

	      k = (byte)(k | char2byte(arrayOfChar[j]));
	      k = (byte)(k << 4);

	      k = (byte)(k | char2byte(arrayOfChar[(j + 1)]));

	      arrayOfByte[i] = k;

	      i++;
	    }
	    return arrayOfByte;
	  }

	public static byte char2byte(char paramChar) {
		switch (paramChar) {
		case '0':
			return 0;
		case '1':
			return 1;
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case 'A':
			return 10;
		case 'B':
			return 11;
		case 'C':
			return 12;
		case 'D':
			return 13;
		case 'E':
			return 14;
		case 'F':
			return 15;
		case ':':
		case ';':
		case '<':
		case '=':
		case '>':
		case '?':
		case '@':
		}
		return 0;
	}
	public static boolean isEmpty(String paramString)
	  {
	    if ((null == paramString) || ("".equals(paramString.trim()))) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isNotEmpty(String paramString)
	  {
	    if ((paramString != null) && (!"".equals(paramString.trim()))) {
	      return true;
	    }
	    return false;
	  }
	 
}
