package util;
/*ログインやユーザー登録処理するコントローラー内で
 *値をDAOに操作させる前に、ハッシュ化させるもの*/
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Utility {

	public static String digest(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//■平文の文字列からハッシュ値に変換する
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		//MD5形式のハッシュ値を計算する => 128bit（16バイト）
		byte[] digest = messageDigest.digest(password.getBytes("UTF-8"));
		//128bit（16バイト） => 32桁の16進数文字列に変換する
		String hexDigest = DatatypeConverter.printHexBinary(digest);
		return hexDigest;
	}

}
