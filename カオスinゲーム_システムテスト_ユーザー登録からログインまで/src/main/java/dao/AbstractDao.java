//令和5年12月13日一時完成。作成者　第3教室チームB　中島　栄作
//データベース接続設定を抽象化。
//→関連ファイル:ArtisticChaosGameFinally/src/main/webapp/META-INF/context.xml
package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public abstract class AbstractDao extends HttpServlet{

	private Connection connection;

	protected Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/game");
			connection = dataSource.getConnection();
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
