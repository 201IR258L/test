package jp.co.minato.pmw.servlet.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.minato.pmw.dto.ProductDto;
import jp.co.minato.pmw.form.ProductForm;
import jp.co.minato.pmw.service.ProductService;

/**
 * 商品削除処理
 */
@WebServlet("/ProductDeleteServlet")
public class ProductDeleteServlet extends ProductServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm(request);

		List<String> messageList = super.validateExistsAndVersionNo(form);

		if (messageList.isEmpty() == false) {
			request.setAttribute("productForm", form);
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("messageList", messageList);
			//ユーザ登録入力画面を表示する
			initProductCategory(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productUpdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		ProductService service = new ProductService();
		ProductDto dto = copyProductFormToProductDto(form);
		Integer loginUserId = getLoginUserId(request);
		dto.setUpdateUserId(loginUserId);
		service.delete(dto);

		response.sendRedirect(request.getContextPath() + "/ProductDeleteCompleteInitServlet");
	}

	protected ProductForm getForm(HttpServletRequest request) {
		String id = request.getParameter("id");
		String versionNo = request.getParameter("versionNo");

		ProductForm form = new ProductForm();
		form.setId(id);
		form.setVersionNo(versionNo);
		return form;
	}

	protected ProductDto copyProductFormToProductDto(ProductForm form) {
		ProductDto dto = new ProductDto();
		dto.setId(Integer.parseInt(form.getId()));
		dto.setVersionNo(Integer.parseInt(form.getVersionNo()));
		return dto;
	}

}
