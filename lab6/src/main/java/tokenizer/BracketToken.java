package tokenizer;

import visitors.TokenVisitor;

public class BracketToken implements Token {
    private final TokenType type;

    public BracketToken(TokenType type) {
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

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
