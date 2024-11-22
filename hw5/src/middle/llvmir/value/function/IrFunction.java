package middle.llvmir.value.function;

import middle.llvmir.value.IrNodeOutput;
import middle.llvmir.value.basicblock.IrBasicBlock;

import java.util.ArrayList;

public class IrFunction implements IrNodeOutput {
    ArrayList<IrFunctionParam> params;
    ArrayList<IrBasicBlock> blocks;

    @Override
    public String toIrNodeString() {
        return "IrFunction";
    }
}
