import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://cat-fact.herokuapp.com/facts";
    public static ObjectMapper mapper = new ObjectMapper();
    public static List<FactsAboutCats> factsAboutCatsList = new ArrayList<>();
    public static List<FactsAboutCats> filteredFactsAboutCats = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        CloseableHttpResponse response = httpClient.execute(request);

        List<FactsAboutCats> factAboutCats = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {});
        //factAboutCats.forEach(System.out::println);
        factAboutCats.forEach(factsAboutCatsList::add);

        filteredFactsAboutCats = factsAboutCatsList.stream()
                .filter(x-> x.getUsed() == true)
                .collect(Collectors.toList());

        filteredFactsAboutCats.forEach(System.out::println);

    }

}
