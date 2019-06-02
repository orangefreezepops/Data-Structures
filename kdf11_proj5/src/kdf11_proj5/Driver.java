import java.util.*;

public class Driver {
	public static void main(String[] args) {
		Map<String, Double> vars = new HashMap<>();
		// you can change or add more variables here.
		vars.put("x", 10.0);
		vars.put("y", 27.0);
                vars.put("a", 3.0);
		vars.put("b", 6.0);

		Expression expr = Expression.quickParse("4*x + y/9 + 12");

		System.out.println("toString:        " + expr);
		System.out.println("toPostfix:       " + expr.toPostfix());
		System.out.println("evaluate:        " + expr.evaluate(vars));
		System.out.println("reciprocal:      " + expr.reciprocal());
		System.out.println("reciprocal(num): " + Expression.Number(7).reciprocal());
		System.out.println("reciprocal(div): " + Expression.quickParse("x / 10").reciprocal());
		System.out.println("getVariables:    " + expr.getVariables());

		Expression mean = Expression.geometricMean(new double[]{4, 9, 3, 7, 6});
		System.out.println("geometricMean:   " + mean);
		System.out.println("it evalutes to:  " + mean.evaluate(vars));

		System.out.println("===================================================");
		System.out.println("NOW TEST MORE THOROUGHLY!!!");
                
                
                //my tests
                
		//testing power
                Expression test1 = Expression.quickParse("2*5 - a^4 + 7*b");
                System.out.println("Keegan test 1");
		System.out.println("toString:        " + test1);
		System.out.println("toPostfix:       " + test1.toPostfix());
		System.out.println("evaluate:        " + test1.evaluate(vars));
                System.out.println("reciprocal:      " + test1.reciprocal());
                System.out.println("getVariables:    " + test1.getVariables());
                
                System.out.println("Keegan geometric mean test: ");
                Expression test2 = Expression.geometricMean(new double[]{3,2,8,7,9,4,2,1,6});
		System.out.println("geometricMean:   " + test2);
		System.out.println("it evalutes to:  " + test2.evaluate(vars));
	}
}