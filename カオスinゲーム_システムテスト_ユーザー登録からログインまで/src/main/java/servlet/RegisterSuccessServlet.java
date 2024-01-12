//登録成功画面に遷移するための、コントローラ。
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServletアノテーション
//外部ツールから利用が可能になる。
@WebServlet("/RegisterSuccessServlet")
public class RegisterSuccessServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerSuccess.jsp");
    dispatcher.forward(request, response);
  }
}
//RegisterSuccessServlet