package jp.co.minato.pmw.dto;

import java.sql.Date;

import jp.co.minato.pmw.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductDto extends AbstractDto {
	private Integer id;
	private String productId;
	private String name;
	private ProductCategory productCategory;
	private Integer unitPrice;
	private Integer purchaseUnitPrice;
	private Date registrationDate;
	private Integer excludeId;

}
