package middle.llvmir.value.globalvariable;

import frontend.lexer.Token;
import frontend.lexer.TokenType;
import frontend.parser.declaration.Decl;
import frontend.parser.declaration.DeclItem;
import frontend.parser.declaration.constDeclaration.ConstDecl;
import frontend.parser.declaration.constDeclaration.ConstDef;
import frontend.parser.declaration.constDeclaration.ConstInitVal;
import frontend.parser.declaration.varDeclaration.InitVal;
import frontend.parser.declaration.varDeclaration.VarDecl;
import frontend.parser.declaration.varDeclaration.VarDef;
import frontend.parser.expression.AddExp;
import frontend.parser.expression.ConstExp;
import middle.llvmir.IrValue;
import middle.llvmir.type.IrValueType;
import middle.llvmir.value.constant.IrConstant;
import middle.llvmir.value.constant.IrConstantInt;

import java.util.ArrayList;

public class IrGlobalVariableBuilder {
    private Decl decl;

    public IrGlobalVariableBuilder(Decl decl) {
        this.decl = decl;
    }

    public ArrayList<IrGlobalVariable> generateGlobalVariables() {
        ArrayList <IrGlobalVariable> globalVariables = new ArrayList<>();
        DeclItem declItem = decl.getDeclItem();

        if (declItem instanceof ConstDecl) {
            ConstDecl constDecl = (ConstDecl) declItem;
            IrValueType type = null;
            Token btype = constDecl.getBtype();
            if (btype.getType() == TokenType.INTTK) {
                type = IrValueType.i32;
            } else {
                type = IrValueType.i8;
            }
            for (ConstDef constDef : constDecl.getConstDefs()) {
                globalVariables.add(generateIrConstGlobalVariable(constDef, type));
            }
        } else {
            VarDecl varDecl = (VarDecl) declItem;
            IrValueType type;
            Token btype = varDecl.getBtype();
            if (btype.getType() == TokenType.INTTK) {
                type = IrValueType.i32;
            } else {
                type = IrValueType.i8;
            }
            for (VarDef varDef : varDecl.getVarDefs()) {
                globalVariables.add(generateIrVarGlobalVariable(varDef, type));
            }
        }
        return globalVariables;
    }

    private IrGlobalVariable generateIrVarGlobalVariable(VarDef varDef, IrValueType type) {
        Token ident = varDef.getIdent();
        String name = ident.getValue();
        if (varDef.getInitVal() != null) {
            InitVal initVal = varDef.getInitVal();
            AddExp exp = initVal.getExps().get(0);
            int value = exp.calculateValue();
            int bitWidth = type.getBitWidth();
            IrConstant irConstant = new IrConstantInt(value, bitWidth);
            return new IrGlobalVariable(name, type, irConstant);
        }
        return new IrGlobalVariable(name, type);
    }

    private IrGlobalVariable generateIrConstGlobalVariable(ConstDef constDef, IrValueType type) {
        Token ident = constDef.getIdent();
        String name = ident.getValue();
        ConstInitVal constInitVal = constDef.getConstInitVal();
        ConstExp constExp = constInitVal.getConstExps().get(0);
        int value = constExp.calculateValue();
        int bitWidth = type.getBitWidth();
        IrConstant irConstant = new IrConstantInt(value, bitWidth);
        return new IrGlobalVariable(name, type, irConstant);
    }
}
