package jp.co.minato.pmw.servlet.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.servlet.AbstractServlet;

/**
 * 商品登録完了画面初期化処理
 */
@WebServlet("/ProductInputCompleteInitServlet")
public class ProductInputCompleteInitServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productInputComplete.jsp");
		dispatcher.forward(request, response);
	}
}
