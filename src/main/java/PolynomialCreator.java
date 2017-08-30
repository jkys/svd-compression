import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math.analysis.solvers.LaguerreSolver;
import org.apache.commons.math.complex.Complex;

public class PolynomialCreator {

    private double[] poly;

    public PolynomialCreator(double[] poly) {
        this.poly = poly;
    }

    private PolynomialCreator multi(PolynomialCreator multiplicand) {
        double[] result = new double[poly.length + multiplicand.poly.length - 1];

        for (int i = 0; i < poly.length; i++) {
            for (int j = 0; j < multiplicand.poly.length; j++) {
                result[i + j] += poly[i] * multiplicand.poly[j];
            }
        }
        return new PolynomialCreator(result);
    }

    public PolynomialCreator multiply(PolynomialCreator multiplicand) {
        return multi(multiplicand);
    }

    public double[] getRoots() throws FunctionEvaluationException, ConvergenceException {
        PolynomialFunction x = new PolynomialFunction(this.poly);
        Complex[] root = new LaguerreSolver().solveAll(this.poly, 0);

        double[] roots = new double[root.length];

        for (int i = 0, rootLength = root.length; i < rootLength; i++) {
            roots[i] = root[i].getReal();
        }

        return roots;
    }

    public double[] getPoly() {
        return this.poly;
    }

    public double[] getTruePoly(double det) {
        double[] truePoly = this.poly;
        truePoly[0] = det;
        return truePoly;
    }
}
