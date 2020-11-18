package cs.cprarch.pa.instructions;

import cs.cprarch.pa.instructions.model.DInstModel;
import cs.cprarch.pa.instructions.util.InstUtil;

public class DFormatInst {

    public static String stur(String bytes) {
        DInstModel results = getInstArguments(bytes);
        return "STUR " + results.getRt() + ", [" + results.getRn() + ", #" + results.getAddress() + "]";
    }

    public static String ldur(String bytes) {
        DInstModel results = getInstArguments(bytes);
        return "LDUR " + results.getRt() + ", [" + results.getRn() + ", #" + results.getAddress() + "]";
    }

    private static DInstModel getInstArguments(String bytes) {
        String address = bytes.substring(11, 20);
        String op = bytes.substring(20, 22);
        String rn = bytes.substring(22, 27);
        String rt = bytes.substring(27, 32);
        return new DInstModel(InstUtil.getValFromBinary(address),
                InstUtil.getValFromBinary(op),
                InstUtil.getRegisterValue(InstUtil.getValFromBinary(rn)),
                InstUtil.getRegisterValue(InstUtil.getValFromBinary(rt)));
    }
}
