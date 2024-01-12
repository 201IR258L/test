package jp.co.minato.pmw.mapper.entity;

import java.sql.Date;

import jp.co.minato.pmw.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductEntity extends AbstractEntity {
	private Integer id;
	private String productId;
	private String name;
	private ProductCategory productCategory;
	private Integer unitPrice;
	private Integer purchaseUnitPrice;
	private Date registrationDate;
}
