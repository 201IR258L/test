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
 * 商品更新処理
 */
@WebServlet("/ProductUpdateServlet")
public class ProductUpdateServlet extends ProductServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProductForm form = getForm(request);

		List<String> messageList = validate(form);

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
		service.update(dto);

		//ユーザ登録完了画面へリダイレクト
		response.sendRedirect(request.getContextPath() + "/ProductUpdateCompleteInitServlet");
	}

	protected ProductDto getConditionForValidateProductId(ProductForm form) {

		ProductDto dto = new ProductDto();
		dto.setProductId(form.getProductId());
		dto.setExcludeId(Integer.parseInt(form.getId()));
		return dto;
	}

	protected List<String> validate(ProductForm form) {
		List<String> messageList = super.validateExistsAndVersionNo(form);
		if (!messageList.isEmpty()) {
			return messageList;
		}

		return super.validate(form);
	}

}
