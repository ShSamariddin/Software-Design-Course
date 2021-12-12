package tokenizer;

import visitors.TokenVisitor;

public interface Token {
    public enum TokenType {
        LEFT_BR,
        RIGHT_BR,
        PLUS,
        MINUS,
        DIV,
        MUL,
        NUMBER;

        public int getPriority() {
            return switch (this) {
                case DIV, MUL -> 2;
                case PLUS, MINUS -> 1;
                case LEFT_BR, RIGHT_BR -> 0;
                default -> -1;
            };
        }
    }

    String toString();

    TokenType getTokenType();

    void accept(TokenVisitor visitor);
}