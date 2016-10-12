package com.tanmay;

import java.io.*;

public class Main {

    private static BufferedReader bufferedReader;
    private static FileWriter fileWriter;

    public static void main(String[] args) {

        File parentFolder = new File("F:/Scotia");
        File[] files = parentFolder.listFiles();

        for (File file : files) {
            if (file.isFile() && !file.getName().contains("txt")) {
                String filePath = file.getPath();
                String outFile = "F:/Scotia/Quick/" + file.getName().replace("del", "csv");
                String delimiter = "\\x01";
                String line;

                long lineNum = 0;
                try {
                    bufferedReader = new BufferedReader(new FileReader(filePath));
                    fileWriter = new FileWriter(outFile);
                    while ((line = bufferedReader.readLine()) != null) {
                        if(lineNum >= 10000) {
                            break;
                        }
                        System.out.println(lineNum);
                        lineNum++;
                        String[] currentLine = line.split(delimiter);
                        String newLine = "";
                        String separator = "";
                        for (String s : currentLine) {
                            newLine = newLine + separator + s;
                            separator = ",";
                            //System.out.println(newLine);
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
            }
        }
    }
}
