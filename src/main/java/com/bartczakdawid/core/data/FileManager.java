package com.bartczakdawid.core.data;

import com.bartczakdawid.features.alert.AlertView;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileManager {
    private final String directory;
    private final String fileName;
    private final File file;
    private final ExecutorService executorService;

    public FileManager(String directory, String fileName) throws IOException {
        this.directory = directory;
        this.fileName = fileName;
        this.file = createFile();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    private File createFile() throws IOException {
        File folder = new File(this.directory);
        if (!folder.exists() && !folder.mkdirs()) {
            throw new IOException("Cannot create \"" + this.directory + "\" directory");
        }

        File file = new File(folder, this.fileName);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Cannot create \"" + this.fileName + "\"");
        }

        return file;
    }

    public String[] readFile() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines()
                .map(line -> line.replace("\\n", "\n"))
                .toArray(String[]::new);
    }

    public void writeLine(String line, boolean append) {
        executorService.submit(() -> {
            String escapedLine = line.replace("\n", "\\n");
            try (FileWriter fileWriter = new FileWriter(this.file, append)) {
                fileWriter.write(escapedLine + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                AlertView.showError("Error writing line to file: " + e.getMessage());
            }
        });
    }

    public void writeLines(String[] lines, boolean append) {
        executorService.submit(() -> {
            try (FileWriter fileWriter = new FileWriter(this.file, append)) {
                for (String line : lines) {
                    String escapedLine = line.replace("\n", "\\n");
                    fileWriter.write(escapedLine + "\n");
                }
                fileWriter.flush();
            } catch (IOException e) {
                AlertView.showError("Error writing lines to file: " + e.getMessage());
            }
        });
    }
}
