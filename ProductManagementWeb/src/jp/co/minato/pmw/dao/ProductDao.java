package jp.co.minato.pmw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import jp.co.minato.pmw.condition.ProductCondition;
import jp.co.minato.pmw.constant.ProductCategory;
import jp.co.minato.pmw.entity.ProductEntity;

/**
 * 商品DAO
 */
public class ProductDao extends AbstractDao {

	/**
	 * 商品情報を登録します。
	 * @param entity 商品情報
	 */
	public void insert(ProductEntity entity) {
		try {

			Connection connection = super.getConnection();
			// @formatter:off
			String sql = "INSERT INTO product ( " +
					"   product_id " +
					"  ,name " +
					"  ,category_code " +
					"  ,unit_price " +
					"  ,purchase_unit_price " +
					"  ,registration_date " +
					"  ,deleted " +
					"  ,create_user_id " +
					"  ,create_timestamp " +
					"  ,update_user_id " +
					"  ,update_timestamp " +
					"  ,version_no " +
					") VALUES (?,?,?,?,?,?,?,?,?,?,?,?); ";
			// @formatter:on
			PreparedStatement statement = connection.prepareStatement(sql);
			//パラメータ設定
			statement.setString(1, entity.getProductId());
			statement.setString(2, entity.getName());
			statement.setString(3, entity.getProductCategory().getCode());

			//販売単価、仕入単価、登録日は、未入力の場合、nullを登録する
			if (isNotEmpty(entity.getUnitPrice())) {
				statement.setInt(4, entity.getUnitPrice());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			if (isNotEmpty(entity.getPurchaseUnitPrice())) {
				statement.setInt(5, entity.getPurchaseUnitPrice());
			} else {
				statement.setNull(5, Types.INTEGER);
			}
			if (isNotEmpty(entity.getRegistrationDate())) {
				statement.setDate(6, entity.getRegistrationDate());
			} else {
				statement.setNull(6, Types.DATE);
			}
			statement.setBoolean(7, entity.getDeleted());
			statement.setInt(8, entity.getCreateUserId());
			statement.setTimestamp(9, entity.getCreateTimestamp());
			statement.setInt(10, entity.getUpdateUserId());
			statement.setTimestamp(11, entity.getUpdateTimestamp());
			statement.setInt(12, entity.getVersionNo());
			statement.executeUpdate();
			statement.close();
			super.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品情報を更新します。
	 * @param entity 商品情報
	 */
	public void update(ProductEntity entity) {
		try {
			Connection connection = super.getConnection();
			// @formatter:off
			String sql = "UPDATE product SET " +
					"   product_id = ? " +
					"  ,name = ? " +
					"  ,category_code = ? " +
					"  ,unit_price = ? " +
					"  ,purchase_unit_price = ? " +
					"  ,registration_date = ? " +
					"  ,deleted = ? " +
					"  ,create_user_id = ? " +
					"  ,create_timestamp = ? " +
					"  ,update_user_id = ? " +
					"  ,update_timestamp = ? " +
					"  ,version_no = ? " +
					" WHERE id = ? ";
			// @formatter:on
			PreparedStatement statement = connection.prepareStatement(sql);
			//パラメータ設定
			statement.setString(1, entity.getProductId());
			statement.setString(2, entity.getName());
			statement.setString(3, entity.getProductCategory().getCode());
			//販売単価、仕入単価、登録日は、未入力の場合、nullを登録する
			if (isNotEmpty(entity.getUnitPrice())) {
				statement.setInt(4, entity.getUnitPrice());
			} else {
				statement.setNull(4, Types.INTEGER);
			}
			if (isNotEmpty(entity.getPurchaseUnitPrice())) {
				statement.setInt(5, entity.getPurchaseUnitPrice());
			} else {
				statement.setNull(5, Types.INTEGER);
			}
			if (isNotEmpty(entity.getRegistrationDate())) {
				statement.setDate(6, entity.getRegistrationDate());
			} else {
				statement.setNull(6, Types.DATE);
			}
			statement.setBoolean(7, entity.getDeleted());
			statement.setInt(8, entity.getCreateUserId());
			statement.setTimestamp(9, entity.getCreateTimestamp());
			statement.setInt(10, entity.getUpdateUserId());
			statement.setTimestamp(11, entity.getUpdateTimestamp());
			statement.setInt(12, entity.getVersionNo());
			statement.setInt(13, entity.getId());
			statement.executeUpdate();
			statement.close();
			super.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品情報を検索します
	 * @param condition 検索条件
	 * @return 商品情報リスト
	 */
	public List<ProductEntity> find(ProductCondition condition) {
		List<ProductEntity> list = new ArrayList<ProductEntity>();
		try {
			Connection connection = super.getConnection();
			//検索用SQL作成
			String sql = getFindSql(condition);
			PreparedStatement statement = connection.prepareStatement(sql);
			//検索用パラメータ設定
			setFindParameter(statement, condition);
			ResultSet resultSet = statement.executeQuery();

			//商品情報をリストに詰め替える
			while (resultSet.next()) {
				ProductEntity entity = new ProductEntity();

				entity.setId(applyNull(resultSet.getInt("id"), resultSet.wasNull()));
				entity.setProductId(applyNull(resultSet.getString("product_id"), resultSet.wasNull()));
				entity.setName(applyNull(resultSet.getString("name"), resultSet.wasNull()));

				String categoryCode = applyNull(resultSet.getString("category_code"), resultSet.wasNull());
				entity.setProductCategory(ProductCategory.codeOf(categoryCode));
				entity.setUnitPrice(applyNull(resultSet.getInt("unit_price"), resultSet.wasNull()));
				entity.setPurchaseUnitPrice(applyNull(resultSet.getInt("purchase_unit_price"), resultSet.wasNull()));
				entity.setRegistrationDate(applyNull(resultSet.getDate("registration_date"), resultSet.wasNull()));

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

	/**
	 * 検索用SQL作成
	 * @param condition 検索条件
	 * @return 検索用SQL
	 */
	private String getFindSql(ProductCondition condition) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append("   id ");
		sql.append("  ,product_id ");
		sql.append("  ,name ");
		sql.append("  ,category_code ");
		sql.append("  ,unit_price ");
		sql.append("  ,purchase_unit_price ");
		sql.append("  ,registration_date ");
		sql.append("  ,deleted ");
		sql.append("  ,create_user_id ");
		sql.append("  ,create_timestamp ");
		sql.append("  ,update_user_id ");
		sql.append("  ,update_timestamp ");
		sql.append("  ,version_no ");
		sql.append(" FROM");
		sql.append("  product");
		sql.append(" WHERE");
		sql.append("  TRUE");
		sql.append(" AND deleted = FALSE");
		//以下の検索条件は指定されている場合のみ、SQLに追加する
		if (isNotEmpty(condition.getId())) {
			sql.append(" AND id = ?");
		}
		if (isNotEmpty(condition.getProductId())) {
			sql.append(" AND product_id = ?");
		}
		if (isNotEmpty(condition.getName())) {
			sql.append(" AND name like ? escape '#'");
		}
		if (isNotEmpty(condition.getExcludeId())) {
			sql.append(" AND id <> ? ");
		}
		sql.append(" ORDER BY product_id ASC");
		return sql.toString();
	}

	/**
	 * 検索用パラメータ設定
	 * @param statement PreparedStatement
	 * @param condition 検索条件
	 * @throws SQLException SQL例外
	 */
	private void setFindParameter(PreparedStatement statement, ProductCondition condition) throws SQLException {
		int index = 1;
		if (isNotEmpty(condition.getId())) {
			statement.setInt(index++, condition.getId());
		}
		if (isNotEmpty(condition.getProductId())) {
			statement.setString(index++, condition.getProductId());
		}
		if (isNotEmpty(condition.getName())) {
			statement.setString(index++, escapeForFuzzySearch(condition.getName()));
		}
		if (isNotEmpty(condition.getExcludeId())) {
			statement.setInt(index++, condition.getExcludeId());
		}
	}

}
