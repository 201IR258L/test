package model;

import java.io.Serializable;

public class Hand implements Serializable {
	private String userHand; // 自分の手
	private int pcHand; // PCの手
	private int gameResults;//ゲーム結果

	public Hand() {
	}

	public Hand(String userHand) {
		this.userHand = userHand;
	}

	public String getUserHand() {
		return userHand;
	}

	public void setUserHand(String userHand) {
		this.userHand = userHand;
	}

	public int getPcHand() {
		return pcHand;
	}

	public void setPcHand(int pcHand) {
		this.pcHand = pcHand;
	}

	public int getGameResults() {
		return gameResults;
	}

	public void setGameResults(int gameResults) {
		this.gameResults = gameResults;
	}
}