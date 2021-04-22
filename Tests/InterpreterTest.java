package Tests;

import Interpreting.Interpreter;
import Parsing.Parser;
import org.junit.jupiter.api.Test;


public class InterpreterTest {

    @Test
    public void test1(){

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser();

        try {
            interpreter.interpret(parser.parse("a = 1"));
            assert interpreter.printResult().equals("(a=1)");

            interpreter.interpret(parser.parse("a = 1 + 1"));
            assert interpreter.printResult().equals("(a=2)");

            interpreter.interpret(parser.parse("a = 1 * 1"));
            assert interpreter.printResult().equals("(a=1)");

            interpreter.interpret(parser.parse("a = 1 + 1 + 1"));
            assert interpreter.printResult().equals("(a=3)");

            interpreter.interpret(parser.parse("a = 1 * 2 * 3"));
            assert interpreter.printResult().equals("(a=6)");

            interpreter.interpret(parser.parse("a = 1 * 2 + 1 * 2"));
            assert interpreter.printResult().equals("(a=4)");

            interpreter.interpret(parser.parse("a = 1 + 2 * 1 + 2"));
            assert interpreter.printResult().equals("(a=5)");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test2(){

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser();

        try {
            interpreter.interpret(parser.parse("a = 1"));
            interpreter.interpret(parser.parse("b = a"));
            assert interpreter.printResult().equals("(a=1,b=1)");

            interpreter.interpret(parser.parse("a = ++a"));
            assert interpreter.printResult().equals("(a=2,b=1)");

            interpreter.interpret(parser.parse("a = 1"));
            interpreter.interpret(parser.parse("a = a++"));
            assert interpreter.printResult().equals("(a=1,b=1)");

            interpreter.interpret(parser.parse("a += b"));
            assert interpreter.printResult().equals("(a=2,b=1)");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test3(){

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser();

        try {
            interpreter.interpret(parser.parse("i = 0"));
            assert interpreter.printResult().equals("(i=0)");

            interpreter.interpret(parser.parse("j = ++i"));
            assert interpreter.printResult().equals("(i=1,j=1)");

            interpreter.interpret(parser.parse("x = i++ + 5"));
            assert interpreter.printResult().equals("(i=2,j=1,x=6)");

            interpreter.interpret(parser.parse("y = 5 + 3 * 10"));
            assert interpreter.printResult().equals("(i=2,j=1,x=6,y=35)");

            interpreter.interpret(parser.parse("i += y"));
            assert interpreter.printResult().equals("(i=37,j=1,x=6,y=35)");

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
