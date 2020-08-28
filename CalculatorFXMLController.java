/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author connormcdonough
 */
public class CalculatorFXMLController implements Initializable {

    @FXML
    private Text textEquation;

    private String equation = "";

    private Stack<Double> stack;

    private Scanner scan;

    private int fontSize = 36;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scan = new Scanner(equation);
        stack = new Stack<Double>();
    }

    @FXML
    private void zeroB(ActionEvent event) {
        equation += "0";
        updateString();
    }

    @FXML
    private void oneB(ActionEvent event) {
        equation += "1";
        updateString();
    }

    @FXML
    private void twoB(ActionEvent event) {
        equation += "2";
        updateString();
    }

    @FXML
    private void threeB(ActionEvent event) {
        equation += "3";
        updateString();
    }

    @FXML
    private void plusB(ActionEvent event) {
        equation += "+";
        updateString();
    }

    @FXML
    private void periodB(ActionEvent event) {
        equation += ".";
        updateString();
    }

    @FXML
    private void fourB(ActionEvent event) {
        equation += "4";
        updateString();
    }

    @FXML
    private void fiveB(ActionEvent event) {
        equation += "5";
        updateString();
    }

    @FXML
    private void sixB(ActionEvent event) {
        equation += "6";
        updateString();
    }

    @FXML
    private void minusB(ActionEvent event) {
        equation += "-";
        updateString();
    }

    @FXML
    private void sevenB(ActionEvent event) {
        equation += "7";
        updateString();
    }

    @FXML
    private void eightB(ActionEvent event) {
        equation += "8";
        updateString();
    }

    @FXML
    private void nineB(ActionEvent event) {
        equation += "9";
        updateString();
    }

    @FXML
    private void timesB(ActionEvent event) {
        equation += "*";
        updateString();
    }

    @FXML
    private void clearB(ActionEvent event) {
        equation = "";
        fontSize = 36;
        updateString();
        textEquation.setText("0");

    }


    @FXML
    private void devidesB(ActionEvent event) {
        equation += "/";
        updateString();
    }

    @FXML
    private void spaceB(ActionEvent event) {
        equation += " ";
        updateString();
    }

    @FXML
    private void equalB(ActionEvent event) {
        try {
            boolean devideZero=false;
            scan = new Scanner(equation);
            String temp = "";
            double popOne = 0;
            double popTwo = 0;
            updateString();
            while (scan.hasNext()) {
                temp = scan.next();
                System.out.println("temp: " + temp);
                if (temp.equals("+")) {
                    popOne = stack.pop();
                    popTwo = stack.pop() + popOne;
                    stack.push(popTwo);

                } else if (temp.equals("-")) {
                    popOne = stack.pop();
                    System.out.println("popOne: "+ popOne );
                    popTwo = stack.pop()-popOne;
                    stack.push(popTwo);

                } else if (temp.equals("/")) {
                    popOne= stack.pop();
                    popTwo= stack.pop();
                    System.out.println("popOne: "+popOne);
                    System.out.println("popTwo: "+popTwo);
                    if(popOne != 0) {
                        stack.push(popTwo/popOne);
                    } else {
                        devideZero=true;
                        textEquation.setFont(Font.font("System", FontWeight.NORMAL, 20));
                        textEquation.setText("Error: cannot divide by 0");
                        stack.push(0.0);
                    }

                } else if (temp.equals("*")) {
                    popOne = stack.pop();
                    popTwo = stack.pop() * popOne;
                    stack.push(popTwo);

                } else {
                    stack.push(Double.parseDouble(temp));
                }

            }
            if(devideZero!=true) {
                equation = roundTwoDecimals(stack.pop()) + "";
                updateString(); 
            } else {
                stack.pop();
            }
            
        } catch (Exception ex) {
            textEquation.setFont(Font.font("System", FontWeight.NORMAL, 24));
            textEquation.setText("Error: invalid equation");
        }

    }

    public void updateString() {
        if (textEquation.getBoundsInLocal().getWidth() <= 210) {
            textEquation.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            System.out.println("w: " + textEquation.getBoundsInLocal().getWidth());
            textEquation.setText(equation);
        } else {
            System.out.println("here");
            fontSize = fontSize - 3;
            textEquation.setFont(Font.font("System", FontWeight.NORMAL, fontSize));
            textEquation.setText(equation);
        }

    }

    double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.#########");
        return Double.valueOf(twoDForm.format(d));
    }

}
