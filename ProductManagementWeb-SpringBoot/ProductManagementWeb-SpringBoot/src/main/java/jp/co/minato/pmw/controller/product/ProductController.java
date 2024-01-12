package jp.co.minato.pmw.controller.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.minato.pmw.constant.MessageEnum;
import jp.co.minato.pmw.constant.ProductCategory;
import jp.co.minato.pmw.controller.AbstractController;
import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.mapper.entity.ProductEntity;
import jp.co.minato.pmw.service.ProductService;
import jp.co.minato.pmw.utility.Utility;
import jp.co.minato.pmw.utility.Validator;

@Controller
public class ProductController extends AbstractController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "/ProductListServlet" })
	public String search(ProductForm productForm, Model model) {

		// 商品を検索する
		ProductDto dto = copyProductFormToProductDtoForSearch(productForm);
		List<ProductEntity> products = productService.find(dto);

		model.addAttribute("productForm", productForm);
		model.addAttribute("products", products);

		if (products.isEmpty()) {
			model.addAttribute("message", "検索結果0件です。");
		}

		return "product/productList";
	}

	@RequestMapping(value = { "/ProductInputInitServlet" })
	public String initForIssue(ProductForm productForm, Model model) {

		productForm.setRegistrationDate(Utility.getCurrentDateString());
		model.addAttribute("productForm", productForm);
		initProductCategory(model);

		return "product/productInput";
	}

	@RequestMapping(value = "/ProductInputServlet")
	public String issue(ProductForm productForm, Model model) {

		model.addAttribute("productForm", productForm);

		initProductCategory(model);

		// 入力チェック
		List<String> messageList = validate(productForm);

		if (messageList.isEmpty() == false) {
			// エラーメッセージをリクエストスコープに保存
			model.addAttribute("messageList", messageList);
			// ユーザ登録入力画面を表示する
			initProductCategory(model);
			return "product/productInput";
		}

		ProductDto dto = copyProductFormToProductDto(productForm);
		Integer loginUserId = getLoginUserId();
		dto.setCreateUserId(loginUserId);
		dto.setUpdateUserId(loginUserId);
		productService.issue(dto);

		// 商品登録完了画面へリダイレクト
		return "redirect:/ProductInputCompleteInitServlet";
	}

	@RequestMapping(value = { "/ProductInputCompleteInitServlet" })
	public String initForIssueComplete(Model model) {
		return "product/productInputComplete";
	}

	/**
	 * 商品変更画面初期化処理
	 */
	@RequestMapping(value = { "/ProductUpdateInitServlet/{id}" })
	public String initForUpdate(@PathVariable(name = "id") String id, ProductForm productForm, Model model) {

		if (id == null) {
			model.addAttribute("message", "商品のIDを指定してください。");
			return "product/productList";
		}

		// 商品を検索する
		ProductDto dto = copyProductFormToProductDtoForUpdate(id);
		List<ProductEntity> products = productService.find(dto);
		if (products.size() == 1) {
			productForm = copyProductEntityToProductForm(products.get(0));
		} else {
			model.addAttribute("message", "商品を特定できません。");
		}
		model.addAttribute("productForm", productForm);
		initProductCategory(model);
		return "product/productUpdate";
	}

	@RequestMapping(value = "/ProductUpdateServlet")
	public String update(ProductForm productForm, Model model) {

		model.addAttribute("productForm", productForm);

		initProductCategory(model);

		// 入力チェック
		List<String> messageList = validateForUpdate(productForm);

		if (messageList.isEmpty() == false) {
			model.addAttribute("productForm", productForm);
			// エラーメッセージをリクエストスコープに保存
			model.addAttribute("messageList", messageList);
			// ユーザ登録入力画面を表示する
			initProductCategory(model);
			return "product/productUpdate";
		}

		ProductDto dto = copyProductFormToProductDto(productForm);
		Integer loginUserId = getLoginUserId();
		dto.setUpdateUserId(loginUserId);
		productService.update(dto);

		// 商品変更完了画面へリダイレクト
		return "redirect:/ProductUpdateCompleteInitServlet";
	}

	@RequestMapping(value = { "/ProductUpdateCompleteInitServlet" })
	public String initForUpdateComplete(Model model) {
		return "product/productUpdateComplete";
	}

	@RequestMapping(value = "/ProductDeleteServlet/{id}/{versionNo}")
	public String delete(ProductForm productForm, Model model) {

		model.addAttribute("productForm", productForm);

		initProductCategory(model);

		// 入力チェック
		List<String> messageList = validateExistsAndVersionNo(productForm);

		if (messageList.isEmpty() == false) {
			model.addAttribute("productForm", productForm);
			// エラーメッセージをリクエストスコープに保存
			model.addAttribute("messageList", messageList);
			// ユーザ登録入力画面を表示する
			initProductCategory(model);
			return "product/productUpdate";
		}

		ProductDto dto = copyProductFormToProductDto(productForm);
		Integer loginUserId = getLoginUserId();
		dto.setUpdateUserId(loginUserId);
		productService.delete(dto);

		// 商品削除完了画面へリダイレクト
		return "redirect:/ProductDeleteCompleteInitServlet";
	}

	@RequestMapping(value = { "/ProductDeleteCompleteInitServlet" })
	public String initForDeleteComplete(Model model) {
		return "product/productDeleteComplete";
	}

	protected List<String> validate(ProductForm form) {
		List<String> messageList = new ArrayList<>();

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
			ProductDto dto = new ProductDto();
			dto.setProductId(form.getProductId());
			// 更新の場合、更新前の商品IDは存在チェックの対象外とする
			String excludeId = form.getId();
			if (Validator.isNotEmpty(excludeId)) {
				dto.setExcludeId(Integer.parseInt(excludeId));
			}
			List<ProductEntity> list = productService.find(dto);
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

	private List<String> validateForUpdate(ProductForm form) {
		List<String> messageList = validateExistsAndVersionNo(form);
		if (!messageList.isEmpty()) {
			return messageList;
		}

		return validate(form);
	}

	private List<String> validateExistsAndVersionNo(ProductForm form) {

		List<String> messageList = new ArrayList<String>();
		int versionNo = Utility.toInteger(form.getVersionNo());
		ProductDto dto = new ProductDto();
		dto.setId(Utility.toInteger(form.getId()));
		List<ProductEntity> list = productService.find(dto);
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

	private ProductForm copyProductEntityToProductForm(ProductEntity entity) {
		ProductForm form = new ProductForm();
		form.setId(toString(entity.getId()));
		form.setProductId(entity.getProductId());
		form.setName(entity.getName());
		form.setCategoryCode(entity.getProductCategory().getCode());
		form.setProductCategory(entity.getProductCategory());
		form.setUnitPrice(toString(entity.getUnitPrice()));
		form.setPurchaseUnitPrice(toString(entity.getPurchaseUnitPrice()));
		form.setRegistrationDate(toString(entity.getRegistrationDate()));
		form.setVersionNo(toString(entity.getVersionNo()));
		return form;
	}

	protected ProductDto copyProductFormToProductDtoForUpdate(String id) {
		ProductDto dto = new ProductDto();
		dto.setId(Integer.parseInt(id));
		return dto;
	}

	private void initProductCategory(Model model) {
		model.addAttribute("productCategoryList", Arrays.asList(ProductCategory.values()));
	}

	private ProductDto copyProductFormToProductDtoForSearch(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setName(form.getName());
		return dto;
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

}
