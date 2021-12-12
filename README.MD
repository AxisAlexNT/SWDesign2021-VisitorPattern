# Homework 6: Visitor pattern

### Translate infix expressions to postfix form and evaluate them.

## What is implemented:

* Finite State Machine for lexer is implemented using **State** pattern;
* **Visitor** pattern is used for infix-to-postfix transform, AST printing and evaluating the whole result;
* In order not to restrict calculations to only numeric type, number tokens are parametrized and must
  extend `NumberToken`. The calculations of math operations over the real data type are *delegated* to
  the `PostfixEvaluator` interface (it might have been actually even simpler `BinaryOperator`).
* In this solution only the `LongToken` is implemented, but adding support, i.e., for `double` would require creating
  only the subclass of
  `NumberToken` and adding `DoubleEvaluator`.