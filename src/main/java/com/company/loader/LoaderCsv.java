package com.company.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderCsv implements Loader {

    private String filePath;

    public void setPath(String path) {
        this.filePath = path;
    }

    public List<String[]> load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            List<String[]> content = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                content.add(line.split(","));
                line = br.readLine();
            }

            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
