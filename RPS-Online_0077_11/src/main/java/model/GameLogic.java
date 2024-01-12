package model;

public class GameLogic {
	public int execute(Hand hand) {
		Hand pcHand = new Hand();
		pcHand.setPcHand(new java.util.Random().nextInt(3));
		/*"0"=グー,"1"=チョキ,"2"=パー
		 * 勝敗パターンで分岐。*/
		if (hand.getUserHand().equals("0") && pcHand.getPcHand() == 0) {
			return 1;
		} else if (hand.getUserHand().equals("0") && pcHand.getPcHand() == 1) {
			return 2;
		} else if (hand.getUserHand().equals("0") && pcHand.getPcHand() == 2) {
			return 3;
		} else if (hand.getUserHand().equals("1") && pcHand.getPcHand() == 0) {
			return 4;
		} else if (hand.getUserHand().equals("1") && pcHand.getPcHand() == 1) {
			return 5;
		} else if (hand.getUserHand().equals("1") && pcHand.getPcHand() == 2) {
			return 6;
		} else if (hand.getUserHand().equals("2") && pcHand.getPcHand() == 0) {
			return 7;
		} else if (hand.getUserHand().equals("2") && pcHand.getPcHand() == 1) {
			return 8;
		} else if (hand.getUserHand().equals("2") && pcHand.getPcHand() == 2) {
			return 9;
		} else {
		}
		return 0;
	}
}
