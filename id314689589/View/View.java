package View;
import java.util.ArrayList;

import Model.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View {

	private Text text = new Text("Choose one of the options: ");
	private Button case1 = new Button("Show Test");
	private Button case2 = new Button("Add Question");
	private Button case3 = new Button("Update question");
	private Button case4 = new Button("Update answer");
	private Button case5 = new Button("Remove answer");
	private Button case6 = new Button("Manual quiz");
	private Button case7 = new Button("Auto quiz");
	private Button case8 = new Button("Last test");
	private Button case9 = new Button("Save & Exit");
	private Button ok2 = new Button("OK");
	private Button ok3 = new Button("OK");
	private Button ok4 = new Button("OK");
	private Button ok5 = new Button("OK");
	private Button ok6 = new Button("OK");
	private Button ok7 = new Button("OK");
	private Button addAnswer = new Button("Add answer");
	private BorderPane bp;
	private ArrayList<TextField> textFileds;
	private ArrayList<CheckBox> allIsTrue;
	private TextField tf1 = new TextField();
	private TextField tf2 = new TextField();
//	private TextField tf3 = new TextField();
	private ComboBox<Integer> comboBox;
	private ComboBox<Integer> mainComboBox;
	private ComboBox<Boolean> isTrue = new ComboBox<Boolean>();
	private ComboBox<Integer> numberQuestionsBox;
	private int comboBoxValue;
	private int numQuestions;
	private Stage primaryStage;
	private Manager test;

	public View(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Project 314689589");
		bp = new BorderPane();
		HBox hbox = new HBox();
		addButtons(hbox);
		bp.setTop(hbox);
		isTrue.getItems().addAll(true, false);
		isTrue.setPromptText("T/F");
		Scene scene = new Scene(bp, 1000, 500);
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
		buttonCase2();
		buttonCase3();
		buttonCase4();
		buttonCase5();
		buttonCase6();
		buttonCase7();
		this.primaryStage = primaryStage;
	}

	public void addButtons(HBox hb) {
		hb.getChildren().addAll(case1, case2, case3, case4, case5, case6, case7, case8, case9);
//		hb.setSpacing(5);
	}

	public void buttonCase1(EventHandler<ActionEvent> e) {
		case1.setOnAction(e);
	}

	public void buttonCase2() {

		case2.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			RadioButton openQuestion = new RadioButton("Open Question");
			RadioButton choiceQuestion = new RadioButton("Choice Question");
			ToggleGroup tgButtons = new ToggleGroup();
			openQuestion.setToggleGroup(tgButtons);
			choiceQuestion.setToggleGroup(tgButtons);
			vbox.getChildren().setAll(text, openQuestion, choiceQuestion);
			vbox.setSpacing(15);
			allIsTrue = new ArrayList<CheckBox>();
			openQuestion.setOnAction(a -> {
				vbox.getChildren().setAll(text, openQuestion, choiceQuestion);
				addQuestion(1);
			});
			choiceQuestion.setOnAction(a -> {
				bp.setCenter(null);
				addAmericanQuestion(vbox);
			});
			bp.setLeft(vbox);
		});
	}

	public void buttonOkAddQuestion(EventHandler<ActionEvent> e) {
		ok2.setOnAction(e);
	}

	public void buttonCase3() {
		case3.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			numberQuestionsBox = new ComboBox<>();
			for (int i = 0; i < numQuestions; i++) {
				numberQuestionsBox.getItems().add(i + 1);
			}
//			tf1.clear();
			tf2.clear();
//			tf1.setPromptText("Number of question to change");
			tf2.setPromptText("Enter the new question");
			numberQuestionsBox.setPromptText("Number of question to change");
			vbox.getChildren().addAll(numberQuestionsBox, tf2, ok3);
			vbox.setSpacing(30);
			bp.setLeft(vbox);
			showTest();
		});
	}

	public void buttonOkSetQuestion(EventHandler<ActionEvent> e) {
		ok3.setOnAction(e);
	}

	public void buttonCase4() {
		case4.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			numberQuestionsBox = new ComboBox<>();
			for (int i = 0; i < numQuestions; i++) {
				numberQuestionsBox.getItems().add(i + 1);
			}
			tf1.clear();
			tf2.clear();
			numberQuestionsBox.setPromptText("Number of question");
			tf1.setPromptText("Enter the new answer");
			tf2.setPromptText("Number of answer to change");
			vbox.getChildren().addAll(numberQuestionsBox, tf1, tf2, isTrue, ok4);
			vbox.setSpacing(30);
			bp.setLeft(vbox);
			showTest();
		});
	}

	public void buttonOkSetAnswer(EventHandler<ActionEvent> e) {
		ok4.setOnAction(e);
	}

	public void buttonCase5() {
		case5.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			numberQuestionsBox = new ComboBox<>();
			for (int i = 0; i < numQuestions; i++) {
				numberQuestionsBox.getItems().add(i + 1);
			}
			tf2.clear();
			numberQuestionsBox.setPromptText("Number of question");
			tf2.setPromptText("Num answer to remove");
			vbox.getChildren().addAll(numberQuestionsBox, tf2, ok5);
			vbox.setSpacing(30);
			bp.setLeft(vbox);
			showTest();
		});
	}

	public void buttonOkRemoveAnswer(EventHandler<ActionEvent> e) {
		ok5.setOnAction(e);
	}

	public void buttonCase6() {
		case6.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			Label txt = new Label("Questions left:");
			numberQuestionsBox = new ComboBox<>();
			comboBox = new ComboBox<>();
			for (int i = 0; i < numQuestions; i++) {
				numberQuestionsBox.getItems().add(i + 1);
				comboBox.getItems().add(i + 1);
			}
			numberQuestionsBox.setPromptText("Number of questions");
			comboBox.setPromptText("Num question to add");
			comboBox.setVisible(false);
			ok6.setDisable(true);
			tf2.clear();
			tf2.setVisible(false);
			txt.setVisible(false);
			vbox.getChildren().addAll(numberQuestionsBox, txt, tf2, comboBox, ok6);
			vbox.setSpacing(20);
			bp.setLeft(vbox);
			showTest();
			numberQuestionsBox.setOnAction(a -> {
				txt.setVisible(true);
				tf2.setVisible(true);
				tf2.setDisable(true);
				tf2.setText(numberQuestionsBox.getValue() + "");
				numberQuestionsBox.setDisable(true);
				comboBox.setVisible(true);
				ok6.setDisable(false);
			});
		});
	}

	public void buttonOkManualTest(EventHandler<ActionEvent> e) {
		ok6.setOnAction(e);
	}

	public void buttonAddAnswer(EventHandler<ActionEvent> e) {
		addAnswer.setOnAction(e);
	}

	public void buttonCase7() {
		case7.setOnAction(e -> {
			cleanBP();
			VBox vbox = new VBox();
			numberQuestionsBox = new ComboBox<>();
			for (int i = 0; i < numQuestions; i++) {
				numberQuestionsBox.getItems().add(i + 1);
			}
			numberQuestionsBox.setPromptText("Number of questions");
			vbox.getChildren().addAll(numberQuestionsBox, ok7);
			vbox.setSpacing(30);
			bp.setLeft(vbox);
		});
	}

	public void buttonOkAutoTest(EventHandler<ActionEvent> e) {
		ok7.setOnAction(e);
	}

	public void buttonShowLaseTest(EventHandler<ActionEvent> e) {
		case8.setOnAction(e);
	}

	public void buttonExit(EventHandler<ActionEvent> e) {
		case9.setOnAction(e);
	}

	public void addAmericanQuestion(VBox vbox) {
		comboBox = new ComboBox<>();
		comboBox.getItems().addAll(2, 3, 4, 5, 6, 7, 8);
		comboBox.setPromptText("How many answers do you want?");
		vbox.getChildren().add(comboBox);
		comboBox.setOnAction(e -> addQuestion(comboBox.getValue()));
	}

