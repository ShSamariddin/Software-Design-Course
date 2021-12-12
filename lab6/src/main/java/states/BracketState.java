package states;

import tokenizer.BracketToken;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class BracketState extends State {
    public BracketState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public Token createToken() {
        char ch = tokenizer.currentChar();
        tokenizer.nextChar();

        Token.TokenType type = switch (ch) {
            case ')' -> Token.TokenType.RIGHT_BR;
            case '(' -> Token.TokenType.LEFT_BR;
            default -> throw new IllegalArgumentException("Illegal char:" + ch);
        };

        return new BracketToken(type);
    }
}