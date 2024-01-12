package jp.co.chaos.game.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.chaos.game.dto.ProductDto;
import jp.co.chaos.game.form.ProductForm;

/**
 * 商品登録処理
 */
@WebServlet("/ProductInputServlet")
public class ProductInputServlet extends ProductServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm(request);
		request.setAttribute("productForm", form);
		List<String> messageList = validate(form);

		if (messageList.isEmpty() == false) {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("messageList", messageList);
			// ユーザ登録入力画面を表示する
			initProductCategory(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productInput.jsp");
			dispatcher.forward(request, response);
			return;
		}

		ProductDto dto = copyProductFormToProductDto(form);
		Integer loginUserId = getLoginUserId(request);
		dto.setCreateUserId(loginUserId);
		dto.setUpdateUserId(loginUserId);
		super.service.issue(dto);

		// 商品登録完了画面へリダイレクト
		response.sendRedirect(request.getContextPath() + "/ProductInputCompleteInitServlet");
	}
}
