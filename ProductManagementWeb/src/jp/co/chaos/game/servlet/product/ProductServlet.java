package jp.co.chaos.game.servlet.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.chaos.game.constant.MessageEnum;
import jp.co.chaos.game.constant.ProductCategory;
import jp.co.chaos.game.dto.ProductDto;
import jp.co.chaos.game.entity.ProductEntity;
import jp.co.chaos.game.form.ProductForm;
import jp.co.chaos.game.service.ProductService;
import jp.co.chaos.game.servlet.AbstractServlet;
import jp.co.chaos.game.utility.Utility;
import jp.co.chaos.game.utility.Validator;

/**
 * 商品処理
 */
public class ProductServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected ProductService service = new ProductService();

	protected List<String> validate(ProductForm form) {
		List<String> messageList = new ArrayList<String>();

		// 商品ID入力チェック
		String productId = form.getProductId();
		String productIdLabel = "商品ID";
		if (!Validator.required(productId)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(productIdLabel));
		} else if (!Validator.length(productId, 4)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_LENGTH.getMessage(productIdLabel, 4));
		} else if (!Validator.alphanumeric(productId)) {
			// 英数字チェック
			messageList.add(MessageEnum.MESSAGE_ALPHANUMERIC.getMessage(productIdLabel));
		} else {
			// 存在チェック
			ProductDto dto = getConditionForValidateProductId(form);
			List<ProductEntity> list = this.service.find(dto);
			if (!list.isEmpty()) {
				messageList.add(MessageEnum.MESSAGE_EXISTS.getMessage(productIdLabel));
			}
		}

		// 商品名入力チェック
		String name = form.getName();
		String nameLabel = "商品名";
		if (!Validator.required(name)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(nameLabel));
		} else if (!Validator.maxLength(name, 100)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_LENGTH.getMessage(productIdLabel, 100));
		}

		// 商品分類入力チェック
		String categoryCode = form.getCategoryCode();
		String categoryCodeLabel = "商品分類";
		if (!Validator.required(categoryCode)) {
			// 必須チェック
			messageList.add(MessageEnum.MESSAGE_REQUIRED.getMessage(categoryCodeLabel));
		} else {
			// 存在チェック
			ProductCategory productCategory = ProductCategory.codeOf(categoryCode);
			if (productCategory == null) {
				messageList.add(MessageEnum.MESSAGE_NOT_EXISTS.getMessage(categoryCodeLabel));
			}
		}

		// 販売単価入力チェック
		String unitPrice = form.getUnitPrice();
		String unitPriceLabel = "販売単価";
		if (Validator.isEmpty(unitPrice)) {
			// 未入力の場合入力チェックしない
		} else if (!Validator.numeric(unitPrice)) {
			// 数値チェック
			messageList.add(MessageEnum.MESSAGE_NUMERIC.getMessage(unitPriceLabel));
		} else if (!Validator.range(unitPrice, 0, 99999999)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_RANGE.getMessage(unitPriceLabel, 0, 99999999));
		}

		// 仕入単価入力チェック
		String purchaseUnitPrice = form.getPurchaseUnitPrice();
		String purchaseUnitPriceLabel = "仕入単価";
		if (Validator.isEmpty(purchaseUnitPrice)) {
			// 未入力の場合入力チェックしない
		} else if (!Validator.numeric(purchaseUnitPrice)) {
			// 数値チェック
			messageList.add(MessageEnum.MESSAGE_NUMERIC.getMessage(purchaseUnitPriceLabel));
		} else if (!Validator.range(purchaseUnitPrice, 0, 99999999)) {
			// 文字数チェック
			messageList.add(MessageEnum.MESSAGE_RANGE.getMessage(purchaseUnitPriceLabel, 0, 99999999));
		}

		// 登録日入力チェック
		String registrationDate = form.getRegistrationDate();
		String registrationDateLabel = "登録日";
		if (Validator.isEmpty(registrationDate)) {
			// 未入力の場合入力チェックしない
		} else if (!Validator.date(registrationDate)) {
			// 日付チェック
			messageList.add(MessageEnum.MESSAGE_DATE.getMessage(registrationDateLabel));
		}

		return messageList;
	}

	protected ProductDto getConditionForValidateProductId(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setProductId(form.getProductId());
		return dto;
	}

	protected ProductForm getForm(HttpServletRequest request) {
		// リクエストパラメータ取得
		String id = request.getParameter("id");
		String productId = request.getParameter("productId");
		String name = request.getParameter("name");
		String categoryCode = request.getParameter("categoryCode");
		String unitPrice = request.getParameter("unitPrice");
		String purchaseUnitPrice = request.getParameter("purchaseUnitPrice");
		String registrationDate = request.getParameter("registrationDate");
		String versionNo = request.getParameter("versionNo");

		// リクエストパラメータをFormに詰め替える
		ProductForm form = new ProductForm();
		form.setId(id);
		form.setProductId(productId);
		form.setName(name);
		form.setCategoryCode(categoryCode);
		form.setUnitPrice(unitPrice);
		form.setPurchaseUnitPrice(purchaseUnitPrice);
		form.setRegistrationDate(registrationDate);
		form.setVersionNo(versionNo);
		return form;
	}

	protected ProductDto copyProductFormToProductDto(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setId(Utility.toInteger(form.getId()));
		dto.setProductId(form.getProductId());
		dto.setName(form.getName());

		dto.setProductCategory(ProductCategory.codeOf(form.getCategoryCode()));
		if (Utility.isNotEmpty(form.getUnitPrice())) {
			dto.setUnitPrice(Integer.parseInt(form.getUnitPrice()));
		}
		if (Utility.isNotEmpty(form.getPurchaseUnitPrice())) {
			dto.setPurchaseUnitPrice(Integer.parseInt(form.getPurchaseUnitPrice()));
		}
		if (Utility.isNotEmpty(form.getRegistrationDate())) {
			dto.setRegistrationDate(Utility.parseToSqlDate(form.getRegistrationDate()));
		}
		dto.setVersionNo(Utility.toInteger(form.getVersionNo()));
		return dto;
	}

	protected List<String> validateExistsAndVersionNo(ProductForm form) {

		List<String> messageList = new ArrayList<String>();
		int versionNo = Utility.toInteger(form.getVersionNo());
		ProductDto dto = new ProductDto();
		dto.setId(Utility.toInteger(form.getId()));
		List<ProductEntity> list = service.find(dto);
		if (list.isEmpty()) {
			messageList.add(MessageEnum.MESSAGE_NOT_EXISTS_OR_DELETED.getMessage("商品"));
			return messageList;
		}

		int currentVersionNo = list.get(0).getVersionNo();
		if (versionNo != currentVersionNo) {
			messageList.add(MessageEnum.MESSAGE_DATA_UPDATED.getMessage());
			return messageList;
		}
		return messageList;
	}

	protected void initProductCategory(HttpServletRequest request) {
		request.setAttribute("productCategoryList", Arrays.asList(ProductCategory.values()));
	}
}
