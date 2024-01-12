//2023年10月28～29日テスト済み。チームB中島作成分
import java.io.UnsupportedEncodingException;

public class Update {
	public void update() {
		// 登録機能
		/* ①項目ごとにwhile文内のif文条件分岐で入力チェック。通過した
		 * 　ものは、別の変数に値を移し替えて、フィールドに値をセット。
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
		String passed_PurchaseUnitPrice = "";//仕入単価
		String passed_RegistrationDate = "";//登録日

		//入力ガイド表示
		printF.printLine();//点線を表示。                         「------------------------------------」
		printF.println("商品情報を変更します。");//      		　「商品情報を変更します。」
		printF.println("変更する商品IDを入力してください。");//	　「変更する商品IDを入力してください。」

		//==商品ID入力チェック項目チェック開始==
		//入力チェック項目エラーの間は繰り返す。
		while (true) {
			printF.print("商品ID > ");
			//入力を行う。「商品ID > A000000001」
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
			//商品IDが存在していなかった。
			if (product.getProductId() == null) {
				//==>エラーメッセージ表示。
				printF.println("存在しない商品IDです。商品IDを確認してください。");
				//「存在しない商品IDです。商品IDを確認してください。」
				//再入力。
				continue;
			} else {
				//次の項目へ。
				break;
			}
		}
		//商品ID重複チェック通過。

		printF.printLine();//点線を表示。                         
		printF.println("変更する項目のみ入力してください。");
		printF.printLine();//点線を表示。

		//「------------------------------------」				
		//「変更する商品IDを入力してください。」
		//「------------------------------------」

		//現在の変数の中身。
		//inputText==>A000000001
		//passed_Id==>A000000001

		while (true) {
			printF.print("商品コード[" + product.getProductCode() + "] > ");
			//入力を行う。「商品コード[4912345123459] > 」
			try {
				inputText = ci.input();
				// inputText==>4912345123459
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//入力チェック項目No.1
			//商品コードが重複していた。
			if (!dataIO.checkDuplicateCode(passed_Id, inputText)) {
				//エラーメッセージ表示。
				printF.println("この商品コードはすでに使用されています。"
						+ "新しい商品コードを設定してください。");
				//再入力。
				continue;
			} 
			//未入力の項目は変更されません。
			else if (inputText.isEmpty()) {
				//通過。
				break;
			}
			// 入力チェック項目No.2
			// 商品コードが半角数字であること。		
			else if (!inputText.matches("[0-9]*")) {
				//=>半角数字以外の文字が入力された。
				//エラーメッセージ表示。
				printF.println("商品コードは半角数字で入力してください。");
				//再入力。
				continue;

			} else {
				/* 入力チェック項目No.3
				 * 商品コードが13桁であること。 */
				try {
					if (inputText.getBytes("Shift_JIS").length != 13) {
						//=>13桁ではなかった。
						//エラーメッセージ表示。
						printF.println("商品コードは13桁で入力してください。");
						//再入力
						continue;
					} else {
						//入力値を代入。
						passed_Code = inputText;//inputText==>4912345123459
						//フィールドに値をセット。passed_Code==>4912345123459
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
		}

		//商品コード入力チェック項目通過。

		//現在の変数の中身。
		//inputText==>4912345123459
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459

		//==商品名入力チェック項目チェック開始==	
		while (true) {
			printF.print("商品名[" + product.getProductName() + "] > ");
			//入力を行う。「商品名[Tシャツ] > Yシャツ」
			try {
				inputText = ci.input();
				//inputText==>Yシャツ

			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//未入力の項目は変更されません。
			if (inputText.isEmpty()) {
				//通過。
				break;
			}
			//「null」を入力した場合は、現在の値を削除する。
			else if (inputText.equals("null")) {
				passed_Name="";
				product.setProductName(passed_Name);
				//通過。
				break;
			} else {
				try {//入力必須。
					if (inputText.getBytes("Shift_JIS").length == 0) {
						//0文字。0バイト。
						//エラーメッセージ表示。
						printF.println("商品名を入力してください。");
						//再入力。
						continue;
						/* 入力チェック項目No.5
						 * 商品名が１００バイト以下であること。
						 */
					} else if (inputText.getBytes("Shift_JIS").length > 100) {
						//100バイトを超えた。
						//エラーメッセージ表示。
						System.out.println("商品名は１００バイト（全角５０文字)以下で入力してください。");
						//再入力
						continue;
					} else {
						//商品名入力チェック項目通過。
						//入力値を代入。
						passed_Name = inputText;//inputText==>Yシャツ
						//フィールドに値をセット。passed_Name==>Yシャツ
						product.setProductName(passed_Name);
						//通過。										 
						break;
					}
				} //getBytes例外。
				catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}

		}

		//商品名入力チェック項目通過。

		//現在の変数の中身。
		//inputText==>Yシャツ
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459
		//passed_Name==>Yシャツ

		//==商品分類入力チェック項目チェック開始==	

		while (true) {
			printF.print("商品分類[" + product.getProductCategory() + "] > ");
			//入力を行う。「商品分類[衣服] > 」<==未入力。
			try {
				inputText = ci.input();
				//inputText==>未入力。
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
			}
			//未入力の項目は変更されません。
			if (inputText.isEmpty()) {
				//通過。
				break;
				//「null」を入力した場合は、現在の値を削除する。
			}else if (inputText.equals("null")){
				passed_Category="";
				product.setProductCategory(passed_Category);
					//通過。
					break;
			} else {
				try {//入力必須。
					if (inputText.getBytes("Shift_JIS").length == 0) {
						//0文字。0バイト。
						//エラーメッセージ表示。
						System.out.println("商品分類を入力してください。");
						//再入力。
						continue;
						/* 入力チェック項目No.
						 * 商品分類が１００バイト以下であること。
						 */
					} else if (inputText.getBytes("Shift_JIS").length > 100) {
						//100バイトを超えた。
						//エラーメッセージ表示。
						printF.println("商品分類は１００バイト（全角５０文字)以下で入力してください。");
						//再入力
						continue;
					} else {
						//商品分類入力チェック項目クリア。
						//入力値を代入。inputText==>空文字。
						passed_Category = inputText;
						//フィールドに値をセット。passed_Category==>空文字。							
						product.setProductCategory(passed_Category);
						//通過。										
						break;
					}
					//getBytes例外。
				} catch (UnsupportedEncodingException e) {
					printF.println("サポートされていない文字が入力されました。");
					//再入力。
					continue;
				}
			}
		}
		//商品分類入力チェック項目通過。

		//現在の変数の中身。
		//inputText==>空文字。
		//passed_Id==>A000000001								
		//passed_Code==>4912345123459
		//passed_Name==>Yシャツ				
		//passed_Category==>空文字

		//==販売単価入力チェック項目チェック開始==
		while (true) {
			printF.print("販売単価[" + product.getUnitSalesPrice() + "] > ");
			//入力を行う。販売単価[10000] > 20000」」=>空文字も入力可。
			try {
				inputText = ci.input();
				//inputText==>20000
			} catch (InstructExitException e) {
				// 例外が発生したとき
				return; // 現在の処理を終了する。
				}
				//未入力の項目は変更されません。
				if (inputText.isEmpty()) {
					//通過。
					break;
				}
				//「null」を入力した場合は、現在の値を削除する。
				else if (inputText.equals("null")) {
					passed_UnitSalesPrice="";
					product.setUnitSalesPrice(passed_UnitSalesPrice);
					//通過。
					break;
				}
				/* 入力チェック項目No.7
				 * 販売単価が半角数字であること。
				 */
				else if (!inputText.matches("[0-9]*")) {
					//半角数字以外の文字が入力された。
					//エラーメッセージ表示。
					printF.println("販売単価は半角数字で入力してください。");
					//再入力。
					continue;
				} else {
					/* 入力チェック項目No.8
					 * 販売単価が８桁以下であること。
					 */
					try {
						if (inputText.getBytes("Shift_JIS").length > 8) {
							//8桁、8バイトを超えた。
							//エラーメッセージ表示。	
							printF.println("販売単価は８桁以下で入力してください。");
							//再入力。
							continue;
						} else {//販売単価が正しく入力された。
							//販売単価入力チェック項目クリア。
							//入力値を代入。inputText==>20000
							passed_UnitSalesPrice = inputText;//
							//passed_UnitSalesPrice==>2000
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
			//inputText==>20000
			//passed_Id==>A000000001								
			//passed_Code==>4912345123459
			//passed_Name==>Yシャツ				
			//passed_Category==>				
			//passed_UnitSalesPrice==>20000

			//==仕入単価入力チェック項目チェック開始==
			while (true) {
				printF.print("仕入単価[" + product.getPurchaseUnitPrice() + "] > ");
				//入力を行う。「仕入単価[] > 」」=>空文字も入力可。
				try {
					inputText = ci.input();
					//inputText==>空文字。
				}
				catch (InstructExitException e) {
					// 例外が発生したとき
					return; // 現在の処理を終了する。
				}
				//未入力の項目は変更されません。
				if (inputText.isEmpty()) {
					//通過。
					break;
				}
				//「null」を入力した場合は、現在の値を削除する。
				else if (inputText.equals("null")){
					passed_PurchaseUnitPrice ="";
					product.setPurchaseUnitPrice(passed_PurchaseUnitPrice);
					//通過。
					break;
				}
				
				/* 入力チェック項目No.10
				 * 仕入単価が半角数字であること。
				 */
				else if (!inputText.matches("[0-9]*")) {
					//半角数字以外の文字が入力された。
					//エラーメッセージ表示。
					printF.println("仕入単価は半角数字で入力してください。");
					//再入力。
					continue;
					/* 入力チェック項目No.11
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
						} else {
							//仕入単価入力チェック項目通過。
							//入力値を代入。inputText==>""
							passed_PurchaseUnitPrice = inputText;
							//passed_PurchaseUnitPrice==>""
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

			} 
			//仕入単価入力チェック項目通過。
	
			//==登録日入力チェック項目チェック開始==	
			while (true) {	
				printF.print("登録日[" + product.getRegistrationDate() + "] > ");
				//入力を行う。「登録日[20200805] > 」
				try {
					inputText = ci.input();
					//inputText==>
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
					if (ci.confirmYN("変更")) {
						// 変更処理
						if (dataIO.update(product)) {
							printF.println("商品情報を変更しました。");
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
					// 確認でYのとき、変更"
					if (ci.confirmYN("変更")) {
						// 変更処理
						if (dataIO.update(product)) {
							printF.println("商品情報を変更しました。");
							// 商品情報表示
							dataIO.displayProduct(product);
							break;
						} else {
							break;
						}
					} else {
						break;
					}
				} else 
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

							// 確認でYのとき、変更"
							if (ci.confirmYN("変更")) {
								// 変更処理
								if (dataIO.update(product)) {
									printF.println("商品情報を変更しました。");
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

	
