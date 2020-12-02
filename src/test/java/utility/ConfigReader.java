package utility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {
        try {
            String path = "src/test/resources/configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties=new Properties();
            properties.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key).trim();
    }
}
