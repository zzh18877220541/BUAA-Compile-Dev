package middle.llvmir.value.globalvariable;

import middle.llvmir.IrUser;
import middle.llvmir.type.IrPointerType;
import middle.llvmir.type.IrValueType;
import middle.llvmir.value.IrNodeOutput;
import middle.llvmir.value.constant.IrConstant;
import middle.llvmir.value.constant.IrConstantInt;

public class IrGlobalVariable extends IrUser implements IrNodeOutput {
    private IrConstant init = null; // 初始化的值

    public IrGlobalVariable(String name, IrValueType type) {
        super(type);
        this.setName(name);
    }

    public IrGlobalVariable(String name,
                            IrValueType type,
                            IrConstant init) {
        this(name, type);
        this.init = init;
    }

    @Override
    public String toIrNodeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("@");
        sb.append(this.getName());
        sb.append(" = ");
        sb.append("dso_local global ");
        sb.append(this.getValueType().toIrNodeString());
        sb.append(" ");
        if (init != null) {
            sb.append(init.toIrNodeString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
