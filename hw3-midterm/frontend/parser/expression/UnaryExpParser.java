package frontend.parser.expression;

import frontend.SyntaxError.ErrorType;
import frontend.SyntaxError.SyntaxErrorHandler;
import frontend.lexer.Token;
import frontend.lexer.TokenListIterator;
import frontend.lexer.TokenType;
import frontend.parser.funcparas.FuncRParams;
import frontend.parser.funcparas.FuncRParamsParser;

import java.util.ArrayList;

public class UnaryExpParser {
    private TokenListIterator tokenListIterator;

    public UnaryExpParser(TokenListIterator tokenListIterator) {
        this.tokenListIterator = tokenListIterator;
    }

    public UnaryExp parseUnaryExp() {
        Token unaryOp = null;
        PrimaryExp primaryExp = null;
        UnaryExp unaryExp = null;
        Token ident = null;
        Token leftParent = null;
        FuncRParams funcRParams = null;
        Token rightParent = null;

        FuncRParamsParser funcRParamsParser = new FuncRParamsParser(tokenListIterator);
        PrimaryExpParser primaryExpParser = new PrimaryExpParser(tokenListIterator);

        Token FirstToken = tokenListIterator.readNextToken();
        if (FirstToken.getType() == TokenType.MINU ||
            FirstToken.getType() == TokenType.NOT ||
            FirstToken.getType() == TokenType.PLUS) {
            unaryOp = FirstToken;
            FirstToken = tokenListIterator.readNextToken();
        }
        if (unaryOp != null) {
            tokenListIterator.unReadTokens(1);
            unaryExp = parseUnaryExp();
            return new UnaryExp(unaryOp, primaryExp, unaryExp, ident, leftParent, funcRParams, rightParent);
        }

        if (FirstToken.getType() == TokenType.IDENFR) {
            Token SecondToken = tokenListIterator.readNextToken();
            if (SecondToken.getType() == TokenType.LPARENT) { // (
                ident = FirstToken;
                leftParent = SecondToken;
                Token ThirdToken = tokenListIterator.readNextToken();
                if (ThirdToken.getType() == TokenType.IDENFR ||
                    ThirdToken.getType() == TokenType.LPARENT ||
                    ThirdToken.getType() == TokenType.INTCON ||
                    ThirdToken.getType() == TokenType.CHRCON ||
                    ThirdToken.getType() == TokenType.PLUS ||
                    ThirdToken.getType() == TokenType.MINU) {
                    tokenListIterator.unReadTokens(1);
                    funcRParams = funcRParamsParser.parseFuncRParams();
                } else {
                    tokenListIterator.unReadTokens(1);
                }
                ThirdToken = tokenListIterator.readNextToken();
                if (ThirdToken.getType() == TokenType.RPARENT) { // )
                    rightParent = ThirdToken;
                } else {
                    tokenListIterator.unReadTokens(1);
                    SyntaxErrorHandler.getInstance().WriteSyntaxError(ErrorType.j, SecondToken.getLine());
                }
            } else { // 如果没有括号，那么是PrimaryExp
                tokenListIterator.unReadTokens(2);
                primaryExp = primaryExpParser.parsePrimaryExp();
            }
        } else {
            tokenListIterator.unReadTokens(1);
            primaryExp = primaryExpParser.parsePrimaryExp();
        }
        return new UnaryExp(unaryOp, primaryExp, unaryExp, ident, leftParent, funcRParams, rightParent);
    }
}
