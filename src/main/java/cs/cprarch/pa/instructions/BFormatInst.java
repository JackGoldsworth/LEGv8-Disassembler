package cs.cprarch.pa.instructions;

import cs.cprarch.pa.Decompiler;
import cs.cprarch.pa.instructions.util.InstUtil;

public class BFormatInst {

    public static String b(String bytes) {
        return getInstArguments(bytes, "B");
    }

    public static String bl(String bytes) {
        return getInstArguments(bytes, "BL");
    }

    private static String getInstArguments(String bytes, String instructionName) {
        String binary = bytes.substring(6, 32);
        int results = Integer.parseInt(binary, 2);
        if (bytes.charAt(6) == '0') {
            return instructionName + " " + Decompiler.getLexer().addLabel(results);
        }
        return instructionName + " " + Decompiler.getLexer().addLabel(-Integer.parseInt(InstUtil.twosCompliment(binary), 2));
    }
}
