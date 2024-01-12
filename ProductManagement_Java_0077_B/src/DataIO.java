import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DataIO {

	CommonInput ci = new CommonInput();
	CsvFileIO csvFileIO = new CsvFileIO();
	static int CSV_ITEMS = 7;

	/** 検索文字列を入力して、商品情報を検索する 
	 */
	public void searchProduct() {

		// 検索結果が商品ＩＤの昇順に並ぶようにTreeSetを使う
		TreeSet<String> treeSet = new TreeSet<String>();
		String inputText = "";

		//		while (inputText.equals(EXEC_SEARCH)) {

		// 入力ガイド
		printLine();
		println("商品情報を検索します。");
		println("検索キーワードを入力してください。");
		print("キーワード > ");
		// inputText = scanner.nextLine();
		try {
			inputText = ci.input();

			//商品情報検索
			treeSet = csvFileIO.searchList(inputText);

			//検索結果：件数表示
			System.out.println("検索結果は" + treeSet.size() + "件です。");
			printLine();

			//検索結果：明細表示
			if (treeSet.size() > 0) {
				for (String string : treeSet) {
					System.out.println(string);
				}
				printLine();

			}
		} catch (InstructExitException e) {
			//e.printStackTrace();
			; // 例外が発生したら、何もしない
		}

	}

	/** 商品ＩＤを受け取って、商品情報を取得する 
	 */
	public Product getById(String productId) {

		String record = csvFileIO.getById(productId);
		Product product = new Product();

		// 取得できなかったら戻る
		if (record.equals("")) {
			return product;
		}
		/*
		*	カンマ区切りの文字列を「split」で配列に格納するとき、
		*	最後が空文字でも取り込むために、第２引数を「-1」にする。
		*	配列の要素数が「7」でなければ、ＣＳＶ形式の警告を表示。
		*/
		
		// 取得した文字列を分割する
		String[] items = record.split(",",-1);

		// 要素数が異なる時は警告する
		if (items.length < CSV_ITEMS) {
			println("ＣＳＶ形式・要確認　項目数 = "
					+ items.length + "（不足）");
		} else if (items.length > CSV_ITEMS) {
			println("ＣＳＶ形式・要確認　項目数 = "
					+ items.length + "（過多）");
		}
		
		// 各項目をリストに格納する
		// 項目不足の時は、空文字を補う
		List<String> list = new ArrayList<String>();		
		for (int i = 0; i < CSV_ITEMS; i++) {
			if(i < items.length) {
				list.add(items[i]);
			}	else {
				list.add("");
			}
		}
		// 商品情報にリストの値をセットする
		product.setProductId(list.get(0));
		product.setProductCode(list.get(1));
		product.setProductName(list.get(2));
		product.setProductCategory(list.get(3));
		product.setUnitSalesPrice(list.get(4));
		product.setPurchaseUnitPrice(list.get(5));
		product.setRegistrationDate(list.get(6));

		return product;

	}

	/** 商品ＩＤと商品コードを受け取って、重複チェックする 
	 */
	public boolean checkDuplicateCode(String productId, String productCode) {

		boolean result = true;

		// 商品コードが等しいデータを取得
		String record = csvFileIO.getByCode(productCode);

		// 該当がなければOK
		if (record.isEmpty()) {
		// 商品ＩＤが等しければOK	
		} else if (record.equals(productId)) {
		// 商品ＩＤが異なれば使用済み
		} else {
			result = false;
		}

		return result;

	}

	/** 商品情報を追加する 
	 */
	public boolean insert(Product product) {

		boolean result = true;

		StringBuffer sb = objectToString(product);
		result = csvFileIO.insert(new String(sb));

		return result;

	}

	/** 商品情報を更新する 
	 */
	public boolean update(Product product) {

		boolean result = true;

		StringBuffer sb = objectToString(product);
		result = csvFileIO.update(new String(sb));

		return result;

	}

	/** 商品情報を削除する 
	 */
	public boolean delete(String record) {

		boolean result = true;

		result = csvFileIO.delete(record);

		return result;

	}

	// インスタンス変数をカンマ区切りの文字列に変換
	private StringBuffer objectToString(Product product) {

		StringBuffer sb = new StringBuffer();

		sb.append(product.getProductId())
				.append(",")
				.append(product.getProductCode())
				.append(",")
				.append(product.getProductName())
				.append(",")
				.append(product.getProductCategory())
				.append(",")
				.append(product.getUnitSalesPrice())
				.append(",")
				.append(product.getPurchaseUnitPrice())
				.append(",")
				.append(product.getRegistrationDate());

		return sb;

	}

	// 商品情報表示
	public void displayProduct(Product product) {

		printLine();
		println("商品ID = " + product.getProductId());
		println("商品コード = " + product.getProductCode());
		println("商品名 = " + product.getProductName());
		println("商品分類 = " + product.getProductCategory());
		println("販売単価 = " + product.getUnitSalesPrice());
		println("仕入単価 = " + product.getPurchaseUnitPrice());
		println("登録日 = " + product.getRegistrationDate());
		printLine();

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
