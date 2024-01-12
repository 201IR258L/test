public class ProductManagement_Java_0077_B {

	public static void main(String[] args) {


//		Scanner scanner = new Scanner(System.in);
		CommonInput ci = new CommonInput();
		final String PROC_EXIT = "0"; 
		
		String inputText = "";

		DataIO dataIO = new DataIO();

		while(inputText != PROC_EXIT) {
		// メニュー表示
			System.out.println("[メニュー] 1:検索 2:登録 3:変更 4:削除 0:終了  >");
			
			// メニュー番号入力			
			try {
				inputText = ci.input();
			} catch (InstructExitException e) {
				// 例外が発生したら「0:終了」を入力したことにする
				//e.printStackTrace();
				inputText = PROC_EXIT;
			}
			// 入力値チェック
			boolean repeat = true;
			switch(inputText) {
			case "0":		//終了
				System.out.println("プログラムを終了します。");
				return;
			case "1":		//検索
				while (repeat) {
					dataIO.searchProduct();
					repeat = ci.confirmRepeat("検索");					
				}
				break;	
			case "2":		//登録
				while (repeat) {
//					System.out.println("登録処理を実行した");
					Insert insert = new Insert();
					insert.insert();
					repeat = ci.confirmRepeat("登録");					
				}
				break;	
			case "3":		//変更
				while (repeat) {
					//System.out.println("変更処理を実行した");
					Update update = new Update();
					update.update();
					repeat = ci.confirmRepeat("変更");					
				}
				break;	
			case "4":		//削除
				while (repeat) {
					Delete del = new Delete();
					del.delete();
					repeat = ci.confirmRepeat("削除");					
				}
				break;	
			default:
				System.out.println("メニュー番号が間違っています。正しい値を入力してください。");
			}
		}
//		scanner.close();
	}

}			
