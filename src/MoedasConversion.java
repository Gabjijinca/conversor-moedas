import java.util.Map;

public record MoedasConversion(String base_code, Map<String, Double> conversion_rates) {
}
