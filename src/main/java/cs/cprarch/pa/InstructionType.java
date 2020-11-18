package cs.cprarch.pa;

import cs.cprarch.pa.instructions.*;

import java.util.function.Function;

/**
 * A enum of instructions with their opcodes
 * and method to handle them.
 */
public enum InstructionType {
    ADD("10001011000", RFormatInst::add),
    ADDI("1001000100", IFormatInst::addi),
    AND("10001010000", RFormatInst::and),
    ANDI("1001000100", IFormatInst::andi),
    B("000101", BFormatInst::b),
    B_COND("01010100", CBFormatInst::bcond),
    BL("100101", BFormatInst::bl),
    BR("11010110000", RFormatInst::br),
    CBNZ("10110101", CBFormatInst::cbnz),
    CBZ("10110100", CBFormatInst::cbz),
    EOR("11001010000", RFormatInst::eor),
    EORI("1101001000", IFormatInst::eori),
    LDUR("11111000010", DFormatInst::ldur),
    LSL("11010011011", RFormatInst::lsl),
    LSR("11010011010", RFormatInst::lsr),
    ORR("10101010000", RFormatInst::orr),
    ORRI("1011001000", IFormatInst::orri),
    STUR("11111000000", DFormatInst::stur),
    SUB("11001011000", RFormatInst::sub),
    SUBI("1101000100", IFormatInst::subi),
    SUBIS("1111000100", IFormatInst::subis),
    SUBS("11101011000", RFormatInst::subs),
    MUL("10011011000", RFormatInst::mul),
    PRNT("11111111101", RFormatInst::prnt),
    PRNL("11111111100", RFormatInst::prnl),
    DUMP("11111111110", RFormatInst::dump),
    HALT("11111111111", RFormatInst::halt);

    private final int opcodeLength;
    private final String opcode;
    private final Function<String, String> handler;

    InstructionType(String opcode, Function<String, String> handler) {
        this.opcodeLength = opcode.length();
        this.opcode = opcode;
        this.handler = handler;
    }

    public String getOpcode() {
        return opcode;
    }

    public int getOpcodeLength() {
        return opcodeLength;
    }

    public Function<String, String> getFunctionHandler() {
        return handler;
    }
}
