import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConversao {
    public static MoedasConversion buscarTaxas(String apiKey, String moedaBase) {
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + moedaBase);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), MoedasConversion.class);
    }
}
