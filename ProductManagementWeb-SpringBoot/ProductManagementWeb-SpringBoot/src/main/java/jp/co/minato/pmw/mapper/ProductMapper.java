package jp.co.minato.pmw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.minato.pmw.condition.ProductCondition;
import jp.co.minato.pmw.mapper.entity.ProductEntity;

/**
 * 商品DAO
 */
@Mapper
public interface ProductMapper {

	/**
	 * 商品情報を登録します。
	 * 
	 * @param entity 商品情報
	 */
	public void insert(ProductEntity entity);

	/**
	 * 商品情報を更新します。
	 * 
	 * @param entity 商品情報
	 */
	public void update(ProductEntity entity);

	/**
	 * 商品情報を検索します
	 * 
	 * @param condition 検索条件
	 * @return 商品情報リスト
	 */
	public List<ProductEntity> find(ProductCondition condition);
}
