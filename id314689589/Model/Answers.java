package Model;

import java.io.Serializable;

public class Answers implements Serializable {
	private String answer;
	private boolean correct;

	public Answers(String answer, boolean correct) {
		this.answer = answer;
		this.correct = correct;
	}

	public Answers(Answers other) {
		this(other.answer, other.correct);
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public int getLength() {
		return answer.length();
	}

	@Override
	public String toString() {
		return answer + "\t( " + correct + ")";
	}

}
