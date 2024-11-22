package middle.llvmir;

import frontend.parser.CompUnit;
import frontend.parser.declaration.Decl;
import frontend.symbol.SymbolTable;
import middle.llvmir.value.globalvariable.IrGlobalVariable;
import middle.llvmir.value.globalvariable.IrGlobalVariableBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IrBuilder {
    private CompUnit compUnit;
    private IrModule module;
    private SymbolTable symbolTable;

    public IrBuilder(CompUnit compUnit, SymbolTable symbolTable) {
        this.compUnit = compUnit;
        this.module = new IrModule();
        this.symbolTable = symbolTable;
    }

    public IrModule generateIrModule() {
        for (Decl decl : compUnit.getDecls()) {
            IrGlobalVariableBuilder globalVariableBuilder = new IrGlobalVariableBuilder(decl);
            ArrayList<IrGlobalVariable> globalVariables = globalVariableBuilder.generateGlobalVariables();
            for (IrGlobalVariable globalVariable : globalVariables) {
                module.addIrGlobalVariables(globalVariable);
            }
        }
        return module;
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter("llvm_ir.txt")) {
            writer.write(this.generateIrModule().toIrNodeString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
