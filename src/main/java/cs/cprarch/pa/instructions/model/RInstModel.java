package cs.cprarch.pa.instructions.model;

public class RInstModel {

    private final String rm;
    private final String rn;
    private final String rd;
    private final int shamt;

    public RInstModel(String rm, int shamt, String rn, String rd) {
        this.rm = rm;
        this.shamt = shamt;
        this.rn = rn;
        this.rd = rd;
    }

    public String getRm() {
        return rm;
    }

    public String getRn() {
        return rn;
    }

    public String getRd() {
        return rd;
    }

    public int getShamt() {
        return shamt;
    }
}
