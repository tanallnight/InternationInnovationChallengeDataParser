package com.tanmay.dataparser;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        File parentFolder = new File("F:/Scotia");
        File[] files = parentFolder.listFiles();
        long startTime;

        for (File file : files) {
            if (file.isFile() && !file.getName().contains("txt")) {
                startTime = System.currentTimeMillis();
                System.out.print("Parsing file: " + file.getName() + "\t\t\t\t\t");
                String filePath = file.getPath();
                String outFile = "F:/Scotia/Outputs/" + file.getName().replace("del", "csv");
                String delimiter = "\\x01";
                String line;

                long lineNum = 0;
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    FileWriter fileWriter = new FileWriter(outFile);
                    while ((line = bufferedReader.readLine()) != null) {
                        if(lineNum > 1000000) {
                            break;
                        }
                        lineNum++;
                        String[] currentLine = line.split(delimiter);
                        String newLine = "";
                        String separator = "";
                        for (String s : currentLine) {
                            newLine = newLine + separator + s;
                            separator = ",";
                        }
                        fileWriter.append(newLine);
                        fileWriter.append("\n");
                    }
                    fileWriter.flush();
                    bufferedReader.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                long operationTime = System.currentTimeMillis() - startTime;
                System.out.println(operationTime + "ms");
            }
        }
        System.out.println("DONE!");
    }
}
