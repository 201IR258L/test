package jp.co.minato.pmw.constant;

public enum MessageEnum {
	// @formatter:off
	  MESSAGE_REQUIRED("{0}を入力してください。")
	, MESSAGE_LENGTH("{0}は{1}文字で入力してください。")
	, MESSAGE_MAX_LENGTH("{0}は{1}文字以内で入力してください。")
	, MESSAGE_ALPHANUMERIC("{0}は英数字で入力してください。")
	, MESSAGE_EXISTS("使用できない{0}です。")
	, MESSAGE_NOT_EXISTS("存在しない{0}です。")
	, MESSAGE_NUMERIC("{0}は数字を入力してください。")
	, MESSAGE_NUMERIC_MAX_LENGTH("{0}は{1}桁以下の数字を入力してください。")
	, MESSAGE_RANGE("{0}は{1}以上{2}以下で入力してください。")
	, MESSAGE_DATE("{0}はyyyy/MM/dd形式で入力してください。入力例：2020/01/01")
	, MESSAGE_DATA_UPDATED("他のユーザによって更新されました。商品一覧画面から再度やりなおしてください。")
	, MESSAGE_NOT_EXISTS_OR_DELETED("存在しない、または、削除された{0}です。")
	;
	// @formatter:on
	/** メッセージ */
	private String message;

	private MessageEnum(String message) {
		this.message = message;
	}

	/**
	 * メッセージテンプレートにパラメータを埋め込み、メッセージ文字列を作成します。
	 * @param parameters パラメータ
	 * @return メッセージ文字列
	 */
	public String getMessage(Object... parameters) {
		String message = this.message;
		int index = 0;
		for (Object parameter : parameters) {
			if (parameter == null) {
				continue;
			}
			message = message.replace("{" + (index++) + "}", parameter.toString());
		}
		return message;
	}

}
