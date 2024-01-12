package jp.co.minato.pmw.service;

import java.util.List;

import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.mapper.entity.ProductEntity;

/**
 * 商品情報サービス
 *
 */
public interface ProductService {

	public void issue(ProductDto dto);

	public void update(ProductDto dto);

	public void delete(ProductDto dto);

	public List<ProductEntity> find(ProductDto dto);
}
