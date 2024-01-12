package jp.co.minato.pmw.service;

import java.sql.Timestamp;
import java.util.List;

import jp.co.minato.pmw.condition.ProductCondition;
import jp.co.minato.pmw.dao.ProductDao;
import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.entity.ProductEntity;
import jp.co.minato.pmw.utility.Utility;

/**
 * 商品情報サービス
 *
 */
public class ProductService {

	public void issue(ProductDto dto) {
		ProductDao dao = new ProductDao();
		ProductEntity entity = copyProductDtoToProductCondition(dto);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setDeleted(Boolean.FALSE);
		entity.setCreateTimestamp(now);
		entity.setUpdateTimestamp(now);
		entity.setVersionNo(0);

		dao.insert(entity);
	}

	public void update(ProductDto dto) {
		ProductDto condition = new ProductDto();
		condition.setId(dto.getId());
		List<ProductEntity> list = find(condition);
		ProductEntity entity = copyProductDtoToProductEntityForUpdate(dto, list.get(0));
		update(entity);
	}

	public void delete(ProductDto dto) {
		ProductDto condition = new ProductDto();
		condition.setId(dto.getId());
		List<ProductEntity> list = find(condition);
		ProductEntity entity = copyProductDtoToProductEntityForDelete(dto, list.get(0));
		update(entity);
	}

	public void update(ProductEntity entity) {
		ProductDao dao = new ProductDao();
		dao.update(entity);
	}

	private static ProductEntity copyProductDtoToProductEntityForUpdate(ProductDto dto, ProductEntity entity) {
		entity.setProductId(dto.getProductId());
		entity.setName(dto.getName());
		entity.setProductCategory(dto.getProductCategory());
		entity.setUnitPrice(dto.getUnitPrice());
		entity.setPurchaseUnitPrice(dto.getPurchaseUnitPrice());
		entity.setRegistrationDate(dto.getRegistrationDate());
		entity.setUpdateUserId(dto.getUpdateUserId());
		entity.setUpdateTimestamp(Utility.getCurrentTimestamp());
		entity.setVersionNo(entity.getVersionNo() + 1);
		return entity;
	}

	private static ProductEntity copyProductDtoToProductEntityForDelete(ProductDto dto, ProductEntity entity) {
		entity.setDeleted(Boolean.TRUE);
		entity.setUpdateUserId(dto.getUpdateUserId());
		entity.setUpdateTimestamp(Utility.getCurrentTimestamp());
		entity.setVersionNo(entity.getVersionNo() + 1);
		return entity;
	}

	private static ProductCondition copyProductDtoToProductCondition(ProductDto dto) {
		ProductCondition condition = new ProductCondition();
		condition.setId(dto.getId());
		condition.setProductId(dto.getProductId());
		condition.setName(dto.getName());
		condition.setProductCategory(dto.getProductCategory());
		condition.setUnitPrice(dto.getUnitPrice());
		condition.setPurchaseUnitPrice(dto.getPurchaseUnitPrice());
		condition.setRegistrationDate(dto.getRegistrationDate());
		condition.setDeleted(dto.getDeleted());
		condition.setCreateUserId(dto.getCreateUserId());
		condition.setCreateTimestamp(dto.getCreateTimestamp());
		condition.setUpdateUserId(dto.getUpdateUserId());
		condition.setUpdateTimestamp(dto.getUpdateTimestamp());
		condition.setVersionNo(dto.getVersionNo());
		condition.setExcludeId(dto.getExcludeId());
		return condition;
	}

	public List<ProductEntity> find(ProductDto dto) {
		ProductDao dao = new ProductDao();
		ProductCondition condition = copyProductDtoToProductCondition(dto);
		return dao.find(condition);
	}
}
