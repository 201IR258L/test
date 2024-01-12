package jp.co.chaos.game.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.dto.ProductDto;
import jp.co.chaos.game.entity.ProductEntity;
import jp.co.chaos.game.form.ProductForm;
import jp.co.chaos.game.service.ProductService;
import jp.co.chaos.game.servlet.AbstractServlet;

/**
 * 商品一覧検索処理
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm(request);

		//商品を検索する
		ProductService service = new ProductService();
		ProductDto dto = copyProductFormToProductDto(form);
		List<ProductEntity> products = service.find(dto);

		request.setAttribute("productForm", form);
		request.setAttribute("products", products);

		if (products.isEmpty()) {
			request.setAttribute("message", "検索結果0件です。");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productList.jsp");
		dispatcher.forward(request, response);
	}

	private ProductForm getForm(HttpServletRequest request) {
		//リクエストパラメータ取得
		String name = request.getParameter("name");

		//リクエストパラメータをFormに詰め替える
		ProductForm form = new ProductForm();
		form.setName(name);
		return form;
	}

	private ProductDto copyProductFormToProductDto(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setName(form.getName());
		return dto;
	}
}
