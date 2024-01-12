//令和5年12月13日18時36分ユーザー登録処理成功
//残りhash化

//令和5年12月13日完成。作成者　第3教室チームB中島　栄作
package servlet;
/* ユーザー登録画面に遷移させるコントローラ
 * ユーザー登録画面_register.jspの登z録フォームに入力された、
 * ユーザーIDとパスワードとロール(画面上には存在しないhidden
 * ボタンの値Value"2")を受取り、登録処理する。
 * ※機能に、管理者として登録する機能もつけたが、ユーザーが
 * 混乱するので、DBeaverからInsert文で、admin、ロール番号
 * 1番で登録しておき、ログイン時に、結果的にユーザーと管理者
 * では、違うページに遷移する。管理者のロール番号を割り振られた
 * ユーザーのみ、管理者ページを閲覧できる。 
 */
//各種APIをインポート。
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.AccountRegisterDao;
import dao.AccountSearchDao;
import entity.AccountEntity;
import form.LoginForm;


//アノテーション「/AccountRegister」で呼ばれたとき処理開始。
//ユーザー登録画面で登録ボタンを押したときに実行。
@WebServlet("/AccountRegisterServlet")
public class AccountRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    //リクエストを処理する機能
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
        //登録フォームに入力された値を受け取る。
    	String userId = request.getParameter("user_id");
    	String name = request.getParameter("name");
        String password = request.getParameter("password");
        String authorityNumber = request.getParameter("authority_number");
          
      //■平文の文字列からハッシュ値に変換する
      		MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
      		//MD5形式のハッシュ値を計算する => 128bit（16バイト）
      		byte[] digest = messageDigest.digest(password.getBytes("UTF-8"));
      		//128bit（16バイト） => 32桁の16進数文字列に変換する
      		String hexDigest = DatatypeConverter.printHexBinary(digest);
        
            
        //権限審査クラスを作成。
        AuthorityReviewServlet authority =new AuthorityReviewServlet();
        /* 権限判定結果を格納。
         * ロール2.ユーザーで登録しようとした。：true。
         * ロール1.管理者で登録しようとした。*/
        //審査結果を代入。
        Boolean examinationOutcome =authority.review(authorityNumber);
        //ユーザー登録しようした。
        if(examinationOutcome == true) {
            // register.jspから受け取った値をビーンズにセット
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setUserId(userId);
            accountEntity.setName(name);
            accountEntity.setPassword(hexDigest);
            accountEntity.setAuthorityNumber(authorityNumber);
            //まずは、重複チェック
    	      LoginForm form = new LoginForm();
    	      form.setUseId(accountEntity.getUserId());
    	      form.setPassword(accountEntity.getPassword());
            //AccountSeatchDaoで検索、
    	      AccountSearchDao search = new AccountSearchDao();
    	      //ログインアカウントを探す。
    	      AccountEntity returnAccountData = search.findAccount(form);
    	     
    	      //アカウントデータがない、存在していない場合。
    	      if(returnAccountData != null) {        	
            // アカウントをDBに登録
			@SuppressWarnings("unused")
				AccountRegisterDao	ard = new AccountRegisterDao(accountEntity);
			
            // セッションにアカウント情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("account", accountEntity);
            //成功ページ
            System.out.println("登録成功");
          }else {
        	  RequestDispatcher rd = request.getRequestDispatcher("registerSuccess.jsp");
              rd.forward(request, response);
        	  
        	  
        	  //登録重複エラーページ。
        	  System.out.println("登録重複");
        	  
          }}else{//権限エラーページにリダイレクト
        	//成功ページにリダイレクト
        	  System.out.println("権限重複");
        	  response.sendRedirect("${pageContext.request.contextPath}\n"
        	  		+ "/PermissionErrorServlet");}}}
      
       
 