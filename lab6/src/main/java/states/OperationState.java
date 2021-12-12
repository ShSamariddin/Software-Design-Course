package states;

import tokenizer.OperationToken;
import tokenizer.Token;
import tokenizer.Tokenizer;

public class OperationState extends State {
    public OperationState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public Token createToken() {
        final char ch = tokenizer.currentChar();
        tokenizer.nextChar();

        Token.TokenType type = switch (ch) {
            case '-' -> Token.TokenType.MINUS;
            case '/' -> Token.TokenType.DIV;
            case '+' -> Token.TokenType.PLUS;
            case '*' -> Token.TokenType.MUL;
            default -> throw new IllegalArgumentException("Illegal char '" + ch);
        };

        return new OperationToken(type);
    }
}