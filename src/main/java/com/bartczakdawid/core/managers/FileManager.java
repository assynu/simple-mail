package com.bartczakdawid.core.managers;

import java.io.*;

public class FileManager {
    private final String directory;
    private final String fileName;
    private final File file;

    public FileManager(String directory, String fileName) throws IOException {
        this.directory = directory;
        this.fileName = fileName;
        this.file = createFile();
    }

    private File createFile() throws IOException {
        File folder = new File(this.directory);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Cannot create \"" + this.directory + "\" directory");
        }

        File file = new File(folder, this.fileName);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Cannot create \"" + this.fileName);
        }

        return file;
    }

    public String[] readFile() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines().toArray(String[]::new);
    }

    public void writeLine(String line, boolean append) throws IOException {
        try (FileWriter fileWriter = new FileWriter(this.file, append)) {
            fileWriter.write(line + "\n");
            fileWriter.flush();
        }
    }

    public void writeLines(String[] lines, boolean append) throws IOException {
        try (FileWriter fileWriter = new FileWriter(this.file, append)) {
            for(String line : lines) {
                fileWriter.write(line + "\n");
            }
            fileWriter.flush();
        }
    }
}
