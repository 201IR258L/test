package jp.co.minato.pmw.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import jp.co.minato.pmw.constant.ProductCategory;

public class ProductCategoryTypeHandler extends BaseTypeHandler<ProductCategory> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, ProductCategory parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCode());
	}

	@Override
	public ProductCategory getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return ProductCategory.codeOf(rs.getString(columnName));
	}

	@Override
	public ProductCategory getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return ProductCategory.codeOf(rs.getString(columnIndex));
	}

	@Override
	public ProductCategory getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ProductCategory.codeOf(cs.getString(columnIndex));
	}
}
