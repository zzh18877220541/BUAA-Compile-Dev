package middle.llvmir.value.function;

import middle.llvmir.IrValue;
import middle.llvmir.type.IrValueType;

public class IrFunctionParam extends IrValue {
    private int rank;

    public IrFunctionParam(int rank, IrValueType type) {
        super(type);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toIrNodeString() {
        return "%" + getName();
    }
}
