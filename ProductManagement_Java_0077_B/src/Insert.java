//2023年10月28～30日テスト済み。チームB中島作成分
import java.io.UnsupportedEncodingException;

public class Insert {//登録機能クラス新規作成開始。
	public void insert() {//登録機能メソッド開始。
		// 登録機能
		/* ①項目ごとにwhile文内のif文条件分岐で入力チェック。通過した
		 * 　ものは、別の変数に値を移し替えて、productクラスフィールド
		 * 　に値をセット。breakでwhile文から抜け、次の項目へ。
		 * ②値が正しく入力されていない場合はエラーメッセージを表示し、
		 * 　continueでwhile文を繰り返す。
		 * ③すべて登録日まで、breakで通過すると、登録確認が始まる。
		 * ④登録確認後、値がCSVに渡され、画面に登録一覧が表示される。
		 */

		//メソッド全体で使用するクラスファイルを新規作成。
		PrintFunction printF = new PrintFunction();//画面出力機能
		CommonInput ci = new CommonInput();//共通入力チェック機能
		DataIO dataIO = new DataIO();//データ関連機能
		Product product = new Product();//商品情報の金型

		String inputText = "";//入力チェックした値を入れる変数

		//チェックを通過した値を入れる変数を宣言し、値を初期化。
		String passed_Id = null;//商品ID
		String passed_Code =null;//商品コード
		String passed_Name = "";//商品名
		String passed_Category = "";//商品分類
		String passed_UnitSalesPrice = "";//販売単価
		String passed_PurchaseUnitPrice = "'";//仕入単価
		String passed_RegistrationDate = "";//登録日

		//入力ガイド表示
		printF.printLine();//点線を表示。
		printF.println("商品情報を登録します。");
		printF.println("商品情報を入力してください。");
		printF.printLine();//点線を表示。    

		// コマンドライン
		//「------------------------------------」
		//「商品情報を登録します。」
		//「商品情報を入力してください。」
		//「------------------------------------」

		//==商品ID入力チェック項目チェック開始==
		while (true) {
			printF.print("商品ID > ");//入力を行う。			  
			//「商品ID > A000000001」
			try {
				inputText = ci.input();

				//IDと一致する情報がある場合取得。
				product = dataIO.getById(inputText);

				//inputText==>A000000001
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}

			//入力チェック項目No.1
			//半角英数字、半角アンダースコア、半角ハイフン以外が入力された。
			if (!inputText.matches("[0-9a-zA-Z\\-\\_]*")) {
				//エラーメッセージ表示。
				printF.println("商品IDは半角英数字・半角アンダースコア・"
						+ "半角ハイフンで入力してください。");
				//再入力。
				continue;
			} else {
				try {
					//商品IDが１０桁ではなかった。
					if (inputText.getBytes("Shift_JIS").length != 10) {
						//エラーメッセージ表示。
						printF.println("商品IDは１０桁で入力してください。");
						//再入力。
						continue;
					} else if (product.getProductId() != null) {
						//==>エラーメッセージ表示。
						printF.println("この商品IDはすでに使用されています。"
								+ "新しい商品IDを設定してください。");
						//再入力。
						continue;
					} else {//商品ID入力チェック項目通過。
						//入力値を代入。inputText==>A000000001
						passed_Id = inputText;
						//フィールドにセット。passed_Id==>A000000001
						product.setProductId(passed_Id);
						//通過。
						break;
					}
					//getBytesメソッド例外
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}
		}

		//商品ID入力チェック項目通過。
		//現在の変数の状態
		//inputText==>A000000001
		//passed_Id==>A000000001

		//==商品コード入力チェック項目チェック開始==
		while (true) {
			printF.print("商品コード > ");
			//入力を行う。「商品コード > 4912345123459」
			try {
				inputText = ci.input();
				//inputText==>4912345123459
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			/* 入力チェック項目No.4
			 * 商品コードが半角数字であること。 */
			if (!inputText.matches("[0-9]*")) {///
				//=>半角数字以外の文字が入力された。
				//エラーメッセージ表示。
				printF.println("商品コードは半角数字で入力してください。");
				//再入力。
				continue;
			} else
				/* 入力チェック項目No.5
				 * 商品コードが13桁であること。 */
				try {
					if (inputText.getBytes("Shift_JIS").length != 13) {
						//=>13桁ではなかった。
						//エラーメッセージ表示。
						printF.println("商品コードは13桁で入力してください。");
						//再入力
						continue;
					} //入力チェック項目No.6
					//商品コードが重複していた。
					else if (!dataIO.checkDuplicateCode(passed_Id, inputText)) {
						//エラーメッセージ表示。
						printF.println("この商品コードはすでに使用されています。"
								+ "新しい商品コードを設定してください。");
						//再入力。
						continue;
					} else { //商品コード入力チェック項目通過。
						//入力値を代入。inputText==>4912345123459
						passed_Code = inputText;
						//フィールドにセット。passed_Code==>4912345123459
						product.setProductCode(passed_Code);
						//通過。
						break;
					}
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
		}

		//商品コード入力チェック項目通過。
		//現在の変数の中身。
		//inputText==>4912345123459
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459

		//==商品名入力チェック項目チェック開始==	
		while (true) {
			printF.print("商品名 > ");
			//入力を行う。 「商品名 > Tシャツ」
			try {
				inputText = ci.input();
				//inputText==>Tシャツ
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//「null」を入力した場合は、現在の値を削除する。
			if (inputText.equals("null")){
				passed_Name = "";
				product.setProductName(passed_Name);
				//通過。
				break;
			} else {
				/* 入力チェック項目No.7
				 * 商品名が入力されていること
				 * １文字以上であること。*/
				try {//何も入力されなかった。
					if (inputText.getBytes("Shift_JIS").length == 0) {
						//0文字。0バイト。
						//エラーメッセージ表示。
						printF.println("商品名を入力してください。");
						//再入力。
						continue;
						/* 入力チェック項目No.8
						 * 商品名が１００バイト以下であること。
						 */
					} else if (inputText.getBytes("Shift_JIS").length > 100) {
						//100バイトを超えた。
						//エラーメッセージ表示。
						printF.println("商品名は１００バイト（全角５０文字)以下"
								+ "で入力してください。");
						//再入力
						continue;
					} else {
						//商品名入力チェック項目通過。
						//入力値を代入。inputText==>Tシャツ
						passed_Name = inputText;//==>Tシャツ
						//フィールドにセット。passed_Name
						product.setProductName(passed_Name);
						//通過。				 
						break;
					}
				} //getBytes例外。正常に処理されなかった。
				catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}

		}
		//商品名入力チェック項目通過。
		//現在の変数の中身。
		//inputText==>Tシャツ
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459
		//passed_Name==>Tシャツ

		//==商品分類入力チェック項目チェック開始==	

		while (true) {
			printF.print("商品分類 > ");
			//入力を行う。　「商品分類 > 衣服」
			try {
				inputText = ci.input();
				//inputText==>衣服
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//「null」を入力した場合は、現在の値を削除する。
			if (inputText.equals("null")) {
				passed_Category = "";
				product.setProductCategory(passed_Category);
				//通過。
				break;
			} else {
				/* 入力チェック項目No.9
				 * 商品分類が入力されていること。
				 * １文字以上であること。*/
				try {//何も入力されなかった。
					if (inputText.getBytes("Shift_JIS").length == 0) {
						//0文字。0バイト。
						//エラーメッセージ表示。
						System.out.println("商品分類を入力してください。");
						//再入力。
						continue;
						/* 入力チェック項目No.10
						 * 商品分類が１００バイト以下であること。
						 */
					} else if (inputText.getBytes("Shift_JIS").length > 100) {
						//100バイトを超えた。
						//エラーメッセージ表示。
						System.out.println("商品分類は１００バイト（全角５０文字)"
								+ "以下で入力してください。");
						//再入力
						continue;
					} else {
						//商品分類入力チェック項目通過。
						//入力値を代入。inputText==>衣服。
						passed_Category = inputText;
						//フィールにセット。passed_Category ==>衣服
						product.setProductCategory(passed_Category);
						//通過。						
						break;
					}
					//getBytes例外。正常に処理されなかった。
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}
		}
		//商品分類入力チェック項目通過。
		//現在の変数の中身。
		//inputText==>衣服
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459
		//passed_Name==>Tシャツ				
		//passed_Category==>衣服

		//==販売単価入力チェック項目チェック開始==	
		while (true) {
			printF.print("販売単価 > ");
			//入力を行う。「販売単価 > 10000」」=>空文字も入力可。
			try {
				inputText = ci.input();
				//inputText==>10000
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//未入力の項目は変更されません。
			if (inputText.isEmpty()) {//
				//通過。
				break;
			}
			//「null」を入力した場合は、現在の値を削除する。
			else if(inputText.equals("null")) {
				passed_UnitSalesPrice="";
				product.setUnitSalesPrice(passed_UnitSalesPrice);
				//通過。
				break;
			}
			/* 入力チェック項目No.11
			 * 販売単価が半角数字であること。
			 */
			else if (!inputText.matches("[0-9]*")) {
				//半角数字以外の文字が入力された。
				//エラーメッセージ表示。
				printF.println("販売単価は半角数字で入力してください。");
				//再入力。
				continue;
				/* 入力チェック項目No.12
				 * 販売単価が８桁以下であること。
				 */
			} else {
				try {
					if (inputText.getBytes("Shift_JIS").length > 8) {
						//8桁、8バイトを超えた。
						//エラーメッセージ表示。	
						printF.println("販売単価は８桁以下で入力してください。");
						//再入力。
						continue;
					} else {
						//販売単価入力チェック項目通過。
						//入力値を代入。inputText==>10000
						passed_UnitSalesPrice = inputText;
						//フィールドにセット。passed_UnitSalesPrice	==>10000
						product.setUnitSalesPrice(passed_UnitSalesPrice);
						//通過。										 
						break;
					}
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}
		} //販売単価入力チェック項目通過。

		//現在の変数の中身。
		//inputText==>10000
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459
		//passed_Name==>Tシャツ				
		//passed_Category==>衣服				
		//passed_UnitSalesPrice==>10000

		//==仕入単価入力チェック項目チェック開始=======================================================	

		while (true) {
			printF.print("仕入単価 > ");//入力を行う。			　「仕入単価 > 」」=>空文字も入力可。
			try {
				inputText = ci.input();
				//inputText==>
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}

			//未入力の項目は変更されません。
			if (inputText.isEmpty()) {//
				//通過。
				break;
			}
			//「null」を入力した場合は、現在の値を削除する。
			else if(inputText.equals("null")) {
				passed_PurchaseUnitPrice="";
				product.setPurchaseUnitPrice(passed_PurchaseUnitPrice);
				//通過。
				break;
			}
			/* 入力チェック項目No.14
			 * 仕入単価が半角数字であること。
			 */
			else if (!inputText.matches("[0-9]*")) {
				//半角数字以外の文字が入力された。
				//エラーメッセージ表示。
				printF.println("仕入単価は半角数字で入力してください。");
				//再入力。
				continue;
				/* 入力チェック項目No.15
				 * 仕入単価が８桁以下であること。
				 */
			} else {
				try {
					if (inputText.getBytes("Shift_JIS").length > 8) {
						//8桁、8バイトを超えた。
						//エラーメッセージ表示。	
						printF.println("仕入単価は８桁以下で入力してください。");
						//再入力。
						continue;
						//getBytes例外。
					} else {
						//仕入単価入力チェック項目通過。
						//入力値を代入する。inputText==>"";
						passed_PurchaseUnitPrice = inputText;
						//フィールドにセット。passed_PurchaseUnitPrice==>"";	
						product.setPurchaseUnitPrice(passed_PurchaseUnitPrice);
						//通過。										 
						break;
					}
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}

		} //仕入単価入力チェック項目通過。

		//==登録日入力チェック項目チェック開始==	
		while (true) {
			printF.print("登録日 > ");
			//入力を行う。「登録日 > 20200805」
			try {
				inputText = ci.input();
				//inputText==>20200805
			} //					
			catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}

			//入力された日付が日付型かどうかを判定する。
			TestDateCheck_New dateYesTorF = new TestDateCheck_New();
			boolean result = dateYesTorF.testD_Result(inputText);

			//未入力の項目は変更されません。
			if (inputText.isEmpty()) {
				printF.printLine();//点線を表示。
				// 確認でYのとき、登録"
				if (ci.confirmYN("登録")) {
					// 登録処理
					if (dataIO.insert(product)) {
						printF.println("商品情報を登録しました。");
						// 商品情報表示
						dataIO.displayProduct(product);
						break;
					} else {
						break;
					}
				} else {
					break;
				}
				//「null」を入力した場合は、現在の値を削除する。
			} else if (inputText.equals("null")) {
				passed_RegistrationDate="";
				product.setRegistrationDate(passed_RegistrationDate);
				printF.printLine();//点線を表示。
				// 確認でYのとき、登録"
				if (ci.confirmYN("登録")) {
					// 登録処理
					if (dataIO.insert(product)) {
						printF.println("商品情報を登録しました。");
						// 商品情報表示
						dataIO.displayProduct(product);
						break;
					} else {
						break;
					}
				} else {
					break;
				}
			} else {
				try {
					if (inputText.getBytes("Shift_JIS").length != 8) {
						//8桁ではなかった。
						printF.println("登録日は８桁の日付で入力してください。");
						continue;
					} else if (result == true) {
						printF.println("登録日は日付ではありません。"
								+ "８桁の日付で入力してください。");

						continue;
					} else {
						passed_RegistrationDate = inputText;//inputText==>20200805
						product.setRegistrationDate(passed_RegistrationDate);
						printF.printLine();//点線を表示。

						// 確認でYのとき、登録"
						if (ci.confirmYN("登録")) {
							// 登録処理
							if (dataIO.insert(product)) {
								printF.println("商品情報を登録しました。");
								// 商品情報表示
								dataIO.displayProduct(product);
								break;
							} else {
								break;
							}
						} else {
							break;
						}
					}
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}
		}
	}
}
