package br.com.fiap.twoespwx.libunclepresser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        System.out.println("LIB UNCLE PRESSER - GRUPO BATATA-DOCE");

        if (args.length != 2) {
            System.err.println("Uso: java -jar <path/to/jar/file> <path/to/input> <path/to/output>");
            return;
        }

        String inputPath = args[0];
        String outputPath = args[1];

        try {
            String sequence = new String(Files.readAllBytes(Paths.get(inputPath))).replaceAll("\\s+", "");
            String compressedData = compressData(sequence);

            Files.write(Paths.get(outputPath), compressedData.getBytes());

            long inputSize = Files.size(Paths.get(inputPath));
            long outputSize = compressedData.getBytes().length;
            double compressionRate = (double) outputSize / inputSize;

            // Exibe os detalhes da compressão
            System.out.printf("-----------------------------------------------------------\n");
            System.out.printf("| LIB UNCLE PRESSER - GRUPO BATATA-DOCE                   |\n");
            System.out.printf("|---------------------------------------------------------|\n");
            System.out.printf("| INPUT FILE NAME: %s                                     |\n", inputPath);
            System.out.printf("| OUTPUT FILE NAME: %s                                    |\n", outputPath);
            System.out.printf("| INPUT FILE SIZE: %d KB                                  |\n", inputSize / 1024);
            System.out.printf("| OUTPUT FILE SIZE: %.2f KB                               |\n", (double) outputSize / 1024);
            System.out.printf("| COMPRESSION RATE: %.2f%%                                 |\n", compressionRate * 100);
            System.out.printf("-----------------------------------------------------------\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String compressData(String sequence) {
        if (sequence.isEmpty()) return ""; // Tratamento para sequência vazia
        
        StringBuilder compressed = new StringBuilder();
        int count = 1;

        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt(i - 1)) {
                count++;
            } else {
                compressed.append(sequence.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        compressed.append(sequence.charAt(sequence.length() - 1)).append(count);
        return compressed.toString();
    }
}
