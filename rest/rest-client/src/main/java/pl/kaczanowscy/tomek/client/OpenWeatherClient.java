package pl.kaczanowscy.tomek.client;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

public class OpenWeatherClient  {

    private final String serverUrl;

    public OpenWeatherClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public double getTemp(String city) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(serverUrl + "/find?mode=json&q=" + city);
        HttpResponse response = httpClient.execute(getRequest);
        Writer writer = new StringWriter();
        IOUtils.copy(response.getEntity().getContent(), writer);
        //System.out.println(writer.toString());
        httpClient.getConnectionManager().shutdown();
        return (Double) (JsonPath.read(writer.toString(), "$.list[0].main.temp"));
    }

    public static void main(String[] args) throws IOException {
        OpenWeatherClient client = new OpenWeatherClient("http://api.openweathermap.org/data/2.5");
        for (String s : Arrays.asList("cracow", "warsaw", "poznan")) {
            System.out.println(s + " : " + client.getTemp(s));
        }
    }
}