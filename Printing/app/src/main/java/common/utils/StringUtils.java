package common.utils;

import java.util.HashMap;

public class StringUtils {
	private final static String EMPTY = "";
	public static boolean isEmpty(String str) {
		if(str == null)
			return true;
		return (str.length() == 0);
	}
	public static boolean isBlank(String str) {
		if(str == null)
			return true;
		return (str.trim().length() == 0);
	}
	public static String nullToEmpty(String str) {
		if(str == null)
			return EMPTY;
		return str;
	}
	public static String emptyToNull(String str) {
		if(str == null)
			return null;
		if(str.length() == 0)
			return null;
		return str;
	}
	public static String trimToEmpty(String str) {
		if(str == null)
			return EMPTY;
		return str.trim();
	}
	public static String trimToNull(String str) {
		if(str == null)
			return null;
		str = str.trim();
		if(str.length() == 0)
			return null;
		return str;
	}
	public static boolean equals(String str1, String str2) {
		if(str1 == null)
			str1 = EMPTY;
		if(str2 == null)
			str2 = EMPTY;
		return str1.equals(str2);
	}
	public static String replace(String src_string, String sub_string, String replace_string )
	{
		if( src_string == null || src_string.length() == 0 ||
			sub_string == null || sub_string.length() == 0 ||
			(replace_string != null && replace_string.equals( sub_string ) )
			)
		{
			return src_string;
		}
		if(src_string.indexOf( sub_string ) == -1)
			return src_string;

		int src_string_len = src_string.length();
		int sub_string_len = sub_string.length();
		int replace_string_len = 0;
		if(replace_string != null)
			replace_string_len = replace_string.length();

		StringBuffer sb = new StringBuffer();
		int iEnd = 0;
		int iStart = 0;
		while( iStart < src_string_len )
		{
			iEnd = src_string.indexOf( sub_string,iStart );
			if(iEnd == -1)
				iEnd = src_string_len;
			sb.append(src_string.substring(iStart, iEnd ));
			if(replace_string_len > 0 && iEnd < src_string_len)
				sb.append(replace_string);

			iStart = iEnd + sub_string_len;
		}
		return sb.toString();
	}
	public static HashMap<String,String> toHashMap(String str, String attrDiv, String keyDiv) {
		HashMap<String,String> hsmpVal = new HashMap<String,String>();
		String[] items = str.split(attrDiv);
		for(int i=0;i<items.length;i++) {
			int div = items[i].indexOf(keyDiv);
			if(div == -1)
				continue;
			String key = items[i].substring(0,div).trim();
			String value = items[i].substring(div + 1).trim();
			hsmpVal.put(key, value);
		}
		return hsmpVal;
	}
	public static String convert(String src, String[][] strConvert) {
		if(src == null || strConvert == null || strConvert.length == 0)
			return src;
		int[] indexs = new int[strConvert.length];
		for(int i=0;i<indexs.length;i++) {
			if(strConvert[i] == null || strConvert[i].length != 2) {
				indexs[i] = -1;
			}
			else if(strConvert[i][0] == null || strConvert[i][0].length() == 0)
				indexs[i] = -1;
			else
				indexs[i] = 0;
		}
		int iStart = 0;
		StringBuffer sb = new StringBuffer();
		while(true) {
			int iMin = -1;
			int iMinIndex = -1;
			for(int i=0;i<strConvert.length;i++)
			{
				if(indexs[i] == -1)
					continue;
				if(iStart >= indexs[i]) {
					indexs[i] = src.indexOf(strConvert[i][0],iStart);
				}
				if(indexs[i] == -1)
					continue;
				if(iMin == -1 || iMin > indexs[i])
				{
					iMin = indexs[i];
					iMinIndex = i;
				}
			}
			if(iMinIndex == -1)
			{
				sb.append(src.substring(iStart));
				return sb.toString();
			}
			else {
				sb.append(src.substring(iStart,indexs[iMinIndex]));
				if(strConvert[iMinIndex][1] != null)
					sb.append(strConvert[iMinIndex][1]);
				iStart = iMin + strConvert[iMinIndex][0].length();
				indexs[iMinIndex] = iStart;
			}
		}
	}
}
