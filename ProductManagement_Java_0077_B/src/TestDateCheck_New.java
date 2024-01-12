//2023年10月28～29日テスト済み。チームB中島作成分
//うるう年まで厳格に判定します。
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TestDateCheck_New{
	public boolean testD_Result(String inputT){
		try {//■文字列を日付に変換する。
			//変換されなかった。
			DateTimeFormatter f1 = DateTimeFormatter.ofPattern("uuuuMMdd")
					.withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(inputT, f1);
			//日付に変換出来たので、入力された値(inputText)は、日付だった。
			//登録日チェッククリア
			return false;
		}catch (DateTimeParseException ex) {
			return true;
		}
	}
}
