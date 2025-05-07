# Order Management System (MVC Pattern)

This is a simple Order Management System implemented in Java using the Model-View-Controller (MVC) design pattern.

## 📁 Project Structure

- **controller/OrderController.java**: Contains business logic and interaction between the model and the view.
- **view/OrderView.java**: Handles all input/output with the user.
- **model/Meal.java & Order.java**: Represents core data structures.
- **utils/CircuitBreaker.java**: Implements a basic circuit breaker for reliability.
- **main/Main.java**: Entry point of the application.

## 💡 Features

- Add, update, delete, and search orders
- Menu-driven console interface
- Simple circuit breaker pattern for resilience
- Password-protected access

## 🛠 Requirements

- Java 11 or above
- VS Code (with Java Extension Pack) or any Java-compatible IDE

## ▶️ How to Run

1. **Clone or copy the project folder.**
2. **Open the folder in Visual Studio Code.**
3. **Ensure Java is installed and environment variables (`java`, `javac`) are working.**
4. **Run the project:**

   - Use the VS Code run button in `Main.java`, or:
   - Compile from terminal:
     ```bash
     javac main/Main.java
     java main.Main
     ```

## 🧪 Running Unit Tests

Unit tests are provided in the `test` folder. You can run them using any JUnit-compatible framework in your IDE or CLI.

```bash
javac -cp .;junit-platform-console-standalone.jar test/OrderTest.java
java -jar junit-platform-console-standalone.jar -cp . --select-class test.OrderTest
