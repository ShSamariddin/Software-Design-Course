import tokenizer.Token;
import tokenizer.Tokenizer;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            if(args.length != 1) {
                throw new IllegalArgumentException("you need to input only expression");
            }
            String expression = args[0];
            Tokenizer tokenizer = new Tokenizer(expression);
            List<Token> tks = tokenizer.parse();
            ParserVisitor parserVisitor = new ParserVisitor();
            tks = parserVisitor.parse(tks);

            System.out.println("Polish notation: " + new PrintVisitor().walk(tks));
            System.out.println("Calc value: " + new CalcVisitor().calculate(tks));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}