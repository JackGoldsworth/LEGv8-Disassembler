package cs.cprarch.pa.instructions.model;

public class DInstModel {

    private final int address;
    private final int op;
    private final String rn;
    private final String rt;

    public DInstModel(int address, int op, String rn, String rt) {
        this.address = address;
        this.op = op;
        this.rn = rn;
        this.rt = rt;
    }

    public int getAddress() {
        return address;
    }

    public int getOp() {
        return op;
    }

    public String getRn() {
        return rn;
    }

    public String getRt() {
        return rt;
    }
}
