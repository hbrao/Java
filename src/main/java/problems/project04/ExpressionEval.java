package problems.project04;

import java.math.*;
import java.util.*;
public class ExpressionEval {
    public static void main(String[] args) {
        Deque<BigDecimal> stk = new LinkedList();

        String expression = "12+3+4/";
        for(Character c : expression.toCharArray()) {
            if ( Character.isDigit(c) ) {
                stk.push(new BigDecimal(Double.parseDouble(String.valueOf(c))));
            } else {
                BigDecimal result = null;
                BigDecimal val1 = stk.pop();
                BigDecimal val2 = stk.pop();
                switch (c) {
                    case '+':
                        result = val1.add(val2);
                        break;
                    case '-':
                        result = val1.subtract(val2);
                        break;
                    case '*':
                        result = val1.multiply(val2);
                        break;
                    case '/':
                        result = val1.divide(val2, RoundingMode.HALF_UP);
                        break;
                }
                stk.push(result);
            }
        }
        System.out.println(stk.pop());
    }
}
