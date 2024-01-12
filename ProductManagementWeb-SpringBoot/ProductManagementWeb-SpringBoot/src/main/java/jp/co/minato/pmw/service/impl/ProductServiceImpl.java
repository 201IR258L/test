package jp.co.minato.pmw.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.minato.pmw.condition.ProductCondition;
import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.mapper.ProductMapper;
import jp.co.minato.pmw.mapper.entity.ProductEntity;
import jp.co.minato.pmw.service.ProductService;
import jp.co.minato.pmw.utility.Utility;

/**
 * 商品情報サービス
 *
 */
@Service
public class ProductServiceImpl extends AbstractService implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	public void issue(ProductDto dto) {
		ProductEntity entity = copyProductDtoToProductCondition(dto);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		entity.setDeleted(Boolean.FALSE);
		entity.setCreateTimestamp(now);
		entity.setUpdateTimestamp(now);
		entity.setVersionNo(0);

		productMapper.insert(entity);
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

	private ProductEntity copyProductDtoToProductEntityForDelete(ProductDto dto, ProductEntity entity) {
		entity.setDeleted(Boolean.TRUE);
		entity.setUpdateUserId(dto.getUpdateUserId());
		entity.setUpdateTimestamp(Utility.getCurrentTimestamp());
		entity.setVersionNo(entity.getVersionNo() + 1);
		return entity;
	}

	public void update(ProductEntity entity) {
		productMapper.update(entity);
	}

	public ProductEntity copyProductDtoToProductEntityForUpdate(ProductDto dto, ProductEntity entity) {
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

//
//	public ProductEntity copyProductDtoToProductEntityForDelete(ProductDto dto, ProductEntity entity) {
//		entity.setDeleted(Boolean.TRUE);
//		entity.setUpdateUserId(dto.getUpdateUserId());
//		entity.setUpdateTimestamp(Utility.getCurrentTimestamp());
//		entity.setVersionNo(entity.getVersionNo() + 1);
//		return entity;
//	}
//
	private ProductCondition copyProductDtoToProductCondition(ProductDto dto) {
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
		ProductCondition condition = copyProductDtoToProductCondition(dto);
		// 商品名であいまい検索文字列に変換
		condition.setName(escapeForFuzzySearch(condition.getName()));
		return productMapper.find(condition);
	}
}
