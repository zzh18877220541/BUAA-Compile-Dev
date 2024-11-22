package middle.llvmir.type;

import frontend.lexer.Token;
import frontend.lexer.TokenType;
import middle.llvmir.IrValue;
import middle.llvmir.value.IrNodeOutput;

public enum IrValueType implements IrNodeOutput {
    i32(32),
    i8(8),
    voidTy(0);

    private int bitWidth;

    IrValueType(int bitWidth) {
        this.bitWidth = bitWidth;
    }

    public int getBitWidth() {
        return bitWidth;
    }

    @Override
    public String toIrNodeString() {
        if (this == voidTy) {
            return "void";
        }
        return this.name();
    }
}
