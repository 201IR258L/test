package jp.co.minato.pmw.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.entity.ProductEntity;
import jp.co.minato.pmw.form.ProductForm;
import jp.co.minato.pmw.service.ProductService;

/**
 * 商品変更画面初期化処理
 */
@WebServlet("/ProductUpdateInitServlet")
public class ProductUpdateInitServlet extends ProductServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm(request);

		if (form.getId() == null) {
			request.setAttribute("message", "商品のIDを指定してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//商品を検索する
		ProductService service = new ProductService();
		ProductDto dto = copyProductFormToProductDto(form);
		List<ProductEntity> products = service.find(dto);
		if (products.size() == 1) {
			ProductForm productForm = copyProductEntityToProductForm(products.get(0));
			request.setAttribute("productForm", productForm);
		} else {
			request.setAttribute("message", "商品を特定できません。");
		}

		initProductCategory(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productUpdate.jsp");
		dispatcher.forward(request, response);
	}

	protected ProductForm getForm(HttpServletRequest request) {
		//リクエストパラメータ取得
		String id = request.getParameter("id");
		//リクエストパラメータをFormに詰め替える
		ProductForm form = new ProductForm();
		form.setId(id);
		return form;
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

	protected ProductDto copyProductFormToProductDto(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setId(Integer.parseInt(form.getId()));
		return dto;
	}
}
