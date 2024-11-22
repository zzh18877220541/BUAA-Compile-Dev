package middle.llvmir;

import middle.llvmir.value.IrNodeOutput;
import middle.llvmir.value.function.IrFunction;
import middle.llvmir.value.globalvariable.IrGlobalVariable;

import java.util.ArrayList;

public class IrModule implements IrNodeOutput {
    private ArrayList<IrGlobalVariable> globalVariables; // Module中的全局变量
    private ArrayList<IrFunction> functions; // Module中的函数

    public IrModule() {
        this.functions = new ArrayList<>();
        this.globalVariables = new ArrayList<>();
    }

    public void addIrFunction(IrFunction function) {
        this.functions.add(function);
    }

    public void addIrGlobalVariables(IrGlobalVariable variable) {
        this.globalVariables.add(variable);
    }

    @Override
    public String toIrNodeString() {
        StringBuilder res = new StringBuilder();
        for (IrGlobalVariable globalVariable : globalVariables) {
            res.append(globalVariable.toIrNodeString());
        }
        for (IrFunction function : functions) {
            res.append(function.toIrNodeString());
        }
        return res.toString();
    }

    public ArrayList<IrFunction> getFunctions() {
        return functions;
    }

    public ArrayList<IrGlobalVariable> getGlobalVariables() {
        return globalVariables;
    }
}
