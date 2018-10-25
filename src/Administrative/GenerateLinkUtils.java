package Administrative;

import pojo.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
*For the resetting password link If we send the link just including username it is not safe, so we try to use MD5 to increase security. So other users cannot put a username directly into reset password
 */
public class GenerateLinkUtils {
	
	private static final String CHECK_CODE = "checkCode";


	public static String generateResetPwdLink(User user) {
		return "http://localhost:8283/resetPassword.jsp?userName="
				+ user.getName() + "&" + CHECK_CODE + "=" + generateCheckcode(user);
	}
	

	public static String generateCheckcode(User user) {
		String userName = user.getName();
		String randomCode = user.getRandomCode();
		return md5(userName + ":" + randomCode);
	}


	private static String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

}
