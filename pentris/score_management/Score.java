package pentris.score_management;//package ScoreTracker;

import java.io.Serializable;

public class Score implements Serializable {
	private int score;
	private String name;

	public int getScore() {
		return score;
	}
	public String getName() {
		return name;
	}
	public Score(String name, int score) {
		this.score = score;
		this.name = name;
	}
	@Override
	public String toString() {
        return name + score;
			}
}
