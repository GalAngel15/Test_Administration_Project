package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

public class Manager implements Serializable, ManagerAble, Cloneable {
	private LocalDate date = LocalDate.now();
	private Test test;
	private static final String TEST_NAME = "Full Test";

//	public static int numTest = 0;
	public Manager() {
		test = new Test();
	}

	public int getNumberOfQuestions() {
		return test.getNumberOfQuestions();
	}

	public boolean haveQuestion(int num) {
		if (num >= test.getNumberOfQuestions() || num < 0) {
			System.out.println("There is no question here");
			return false;
		}
		return true;
	}

	public boolean addChoiceQuestion(String question) {
		if (test.addQuestion(new ChoiceQuestion(question)))
			return true;
		return false;
	}

	public boolean addOpenQuestion(String question) {
		if (test.addQuestion(new OpenQuestion(question)))
			return true;
		return false;
	}

	public boolean addOpenQuestion(Questions question) {
		if (test.addQuestion(new OpenQuestion((OpenQuestion) question)))
			return true;
		return false;
	}

	public boolean addAnswer(int numQuestion, String answer, boolean isTrue) {
		if (test.addAnswer(numQuestion, new Answers(answer, isTrue)))
			return true;
		return false;
	}

	public boolean addChoiceAnswer(int numQuestion, int numAnswer, ChoiceQuestion theQuestion) {
		if (test.addAnswer(numQuestion, new Answers(theQuestion.getAnswer(numAnswer))))
			return true;
		return false;
	}

	public boolean updateQuestion(int index, String newAnswer) {
		if (test.changeName(index, newAnswer))
			return true;
		return false;
	}

	public Questions getQuestion(int index) {
		if (index < 0 || index > test.getNumberOfQuestions())
			return null;
		return test.getQuestion(index);
	}

	public boolean updateChoiceQuestion(int index, String newAnswer, boolean isTrue, int numAnswer) {
		if (numAnswer < test.getQuestion(index).getNumAnswers() && numAnswer >= 0) {
			if (test.getQuestion(index).setAnswer(new Answers(newAnswer, isTrue), numAnswer))
				return true;
		}
		return false;
	}

	public boolean updateOpenAnswer(int index, String newAnswer) {
		if (test.getQuestion(index).setAnswer(new Answers(newAnswer, true), 0))
			return true;
		return false;
	}

	public void showQuestions() {
		for (int i = 0, k = 1; i < test.getNumberOfQuestions(); i++) {
			if (test.getQuestion(i) != null) {
				System.out.println(k + ") " + test.getQuestion(i).getDescription());
				k++;
			}
		}
	}

	public boolean removeAnswer(int numQuestion, int numAnswer) {
		ChoiceQuestion temp = (ChoiceQuestion) test.getQuestion(numQuestion);
		if (temp.removeAnswer(numAnswer))
			return true;
		else
			return false;
	}

	public void sortByAnswerLength() {
		test.sortByAnswerLength();
	}

	public String toStringQuestions() {
		return test.toStringQuestions();
	}

	public String showChoiceQuestins() {
		return test.showChoiceQuestins();
	}

	@Override
	public String toString() {
		return test.toString();
	}

	public void SelectionSort() {
		test.SelectionSort();
	}

	public Manager automaticTest(int numQuestions) {
		Manager newTest = new Manager();
		ChoiceQuestion temp;
		int rnd;
		boolean thereIsTrue = false;
		int randomAnswer;

		for (int i = 0; i < numQuestions;) {
			rnd = (int) (Math.random() * this.getNumberOfQuestions());
			if (test.getQuestion(rnd).getClass().equals(OpenQuestion.class)) {
				if (newTest.addOpenQuestion(this.getQuestion(rnd)))
					i++;

			} else {
				temp = (ChoiceQuestion) this.getQuestion(rnd);
				thereIsTrue = false;
				if (newTest.addChoiceQuestion(temp.getDescription())) {
					for (int j = 0; j < 4 && j != temp.getNumAnswers();) {
						randomAnswer = (int) (Math.random() * temp.getNumAnswers());
						if (!thereIsTrue) {
							if (newTest.addChoiceAnswer(i, randomAnswer, temp)) {
								j++;
								if (temp.getAnswer(randomAnswer).isCorrect())
									thereIsTrue = true;
							}
						} else { // thereIsTrue = true
							if (j == (temp.getNumAnswers() - (temp.numberOfCorrectAnswers() - 1)))
								j = 4;
							else {
								if (!temp.getAnswer(randomAnswer).isCorrect()) {
									if (newTest.addChoiceAnswer(i, randomAnswer, temp))
										j++;
								}
							}
						}

					}

					temp = (ChoiceQuestion) newTest.getQuestion(i);
					newTest.addAnswer(i, "more then one answer is correct", (temp.numberOfCorrectAnswers() > 1));
					newTest.addAnswer(i, "no answer is correct", (temp.numberOfCorrectAnswers() == 0));
					i++;
				}
			}
		}
		return newTest;
	}
	
	

	public void saveQuestions() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("exam " + date.toString() + ".txt");
		for (int i = 0; i < this.getNumberOfQuestions(); i++) {
			pw.println(test.getQuestion(i).toStringQuestions());
		}
		System.out.println("Questions file saved!");
		pw.close();
	}

	public void saveAnswers() throws FileNotFoundException {
		PrintWriter pw = new PrintWriter("solution " + date.toString() + ".txt");
		pw.println(test);
		System.out.println("Solution file saved!");
		pw.close();
	}

	public void read(String day, String month, String year) throws FileNotFoundException {
		String filename = ("solution " + year + "-" + month + "-" + day + ".txt");
		File f = new File(filename);
		System.out.println("\nHere is your test: ");
		Scanner input = new Scanner(f);
		while (input.hasNext()) {
			String str = input.nextLine();
			System.out.println(str);
		}
		input.close();
	}

	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(TEST_NAME + ".txt"));
		o.writeObject(test);
		o.close();
		System.out.println("File saved!");
	}

	public void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(TEST_NAME + ".txt"));
		test = (Test) i.readObject();
//		System.out.println("The content of your file is: ");
//		System.out.println(test);
		i.close();
	}
	
	public Manager clone() {
		Manager cloneManager=new Manager();
		cloneManager.test=this.test.clone();
		return cloneManager;
	}

}
