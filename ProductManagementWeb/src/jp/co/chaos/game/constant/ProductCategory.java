package jp.co.chaos.game.constant;

/**
 * 商品分類Enum
 *
 */
public enum ProductCategory {
	// @formatter:off
	Clothes("01", "衣服"),
	OfficeSupplies("02", "事務用品"),
	KitchenSupplies("03", "キッチン用品");
	// @formatter:on

	/** 商品分類コード */
	private String code;
	/** 商品分類名 */
	private String name;

	private ProductCategory(String code, String name) {
		this.setCode(code);
		this.setName(name);
	}

	/**
	 * 商品分類コードから商品分類Enumを取得します。
	 * @param code 商品分類コード
	 * @return 商品分類Enum
	 */
	public static ProductCategory codeOf(String code) {
		for (ProductCategory productCategory : values()) {
			if (productCategory.getCode().equals(code)) {
				return productCategory;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	private void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

}
