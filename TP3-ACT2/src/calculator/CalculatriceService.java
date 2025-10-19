package calculator;

public class CalculatriceService {
    public static double calculer(Operation op) {
        switch (op.getOperator()) {
            case "+":
                return op.getA() + op.getB();
            case "-":
                return op.getA() - op.getB();
            case "*":
                return op.getA() * op.getB();
            case "/":
                if (op.getB() == 0) {
                    System.out.println("Erreur : Division par zéro !");
                    return Double.NaN;
                }
                return op.getA() / op.getB();
            default:
                System.out.println("Erreur : Opérateur non valide.");
                return Double.NaN;
        }
    }
}
