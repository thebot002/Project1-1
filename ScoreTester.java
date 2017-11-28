//There will be an error when compiling, just compile it again.
public class ScoreTester{
	public static void main(String[] args){
		HighScoreManager hm = new HighScoreManager();
		hm.addScore("a", 100);
		hm.addScore("b", 200);
		hm.addScore("c", 110);
		hm.addScore("d", 190);
		hm.addScore("e", 120);
		hm.addScore("f", 180);
		hm.addScore("g", 130);
		hm.addScore("h", 170);
		hm.addScore("i", 140);
		hm.addScore("j", 150);
		System.out.println(hm.toString());
	}
}
