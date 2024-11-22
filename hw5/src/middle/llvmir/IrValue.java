package middle.llvmir;

import middle.llvmir.type.IrValueType;
import middle.llvmir.value.IrNodeOutput;

import java.util.ArrayList;
import java.util.LinkedList;

public class IrValue implements IrNodeOutput {
    private IrValueType valueType; // Value 类型
    private String name; //
    private LinkedList<IrUse> uses;
    private boolean isParam = false;

    public IrValue(IrValueType valueType) {
        this.valueType = valueType;
        this.name = "";
        this.uses = new LinkedList<>();
    }

    public IrValue(IrValueType valueType, String name) {
        this(valueType);
        this.name = name;
    }

    public IrValue(IrValueType valueType, String name, boolean isParam) {
        this(valueType, name);
        this.isParam = isParam;
    }

    public void setValueType(IrValueType valueType) {
        this.valueType = valueType;
    }

    public void addUse(IrUse use) {
        this.uses.add(use);
    }

    public void removeUse(IrUse use) {
        this.uses.removeIf(h -> h.equals(use));
    }

    public IrValueType getValueType() {
        return valueType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toIrNodeString() {
        return this.name;
    }

    public boolean isParam() {
        return this.isParam;
    }
}
