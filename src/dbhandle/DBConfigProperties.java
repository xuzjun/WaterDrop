package dbhandle;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author len
 */
public class DBConfigProperties {

    private Properties properties;
    private URL url;

    public DBConfigProperties(URL url) {
        this.url = url;
    }

    public void load() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(url.getPath()));
    }

    public String getDriver() {
        return properties.getProperty("oracle_driver_class");
    }

    public String getUrl(String key) {
        return properties.getProperty(key + "_url");
    }

    public String getUserName(String key) {
        return properties.getProperty(key + "_username");
    }

    public String getPassword(String key) {
        return properties.getProperty(key + "_password");
    }
}
