package lexer.states;

public class Error implements State {
    @Override
    public State handleCharacter(char c) {
        throw new UnsupportedOperationException("TODO");
    }
}
