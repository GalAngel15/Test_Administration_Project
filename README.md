# Test Administration Project

This project is a Java-based application for managing and administering tests, designed using the **Model-View-Controller (MVC)** architecture. It supports both students and administrators by providing functionalities for test creation, test-taking, and grading.

## Key Features

- **MVC Architecture**: 
  - **Model**: Manages the data and logic for tests, questions, and answers. Classes such as `Questions.java`, `Test.java`, and `Answers.java` handle core functionalities.
  - **View**: Built with Java **Swing**, providing a user interface for both students and administrators.
  - **Controller**: Manages user interactions, controlling the flow between the model and view. Main controllers include `Controller.java` and `MainFX.java`.

- **Test Creation and Administration**:
  - Administrators can create new tests, specifying both multiple-choice (`ChoiceQuestion.java`) and open-ended questions (`OpenQuestion.java`).
  - Each test is organized into sets (`Set.java`) to group questions logically.

- **Answer Submission**:
  - Students can submit answers to both question types. The system handles automatic grading for multiple-choice questions and allows manual grading for open-ended questions.

- **Grading and Reports**:
  - Administrators can manually review open-ended questions and provide grades.
  - After tests are graded, students can access their results and feedback through the system.

- **Data Persistence**:
  - The system uses serialized data structures to store tests, questions, and answers.

## Technologies Used

- **Java**: Core language for the application logic.
- **Swing**: Used for building the graphical user interface (GUI).
- **JUnit**: Utilized for testing the components of the application.
- **MVC Design Pattern**: Ensures a clean separation between data (model), user interface (view), and control logic (controller).

## System Components

- **Model**:
  - Core classes: `Test.java`, `Questions.java`, `Answers.java`, `Set.java`.
  - Handles the logic and storage of test questions, answers, and grading data.

- **View**:
  - Provides the interface for students and administrators to interact with the system.
  - Core file: `View.java`.

- **Controller**:
  - Manages the interaction between users and the system.
  - Core files: `Controller.java`, `MainFX.java`.

## Installation and Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/GalAngel15/Test_Administration_Project.git
    ```

2. **Open the project** in your preferred Java IDE (e.g., Eclipse or IntelliJ).

3. **Run the application**:
   - The entry point is likely in `MainFX.java` or `Controller.java`. Run the appropriate file to start the application.

4. **Manage tests**:
   - Use the administrator panel to create and manage tests.
   - Students can log in to take tests and view results.
