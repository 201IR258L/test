package constant;
//例)値を受け渡す必要があるものを事前に定数定義。
//メンテナンス性向上。
public class Constant {
	//LoginServlet.java内で使用。
	public static final String SESSION_KEY_LOGIN_USER = "SESSION_KEY_LOGIN_USER";
	//ユーザー登録時可否判定時、DAOに渡すinsert文を定義。
	private String sqlInsert;
    //ログイン判定で、DAOに渡すselect文を定義。
	private String sqlSelect;
	public String getSqlInsert() {
		this.sqlInsert= "insert into account (user_id,password,name,authority_number)values(?,?,?,?);";
		return sqlInsert;
	}
	public void setSqlInsert(String sqlInsert) {
		this.sqlInsert = sqlInsert;
	}
	public String getSqlSelect() {
		this.sqlSelect="select "
				+ "  * "
				+ "from "
				+ "  account "
				+ "where "
				+ "  user_id = ?; ";
				//+ "and "
				//+ "  password = ?;";
		return sqlSelect;
	}	
	//public void setSqlSelect(String sqlSelect) {
		//this.sqlSelect = sqlSelect;
	//public String getSqlInsert2() {
		//this.sqlInsert2= "insert into accou;
	//	return sqlInsert2;
	//}
	//public void setSqlInsert2(String sqlInsert2) {
		
		//this.sqlInsert2 = sqlInsert2;
	//}
	}//
