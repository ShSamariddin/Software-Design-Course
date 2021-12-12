package tokenizer;

import visitors.TokenVisitor;

public class OperationToken implements Token {
    private final TokenType type;

    public OperationToken(TokenType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public TokenType getTokenType() {
        return type;
    }

    public long perform(long a, long b) {
        return switch (type) {
            case DIV -> a / b;
            case MUL -> a * b;
            case MINUS -> a - b;
            case PLUS -> a + b;
            default -> throw new RuntimeException();
        };
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
