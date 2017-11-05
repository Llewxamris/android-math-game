package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

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

    private static int solveEquation() {
        return 0;
    }
}
