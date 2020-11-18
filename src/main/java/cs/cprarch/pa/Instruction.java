package cs.cprarch.pa;

/**
 * Class to represent an instruction's 32
 * bits and it's label.
 */
public class Instruction {

    private final String[] bytes;
    private final String label;

    public Instruction(String label, String bytes) {
        this.bytes = bytes.split("");
        this.label = label;
    }

    public String[] getBytes() {
        return bytes;
    }

    public String getLabel() {
        return label;
    }
}
