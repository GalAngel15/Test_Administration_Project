package id314689589;

import java.io.Serializable;

public class ChoiceQuestion extends Questions implements Serializable {
	Set<Answers> allAnswers = new Set<Answers>();

	public ChoiceQuestion(String description) {
		super(description);
	}

	public ChoiceQuestion(ChoiceQuestion other) {
		this(other.getDescription());
	}

	public int getNumAnswers() {
		return allAnswers.size();
	}

	public Answers getAnswer(int index) {
		return allAnswers.get(index);
	}

	public int getAnswerLength() {
		int length = 0;
		for (int i = 0; i < allAnswers.size(); i++) {
			length += allAnswers.get(i).getLength();
		}
		return length;
	}

	public boolean removeAnswer(int index) {
		if (index >= allAnswers.size() || index < 0)
			return false;
		allAnswers.remove(index);
		return true;
	}

	public boolean addAnswer(Answers newAnswer) {
		if (allAnswers.add(newAnswer))
			return true;
		return false;
	}

	public int numberOfCorrectAnswers() {
		int num = 0;
		for (int i = 0; i < allAnswers.size(); i++) {
			if (allAnswers.get(i).isCorrect())
				num++;
		}
		return num;
	}

	@Override
	public boolean setAnswer(Answers theAnswer, int index) {
		if(allAnswers.set(theAnswer, index))
			return true;
		return false;
	}
	
	public Questions clone() {
		ChoiceQuestion cloneQuestion=new ChoiceQuestion(this);
		for(int i=0; i<allAnswers.size();i++) {
			cloneQuestion.addAnswer(new Answers(this.getAnswer(i)));
		}
		return cloneQuestion;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(super.toString());
		for (int i = 0; i < allAnswers.size(); i++) {
			str.append("\t" + (i + 1) + ") " + allAnswers.get(i).toString() + "\n");
		}
		return str.toString();
	}

	@Override
	public String toStringQuestions() {
		StringBuilder str = new StringBuilder(super.toStringQuestions() + "\n");
		for (int i = 0; i < allAnswers.size(); i++) {
			str.append("\t" + (i + 1) + ") " + allAnswers.get(i).toString() + "\n");
		}
		return str.toString();
	}

}
