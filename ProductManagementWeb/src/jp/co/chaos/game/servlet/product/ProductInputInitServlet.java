package jp.co.chaos.game.servlet.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.form.ProductForm;
import jp.co.chaos.game.utility.Utility;

/**
 * 商品登録画面初期化処理
 */
@WebServlet("/ProductInputInitServlet")
public class ProductInputInitServlet extends ProductServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm();

		request.setAttribute("productForm", form);

		initProductCategory(request);

		//ログイン画面を表示する
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productInput.jsp");
		dispatcher.forward(request, response);
	}

	private ProductForm getForm() {
		ProductForm productForm = new ProductForm();
		productForm.setRegistrationDate(Utility.getCurrentDateString());
		return productForm;
	}

}
