# Trivia Game in JavaFX

## Description
This is a JavaFX application implementing a Trivia game where players answer multiple-choice questions on various topics. Each question has four possible answers, with only one being correct. The game operates as follows:

- Players select an answer from four options displayed for each question.
- A correct answer awards 10 points; an incorrect answer deducts 5 points.
- Questions are randomly selected from a text file and presented one at a time.
- The same question is never repeated during a single game session.
- The game ends when the player chooses to stop or when all questions are exhausted.
- The cumulative score is tracked and displayed at the end of the game.

## Features
- **Graphical Interface**: Built with JavaFX, featuring a question label, four answer buttons, a score display, and feedback messages.
- **Question Bank**: Loads trivia questions from a `trivia.txt` file, with each question followed by a correct answer and three incorrect options.
- **Game Controls**: Includes "New Game" and "End Game" buttons for starting a new session or ending the current one.
- **Feedback**: Provides immediate visual feedback (green for correct, red for incorrect) after each answer.
- **Randomized Answers**: Answer options are shuffled for each question to ensure variety.

## Project Structure
The project follows Object-Oriented Programming (OOP) principles with the following key classes:
- `Trivia`: The main application entry point, loading the FXML interface.
- `TriviaController`: Manages user interactions, game logic, score updates, and UI updates.
- `TriviaQuestion`: Represents a single trivia question with its text, correct answer, and answer options.
- `TriviaQuestionBank`: Handles the loading, shuffling, and retrieval of questions from the file.
