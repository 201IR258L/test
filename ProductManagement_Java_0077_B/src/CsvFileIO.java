import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

class CsvFileIO {

	// 商品ＩＤ位置情報
	private final int ID_LENGTH = 10;
	private final int ID_START = 0;
	private final int ID_END = ID_LENGTH + ID_START;

	// 商品コード位置情報
	private final int CODE_LENGTH = 13;
	private final int CODE_START = 11;
	private final int CODE_END = CODE_LENGTH + CODE_START;

	//ＣＳＶファイル名
	private static String csvName = "\\products_0077_B.csv";
	Path AbsolutePath = null;
	File csv = null;

	// ＣＳＶファイル出力モード
	boolean APPEND_MODE = true;
	boolean WRITE_MODE = false;
	
	
	/** コンストラクタ
	 * 
	 */
	CsvFileIO() {
		Path path1 = Paths.get("");
		AbsolutePath = path1.toAbsolutePath();
		csv = new File(AbsolutePath.toString() + csvName);

		/** ファイルが無いときは,新規に作成する
		 * 
		 */
		try {
			csv.createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/** 検索文字列を受け取って、商品情報を検索する 
	 */
	TreeSet<String> searchList(String text) {

		TreeSet<String> treeSet = new TreeSet<String>();
		treeSet.clear();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(csv), "Shift-JIS"))) {

			//１行読み込み
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (text.equals("")) {
					treeSet.add(line);
				} else if (line.contains(text)) {
					treeSet.add(line);
				}
			}

		} catch (IOException e) {
			//catchする例外の親クラスは下に定義する
			System.out.println(e.getMessage());
		}
		return treeSet;
	}

	/** 商品ＩＤを受け取って、商品情報を検索する 
	 */
	String getById(String productId) {

		String result = "";

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(csv), "Shift-JIS"))) {

			//１行読み込み
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {

				// 文字数不足の例外対策
				String judgeLine = line + String.join("", Collections.nCopies(ID_END, " "));
				// 該当するデータが見つかったら、１行分を結果にセットする
				if (judgeLine.substring(ID_START, ID_END).equals(productId)) {
					result = line;
					break;
				}
			}

		} catch (IOException e) {
			//catchする例外の親クラスは下に定義する
			System.out.println(e.getMessage());
		}
		return result;

	}

	/** 商品コードを受け取って、商品情報を取得する
	 *  取得した商品ＩＤを返す
	 */
	String getByCode(String productCode) {

		String result = "";

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(csv), "Shift-JIS"))) {

			//１行読み込み
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {

				// 文字数不足の例外対策
				String judgeLine = line + String.join("", Collections.nCopies(CODE_END, " "));
				// 該当するデータが見つかったら、商品ＩＤを結果にセットする
				if (judgeLine.substring(CODE_START, CODE_END).equals(productCode)) {
					result = line.substring(ID_START, ID_END);
				break;
				}
			}

		} catch (IOException e) {
			//catchする例外の親クラスは下に定義する
			System.out.println(e.getMessage());
		}
		return result;

	}

	/** 商品情報を追加する 
	 */
	boolean insert(String record) {

		//追加する商品情報をリストに入れる
		ArrayList<String> list = new ArrayList<String>();

		list.add(record);

		//リストからＣＳＶに書き込む
		try {
			output(list,APPEND_MODE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

	/** 商品情報を更新する 
	 */
	boolean update(String record) {

		//リストに読み込みながら置き換える
		ArrayList<String> list = new ArrayList<String>();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(csv), "Shift-JIS"))) {

			//１行読み込み
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {

				// 文字数不足の例外対策
				String judgeLine = line + String.join("", Collections.nCopies(ID_END, " "));
				// 該当は置き換え
				if (judgeLine.substring(ID_START, ID_END).equals(record.substring(ID_START, ID_END))) {
					list.add(record);
				} else {
					list.add(line);
				}
			}

		} catch (IOException e) {
			//catchする例外の親クラスは下に定義する
			System.out.println(e.getMessage());
			return false;
		}

		//リストからＣＳＶに書き込む
		try {
			output(list,WRITE_MODE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

	/** 商品情報を削除する 
	 */
	boolean delete(String record) {

		//リストに読み込みながら同じＩＤを読み飛ばす
		ArrayList<String> list = new ArrayList<String>();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(csv), "Shift-JIS"))) {

			//１行読み込み
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {

				// 文字数不足の例外対策
				String judgeLine = line + String.join("", Collections.nCopies(ID_END, " "));
				// 該当は読み飛ばし
				if (judgeLine.substring(ID_START, ID_END).equals(record.substring(ID_START, ID_END))) {
					continue;
				} else {
					list.add(line);
				}
			}

		} catch (IOException e) {
			//catchする例外の親クラスは下に定義する
			System.out.println(e.getMessage());
			return false;
		}

		//リストからＣＳＶに書き込む
		try {
			output(list,WRITE_MODE);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}

	/** リストの内容をＣＳＶファイルに書き込む
	 */
	boolean output(ArrayList<String> list, boolean append)  throws IOException {

		// 念のためバックアップをとる
		long curTime = System.currentTimeMillis();
		File backup = new File(csv.toString() + curTime);
		Path p1 = Paths.get(csv.toString());
		Path p2 = Paths.get(backup.toString());
		Files.copy(p1, p2);
		
		//リストからＣＳＶに書き込む
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(csv,append),
						"Shift-JIS"))) {

			for (String line : list) {
				bufferedWriter.write(line + "\n");
			}
			// ファイルを閉じる
			bufferedWriter.close();
			// バックアップを削除する
			backup.delete();

		} catch (IOException e) {
			return false;
		}
		return true;

	}

}
