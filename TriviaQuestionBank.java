import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TriviaQuestionBank{
	private ArrayList<TriviaQuestion> questions; // list to store all the questions
	private ArrayList<TriviaQuestion> usedQuestions; // list to store questions that have been used

	
	/**
    * Constructor that initializes the question lists.
    */
	public TriviaQuestionBank() {
		questions = new ArrayList<>(); //Hold the questions to be asked
		usedQuestions = new ArrayList<>(); //Store the questions that have already been used
	}
	
	
	/**
     * Adds a new question to the bank of questions.
     */
	public void addQuestion(TriviaQuestion question) {
		questions.add(question);
	}
	
	/**
     * Shuffles the list of questions so that they appear in a random order.
     */
	public void shuffleQuestions() {
		Collections.shuffle(questions); // Randomizes the order of the questions in the list
	}
	
	/**
     * Retrieves the next question from the list, removes it from the available questions, 
     * and adds it to the list of used questions. 
     * If there are no more questions left, it returns null.
     */
	public TriviaQuestion getNextQuestion() {
		if (!questions.isEmpty()) {
			// Remove the first question from the list, add it to the used questions list, and return it
			TriviaQuestion question = questions.remove(0);
			usedQuestions.add(question); // Keeps track of the questions that have been used
			return question;
		}
		
		return null; // If no questions are left, return null
	}	
	
	
	/**
     * Checks if there are any more questions available in the bank.
     */
	public boolean hasMoreQuestions() {
		return !questions.isEmpty();
	}
	
	
	/**
     * Loads trivia questions from a file. Each question is read along with its correct answer 
     * and a list of wrong answers.
     */
	public void loadQuestionsFromFile(String filePath) {
	    try {
	    	// Create a Scanner object to read the file line by line
	        Scanner input = new Scanner(new File(filePath));
	        
	        // Loop through the file, reading the question, correct answer, and wrong answers
	        while (input.hasNextLine()) {
	            String questionText = input.nextLine().trim();  // Read the question text
	            if (questionText.isEmpty()) {
	                continue; // Skip empty lines in the file
	            }

	            // Read the correct answer, if it exists
	            String correctAnswer = null;
	            if (input.hasNextLine()) {
	                correctAnswer = input.nextLine().trim();
	                if (correctAnswer.isEmpty()) {
	                    continue; // Skip if the correct answer is empty  
	                }
	            }
	            
	            // Read the wrong answers
	            ArrayList<String> wrongAnswers = new ArrayList<>();
	            if (input.hasNextLine()) {
	                String wrongAnswer1 = input.nextLine().trim();
	                if (!wrongAnswer1.isEmpty()) {
	                    wrongAnswers.add(wrongAnswer1); // Add to the list of wrong answers
	                }
	            }

	            if (input.hasNextLine()) {
	                String wrongAnswer2 = input.nextLine().trim();
	                if (!wrongAnswer2.isEmpty()) {
	                    wrongAnswers.add(wrongAnswer2); // Add to the list of wrong answers
	                }
	            }

	            if (input.hasNextLine()) {
	                String wrongAnswer3 = input.nextLine().trim();
	                if (!wrongAnswer3.isEmpty()) {
	                    wrongAnswers.add(wrongAnswer3); // Add to the list of wrong answers
	                }
	            }

	            // Create a TriviaQuestion object using the question text, correct answer, and wrong answers
	            TriviaQuestion question = new TriviaQuestion(questionText, correctAnswer, wrongAnswers);
	            
	            // Add the question to the question bank.
	            addQuestion(question);
	        }

	        //Shuffle the questions so that they appear in random order when asked
	        shuffleQuestions();

	        input.close();// Close the file scanner after reading all lines
	        
	    } catch (IOException e) {
	        System.err.println("Error reading questions from file: " + e.getMessage()); // Print an error message if there is an issue with reading the file
	    } catch (Exception e) {
	        System.err.println("Unexpected error: " + e.getMessage()); // Print an error message for any other unexpected exceptions
	    }
	}
}