package org.heyrmi.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertyUtility {

    @SneakyThrows
    public static Properties readPropertiesFile(String fileName) {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        Properties properties = null;
        try (fileInputStream) {
            properties = new Properties();
            properties.load(fileInputStream);
            log.info("File Found and read");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            log.error("Unable to find file: " + fileNotFoundException.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            log.error("IO Exception occured: " + ioException.toString());
        }
        return properties;
    }
}
