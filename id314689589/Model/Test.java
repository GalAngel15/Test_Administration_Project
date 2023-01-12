package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable, Cloneable {
	private ArrayList<Questions> abcQuestions;

	public Test() {
		abcQuestions = new ArrayList<Questions>();
	}

	public boolean setQuestion(Questions theQuestion, int index) {
		if (theQuestion.getDescription() != null && abcQuestions.get(index) != null) {
			abcQuestions.set(index, theQuestion);
			return true;
		}
		return false;
	}

	public int getNumberOfQuestions() {
		return abcQuestions.size();
	}

	public boolean addAnswer(int index, Answers theAnswer) {
		return abcQuestions.get(index).addAnswer(theAnswer);
	}

	public boolean addQuestion(Questions theQuestion) {
		for (int i = 0; i < abcQuestions.size(); i++) {
			if (abcQuestions.get(i).equals(theQuestion))
				return false;
		}
		abcQuestions.add(theQuestion);
		return true;
	}

	public void sortByAnswerLength() {
		int minLength;
		for (int n = 0; n < abcQuestions.size(); n++) {
			minLength = n;
			for (int i = n + 1; i < abcQuestions.size(); i++) {
				if (abcQuestions.get(i).getAnswerLength() < abcQuestions.get(minLength).getAnswerLength())
					minLength = i;
			}
			if (minLength != n)
				swap(this, n, minLength);
		}
	}

	public void SelectionSort() {
		int n = abcQuestions.size();
		int i, IndMax; // IndMax is for the index of the maximum
		for (; n > 1; n--) {
			for (IndMax = 0, i = 1; i < n; i++)
				if (this.getQuestion(IndMax).compareTo(this.getQuestion(i)) < 0)
					IndMax = i;
			swap(this, n - 1, IndMax);
		}
	}

	public static void swap(Test test, int i, int j) {
		Questions tmp = test.getQuestion(i);
		test.setQuestion(test.getQuestion(j), i);
		test.setQuestion(tmp, j);
	}

	public Questions getQuestion(int index) {
		return abcQuestions.get(index);
	}

	public boolean changeName(int index, String str) {
		if (str == null)
			return false;
		for (int i = 0; i < abcQuestions.size(); i++) {
			if (abcQuestions.get(i).getDescription().equalsIgnoreCase(str))
				return false;
		}
		abcQuestions.get(index).setDescription(str);
		return true;
	}
	
	public Test clone() {
		Test cloneTest = new Test();
		for(int i=0; i<abcQuestions.size();i++) {
			cloneTest.addQuestion(abcQuestions.get(i).clone());
		}
		return cloneTest;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < abcQuestions.size(); i++) {
			str += "question " + (i + 1) + ": " + abcQuestions.get(i).toString() + "\n";
		}
		return str;
	}

	public String toStringQuestions() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < abcQuestions.size(); i++) {
			str.append((i + 1) + ") " + abcQuestions.get(i).toStringQuestions() + "\n");
		}
		return str.toString();
	}

	public String showChoiceQuestins() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < abcQuestions.size(); i++) {
			if ((abcQuestions.get(i)).getClass().equals(ChoiceQuestion.class))
				str.append((i + 1) + ") " + abcQuestions.get(i).toString() + "\n");
		}
		return str.toString();
	}

}
