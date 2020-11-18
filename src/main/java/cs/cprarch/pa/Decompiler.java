package cs.cprarch.pa;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Decompiler {

    private static Lexer lexer;

    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            List<Instruction> instructions = new ArrayList<>();
            int instructionCount = 0;
            try {
                InputStream stream = new FileInputStream(args[0]);
                int byteToRead;
                while ((byteToRead = stream.read()) != -1) {
                    String binary = getInstructionString(byteToRead, stream.read(), stream.read(), stream.read());
                    instructions.add(new Instruction("label" + instructionCount, binary));
                    instructionCount++;
                }
            } catch (IOException e) {
                System.err.println("There was an issue reading the instructions from the machine file.");
                throw e;
            }
            lexer = new Lexer(instructions);
            while (lexer.hasNext()) {
                lexer.next();
            }
        } else {
            throw new IllegalArgumentException("There was no file location found in the arguments.");
        }
    }

    public static Lexer getLexer() {
        return lexer;
    }

    private static String getInstructionString(int byte1, int byte2, int byte3, int byte4) {
        return padString(Integer.toBinaryString(byte1))
                + padString(Integer.toBinaryString(byte2))
                + padString(Integer.toBinaryString(byte3))
                + padString(Integer.toBinaryString(byte4));
    }

    private static String padString(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        while (stringBuilder.length() < 8) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }
}
