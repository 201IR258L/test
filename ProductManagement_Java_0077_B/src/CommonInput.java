import java.util.Scanner;

public class CommonInput {

	final String NG_CHARACTOR = ".*,.*";
	
	public String input() throws InstructExitException {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		String inputText = "";

		while (true) {
			inputText = scanner.nextLine();
			if (inputText.matches(NG_CHARACTOR)) {
				System.out.println("カンマは使用できません");
			} else if (inputText.equalsIgnoreCase("exit")){
				throw new InstructExitException("「exit」が入力されました");
			} else if (inputText.equalsIgnoreCase("\\q")) {
				throw new InstructExitException("「\\q」が入力されました");
			} else {					
				return  inputText;
			}						
		}
	}

	//継続確認
	public boolean confirmRepeat(String proc) {

		while (true) {
			System.out.println("続けて商品を" + proc + "しますか？");
			System.out.print("1:続けて" + proc + "する 2:メニューへ戻る >");
//			inputText = scanner.nextLine();
			String inputText = "";
			try {
				inputText = this.input();
			} catch (InstructExitException e) {
				// 例外が発生したら「2:メニューへ戻る」
				// を入力したことにする
				//e.printStackTrace();
				inputText = "2";
			}

			if (inputText.equals("1")) {
				return true;
			} else if (inputText.equals("2")) {
				return false;
			} else {
				continue;
			}

		}
	}

	//継続確認
	public boolean confirmYN(String proc) {

		while (true) {
			System.out.print("商品情報を" + proc + "しますか？ Y/N >");
//			inputText = scanner.nextLine();
			String inputText = "";
			try {
				inputText = this.input();
			} catch (InstructExitException e) {
				// 例外が発生したら「N」
				// を入力したことにする
				//e.printStackTrace();
				inputText = "N";
			}

			if (inputText.equalsIgnoreCase("Y")) {
				return true;
			} else if (inputText.equalsIgnoreCase("N")) {
				return false;
			} else {
				continue;
			}

		}
	}

}
