package org.example.utilitarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties props;

    public PropertiesReader() {
        props = new Properties();
    }

    public String getPropertiesValue(String value){
        try {
            props.load(new FileInputStream(".\\src\\main\\java\\properties\\info.properties"));
            return props.getProperty(value);
        } catch (IOException e) {
            LoggerUtil.logError(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }


}
