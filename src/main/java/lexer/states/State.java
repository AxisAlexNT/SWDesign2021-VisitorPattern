package lexer.states;

public interface State {
    State handleCharacter(char c);
}
