package jp.co.minato.pmw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jp.co.minato.pmw.utility.Validator;

/**
 * DAO共通クラス
 */
public class AbstractDao2 {

	/** データベース接続オブジェクト */
	private Connection connection;

	/**
	 * データベースに接続します。
	 */
	private void connectDb() {
		try {
			Class.forName("org.postgresql.Driver");
			//接続URL
			String url = "jdbc:postgresql://localhost:5432/product_management";
			//接続ユーザ
			String user = "postgres";
			//接続パスワード
			String dbPassword = "root";
			connection = DriverManager.getConnection(url, user, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * データベースを切断します。
	 */
	protected void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {
		if (connection == null) {
			connectDb();
		}
		return connection;
	}

	/**
	 * あいまい検索の特殊文字をエスケープします
	 * @param string エスケープ前の文字列
	 * @return エスケープ後の文字列
	 */
	protected String escape(String string) {

		return string.replace("#", "##")
				.replace("_", "#_")
				.replace("%", "#%");
	}

	/**
	 * 検索文字列をあいまい検索の部分一致検索文字列に変換します。
	 * @param string 検索文字列
	 * @return 部分一致検索文字列
	 */
	protected String escapeForFuzzySearch(String string) {
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(escape(string));
		sb.append("%");
		return sb.toString();
	}

	/**
	 * 非空白チェック
	 * @param <E> 数値型
	 * @param e チェック対象
	 * @return チェック対象がnullでない場合trueを返す、それ以外の場合はfalseを返す。
	 */
	protected <E extends Number> boolean isNotEmpty(E e) {
		return Validator.isNotEmpty(e);
	}

	/**
	 * 非空白チェック
	 * @param object チェック対象
	 * @return チェック対象がnullでない場合trueを返す、それ以外の場合はfalseを返す。
	 */
	protected boolean isNotEmpty(Object object) {
		return Validator.isNotEmpty(object);
	}

	/**
	 * 非空白チェック
	 * @param string チェック対象
	 * @return チェック対象がnullでない場合trueを返す、それ以外の場合はfalseを返す。
	 */
	protected boolean isNotEmpty(String string) {
		return Validator.isNotEmpty(string);
	}

	/**
	 * データベースのnull値をJavaのnull値に変換します。
	 * @param <E> データ型
	 * @param e チェック対象
	 * @param wasNull null判定値
	 * @return チェック対象がnullの場合、nullを返す、それ以外の場合はチェック対象を返す。
	 */
	protected <E> E applyNull(E e, boolean wasNull) {
		return wasNull ? null : e;
	}

}
