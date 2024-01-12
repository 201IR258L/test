/**「exit」「\q」が入力されたときに、発生させる例外
 */
	
public class InstructExitException extends Exception{

	// コンストラクタ
	public InstructExitException(String msg) {
		super(msg);
	}
}
