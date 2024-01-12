package jp.co.minato.pmw.servlet.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.servlet.AbstractServlet;

/**
 * 商品削除完了画面初期化処理
 */
@WebServlet("/ProductDeleteCompleteInitServlet")
public class ProductDeleteCompleteInitServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productDeleteComplete.jsp");
		dispatcher.forward(request, response);
	}
}
