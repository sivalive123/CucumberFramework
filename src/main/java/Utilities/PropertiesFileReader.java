package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {

	public final Properties properties;

	public PropertiesFileReader(String path) {

		// Example path config/configuration.properties

		BufferedReader bufferedReader;
		FileReader fileReader;

		try {
			fileReader = new FileReader(path);
			bufferedReader = new BufferedReader(fileReader);
			properties = new Properties();

			try {
				properties.load(bufferedReader);
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties not found at " + path);
		}
	}

	public static void main(String[] args) {
		PropertiesFileReader configFileReader = new PropertiesFileReader(new FilePaths().CONFIG_FILEPATH);
		System.out.println(configFileReader.properties.getProperty("browser"));

	}

}
