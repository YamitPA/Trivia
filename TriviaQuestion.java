import java.util.ArrayList;
import java.util.Collections;


/**
 * Constructor to create a new TriviaQuestion.
 */
public class TriviaQuestion{
	private String correctAnswer; //The correct answer to the question.
	private String questionText; //The text of the question.
	private ArrayList<String> answers; //A list of wrong answers to the question.
	
	public TriviaQuestion(String questionText, String correctAnswer, ArrayList<String> wrongAnswers) {
		this.correctAnswer = correctAnswer;
		this.questionText = questionText;
		this.answers = new ArrayList<>();
		
		// Add correct and wrong answers to the answers list
		this.answers.add(correctAnswer);
		this.answers.addAll(wrongAnswers);
		
		// Shuffle the answers to randomize their order
		Collections.shuffle(this.answers);
	}
	
	
	/**
     * Get the text of the question.
     */
	public String getQuestionText() {
		return questionText;
	}
	
	
	/**
     * Return the correct answer to the question.
     */
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	
	/**
     * Return a list of all possible answers (correct and wrong).
     */
	public ArrayList<String> getAnswers() {
		return answers;
	}
}