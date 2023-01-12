package Model;

import java.io.Serializable;

public class OpenQuestion extends Questions implements Serializable {
	private Answers answer;

	public OpenQuestion(String description) {
		super(description);
		answer = null;
	}

	public OpenQuestion(String description, Answers answer) {
		super(description);
		this.answer = answer;
	}

	public OpenQuestion(OpenQuestion other) {
		super(other.getDescription());
		this.answer = other.answer;
	}

	public Answers getAnswer() {
		return answer;
	}

	public int getAnswerLength() {
		return answer.getLength();
	}

	public boolean setAnswer(Answers answer, int index) {
		if (answer.getAnswer() != null) {
			this.answer = answer;
			return true;
		}
		return false;
	}
	public Questions clone() {
		OpenQuestion cloneQuestion=new OpenQuestion(this);
		return cloneQuestion;
	}

	@Override
	public String toString() {
		return super.toString() + "\t" + answer.getAnswer();
	}

	@Override
	public boolean addAnswer(Answers answer) {
		if (this.answer != null || answer==null)
			return false;
		else
			this.answer = answer;
		return true;
	}

	@Override
	public boolean addDefultAnswers() {
		// TODO Auto-generated method stub
		return false;
	}

}
