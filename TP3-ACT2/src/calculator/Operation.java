package calculator;

import java.io.Serializable;

public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private double a, b;
    private String operator;

    public Operation(double a, double b, String operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public String getOperator() { return operator; }
}
