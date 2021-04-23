package Tests;

import Interpreting.Interpreter;
import Interpreting.UndefinedVariableException;
import Parsing.*;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {


    @Test
    public void testInvalidExpresionExceptions(){

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser();

        try {
            interpreter.interpret(parser.parse("33dew3rj"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new IllegalVariableNameException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a"));

        }
        catch (Exception e){
            assert e.getMessage().equals(new MissingAssignmentExpressionException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = "));
        }
        catch (Exception e){
            assert e.getMessage().equals(new MissingAssignmentValueException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 2 2"));
            assert interpreter.printResult().equals(new MissingOperatorException().getMessage());
        }
        catch (Exception e){
            assert e.getMessage().equals(new MissingOperatorException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 2 +"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new MissingOperandException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 2 & 2"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UnsupportedOperatorException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 2 = b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new MultiAssignmentException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 2 += b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new MultiAssignmentException().getMessage());
        }

    }

    @Test
    public void testUndefinedVariableException(){

        Interpreter interpreter = new Interpreter();
        Parser parser = new Parser();

        try {
            interpreter.interpret(parser.parse("a = b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UndefinedVariableException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a = 1 + b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UndefinedVariableException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("a += b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UndefinedVariableException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("b += b"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UndefinedVariableException().getMessage());
        }

        try {
            interpreter.interpret(parser.parse("b = b++"));
        }
        catch (Exception e){
            assert e.getMessage().equals(new UndefinedVariableException().getMessage());
        }


    }
}
