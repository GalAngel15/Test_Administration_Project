package Model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ManagerAble {
	public int getNumberOfQuestions();

	public boolean haveQuestion(int num);

	public boolean addChoiceQuestion(String question);

	public boolean addOpenQuestion(String question);

	public boolean addOpenQuestion(Questions question);

	public boolean addAnswer(int numQuestion, String answer, boolean isTrue);

	public boolean addChoiceAnswer(int numQuestion, int numAnswer, ChoiceQuestion theQuestion);

	public boolean updateQuestion(int index, String newAnswer) ;

	public Questions getQuestion(int index) ;

	public boolean updateChoiceQuestion(int index, String newAnswer, boolean isTrue, int numAnswer) ;

	public boolean updateOpenAnswer(int index, String newAnswer) ;

	public void showQuestions() ;

	public boolean removeAnswer(int numQuestion, int numAnswer) ;
	public void sortByAnswerLength() ;

	public String toStringQuestions() ;

	public String showChoiceQuestins() ;

	@Override
	public String toString();

	public void SelectionSort() ;

	public Manager automaticTest(int numQuestions) ;

	public void saveQuestions() throws FileNotFoundException ;

	public void saveAnswers() throws FileNotFoundException ;

	public void read(String day, String month, String year) throws FileNotFoundException ;

	public void save() throws FileNotFoundException, IOException ;

	public void read() throws FileNotFoundException, IOException, ClassNotFoundException;
}
