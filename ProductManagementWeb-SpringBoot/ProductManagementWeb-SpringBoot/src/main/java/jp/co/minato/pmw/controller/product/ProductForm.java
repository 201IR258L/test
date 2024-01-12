package jp.co.minato.pmw.controller.product;

import java.io.Serializable;

import jp.co.minato.pmw.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductForm implements Serializable {
	private String id;
	private String productId;
	private String name;
	private String categoryCode;
	private ProductCategory productCategory;
	private String unitPrice;
	private String purchaseUnitPrice;
	private String registrationDate;
	private String versionNo;

}
