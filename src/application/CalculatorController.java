package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalculatorController {
// Assigning components
	@FXML
	Button DEL;
	@FXML
	Button LOG;
	@FXML
	Button AC;
	@FXML
	Button DIVIDE;
	@FXML
	Button MULTIPLY;
	@FXML
	Button ADDITION;
	@FXML
	Button SUBTRACT;
	@FXML
	Button DECIMAL;
	@FXML
	Button one;
	@FXML
	Button two;
	@FXML
	Button three;
	@FXML
	Button four;
	@FXML
	Button five;
	@FXML
	Button six;
	@FXML
	Button seven;
	@FXML
	Button eight;
	@FXML
	Button nine;
	@FXML
	Button zero;
	@FXML
	Label screen;
	@FXML
	Label calculations;

	double num1 = 0, num2 = 0, total = 0, multiplicand = 0;
	String operator = "", prevOperator = "", displayCalculations = "", currentVal = "", currentNumber = "";

	@FXML
	public void onNumberPressed(ActionEvent event) {
		Button numberBtn = (Button) event.getSource();
		currentVal = numberBtn.getText(); // Getting the single digit that is pressed.

		// Checking for an already existing decimal point.
		if (!currentNumber.contains(".") || !currentVal.equals(".")) {
			currentNumber += currentVal; // Concatenating the pressed digits into a number.
			displayCalculations += currentVal; // Storing the digits pressed as the user presses them.
			calculations.setText(displayCalculations); // Displaying the calculations the user is doing.
		}

		num1 = Double.parseDouble(currentNumber);

	}

	@FXML
	public void onOperatorPressed(ActionEvent event) {
		Button operatorBtn = (Button) event.getSource();
		operator = operatorBtn.getText(); // Getting the current selected operator.

		displayCalculations += operator; // Adding the operator to the calculations display.
		calculations.setText(displayCalculations);

		if (prevOperator.isEmpty() || prevOperator.equals(operator)) {
			if (operator.equals("+")) {
				total += num1;
			} else if (operator.equals("-")) {
				// The check below ensures that if the first value entered is to be subtracted from, it doesn't become a negative.
				if (total == 0) {
					total += num1;
				} else {
					total -= num1;
				}
			} else if (operator.equals(MULTIPLY.getText())) {
				multiplicand = num1;
				total = multiplicand * num2;
			}
		} else { // If the new operator is different to the previous one, we want to complete the last calculation with the right operator before moving on.
			if (prevOperator.equals("+")) {
				total += num1;
			} else if (prevOperator.equals("-")) {
				total -= num1;
			} else if (prevOperator.equals(MULTIPLY.getText())) {
				multiplicand = num1;
				total = multiplicand * num2;
			}
		}

		screen.setText(String.valueOf(total));

		currentNumber = ""; // Resets the value of the current number;
		currentVal = ""; // Resets the value of the current value;
		prevOperator = operator;
		operator = "";

		// Check for multiplication.
		if (total == 0) {
			num2 = num1;
		} else {
			num2 = total;
		}

	}

	@FXML
	public void onActionPressed(ActionEvent event) {
		Button actionBtn = (Button) event.getSource();
		String action = actionBtn.getText();
		if (action.equals("AC")) {
			// Reset all previous values, ready for the next calculation.
			currentNumber = "";
			currentVal = "";
			prevOperator = operator;
			operator = "";

			total = 0;
			num1 = 0;
			num2 = 0;
			displayCalculations = "";

			screen.setText(String.valueOf(total));
			calculations.setText(displayCalculations);
		}
	}

	@FXML
	public void onResultPressed() {
		num2 = Double.parseDouble(currentNumber);

		if (prevOperator.isEmpty() || prevOperator.equals(operator)) {
			if (operator.equals("+")) {
				total += num1;
			} else if (operator.equals("-")) {
				total -= num1;
			}  else if (operator.equals(MULTIPLY.getText())) {
				multiplicand = num1;
				total = multiplicand * num2;
			}
		} else {
			if (prevOperator.equals("+")) {
				total += num1;
			} else if (prevOperator.equals("-")) {
				total -= num1;
			} else if (prevOperator.equals(MULTIPLY.getText())) {
				multiplicand = num1;
				total = multiplicand * num2;
			}
		}

		screen.setText(String.valueOf(total));

		// Reset all previous values, ready for the next calculation.
		currentNumber = "";
		currentVal = "";
		prevOperator = operator;
		operator = "";

		total = 0;
		num1 = 0;
		num2 = 0;
		displayCalculations = "";

	}

}
