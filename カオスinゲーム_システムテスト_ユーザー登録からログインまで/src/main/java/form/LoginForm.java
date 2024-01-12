package form;
import java.io.Serializable;
//accountテーブルのレコードを格納するAccountクラス。
//これとは別に、ログイン情報を格納するLoginクラスは、
//LoginFormにLoginForm.javaを用意する。
//今回、dtoは仕様上作成する必要はない。
//ログインフォームから入力された情報を受け取るクラス。
//パスワード
public class LoginForm implements Serializable {

	private String useId;
	private String password;
	
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String hexDigest) {
		this.password = hexDigest;
	}
	}
	