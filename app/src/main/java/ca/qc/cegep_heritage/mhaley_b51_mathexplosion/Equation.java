package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import java.util.Arrays;

/**
 * Created by max on 05/11/17.
 */

public class Equation {

    private String equation;
    private int answer;

    Equation(String eq) {
        equation = eq;
        answer = solveEquation();
    }

    String getEquation() {
        return equation;
    }

    public boolean checkIsAnswer(int userAnswer) {
        return userAnswer == answer;
    }

    private int solveEquation() {
        String[] equationArray = equation.split("");
        equationArray = Arrays.copyOfRange(equationArray, 1, equationArray.length);

        int solution = 0;

        switch (equationArray[1]) {
            case "+":
                solution = Integer.parseInt(equationArray[0]) + Integer.parseInt(equationArray[2]);
                break;
            case "-":
                solution = Integer.parseInt(equationArray[0]) - Integer.parseInt(equationArray[2]);
                break;
            case "*":
                solution = Integer.parseInt(equationArray[0]) * Integer.parseInt(equationArray[2]);
                break;
            case "/":
                solution = Integer.parseInt(equationArray[0]) / Integer.parseInt(equationArray[2]);
                break;
        }
        if (equationArray.length != 3) {
            switch (equationArray[3]) {
                case "+":
                    solution += Integer.parseInt(equationArray[4]);
                    break;
                case "-":
                    solution -= Integer.parseInt(equationArray[4]);
                    break;
                case "*":
                    solution *= Integer.parseInt(equationArray[4]);
                    break;
                case "/":
                    solution /= Integer.parseInt(equationArray[4]);
                    break;
            }
        }

        return solution;
    }
}

