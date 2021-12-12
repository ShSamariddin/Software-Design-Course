package states;

import tokenizer.NumberToken;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class NumberState extends State {
    public NumberState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public Token createToken() {
        char ch = tokenizer.currentChar();
        StringBuilder number = new StringBuilder();

        while (Character.isDigit(ch)) {
            number.append(ch);
            ch = tokenizer.nextChar();
        }

        return new NumberToken(number.toString());
    }
}
