/*2023年12月8日　作成者　第3教室チームB　テクニカルリーダー　中島　栄作
 * テキスト「スッキリわかるサーブレット&JSP」にはない、管理者用のアカウント検索のコントローラを作成。
 * 変数名は、極力テキストに合わせた。他のサイト、訓練資料をもとにメンテナンス性も高めた。
 * <<参照先>>
 * ・テキスト：437ページ、コード14-3_ACCOUNTSテーブルを担当するDAO_AccountsDAO.java
 * ・テキスト：第13章JDBCプログラムとDAOパターン
 * ・訓練資料：ProductManagementOnline_33_AccountDao.java
 * ・WEBサイト：1.Javaでログイン機構を作ってみた。
 *		    　　https://qiita.com/parapore/items/8043f424ad66aa0d450e
 * 		　　　2.JNDIを使ったデータベース接続設定のXMLファイル定義
 *　        	  https://qiita.com/zaki-lknr/items/2bd955df62d4de0528ac
 *		　　　3.Apache Tomcat9_JNDIデータソースの使い方
 * 			  https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html#MySQL_DBCP_Example
 */
package servlet;
//各種APIをインポート
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import dao.AccountSearchDao;
import entity.AccountEntity;
import form.LoginForm;
/*
 * Servlet implementation class AccountDAO2
 */
//ログイン関係ファイル2.アカウント検索のコントローラ
//loin.jspのログインフォームからログインボタンがクリックされた場合。
@WebServlet("/AccountSearchServlet")
public class AccountSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public AccountSearchServlet() {
        super();
        }
    //リクエストのみなのでdoGetはコメント化。
    /*GETリクエスト(テキスト56ページ）
     * WebサーバからWebページの情報を取得するときに使用するリクエストメソッド。
     * 今回は、ログイン情報をサーバに送るので処理は発生しない。
     */
    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Eclipseが自動生成したコメント。処理は何もない。
    	// TODO Auto-generated method stub
    
    }*/
    /*POSTリクエスト(テキスト56ページ)
     * アンケートフォームなどのユーザーが入力した情報をサーバに送りたい場合に使用する。
     * 今回は、ログイン関係ファイルlogin.jspのログインフォームから送信された、物理名loginId,
     * passをサーバに送る。
     */
    //POSTリクエストでは、doPost()が実行される。(テキスト92ページ)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
    	LoginForm loginForm = getForm(request);
    	

        // ログイン情報をDAOに渡しアカウント情報の検索を依頼。
    	AccountSearchDao accountSearchDao = new AccountSearchDao();
        //DAOにログイン情報を渡し、検索を指示。
        AccountEntity returnAccountData = accountSearchDao.findAccount(loginForm);
        //=>検索用DAOのAccountSearchDAOが、検索結果をアカウント情報に格納。
        
        //アカウントテーブルの情報が空ではなかった。
        //つまり、アカウントは存在していた。
        if(returnAccountData !=null) {
        	//■ログイン成功
            //セッションスコープにアカウント情報＆権限番号(1or2)を登録。
            HttpSession session = request.getSession();
            //属性名は、遷移先の演算の種類を考慮し、accountとした。
            /*23年12月12日変更：属性名をConstantで定数宣言し、記述。
            session.setAttribute("account", searchResult);
             授業の内容と同様にConstant.SESSION_KEY_LOGIN_USERの値、文字列
             "SESSION_KEY_LOGIN_USER"を属性名とする。*/
            session.setAttribute("account", returnAccountData);
            //成功したので、ログイン成功画面に遷移。
            //以後は、以前作成したゲームのメイン画面に遷移するなどページを進めていく。
            //授業より引用。
			//　リダイレクトでメニュー画面へ遷移する。
			//　画面再読み込によるログイン処理の再実行を防止する
			response.sendRedirect(request.getContextPath() + "/LoginSuccessfulServlet");
            return;
        //アカウント情報が存在しなかった場合。
        } else {
        	//ログイン失敗には、ログイン失敗画面に遷移。
        	response.sendRedirect(request.getContextPath() + "/LoginFailureServlet");
			return;
        }
    	       
    }
    
    //ログインフォームに詰め替えるメソッド作成。
    private LoginForm getForm(HttpServletRequest request) throws UnsupportedEncodingException {
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		///■平文の文字列からハッシュ値に変換する
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
  		
		LoginForm form = new LoginForm();
		form.setUseId(userId);
		form.setPassword(hexDigest);
		return form;
	}
   }
