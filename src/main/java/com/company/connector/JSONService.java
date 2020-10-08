package com.company.connector;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONService {

    public DatabaseCredentials readEnvironment() {

        try {
            Gson gson = new Gson();
//            BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/environment.json"));
            BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/environment2.json"));
            DatabaseCredentials credentials = gson.fromJson(reader, DatabaseCredentials.class);
            reader.close();
            return credentials;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public DatabaseCredentials readEnvironment2(String path) {

        try {
            Gson gson = new Gson();
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            DatabaseCredentials credentials = gson.fromJson(reader, DatabaseCredentials.class);
            reader.close();
            return credentials;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
