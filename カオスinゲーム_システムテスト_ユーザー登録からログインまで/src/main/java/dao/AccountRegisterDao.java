//令和5年12月13日　作成者　チームB　中島　栄作
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constant.Constant;
import entity.AccountEntity;

//■DAO（Data Access Object）
//■データの操作はDaoクラスで行う
//■データベース、ファイル、ネットワーク上リソースなど
public class AccountRegisterDao extends AbstractDao {
	        public   AccountRegisterDao(AccountEntity ard)  {
 
	        	try {
	      	    	  Connection connection = super.getConnection();
	            //sql文を取得して代入。
	            Constant sqlInsert= new Constant();
	            //sql文を代入。
	            String sql= sqlInsert.getSqlInsert(); 
	    				PreparedStatement ps;
	    				ps = connection.prepareStatement(sql);
	    				ps.setString(1, ard.getUserId());
	    	            ps.setString(2, ard.getPassword());
	    	            ps.setString(3, ard.getName());
	    	            ps.setString(4, ard.getAuthorityNumber());
	    	            ps.executeUpdate();}	        	
	        	catch (SQLException e) {
	                e.printStackTrace();}}}	
	        	
	        	
	        	
	        	
	        	