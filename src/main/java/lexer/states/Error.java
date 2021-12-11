package lexer.states;

public class Error extends AbstractState {
    @Override
    public State handleCharacter(char c) {
        throw new UnsupportedOperationException("TODO");
    }
}
