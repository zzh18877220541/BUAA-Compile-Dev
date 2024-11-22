package middle.llvmir.value.instruction;

public enum IrInstructionType {
    /* Compare */
    icmp,
    /* Binary */
    /* Arithmetic Binary */
    add,// +
    sub,// -
    mul,// *
    sdiv,// /
    srem,// %
    /* Logic Binary */
    lt, // <
    le, // <=
    ge, // >=
    gt, // >
    eq, // ==
    ne, // !=
    and,// &
    or, // |
    /* Terminator */
    br,
    call,
    ret,
    /* mem op */
    alloca,
    load,
    store,
    gep, // Get Element Ptr
    Zext, // zero extend
    Trunc, // truncate
    phi,//用于 mem2reg
}
