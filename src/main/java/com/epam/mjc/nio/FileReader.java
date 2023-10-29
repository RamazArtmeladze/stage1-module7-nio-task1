package com.epam.mjc.nio;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            // Step 1: Read the File Data
            String fileContent = Files.readString(file.toPath());

            // Step 2: Parse the Data
            Map<String, String> keyValuePairs = parseData(fileContent);

            // Step 3: Create a Profile Object
            String name = keyValuePairs.get("Name");
            Integer age = Integer.parseInt(keyValuePairs.get("Age"));
            String email = keyValuePairs.get("Email");
            Long phone = Long.parseLong(keyValuePairs.get("Phone"));

            Profile profile = new Profile(name, age, email, phone);

            return profile;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error
        }
    }

    private Map<String, String> parseData(String fileContent) {
        Map<String, String> keyValuePairs = new HashMap<>();

        String[] lines = fileContent.split(System.lineSeparator());

        for (String line : lines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                keyValuePairs.put(key, value);
            }
        }

        return keyValuePairs;
    }
}
