package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import Model.ChoiceQuestion;
import Model.Manager;
import View.View;

public class Controller {
	private Manager model;
	private View view;
	private int numAnswers;
	private int numQuestion;
	private int ansIndex;
	private String ans;
	private boolean isTrue;
	private Manager temp = new Manager();
	private Manager manual = new Manager();
	int ansAddIndex;

	public Controller(Manager model, View view) {
		this.model = model;
		this.view = view;
		try {
			model.read();
		} catch (FileNotFoundException e) {
			addQuestions();
			System.out.println("first time here, hey!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			addQuestions();
		}
		view.setNumQuestions(model.getNumberOfQuestions());
		view.setTest(model);
		view.buttonCase1(e -> { // case1
			view.cleanBP();
			view.setCenterMsg(model.toString());
			view.setLeftMsg(null);
			view.setTest(model);
		});

		view.buttonOkAddQuestion(e -> { // case2
			try {
				numAnswers = view.getComboBoxValue();
			} catch (NullPointerException a) {
				numAnswers = 1;
			}
			if (numAnswers > 1) {
				if (model.addChoiceQuestion((view.getTextFileds(0)))) {
					for (int i = 1; i <= numAnswers; i++) {
						model.addAnswer(model.getNumberOfQuestions() - 1, view.getTextFileds(i),
								view.getIsTrueForEach(i - 1));
					}
				} else {
					view.setRightMsg("Question already exist");
				}
			} else {
				if (model.addOpenQuestion((view.getTextFileds(0)))) {
					model.addAnswer(model.getNumberOfQuestions() - 1, view.getTextFileds(1), true);
				} else
					view.setRightMsg("Answer already exist");
			}
			view.setNumQuestions(model.getNumberOfQuestions());
			view.setTest(model);
			view.showTest();
		});

		view.buttonOkSetQuestion(e -> { // case3
			numQuestion = view.getNumberQuestion();
			model.updateQuestion(numQuestion-1, view.getTextFiled2());
			view.setTest(model);
			view.showTest();
		});

		view.buttonOkSetAnswer(e -> { // case4
			numQuestion = (view.getNumberQuestion() - 1);
			ans = view.getTextFiled1();
			if (model.getQuestion(numQuestion).getClass().getSimpleName().equalsIgnoreCase("OpenQuestion")) {
				model.updateOpenAnswer(numQuestion, ans);
				view.setTest(model);
				view.showTest();
			} else {
				try {
					ansIndex = Integer.parseInt(view.getTextFiled2()) - 1;
					isTrue = view.getIsTrue();
					model.updateChoiceQuestion(numQuestion, ans, isTrue, ansIndex);
					view.setTest(model);
					view.showTest();
				} catch (NumberFormatException a) {
					view.setCenterMsg("Type should be int");
				} catch (NullPointerException b) {
					view.setCenterMsg("There is no answer");
				}
			}
		});

		view.buttonOkRemoveAnswer(e -> { // case5
			numQuestion = (view.getNumberQuestion() - 1);
			if (model.getQuestion(numQuestion).getClass().getSimpleName().equalsIgnoreCase("ChoiceQuestion")) {
				ansIndex = (Integer.parseInt(view.getTextFiled2()) - 1);
				if (model.removeAnswer(numQuestion, ansIndex)) {
					view.setCenterMsg("Succed");
					view.setTest(model);
					view.showTest();
				}
				else
					view.setCenterMsg("There is no answer");
			} else {
				view.setCenterMsg("The type of that question is open");
			}
		});

		view.buttonOkManualTest(e -> {// case6
			numQuestion = Integer.parseInt(view.getTextFiled2());
			ansIndex = (view.getComboBoxValue() - 1);
			if (numQuestion == 0) {
				for(int i=0; i<manual.getNumberOfQuestions(); i++) {
					if(manual.getQuestion(i).getClass().getSimpleName().equalsIgnoreCase("ChoiceQuestion")) {
						manual.getQuestion(i).addDefultAnswers();
					}
				}
				view.cleanBP();
				temp = manual.clone();
				view.setCenterMsg(temp.toString());
				manual = new Manager();
			} else {
				view.setRightMsg(null);
				if (model.getQuestion(ansIndex).getClass().getSimpleName().equalsIgnoreCase("OpenQuestion")) {
					if (manual.addOpenQuestion(model.getQuestion(ansIndex))) {
						view.setCenterMsg("Added");
						view.setText2((numQuestion - 1) + "");
					} else {
						view.setCenterMsg("Question already exist");
					}
				} else {
					if (manual.addChoiceQuestion(model.getQuestion(ansIndex).getDescription())) {
						view.addComboBox(model.getQuestion(ansIndex).getNumAnswers());
						
					} else {
						view.setCenterMsg("Question already exist");
					}
				}
			}
		});

		view.buttonAddAnswer(e -> {
			int numAnswers = 0;
			view.setText2((numQuestion - 1) + "");
			if (numAnswers < 9) {
				if (manual.addChoiceAnswer((manual.getNumberOfQuestions() - 1), (view.getMainComboBox() - 1),
						(ChoiceQuestion) model.getQuestion(ansIndex))) {
					view.setRightMsg("Answer Added");
				} else {
					view.setRightMsg("Answer already exist");
				}
			}else {
				view.setRightMsg("Can't add more answers");
			}
		});

		view.buttonOkAutoTest(e -> {// case7
			numAnswers = view.getNumberQuestion();
			temp = model.automaticTest(numAnswers);
			view.setCenterMsg(temp.toString());
		});

		view.buttonShowLaseTest(e -> { // case8
			view.cleanBP();
			view.setCenterMsg(temp.toString());
		});

		view.buttonExit(e -> {
			try {
				model.save();
				view.cleanBP();
				view.exit();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	private void addQuestions() {
		model.addOpenQuestion("20 + 4 = ?");
		model.addAnswer(model.getNumberOfQuestions() - 1, "24", true);
		model.addOpenQuestion("10 + 5 = ?");
		model.addAnswer(model.getNumberOfQuestions() - 1, "15", true);
		model.addChoiceQuestion("9 * 5 = ?");
		model.addAnswer(model.getNumberOfQuestions() - 1, "10", false);
		model.addAnswer(model.getNumberOfQuestions() - 1, "20", false);
		model.addAnswer(model.getNumberOfQuestions() - 1, "35", true);
		model.addAnswer(model.getNumberOfQuestions() - 1, "45", false);
		model.addOpenQuestion("20 + 4 = ?");
		model.addAnswer(model.getNumberOfQuestions() - 1, "24", true);
		model.addChoiceQuestion("1900 + 48 = ?");
		model.addAnswer(model.getNumberOfQuestions() - 1, "1948", true);
		model.addAnswer(model.getNumberOfQuestions() - 1, "1908", false);
	}
}