//	public void showErrorMsg() {
//		Alert alert = new Alert(AlertType.ERROR);
//		alert.setTitle("Error Dialog");
//		alert.setHeaderText("Look, an Error Dialog");
//		alert.setContentText("Ooops, there was an error!");
//		alert.showAndWait();
//	}

	public void addQuestion(int numAnswers) {
		VBox vboxQuestions = new VBox();
		comboBoxValue = numAnswers;
		textFileds = new ArrayList<TextField>();
		textFileds.add(new TextField());
		textFileds.get(0).setPromptText("write your question");
		vboxQuestions.getChildren().add(textFileds.get(0));
		for (int i = 1; i <= numAnswers; i++) {
			HBox hBoxCase3 = new HBox();
			if (allIsTrue.size() <= 8) {
				allIsTrue.add(new CheckBox("true"));
			}
			if (textFileds.size() <= 8) {
				textFileds.add(new TextField());
			}
			textFileds.get(i).setPromptText("enter answer");
			hBoxCase3.getChildren().addAll(textFileds.get(i), allIsTrue.get(i - 1));
			vboxQuestions.getChildren().add(hBoxCase3);
		}
		vboxQuestions.getChildren().add(ok2);
		bp.setCenter(vboxQuestions);
	}

	public String getTextFileds(int index) {
		return textFileds.get(index).getText();
	}

	public String getTextFiled1() {
		return tf1.getText();
	}

	public String getTextFiled2() {
		return tf2.getText();
	}

	public boolean getIsTrueForEach(int num) {
		return ((allIsTrue.get(num).isSelected()) ? true : false);
	}

	public int getComboBoxValue() throws NullPointerException {
		return comboBox.getValue();
	}

	public boolean getIsTrue() {
		return isTrue.getValue();
	}

	public void setCenterMsg(String str) {
		Label lb = new Label(str);
		bp.setCenter(lb);
	}

	public void setLeftMsg(String str) {
		Label lb = new Label(str);
		bp.setLeft(lb);
	}
	public void setRightMsg(String str) {
		Label lb = new Label(str);
		bp.setRight(lb);
	}

	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	public int getNumberQuestion() {
		return numberQuestionsBox.getValue();
	}

	public void setText2(String str) {
		tf2.setText(str);
	}

	public void cleanBP() {
		bp.setLeft(null);
		bp.setCenter(null);
		bp.setRight(null);
		bp.setBottom(null);
	}

	public void exit() {
		primaryStage.close();
	}

	public void addComboBox(int maxValue) {
		Label txt=new Label("Which answer to add:");
		mainComboBox = new ComboBox<>();
		for (int i = 0; i < maxValue; i++) {
			mainComboBox.getItems().add(i + 1);
		}
		VBox vbox = new VBox();
		vbox.getChildren().addAll(txt,mainComboBox, addAnswer);
		vbox.setSpacing(15);
		bp.setCenter(vbox);
	}
	
	public void setTest(Manager newTest) {
		test=newTest;
	}
	public void showTest() {
		Label lb=new Label(test.toString());
		bp.setBottom(lb);
	}

	public int getMainComboBox() {
		return mainComboBox.getValue();
	}
}
