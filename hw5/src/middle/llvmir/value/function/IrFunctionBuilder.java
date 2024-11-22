package middle.llvmir.value.function;

import frontend.lexer.Token;
import frontend.lexer.TokenType;
import frontend.parser.block.Block;
import frontend.parser.funcparas.FuncFParam;
import frontend.parser.funcparas.FuncFParams;
import frontend.parser.function.FuncDef;
import frontend.parser.function.MainFuncDef;
import frontend.symbol.SymbolTable;
import frontend.symbol.SymbolType;
import middle.llvmir.type.IrValueType;
import middle.llvmir.value.basicblock.IrBasicBlock;

import java.util.ArrayList;

public class IrFunctionBuilder {
    private SymbolTable symbolTable;
    private FuncDef funcDef;
    private MainFuncDef mainFuncDef;


    public IrFunctionBuilder(SymbolTable symbolTable, FuncDef funcDef) {
        this.symbolTable = symbolTable;
        this.funcDef = funcDef;
        this.mainFuncDef = null;
    }

    public IrFunctionBuilder(SymbolTable symbolTable, MainFuncDef mainFuncDef) {
        this.symbolTable = symbolTable;
        this.mainFuncDef = mainFuncDef;
    }

    public IrFunction buildFunction() {
        if (mainFuncDef != null) {
            return genMainFunction(symbolTable, mainFuncDef);
        } else {
            return genFunction(symbolTable, funcDef);
        }
    }

    private IrFunction genFunction(SymbolTable symbolTable, FuncDef funcDef) {
        Token funcType = funcDef.getFuncType();
        FuncFParams funcFParams = funcDef.getFuncFParams();
        Block block = funcDef.getBlock();
        IrValueType irFuncType;
        ArrayList<IrFunctionParam> irFuncParams = null;
        if (funcType.getType() == TokenType.INTTK) {
            irFuncType = IrValueType.i32;
        } else if (funcType.getType() == TokenType.CHARTK) {
            irFuncType = IrValueType.i8;
        } else {
            irFuncType = IrValueType.voidTy;
        }
        if (funcFParams != null) {
            irFuncParams = new ArrayList<>();
            for (int i = 0; i < funcFParams.getFuncFParams().size(); i++) {
                FuncFParam funcFParam = funcFParams.getFuncFParams().get(i);
                Token bType = funcFParam.getbType();
                IrValueType irType;
                if (bType.getType() == TokenType.INTTK) {
                    irType = IrValueType.i32;
                } else {
                    irType = IrValueType.i8;
                }
                int rank = i + 1;
                irFuncParams.add(new IrFunctionParam(rank, irType));
            }
        }
        ArrayList<IrBasicBlock> basicBlocks = genBasicBlocks(symbolTable, block);
    }
}
