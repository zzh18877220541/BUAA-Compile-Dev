package frontend.parser.block;

import frontend.lexer.Token;
import frontend.parser.SyntaxOutput;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.Lval;

import java.util.ArrayList;

public class Stmt implements SyntaxOutput {
    private String syntaxName = "<Stmt>";
    private Lval lval = null;
    private Token assign = null;
    private AddExp addExp = null;
    private Token getintToken = null;
    private Token leftParent_getint = null;
    private Token rightParent_getint = null;
    private Token getcharToken = null;
    private Token leftParent_getchar = null;
    private Token rightParent_getchar = null;
    private Token semicolon_Lval = null;

    private AddExp exp = null;
    private Token semicolon_singleExp = null;

    private Block block = null;

    private Token ifToken = null;
    private Token leftParent = null;
    private LOrExp LOrExp = null;
    private Token rightParent = null;
    private Stmt stmt_if = null;
    private Token elseToken = null;
    private Stmt stmt_else = null;

    private Token breakToken = null;
    private Token semicolon_break = null;

    private Token continueToken = null;
    private Token semicolon_continue = null;

    private Token forToken = null;
    private Token leftParent_for = null;
    private ForStmt forStmt1 = null;
    private Token semicolon_for1 = null;
    private LOrExp forLOrExp = null;
    private Token semicolon_for2 = null;
    private ForStmt forStmt2 = null;
    private Token rightParent_for = null;
    private Stmt stmt_for = null;
    private Token returnToken = null;
    private AddExp addExp_return = null;
    private Token semicolon_return = null;

    private Token printfToken = null;
    private Token leftParent_printf = null;
    private Token stringConst = null;
    private ArrayList<Token> comma_printf = null;
    private ArrayList<AddExp> addExps_printf = null;
    private Token rightParent_printf = null;
    private Token semicolon_printf = null;

    private Token repeatToken = null;
    private Stmt stmt_repeat = null;
    private Token untilToken = null;
    private Token leftParent_until = null;
    private LOrExp until_condition = null;
    private Token rightParent_until = null;
    private Token semicolon_until = null;


    public Stmt(Token repeatToken, Stmt stmt_repeat, Token untilToken, Token leftParent_until, LOrExp until_condition, Token rightParent_until, Token semicolon_until) {
        this.repeatToken = repeatToken;
        this.stmt_repeat = stmt_repeat;
        this.untilToken = untilToken;
        this.leftParent_until = leftParent_until;
        this.until_condition = until_condition;
        this.rightParent_until = rightParent_until;
        this.semicolon_until = semicolon_until;
    }

    public Stmt(Lval lval, Token assign, AddExp addExp, Token semicolon_Lval, Token getintToken, Token leftParent_getint, Token rightParent_getint, Token getcharToken, Token leftParent_getchar, Token rightParent_getchar) {
        this.lval = lval;
        this.assign = assign;
        this.addExp = addExp;
        this.semicolon_Lval = semicolon_Lval;
        this.getintToken = getintToken;
        this.leftParent_getint = leftParent_getint;
        this.rightParent_getint = rightParent_getint;
        this.getcharToken = getcharToken;
        this.leftParent_getchar = leftParent_getchar;
        this.rightParent_getchar = rightParent_getchar;
    }

    public Stmt(AddExp exp, Token semicolon_singleExp) {
        this.exp = exp;
        this.semicolon_singleExp = semicolon_singleExp;
    }

    public Stmt(Block block) {
        this.block = block;
    }

    public Stmt(Token ifToken, Token leftParent, LOrExp LOrExp, Token rightParent, Stmt stmt_if, Token elseToken, Stmt stmt_else) {
        this.ifToken = ifToken;
        this.leftParent = leftParent;
        this.LOrExp = LOrExp;
        this.rightParent = rightParent;
        this.stmt_if = stmt_if;
        this.elseToken = elseToken;
        this.stmt_else = stmt_else;
    }

    public Stmt(Token breakToken, Token semicolon_break, Token continueToken, Token semicolon_continue) {
        this.breakToken = breakToken;
        this.semicolon_break = semicolon_break;
        this.continueToken = continueToken;
        this.semicolon_continue = semicolon_continue;
    }

    public Stmt(Token forToken, Token leftParent_for, ForStmt forStmt1, Token semicolon_for1, LOrExp forLOrExp, Token semicolon_for2, ForStmt forStmt2, Token rightParent_for, Stmt stmt_for) {
        this.forToken = forToken;
        this.leftParent_for = leftParent_for;
        this.forStmt1 = forStmt1;
        this.semicolon_for1 = semicolon_for1;
        this.forLOrExp = forLOrExp;
        this.semicolon_for2 = semicolon_for2;
        this.forStmt2 = forStmt2;
        this.rightParent_for = rightParent_for;
        this.stmt_for = stmt_for;
    }

