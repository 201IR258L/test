package entity;
import java.io.Serializable;
/*accountテーブルのレコードを格納するAccountクラス。
 *これとは別に、ログイン情報を格納するJavaBeansは、
 *LoginFormにLoginForm.javaを用意する。
 *今回、dtoは仕様上作成する必要はない。
 */
public class AccountEntity implements Serializable {

	private String userId;
	private String password;
	private String name;
	private String authorityNumber;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthorityNumber() {
		return authorityNumber;
	}
	public void setAuthorityNumber(String authorityNumber) {
		this.authorityNumber = authorityNumber;
	}
	

}
