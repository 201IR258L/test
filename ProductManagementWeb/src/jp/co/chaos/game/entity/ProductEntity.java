package jp.co.chaos.game.entity;

import java.sql.Date;

import jp.co.chaos.game.constant.ProductCategory;

public class ProductEntity extends AbstractEntity {
	private Integer id;
	private String productId;
	private String name;
	private ProductCategory productCategory;
	private Integer unitPrice;
	private Integer purchaseUnitPrice;
	private Date registrationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}

	public void setPurchaseUnitPrice(Integer purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
