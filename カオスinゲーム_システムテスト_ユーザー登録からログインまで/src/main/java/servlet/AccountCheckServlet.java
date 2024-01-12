//ログイン者の権限次第で異なる画面に遷移させるコントローラ
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.AccountEntity;

@WebServlet("/AccountCheckServlet")
public class AccountCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    public AccountCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        // セッションからログイン情報を取得
        HttpSession session = request.getSession();
        AccountEntity authorityNumber = (AccountEntity) session.getAttribute("account");

        // ロールでフォワード先を振り分ける
        if(authorityNumber.getAuthorityNumber().equals("1")) {
            RequestDispatcher rd = request.getRequestDispatcher("/AdminPageTopServlet");
            rd.forward(request, response);}
        else if(authorityNumber.getAuthorityNumber().equals("2")) {
            RequestDispatcher rd = request.getRequestDispatcher("/UserAuthorityPageTopServlet");
            rd.forward(request, response);
        } else {}
        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
        rd.forward(request, response);
        }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   doGet(request, response);
    }
}