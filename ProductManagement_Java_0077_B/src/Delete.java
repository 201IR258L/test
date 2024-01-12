
public class Delete {

	public void delete() {
		
		CommonInput ci = new CommonInput();
		DataIO dataIO = new DataIO();
		Product product = new Product();
		String inputText = "";

		printLine();
		println("商品情報を削除します。");
		println("削除する商品IDを入力してください。");
		
		// 商品ID入力処理
		while (true) {
			print("商品ID >");
			
			try {
				inputText = ci.input();
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return;		// 現在の処理を終了する
			}
			// 商品情報取得
			product = dataIO.getById(inputText);
			if (product.getProductId() != null) {
				break;
			} else {
				println("存在しない商品IDです。商品IDを確認してください。");					
			}
		}
		// 商品情報表示
		dataIO.displayProduct(product);
		
		// 確認でYのとき、削除
		if (ci.confirmYN("削除")) {
			// 削除処理
			if (dataIO.delete(inputText)) {
				println("商品情報を削除しました。");
				// 商品情報表示
				dataIO.displayProduct(product);				
			}
		}
	
	}
	
	public void printLine() {
		System.out.println("------------------------------------");
	}
	public void println(String str) {
		System.out.println(str);
	}
	public void print(String str) {
		System.out.print(str);
	}

}
