
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;
import java.util.function.*;

class Expression {

    private String _type;
    private String _value;
    private Expression _left, _right;

    private Expression(String type, String value) {
        this(type, value, null, null);
    }

    private Expression(String type, String value, Expression left, Expression right) {
        _type = type;
        _value = value;
        _left = left;
        _right = right;
    }

    /**
     * Creates an operator expression.
     */
    public static Expression Operator(Expression left, String operator, Expression right) {
        return new Expression("Operator", operator, left, right);
    }

    /**
     * Creates a number expression.
     */
    public static Expression Number(double value) {
        return new Expression("Number", Double.toString(value));
    }

    /**
     * Creates a variable expression.
     */
    public static Expression Variable(String name) {
        return new Expression("Variable", name);
    }

    /**
     * Very quick-and-dirty expression parser; doesn't really do any error
     * checking. But it's enough to build an Expression from a
     * (known-to-be-correct) String.
     */
    public static Expression quickParse(String input) {
        StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(input));
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        Stack<Character> operators = new Stack<>();
        Stack<Expression> operands = new Stack<>();
        try {
            tokenizer.nextToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (tokenizer.ttype != StreamTokenizer.TT_EOF) {
            int prec = 2;
            switch (tokenizer.ttype) {
                case StreamTokenizer.TT_NUMBER:
                    operands.push(Number(tokenizer.nval));
                    break;
                case StreamTokenizer.TT_WORD:
                    operands.push(Variable(tokenizer.sval));
                    break;
                case '^':
                case '(':
                    operators.push((char) tokenizer.ttype);
                    break;
                case ')':
                    while (operators.peek() != '(') {
                        poperator(operators, operands);
                    }
                    operators.pop();
                    break;
                case '+':
                case '-':
                    prec = 1; // fall thru
                case '*':
                case '/':
                    while (!operators.empty()) {
                        char top = operators.peek();
                        int topPrec = (top == '^') ? 3 : (top == '*' || top == '/') ? 2 : 1;
                        if (top == '(' || topPrec < prec) {
                            break;
                        }
                        poperator(operators, operands);
                    }
                    operators.push((char) tokenizer.ttype);
                    break;
                default:
                    throw new RuntimeException("wat");
            }
            try {
                tokenizer.nextToken();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        while (!operators.empty()) {
            poperator(operators, operands);
        }
        return operands.pop();
    }

    private static void poperator(Stack<Character> operators, Stack<Expression> operands) {
        Expression r = operands.pop();
        Expression l = operands.pop();
        operands.push(Operator(l, operators.pop() + "", r));
    }

    // These can be used to quickly check if an Expression is an Operator, Number, or Variable.
    public boolean isOperator() {
        return _type.equals("Operator");
    }

    public boolean isNumber() {
        return _type.equals("Number");
    }

    public boolean isVariable() {
        return _type.equals("Variable");
    }

    /**
     * For Numbers, converts the _value to a double and returns it. Will crash
     * for non-Numbers.
     */
    private double getNumberValue() {
        return Double.parseDouble(_value);
    }

    /**
     * Recursively clones an entire Expression tree. Note how this method works:
     * operators are the recursive case, and numbers and variables are base
     * cases.
     */
    public Expression clone() {
        if (this.isOperator()) {
            return Expression.Operator(_left.clone(), _value, _right.clone());
        } else if (this.isVariable()) {
            return Expression.Variable(_value);
        } else {
            return Expression.Number(getNumberValue());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Converts this expression to an infix expression representation.
     */
    public String toString() {
        String result = null;
        if (this.isNumber()) {
            result = _value;
        }
        if (this.isVariable()) {
            result = _value;
        }
        if (this.isOperator()) {
            result = "(" + _left.toString() + " " + _value + " " + _right.toString() + ")";
        }
        return result;
    }

    /**
     * Converts this expression to a postfix expression representation.
     */
    public String toPostfix() {
        // TODO
        String result = null;
        if (this.isNumber()) {
            result = _value;
        }
        if (this.isVariable()) {
            result = _value;
        }
        if (this.isOperator()) {
            result = _left.toPostfix() + " " + _right.toPostfix() + " " + _value;
        }
        return result;
    }

    /**
     * Given the variables map (which tells what values each variable has),
     * evaluates the expression and returns the computed value.
     */
    public double evaluate(Map<String, Double> variables) {
        // TODO
        double result = 0;
        if (this.isNumber()) {
            //get the double value of the number string
            result = this.getNumberValue();
        }
        if (this.isVariable()) {
            //get the variable
            if (variables.containsKey(_value)) {
                result = variables.get(_value);
            } else {
                //or throw an exception if its not valid 
                throw new ExpressionError("We cannot complete the operation: " + _value);
            }
        }
        if (this.isOperator()) {
            //depending on the operator recursively call the leaves of the tree
            //performing the designated operation of them
            if (_value.equals("+")) {
                result = _left.evaluate(variables) + _right.evaluate(variables);
            } else if (_value.equals("-")) {
                result = _left.evaluate(variables) - _right.evaluate(variables);
            } else if (_value.equals("*")) {
                result = _left.evaluate(variables) * _right.evaluate(variables);
            } else if (_value.equals("/")) {
                result = _left.evaluate(variables) / _right.evaluate(variables);
            } else if (_value.equals("^")) {
                result = Math.pow(_left.evaluate(variables), _right.evaluate(variables));
            }
        }
        return result;
    }

    /**
     * Creates a new Expression that is the reciprocal of this one.
     */
    public Expression reciprocal() {
        // TODO
        Expression reciprocal;
        if (this.isNumber()) {
            //the reciprocal of a number is 1/(that number)
            reciprocal = Number(1 / this.getNumberValue());
        } else if (_value.equals("/")) {
            //if its a division just do the operation but flip the left and right
            reciprocal = Operator(_right.clone(), "/", _left.clone());
        } else {
            //otherwise it will be 1/(the variable)
            reciprocal = Operator(Number(1), "/", this.clone());
        }
        return reciprocal;
    }

    /**
     * Gets a set of all variables which appear in this expression.
     */
    public Set<String> getVariables() {
        // TODO
        Set<String> variables = new HashSet<>();
        //calling and returning my helper method
        return findVariables(variables);
    }

    /**
     * This is the recursive helper function. It iterates through the set of
     * variables and returns every unique variable back to getVariables()
     *
     * @param variables set passed from the getVariables() method
     * @return void
     */
    private Set<String> findVariables(Set<String> variables) {
        //if its an operator check the left and right leaves and add the variables
        //to the set if its a variable
        //ignoring the number case because numbers dont have leaves and are dead ends
        if (this.isOperator()) {
            _left.findVariables(variables).toString();
            if (this.isVariable()) {
                variables.add(_value);
            }
            _right.findVariables(variables).toString();
            if (this.isVariable()) {
                variables.add(_value);
            }
            //otherwise just add the variable to the set
        } else if (this.isVariable()){
            variables.add(_value);
        }
        return variables;
    }

    /**
     * Constructs a new Expression of the form: (numbers[0] * numbers[1] * ...
     * numbers[n-1]) ^ (1 / n) and returns it.
     */
    public static Expression geometricMean(double[] numbers) {
        // TODO
        int i = 0;
        Expression size = Number(numbers.length);
        //setting the results as the multiplication of the first two indices 
        Expression result = Operator(Number(numbers[i]), "*", Number(numbers[i+1]));
        //iterating the rest of the way using the result as the first
        //expressing in the Operator() method and the third index after i as the second
        for (i = 0; i + 2 < numbers.length; i++) {
            //System.out.println("iteration: " + i);
            result = Operator(result, "*", Number(numbers[i+2]));
            //System.out.println(result);
        }
        //finally taking the result to the power of the reciprocal of the size
        //of the set
        result = Operator(result, "^", size.reciprocal());
        return result;
    }

//    private static void geometricHelper(double [] numbers, int i){
//        Operator(Number(numbers[i]), "*", Number(numbers[i+1]));
//    }
    /**
     * EXTRA CREDIT: converts this expression to an infix expression
     * representation, but only places parentheses where needed to override the
     * order of operations.
     */
    public String toNiceString() {
        // TODO
        return "<not implemented>";
    }
}
