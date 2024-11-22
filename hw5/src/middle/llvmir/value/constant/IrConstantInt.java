package middle.llvmir.value.constant;

public class IrConstantInt extends IrConstant {
    private long value;
    private int bitWidth;

    public IrConstantInt(long value, int bitWidth) {
        this.value = value;
        this.bitWidth = bitWidth;
    }

    public long getValue() {
        return value;
    }

    public int getBitWidth() {
        return bitWidth;
    }

    @Override
    public String toIrNodeString() {
        return String.format("%d", value);
    }
}
