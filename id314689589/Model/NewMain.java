package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NewMain {
	public static Scanner s = new Scanner(System.in);
	public static LocalDate date = LocalDate.now();
	public static final String TEST_NAME = "Full Test";
	public static Manager test = new Manager();
	public static Manager tempTest = new Manager();
	public static void main(String[] args) {
		int choice = 0;
		int ans;
		test.addOpenQuestion("20 + 4 = ?");
		test.addAnswer(test.getNumberOfQuestions() - 1, "24", true);
		test.addOpenQuestion("10 + 5 = ?");
		test.addAnswer(test.getNumberOfQuestions() - 1, "15", true);
		test.addChoiceQuestion("9 * 5 = ?");
		test.addAnswer(test.getNumberOfQuestions() - 1, "10", false);
		test.addAnswer(test.getNumberOfQuestions() - 1, "20", false);
		test.addAnswer(test.getNumberOfQuestions() - 1, "35", true);
		test.addAnswer(test.getNumberOfQuestions() - 1, "45", false);
		test.addOpenQuestion("20 + 4 = ?");
		test.addAnswer(test.getNumberOfQuestions() - 1, "24", true);
		test.addChoiceQuestion("1900 + 48 = ?");
		test.addAnswer(test.getNumberOfQuestions() - 1, "1948", true);
		test.addAnswer(test.getNumberOfQuestions() - 1, "1908", false);

		try {
			test.read();
		} catch (FileNotFoundException e) {
			System.out.println("first time here, hey!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		do {
			try {
			System.out.println();
			System.out.println("Choose one of the following options:");
			System.out.println("1)  Show all questions and answers");
			System.out.println("2)  Add a question and answers");
			System.out.println("3)  Update a question");
			System.out.println("4)  Update an answer");
			System.out.println("5)  Delete an answer");
			System.out.println("6)  Manual build a quiz");
			System.out.println("7)  Automatic build a quiz");
			System.out.println("8)  Create a new test as a copy of an existing test");
			System.out.println("9)  Exit");
			System.out.print("Enter your choice --> ");
			choice = Integer.parseInt(s.nextLine());
			switch (choice) {
			case 1:
				System.out.println("\n" + test);// printing all the questing and its answers
				break;
			case 2:
				System.out.println();
				addQuestion(); // adding a questing and its answer/s
				break;
			case 3:
				showQuestions();
				System.out.println();
				updateQuestion();
				// Updating a question"
				break;
			case 4:
				showQuestions();
				System.out.println();
				System.out.println("please choose the number question to change:");
				ans = Integer.parseInt(s.nextLine());
				if (haveQuestion(ans))
					updateAnswer(ans - 1);
				// Updating an answer
				break;
			case 5:
				System.out.println(test.showChoiceQuestins());
				System.out.println();
				removeAnswer();
				break;

			case 6:
				System.out.println();
				try {
					manualTest();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					// ....
				}
				// manual build a quiz
				break;
			case 7:
				System.out.println();
				try {
					automaticTest();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					// ....
				}
				// automatic build a quiz
				break;
			case 8:
				Manager cloneManager=new Manager();
				cloneManager= tempTest.clone();
				System.out.println(cloneManager);
				break;
			case 9:
				try {
					System.out.println();
					test.save();
					System.out.println("goodbye :)");
					break;
				} catch (FileNotFoundException e) {
					System.out.println("didnt saved the file");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}catch(InputMismatchException e) {
				System.out.println("Incorrect input");
			}catch (NumberFormatException n) {
				System.out.println("Incorrect input");
			}

		} while (choice != 9);
	}

	public static boolean haveQuestion(int num) {
		if (num > test.getNumberOfQuestions() || num < 1) {
			System.out.println("There is no question here");
			return false;
		}
		return true;
	}

	public static void addQuestion() {
		String ans;
		String question;
		String questionAnswer;
		boolean flag;
		int answerIndex;
		System.out.println("what kind of question is it? \nchoice question or an open question \nwrite open or choice");
		ans = s.nextLine();
		if (ans.equalsIgnoreCase("open")) {
			System.out.println("write the question:");
			question = s.nextLine();
			System.out.println("write the answer:");
			questionAnswer = s.nextLine();
			if (test.addOpenQuestion(question)) {
				test.addAnswer(test.getNumberOfQuestions() - 1, questionAnswer, true);
				System.out.println("the question added successfully");
			} else {
				System.out.println("the question already exist");
			}
		} else if (ans.equalsIgnoreCase("choice")) {
			System.out.println("write the question:");
			question = s.nextLine();
			System.out.println("how many answers are there?: (2-8)");
			answerIndex = Integer.parseInt(s.nextLine());
			if (answerIndex < 2 || answerIndex > 8) {
				System.out.println("Incorrect input");
				return;
			}
			if (!test.addChoiceQuestion(question))
				System.out.println("Question already exist");
			for (int i = 1; i <= answerIndex; i++) {
				System.out.println("enter an answer: ");
				questionAnswer = s.nextLine();
				System.out.println("is it the correct answer? (yes / no) ");
				ans = s.nextLine();
				if (ans.equalsIgnoreCase("yes")) {
					flag = true;
				} else if (ans.equalsIgnoreCase("no")) {
					flag = false;
				} else {
					System.out.println("incorrect input EXITING");
					return;

				}

				if (!(test.addAnswer(test.getNumberOfQuestions() - 1, questionAnswer, flag))) {
					System.out.println("the answer wasn't added");
					i--;
				} else {
					System.out.println("Answer added! " + (answerIndex - i) + " answers left to add");
				}
			}
			System.out.println("the question added successfully");
		} else {
			System.out.println("incorrect input EXITING");
			return;
		}
	}

	public static void updateQuestion() {
		System.out.println("please choose the number question to change:");
		int ans = Integer.parseInt(s.nextLine()); // add try
		if (ans > 0 && ans <= test.getNumberOfQuestions()) {
			System.out.println("what is the new question? ");
			if (test.updateQuestion(ans - 1, s.nextLine()))
				System.out.println("Succeed!");
			else
				System.out.println("Question already exist");
		}
	}

	public static void updateAnswer(int numQuestion) {
		int answerIndex;
		String ans;
		boolean flag;
		System.out.println(test.getQuestion(numQuestion));
		System.out.println("what is the new answer:");
		Questions temp = test.getQuestion(numQuestion);
		String newAnswer = s.nextLine();
		if (temp.getClass().getSimpleName().equalsIgnoreCase("OpenQuestion")) {
			test.updateOpenAnswer(numQuestion, newAnswer);
		} else {
			System.out.println("is it true? (yes/no)");
			ans = s.nextLine();
			if (ans.equalsIgnoreCase("yes")) {
				flag = true;
			} else if (ans.equalsIgnoreCase("no")) {
				flag = false;
			} else {
				System.out.println("incorrect input EXITING");
				return;
			}
			System.out.println("what answer to change: ");
			answerIndex = Integer.parseInt(s.nextLine());
			if (test.updateChoiceQuestion(numQuestion, newAnswer, flag, answerIndex - 1))
				System.out.println("the answer was changed successfully");
			else {
				System.out.println("there is no answer here");
				return;
			}
		}
	}

	public static void showQuestions() {
		for (int i = 0, k = 1; i < test.getNumberOfQuestions(); i++) {
			System.out.println(k + ") " + test.getQuestion(i).getDescription());
			k++;
		}
	}

	public static void removeAnswer() {
		System.out.println("please choose the number question to change:");
		int numQuestion = Integer.parseInt(s.nextLine());
		if (!test.haveQuestion(numQuestion - 1))
			return;
		else if (!(test.getQuestion(numQuestion - 1).getClass().getSimpleName().equalsIgnoreCase("ChoiceQuestion"))) {
			System.out.println("can't remove that answer");
			return;
		}
		System.out.println("These are the answers: \n");
		System.out.println(test.getQuestion(numQuestion - 1).toString());
		System.out.println("Which answer do you want to remove? \n");
		if (test.removeAnswer(numQuestion - 1, Integer.parseInt(s.nextLine()) - 1)) {
			System.out.println("succeed");
			return;
		} else {
			System.out.println("you can't remove that answer");
			return;
		}
	}

	public static void manualTest() throws FileNotFoundException {
		int numQ;
		int numQuestions;
		int numAnswer;
		ChoiceQuestion temp;
		System.out.println("How many questions do you want?");
		numQuestions = Integer.parseInt(s.nextLine());
		if (numQuestions > test.getNumberOfQuestions()) {
			System.out.println("There are not enough questions please add more");
			return;
		}
		for (int i = 0; i < numQuestions;) {
			System.out.println(test);
			System.out.println("Which question you want? ");
			numQ = (Integer.parseInt(s.nextLine()) -1 );
			if (haveQuestion(numQ + 1)) {
				if (test.getQuestion(numQ).getClass().getSimpleName().equalsIgnoreCase("OpenQuestion")) {
					if (tempTest.addOpenQuestion(test.getQuestion(numQ))) {
						i++;
						System.out.println("Question added! \n" + (numQuestions - i) + " questions left to add \n");
					} else {
						System.out.println("Question already exist");
					}
				} else {
					temp = (ChoiceQuestion) test.getQuestion(numQ);
					if (tempTest.addChoiceQuestion(temp.getDescription())) {
						System.out.println("\nwhat answers do you want to use? (press 0 to finish)");
						do {
							numAnswer = Integer.parseInt(s.nextLine());
							if (numAnswer != 0) {
								if (numAnswer > temp.getNumAnswers() || numAnswer < 0)
									System.out.println("There is no answer there");
								else if (tempTest.addChoiceAnswer(i , numAnswer - 1, temp))
									System.out.println("Succeed!");
								else
									System.out.println("Answer didn't add");
							}
						} while (numAnswer != 0);
						temp = (ChoiceQuestion) tempTest.getQuestion(i);
						tempTest.addAnswer(i, "more then one answer is correct", temp.numberOfCorrectAnswers() > 1);
						tempTest.addAnswer(i, "no answer is correct", (temp.numberOfCorrectAnswers() == 0));
						i++;
						System.out.println("Question added! \n" + (numQuestions - i) + " questions left to add \n");
					}
				}

			}
		}
		tempTest.sortByAnswerLength();
		System.out.println("Here is your test: \n");
		System.out.println(tempTest.toStringQuestions());
		System.out.println(tempTest);
		tempTest.saveQuestions();
		tempTest.saveAnswers();
	}

	public static void automaticTest() throws FileNotFoundException {
		System.out.println("how many questions do you want?");
		int numQuestions = Integer.parseInt(s.nextLine());

		if (numQuestions > test.getNumberOfQuestions()) {
			System.out.println("there not enough questions please add more");
			return;
		}
		tempTest = test.automaticTest(numQuestions);
		System.out.println("Here is your test: \n");
		tempTest.SelectionSort();
		System.out.println(tempTest.toStringQuestions());
		System.out.println(tempTest);
		tempTest.saveQuestions();
		tempTest.saveAnswers();
	}
}
