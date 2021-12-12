package tokenizer;

import visitors.TokenVisitor;

public class NumberToken implements Token {
    private final String value;

    public NumberToken(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getTokenType().toString() + '(' + value + ')';
    }

    @Override
    public TokenType getTokenType() {
        return TokenType.NUMBER;
    }

    public long getValue() {
        return Long.parseLong(value);
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
