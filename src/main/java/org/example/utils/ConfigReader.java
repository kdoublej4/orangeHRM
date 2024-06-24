package org.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private ConfigReader() {}

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String key) {
        return properties.getProperty(key).trim();
    }
}
