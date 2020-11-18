package cs.cprarch.pa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    private final PrintWriter printWriter;
    private final List<Instruction> instructions;
    private final List<String> linesOfCode;
    private final Map<Integer, String> labels;
    private String tokenBuffer;
    private String[] bytes;
    private int currentPos;
    private int currentInst;

    public Lexer(List<Instruction> instructions) throws FileNotFoundException {
        this.currentInst = 0;
        this.linesOfCode = new ArrayList<>();
        this.bytes = instructions.get(currentInst).getBytes();
        this.instructions = instructions;
        this.currentPos = 0;
        this.tokenBuffer = "";
        File outputFile = new File("test1.legv8asm");
        this.printWriter = new PrintWriter(outputFile);
        this.labels = new HashMap<>();
    }

    /**
     * Go through each bit in the array and append it
     * to a token buffer so we can check if it's an instruction.
     */
    public void next() {
        tokenBuffer += bytes[currentPos];
        if (tokenBuffer.length() >= 6 && isInstruction(tokenBuffer)) {
            tokenBuffer = "";
            currentPos = 0;
            currentInst++;
            if (currentInst < instructions.size()) {
                this.bytes = instructions.get(currentInst).getBytes();
            }
            return;
        }
        currentPos++;
    }

    /**
     * Whether we can continue lexing or not.
     *
     * @return if the current position is within the bounds of the bytes array.
     */
    public boolean hasNext() {
        if (currentPos < bytes.length && currentInst < instructions.size()) {
            return true;
        } else {
            for (int i = 0; i < linesOfCode.size(); i++) {
                String line = linesOfCode.get(i);
                String label = labels.get(i);
                if (label != null) {
                    printWriter.println(label);
                    System.out.println(label);
                }
                printWriter.println("   " + line);
                System.out.println("   " + line);
            }
            printWriter.close();
            return false;
        }
    }

    /**
     * Checks if what's current in the token buffer is
     * an instruction, and then handles the instruction,
     * and then prints what it's created out to a file.
     *
     * @param buffer current token buffer.
     * @return whether the what's in the buffer is an instruction.
     */
    public boolean isInstruction(String buffer) {
        for (InstructionType instruction : InstructionType.values()) {
            if (buffer.contains(instruction.getOpcode()) && checkInst(instruction, buffer)) {
                String result = instruction.getFunctionHandler().apply(getFullBits(buffer));
                linesOfCode.add(result);
                return true;
            }
        }
        if (buffer.length() > 11) {
            throw new IllegalStateException("The current buffer contains something that isn't an instruction.");
        }
        return false;
    }

    /**
     * This makes sure that the instruction is in-fact correct.
     * It does this by using what it thought was the correct instruction,
     * and then goes to the end of the opcode value and double checks it.
     *
     * @param currentOpcode what we current have as an opcode.
     * @param instruction   what we think is the correct instruction.
     * @return if it is the correct instruction.
     */
    private boolean checkInst(InstructionType instruction, String currentOpcode) {
        StringBuilder tempStr = new StringBuilder(currentOpcode);
        for (int i = currentOpcode.length() + 1; i < instruction.getOpcodeLength(); i++) {
            tempStr.append(bytes[i]);
        }
        return instruction.getOpcode().equals(tempStr.toString());
    }

    /**
     * This just returns a string off the full 32 bits
     * of the instruction that we're on.
     *
     * @param currentBuffer what's currently in the buffer.
     * @return String of 32 bits.
     */
    private String getFullBits(String currentBuffer) {
        StringBuilder tempStr = new StringBuilder(currentBuffer);
        int finalPos = currentPos + (32 - currentPos);
        for (int i = currentPos + 1; i < finalPos; i++) {
            tempStr.append(bytes[i]);
        }
        return tempStr.toString();
    }

    /**
     * Adds a label to the current instructions.
     *
     * @param location number of instructions away from the current instruction to jump to.
     * @return the label name.
     */
    public String addLabel(int location) {
        int index = currentInst + location;
        String label = instructions.get(index).getLabel();
        labels.put(index, label + ":");
        return label;
    }
}