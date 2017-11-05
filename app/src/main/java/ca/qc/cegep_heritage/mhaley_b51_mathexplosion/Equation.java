package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

/**
 * Created by max on 05/11/17.
 */

public class Equation {

    private String equation;
    private int answer;

    public Equation() {
        equation = "n/a";
        answer = -1;
    }

    public Equation(String eq) {
        equation = eq;
        answer = solveEquation();
    }

    public String getEquation() {
        return equation;
    }

    public boolean checkIsAnswer(int userAnswer) {
        return userAnswer == answer;
    }

    private static int solveEquation() {
        return 0;
    }
}
