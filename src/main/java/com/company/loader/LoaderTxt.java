package com.company.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoaderTxt implements Loader {

    private String filePath;

    @Override
    public void setPath(String path) {
        this.filePath = path;
    }

    public List<String[]> load() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            List<String[]> content = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                content.add(new String[]{line});
                line = br.readLine();
            }

            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
