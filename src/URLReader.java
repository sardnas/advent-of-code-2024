import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class URLReader {

    private String sessionCookie;

    // Constructor to initialize session cookie from config
    public URLReader() {
        try {
            Properties props = new Properties();
            try (InputStream input = new FileInputStream("config.properties")) {
                props.load(input);
            }
            sessionCookie = props.getProperty("session_cookie");
            if (sessionCookie == null || sessionCookie.isEmpty()) {
                throw new IllegalArgumentException("Session cookie not found in config file");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize URLReader", e);
        }
    }

    // Method to fetch data from a URL
    public String getData(String urlString) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", "session=" + sessionCookie);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append("\n");
                    }
                }
            } else {
                throw new RuntimeException("Failed to fetch data. HTTP response code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching data from URL", e);
        }
        return response.toString();
    }
}
