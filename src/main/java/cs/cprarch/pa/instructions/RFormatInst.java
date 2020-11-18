package cs.cprarch.pa.instructions;

import cs.cprarch.pa.instructions.model.RInstModel;
import cs.cprarch.pa.instructions.util.InstUtil;

public class RFormatInst {

    public static String add(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "ADD " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String subs(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "SUBS " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String and(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "AND " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String br(String bytes) {
        return "BR LR";
    }

    public static String eor(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "EOR " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String lsl(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "LSL " + results.getRd() + ", " + results.getRn() + ", #" + results.getShamt();
    }

    public static String lsr(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "LSR " + results.getRd() + ", " + results.getRn() + ", #" + results.getShamt();
    }

    public static String orr(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "ORR " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String sub(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "SUB " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String mul(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "MUL " + results.getRd() + ", " + results.getRn() + ", " + results.getRm();
    }

    public static String prnt(String bytes) {
        RInstModel results = getInstArguments(bytes);
        return "PRNT " + results.getRd();
    }

    public static String prnl(String bytes) {
        return "PRNL";
    }

    public static String dump(String bytes) {
        return "DUMP";
    }

    public static String halt(String bytes) {
        return "HALT";
    }

    private static RInstModel getInstArguments(String bytes) {
        String rm = bytes.substring(11, 16);
        String rn = bytes.substring(21, 27);
        String rd = bytes.substring(27, 32);
        String shamt = bytes.substring(16, 22);
        return new RInstModel(InstUtil.getRegisterValue(InstUtil.getValFromBinary(rm)),
                InstUtil.getValFromBinary(shamt),
                InstUtil.getRegisterValue(InstUtil.getValFromBinary(rn)),
                "X" + InstUtil.getValFromBinary(rd));
    }
}
