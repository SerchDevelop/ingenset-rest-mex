package mx.indra.ingenset.utils;

import java.util.regex.Pattern;

public class ThisPasswordEncoder {

//	private static final String SALT = "KO3CK4AL8EZE4DIR3OU";

	public boolean isAcceptedByPolicy(String password) {
		
		boolean ret = true;

		if (password.length() < 6) {
			ret = false;
		}

		if (!(password.matches("^[\\pL\\pN]+$"))) {
			ret = false;
		}
		
		if (!(Pattern.compile("\\p{javaUpperCase}").matcher(password).find())) {
			ret = false;
		}
		
		if (!(Pattern.compile("\\p{javaLowerCase}").matcher(password).find())) {
			ret = false;
		}
		
		if (!(Pattern.compile("\\p{javaDigit}").matcher(password).find())) {
			ret = false;
		}
		
		return ret;
		
	}

	public String encodePassword(String rawPass, Object salt) {
		
		String saltString = (salt == null) ? "KO3CK4AL8EZE4DIR3OU" : (String) salt;

		String encPass = "X";
		int lenRawPass = rawPass.length();

		if (saltString.length() == 0)
			saltString = " ";

		int lenSalt = saltString.length();

		int pwdIdx = 0;
		for (int saltIdx = 0; pwdIdx < lenRawPass; ++saltIdx) {
			if (saltIdx == lenSalt)
				saltIdx = 0;

			char saltChar = (char) (saltString.charAt(saltIdx) % '$');
			char pwdChar = rawPass.charAt(pwdIdx);

			if ((pwdChar == '_') || (pwdChar == '.')) {
				encPass = encPass + pwdChar;
			} else {
				if (pwdChar > '@')
					pwdChar = (char) (pwdChar - '\7');
				pwdChar = (char) (pwdChar - '0');
				pwdChar = (char) (pwdChar + saltChar);
				if (pwdChar > '#')
					pwdChar = (char) (pwdChar - '$');
				pwdChar = (char) (pwdChar + '0');
				if (pwdChar > '9')
					pwdChar = (char) (pwdChar + '\7');

				encPass = encPass + pwdChar;
			}
			++pwdIdx;
		}

		return encPass;
	}

	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		
		return ((encPass == null) ? false : encPass.equals(encodePassword(rawPass, salt)));
		
	}
	
}