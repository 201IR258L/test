package jp.co.chaos.game.condition;

import jp.co.chaos.game.entity.ProductEntity;

/**
 * 商品検索条件クラス
 *
 */
public class ProductCondition extends ProductEntity {
	/** 検索対象外ID */
	private Integer excludeId;

	public Integer getExcludeId() {
		return excludeId;
	}

	public void setExcludeId(Integer excludeId) {
		this.excludeId = excludeId;
	}

}