    public Stmt(Token returnToken, AddExp addExp_return, Token semicolon_return) {
        this.returnToken = returnToken;
        this.addExp_return = addExp_return;
        this.semicolon_return = semicolon_return;
    }

    public Stmt(Token printfToken, Token leftParent_printf, Token stringConst, ArrayList<Token> comma_printf, ArrayList<AddExp> addExps_printf, Token rightParent_printf, Token semicolon_printf) {
        this.printfToken = printfToken;
        this.leftParent_printf = leftParent_printf;
        this.stringConst = stringConst;
        this.comma_printf = comma_printf;
        this.addExps_printf = addExps_printf;
        this.rightParent_printf = rightParent_printf;
        this.semicolon_printf = semicolon_printf;
    }

    public Stmt(Token semicolon_singleExp) {
        this.semicolon_singleExp = semicolon_singleExp;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        if (lval != null) {
            sb.append(lval.toSyntaxString());
            sb.append(assign.toSyntaxString());
            if (addExp != null) {
                sb.append(addExp.toSyntaxString());
                sb.append("<Exp>\n");
            } else if (getcharToken != null) {
                sb.append(getcharToken.toSyntaxString());
                sb.append(leftParent_getchar.toSyntaxString());
                sb.append(rightParent_getchar.toSyntaxString());
            } else if (getintToken != null) {
                sb.append(getintToken.toSyntaxString());
                sb.append(leftParent_getint.toSyntaxString());
                sb.append(rightParent_getint.toSyntaxString());
            }
            if (semicolon_Lval != null) {
                sb.append(semicolon_Lval.toSyntaxString());
            }
        }
        if (exp != null) {
            sb.append(exp.toSyntaxString());
            sb.append("<Exp>\n");
            if (semicolon_singleExp != null) {
                sb.append(semicolon_singleExp.toSyntaxString());
            }
        }
        if (exp == null && semicolon_singleExp != null) {
            sb.append(semicolon_singleExp.toSyntaxString());
        }
        if (block != null) {
            sb.append(block.toSyntaxString());
        }
        if (ifToken != null) {
            sb.append(ifToken.toSyntaxString());
            sb.append(leftParent.toSyntaxString());
            sb.append(LOrExp.toSyntaxString());
            sb.append(rightParent.toSyntaxString());
            sb.append(stmt_if.toSyntaxString());
            if (elseToken != null) {
                sb.append(elseToken.toSyntaxString());
                sb.append(stmt_else.toSyntaxString());
            }
        }
        if (breakToken != null) {
            sb.append(breakToken.toSyntaxString());
            if (semicolon_break != null) {
                sb.append(semicolon_break.toSyntaxString());
            }
        }
        if (continueToken != null) {
            sb.append(continueToken.toSyntaxString());
            if (semicolon_continue != null) {
                sb.append(semicolon_continue.toSyntaxString());
            }
        }
        if (forToken != null) {
            sb.append(forToken.toSyntaxString());
            sb.append(leftParent_for.toSyntaxString());
            if (forStmt1 != null) {
                sb.append(forStmt1.toSyntaxString());
            }
            sb.append(semicolon_for1.toSyntaxString());
            if (forLOrExp != null) {
                sb.append(forLOrExp.toSyntaxString());
            }
            sb.append(semicolon_for2.toSyntaxString());
            if (forStmt2 != null) {
                sb.append(forStmt2.toSyntaxString());
            }
            sb.append(rightParent_for.toSyntaxString());
            sb.append(stmt_for.toSyntaxString());
        }
        if (returnToken != null) {
            sb.append(returnToken.toSyntaxString());
            if (addExp_return != null) {
                sb.append(addExp_return.toSyntaxString());
                sb.append("<Exp>\n");
            }
            if (semicolon_return != null) {
                sb.append(semicolon_return.toSyntaxString());
            }
        }
        if (printfToken != null) {
            sb.append(printfToken.toSyntaxString());
            sb.append(leftParent_printf.toSyntaxString());
            sb.append(stringConst.toSyntaxString());
            for (int i = 0; i < addExps_printf.size(); i++) {
                sb.append(comma_printf.get(i).toSyntaxString());
                sb.append(addExps_printf.get(i).toSyntaxString());
                sb.append("<Exp>\n");
            }
            sb.append(rightParent_printf.toSyntaxString());
            if (semicolon_printf != null) {
                sb.append(semicolon_printf.toSyntaxString());
            }
        }
        if (repeatToken != null) {
            sb.append(repeatToken.toSyntaxString());
            sb.append(stmt_repeat.toSyntaxString());
            sb.append(untilToken.toSyntaxString());
            sb.append(leftParent_until.toSyntaxString());
            sb.append(until_condition.toSyntaxString());
            if (rightParent_until != null) {
                sb.append(rightParent_until.toSyntaxString());
            }
            if (semicolon_until != null) {
                sb.append(semicolon_until.toSyntaxString());
            }
        }
        sb.append(syntaxName + "\n");
        return sb.toString();
    }
}
