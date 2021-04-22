import ValidationCheck.InvalidExpressionException;
import Interpreting.Interpreter;
import Parsing.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            Parser parser = new Parser(line);
            try{
                interpreter.interpret(parser.parse());
            }
            catch (UnsupportedOperationException e) {
                System.out.println("Invalid expression: Unsupported operation");
            }
            catch (InvalidExpressionException e){
                System.out.println("Invalid expression: " + e.getMessage());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        interpreter.printResult();

    }
}
