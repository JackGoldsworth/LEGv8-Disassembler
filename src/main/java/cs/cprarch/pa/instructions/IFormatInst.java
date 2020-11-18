package cs.cprarch.pa.instructions;

import cs.cprarch.pa.instructions.util.InstUtil;

public class IFormatInst {

    public static String addi(String bytes) {
        return getInstArguments(bytes, "ADDI");
    }

    public static String subi(String bytes) {
        return getInstArguments(bytes, "SUBI");
    }

    public static String subis(String bytes) {
        return getInstArguments(bytes, "SUBIS");
    }

    public static String andi(String bytes) {
        return getInstArguments(bytes, "ANDI");
    }

    public static String eori(String bytes) {
        return getInstArguments(bytes, "EORI");
    }

    public static String orri(String bytes) {
        return getInstArguments(bytes, "ORRI");
    }

    private static String getInstArguments(String bytes, String instructionName) {
        String immediate = bytes.substring(11, 22);
        String rn = bytes.substring(22, 27);
        String rd = bytes.substring(27, 32);
        return instructionName
                + " " + InstUtil.getRegisterValue(InstUtil.getValFromBinary(rd))
                + ", " + InstUtil.getRegisterValue(InstUtil.getValFromBinary(rn))
                + ", #" + InstUtil.getValFromBinary(immediate);
    }
}
