package cs.cprarch.pa.instructions.util;

/**
 * Instruction Utils
 */
public class InstUtil {

    /**
     * Private constructor so the class can't be initialized.
     */
    private InstUtil() {
    }

    /**
     * Get a value from a binary string.
     *
     * @param binary binary string.
     * @return binary value as an integer.
     */
    public static int getValFromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    /**
     * Get's the value of a register based
     * on the registers number.
     *
     * @param number register number.
     * @return register value in code.
     */
    public static String getRegisterValue(int number) {
        switch (number) {
            case 28:
                return "SP";
            case 29:
                return "FP";
            case 30:
                return "LR";
            case 31:
                return "XZR";
            default:
                return "X" + number;
        }
    }

    /**
     * Perform two's compliment on a binary string.
     *
     * @param bytes binary string.
     * @return complimented binary string.
     */
    public static String twosCompliment(String bytes) {
        StringBuilder compliment = new StringBuilder();
        String[] tempArr = bytes.split("");
        for (String s : tempArr) {
            compliment.append(s.equals("1") ? "0" : "1");
        }
        String tempComp = compliment.toString();
        for (int i = bytes.length() - 1; i >= 0; i--) {
            if (tempComp.charAt(i) == '1') {
                tempComp = tempComp.substring(0, i) + '0' + tempComp.substring(i + 1);
            } else {
                tempComp = tempComp.substring(0, i) + '1' + tempComp.substring(i + 1);
                break;
            }
        }
        return tempComp;
    }
}
