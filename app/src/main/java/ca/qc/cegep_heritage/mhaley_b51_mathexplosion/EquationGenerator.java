package ca.qc.cegep_heritage.mhaley_b51_mathexplosion; /**
 * Created by max on 05/11/17.
 */
import android.support.annotation.NonNull;

import java.util.Random;
import java.util.Arrays;

final class EquationGenerator {

    @NonNull
    static Equation generateEquation(String operators, int operations) {
        Random randomNumberGenerator = new Random();
        String[] ops;

        if (operators.equals("all")) {
            ops = new String[] {"+", "-", "*", "/"};
        } else {
            ops = operators.split("");
            ops = Arrays.copyOfRange(ops, 1, ops.length);
        }

        StringBuilder equationStringBuilder = new StringBuilder("");

        for (int i = 0; i <= operations; i++) {
            equationStringBuilder.append(randomNumberGenerator.nextInt(10 + 1 - 1));

            equationStringBuilder.append(ops[randomNumberGenerator
                    .nextInt(ops.length)]);
        }
        equationStringBuilder.deleteCharAt(equationStringBuilder.length() - 1);

        return new Equation(equationStringBuilder.toString());
    }
}
