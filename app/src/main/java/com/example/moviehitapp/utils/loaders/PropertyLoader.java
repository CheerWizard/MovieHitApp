package com.example.moviehitapp.utils.loaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**This class will load properties from entered property directory.*/
public final class PropertyLoader {
    //global property var
    private static Properties properties = new Properties();
    //load properties object from appropriate .properties directory
    public static synchronized void load(String propertyPath) {
        try (InputStream inputStream = new FileInputStream(propertyPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties getProperties() {
        return properties;
    }
    //get value of appropriate property
    public static String getPropertyValue(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}