package frontend.parser;

import frontend.parser.declaration.Decl;
import frontend.parser.function.FuncDef;
import frontend.parser.function.MainFuncDef;

import java.io.FileWriter;
import java.util.ArrayList;

public class CompUnit implements SyntaxOutput {
    private final String syntaxName = "<CompUnit>";
    private ArrayList<Decl> decls;
    private ArrayList<FuncDef> funcDefs;
    private MainFuncDef mainFuncDef;

    public CompUnit(ArrayList<Decl> decls, ArrayList<FuncDef> funcDefs, MainFuncDef mainFuncDef) {
        this.decls = decls;
        this.funcDefs = funcDefs;
        this.mainFuncDef = mainFuncDef;
    }

    @Override
    public String toSyntaxString() {
        StringBuilder sb = new StringBuilder();
        for (Decl decl : decls) {
            sb.append(decl.toSyntaxString());
        }
        for (FuncDef funcDef : funcDefs) {
            sb.append(funcDef.toSyntaxString());
        }
        sb.append(mainFuncDef.toSyntaxString());
        sb.append(syntaxName + "\n");
        try {
            FileWriter writer = new FileWriter("parser.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
