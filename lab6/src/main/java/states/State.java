package states;

import tokenizer.Token;
import tokenizer.Tokenizer;

public abstract class State {
    protected final Tokenizer tokenizer;

    public abstract Token createToken();

    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }


}
