package com.company.loader;

import java.io.BufferedReader;
import java.io.File;
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

    public List<String[]> load() throws IOException {
        File file = new File(this.filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        List<String[]> content = new ArrayList<>();
        String[] arr = new String[1];
        while ((st = br.readLine()) != null) {
            arr[0] = st;
            content.add(arr);
        }

        return content;
    }
}
