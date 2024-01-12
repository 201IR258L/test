package jp.co.chaos.game.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.chaos.game.entity.UserEntity;

/**
 * ユーザDAO
 */
public class UserDao extends AbstractDao {

	public void insert(UserEntity entity) {
		try {

			Connection connection = super.getConnection();
			String sql = "INSERT INTO \"user\" ( " +
					"   name " +
					"  ,login_id " +
					"  ,password " +
					"  ,deleted " +
					"  ,create_user_id " +
					"  ,create_timestamp " +
					"  ,update_user_id " +
					"  ,update_timestamp " +
					"  ,version_no " +
					") VALUES (?,?,?,?,?,?,?,?,?); ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, entity.getName());
			statement.setString(2, entity.getLoginId());
			statement.setString(3, entity.getPassword());
			statement.setBoolean(4, entity.getDeleted());
			statement.setInt(5, entity.getCreateUserId());
			statement.setTimestamp(6, entity.getCreateTimestamp());
			statement.setInt(7, entity.getUpdateUserId());
			statement.setTimestamp(8, entity.getUpdateTimestamp());
			statement.setInt(9, entity.getVersionNo());
			statement.executeUpdate();
			statement.close();
			super.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserEntity> find(UserEntity condition) {
		List<UserEntity> list = new ArrayList<UserEntity>();
		try {

			Connection connection = super.getConnection();
			String sql = getFindSql(condition);
			PreparedStatement statement = connection.prepareStatement(sql);
			setFindParameter(statement, condition);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				UserEntity entity = new UserEntity();
				entity.setId(applyNull(resultSet.getInt("id"), resultSet.wasNull()));
				entity.setName(applyNull(resultSet.getString("name"), resultSet.wasNull()));
				entity.setLoginId(applyNull(resultSet.getString("login_id"), resultSet.wasNull()));
				entity.setPassword(applyNull(resultSet.getString("password"), resultSet.wasNull()));
				entity.setDeleted(applyNull(resultSet.getBoolean("deleted"), resultSet.wasNull()));
				entity.setCreateUserId(applyNull(resultSet.getInt("create_user_id"), resultSet.wasNull()));
				entity.setCreateTimestamp(applyNull(resultSet.getTimestamp("create_timestamp"), resultSet.wasNull()));
				entity.setUpdateUserId(applyNull(resultSet.getInt("update_user_id"), resultSet.wasNull()));
				entity.setUpdateTimestamp(applyNull(resultSet.getTimestamp("update_timestamp"), resultSet.wasNull()));
				entity.setVersionNo(applyNull(resultSet.getInt("version_no"), resultSet.wasNull()));
				list.add(entity);
			}
			resultSet.close();
			statement.close();
			super.closeConnection();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getFindSql(UserEntity condition) {
		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT");
		sb.append("   id ");
		sb.append("   ,name ");
		sb.append("   ,login_id ");
		sb.append("   ,password ");
		sb.append("   ,deleted ");
		sb.append("   ,create_user_id ");
		sb.append("   ,create_timestamp ");
		sb.append("   ,update_user_id ");
		sb.append("   ,update_timestamp ");
		sb.append("   ,version_no ");
		sb.append(" FROM");
		sb.append("   \"user\"");
		sb.append(" WHERE");
		sb.append("     TRUE");
		if (isNotEmpty(condition.getLoginId())) {
			sb.append(" AND login_id = ?");
		}
		if (isNotEmpty(condition.getPassword())) {
			sb.append(" AND password = ?");
		}

		return sb.toString();
	}

	private void setFindParameter(PreparedStatement statement, UserEntity condition) throws SQLException {
		int index = 1;
		if (isNotEmpty(condition.getLoginId())) {
			statement.setString(index++, condition.getLoginId());
		}
		if (isNotEmpty(condition.getPassword())) {
			statement.setString(index++, condition.getPassword());
		}
	}
}
