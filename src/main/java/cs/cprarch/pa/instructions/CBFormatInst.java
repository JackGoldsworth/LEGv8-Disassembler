package cs.cprarch.pa.instructions;

import cs.cprarch.pa.Decompiler;
import cs.cprarch.pa.instructions.util.InstUtil;
import javafx.util.Pair;

public class CBFormatInst {

    public static String bcond(String bytes) {
        Pair<Integer, String> results = getInstArguments(bytes);
        String argResults = getConditionalResult(results, bytes);
        switch (results.getKey()) {
            case 0:
                return "B.EQ " + argResults;
            case 1:
                return "B.NE " + argResults;
            case 2:
                return "B.HS " + argResults;
            case 3:
                return "B.LO " + argResults;
            case 4:
                return "B.MI " + argResults;
            case 5:
                return "B.PL " + argResults;
            case 6:
                return "B.VS " + argResults;
            case 7:
                return "B.VC " + argResults;
            case 8:
                return "B.HI " + argResults;
            case 9:
                return "B.LS " + argResults;
            case 10:
                return "B.GE " + argResults;
            case 11:
                return "B.LT " + argResults;
            case 12:
                return "B.GT " + argResults;
            case 13:
                return "B.LE " + argResults;
            default:
                throw new IllegalArgumentException("There was no conditional branch type found!");
        }
    }

    private static String getConditionalResult(Pair<Integer, String> results, String bytes) {
        if (bytes.charAt(8) == '0') {
            return Decompiler.getLexer().addLabel(Integer.parseInt(results.getValue(), 2));
        }
        return Decompiler.getLexer().addLabel(-Integer.parseInt(InstUtil.twosCompliment(results.getValue()), 2));
    }

    public static String cbnz(String bytes) {
        Pair<Integer, String> results = getInstArguments(bytes);
        String binary = results.getValue();
        String register = InstUtil.getRegisterValue(results.getKey());
        if (bytes.charAt(8) == '0') {
            return "CBNZ " + register + ", " + Decompiler.getLexer().addLabel(Integer.parseInt(binary, 2));
        }
        return "CBNZ " + register + ", " + Decompiler.getLexer().addLabel(-Integer.parseInt(InstUtil.twosCompliment(binary), 2));
    }

    public static String cbz(String bytes) {
        Pair<Integer, String> results = getInstArguments(bytes);
        String binary = results.getValue();
        String register = InstUtil.getRegisterValue(results.getKey());
        if (bytes.charAt(8) == '0') {
            return "CBZ " + register + ", " + Decompiler.getLexer().addLabel(Integer.parseInt(binary, 2));
        }
        return "CBZ " + register + ", " + Decompiler.getLexer().addLabel(-Integer.parseInt(InstUtil.twosCompliment(binary), 2));
    }

    private static Pair<Integer, String> getInstArguments(String bytes) {
        String address = bytes.substring(8, 27);
        String rt = bytes.substring(27, 32);
        return new Pair<>(InstUtil.getValFromBinary(rt), address);
    }
}
