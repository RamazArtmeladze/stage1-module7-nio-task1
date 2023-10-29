package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                String fileContent = reader.readLine();
                if (fileContent != null) { // Check if file is not empty
                    Map<String, String> keyValuePairs = parseData(fileContent);
                    if (keyValuePairs.containsKey("Name") && keyValuePairs.containsKey("Age") &&
                            keyValuePairs.containsKey("Email") && keyValuePairs.containsKey("Phone")) {
                        String name = keyValuePairs.get("Name");
                        Integer age = Integer.parseInt(keyValuePairs.get("Age"));
                        String email = keyValuePairs.get("Email");
                        Long phone = Long.parseLong(keyValuePairs.get("Phone"));

                        return new Profile(name, age, email, phone);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle error
        }
        return null; // Handle invalid data or return a default Profile
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
