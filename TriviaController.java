import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.animation.PauseTransition;


public class TriviaController {

    @FXML
    private Label scoreLabel; // Label to display the player's score

    @FXML
    private Label questionLabel; // Label to display the current trivia question

    @FXML
    private Label feedbackLabel; // Label to display feedback after each answer (correct/wrong)

    @FXML
    private Button answerButton1; // Button for the first possible answer

    @FXML
    private Button answerButton2; // Button for the second possible answer

    @FXML
    private Button answerButton3; // Button for the third possible answer

    @FXML
    private Button answerButton4; // Button for the fourth possible answer
    
    private TriviaQuestionBank questionBank; // The bank of trivia questions
    private TriviaQuestion currentQuestion; // The current question that is being asked
    private int score; // The player's current score
    
    
   /**
    * This method initializes the game by loading the questions from a file, setting the score to 0,
    * and displaying the first question.
    */
    public void initialize() {
        questionBank = new TriviaQuestionBank(); // Create a new TriviaQuestionBank object
        questionBank.loadQuestionsFromFile("trivia.txt"); // Load questions from a file
        score = 0; // Initialize score to 0
        updateScoreLabel(); // Update the score display
        loadNextQuestion(); // Load the first question
    }
    
    
    /**
     * This method is triggered when the player clicks an answer button.
     * It checks if the selected answer is correct or wrong, updates the score,
     * provides feedback, and loads the next question after a brief pause.
     */
    @FXML
    private void handleAnswerButton	(ActionEvent event) {
    	Button clickedButton = (Button) event.getSource(); // Get the button that was clicked
    	String selectedAnswer = clickedButton.getText(); // Get the text of the clicked button (the answer)
    	
    	// Check if the selected answer is correct
    	if (currentQuestion.getCorrectAnswer().equals(selectedAnswer)) {
    		feedbackLabel.setText("Correct!"); 
    		feedbackLabel.setStyle("-fx-text-fill: green;");
    		score += 10; // Increase score by 10 for correct answer
    	} else { 
    		feedbackLabel.setText("Wrong!");
    		feedbackLabel.setStyle("-fx-text-fill: red;");
    		score -= 5; // Decrease score by 5 for wrong answer
    	}
    	
    	updateScoreLabel(); // Update the score display
    	disableAnswerButtons(); // Disable answer buttons to prevent further clicks
    	
    	// Create a PauseTransition that waits 2 seconds before loading the next question
    	PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(2));
        pause.setOnFinished(e -> loadNextQuestion()); // Load the next question after the pause
        pause.play(); // Play the pause transition
    }
 
    
    /**
     * This method is triggered when the "New Game" button is clicked.
     * It resets the score, loads the questions again, and starts a new game.
     */
    @FXML
    void newGameButton(ActionEvent event) {
    	score = 0; // Reset score to 0
    	questionBank.loadQuestionsFromFile("trivia.txt"); // Reload questions from the file
    	feedbackLabel.setText(""); // Clear feedback message
    	updateScoreLabel();
    	loadNextQuestion();
    }

    
    /**
     * This method is triggered when the "End Game" button is clicked.
     * It ends the game, displays the final score, and disables the answer buttons.
     */
    @FXML
    void endGameButton(ActionEvent event) {
    	feedbackLabel.setText("Game Over! Final Score: " + score); //Display game over message with final score
    	disableAnswerButtons(); //Disable the answer buttons at the end of the game.
    }

    
    /**
     * This method loads the next question from the question bank.
     * It displays the question and its answers, and enables the answer buttons.
     * If there are no more questions, it ends the game and displays the final score.
     */
    private void loadNextQuestion() {
    	if (questionBank.hasMoreQuestions()) {
    		currentQuestion = questionBank.getNextQuestion(); // Get the next question from the question bank
    		questionLabel.setText(currentQuestion.getQuestionText()); // Display the question text
    		
    		questionLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: blue; -fx-font-family: Arial;");
    		
    		// Get the list of answers and display them on the answer buttons
    		ArrayList<String> answers = currentQuestion.getAnswers();
    		answerButton1.setText(answers.get(0));
    		answerButton2.setText(answers.get(1));
    		answerButton3.setText(answers.get(2));
    		answerButton4.setText(answers.get(3));
    		
    		feedbackLabel.setText(""); // Clear the feedback label before displaying the next question
    		
    		enableAnswerButtons(); //Enable the answer buttons so the player can click them
    	} else {
    		// Display "No more questions" message and final score.
    		feedbackLabel.setText("No more questions! Final Score: " + score);
    		disableAnswerButtons(); // Disable the answer buttons as the game is over
    	}
    }
    
    /**
     * This method updates the score display on the scoreLabel.
     */
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }
    
    
    /**
     * This method disables all the answer buttons to prevent the player from clicking them.
     */
    private void disableAnswerButtons() {
        answerButton1.setDisable(true);
        answerButton2.setDisable(true);
        answerButton3.setDisable(true);
        answerButton4.setDisable(true);
    }
    
    /**
     * This method enables all the answer buttons so the player can click on them.
     */
    private void enableAnswerButtons() {
        answerButton1.setDisable(false);
        answerButton2.setDisable(false);
        answerButton3.setDisable(false);
        answerButton4.setDisable(false);
    }

}
