//管理者権限サイトに画面遷移させるためのコントローラ
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
//welcome.jspで、「オープニングへ進む」ボタンをクリックすると起動。
//WEB-INF/jsp/oprening01.jspに処理を転送させる。
@WebServlet("/AdminPageTopServlet")
public class AdminPageTopServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminPrivilegePageTop.jsp");
    dispatcher.forward(request, response);
  }
}