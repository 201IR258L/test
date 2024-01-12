//ログイン情報が確認でき、ロール番号で2が自動選択され、ログイン画面に遷移する。
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
@WebServlet("/LoginSuccessfulServlet")
public class LoginSuccessfulServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginSuccessful.jsp");
    dispatcher.forward(request, response);
  }
}