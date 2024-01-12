package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constant.Constant;
import entity.AccountEntity;
import form.LoginForm;

public class AccountSearchDao extends AbstractDao {

	// ログインアカウントを探す
    public AccountEntity findAccount(LoginForm loginForm) {

        // 戻り値の用意
        AccountEntity returnAccountData = new AccountEntity();
        try {
        // データベースへ接続
        Connection connection = super.getConnection();
        //SQL文準備
        //SQL文の内容は、Constantクラスで事前定義。
        Constant sqlStatement =new Constant();
        //SQL文を代入。
        String sql = sqlStatement.getSqlSelect();
        PreparedStatement ps= connection.prepareStatement(sql);
        
        ps.setString(1, loginForm.getUseId());
        //ps.setString(2, loginForm.getPassword());
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
        	// 見つかったアカウント情報を戻り値にセット
        	returnAccountData.setUserId(rs.getString("user_id"));
        	returnAccountData.setPassword(rs.getString("password"));
        	returnAccountData.setName(rs.getString("name"));
        	returnAccountData.setAuthorityNumber(rs.getString("authority_number"));
        } else {
                // アカウントがなければnullを返す
                return null;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
			return null;
			}
        return returnAccountData;
    }
}
