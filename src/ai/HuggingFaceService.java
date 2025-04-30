package ai;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class HuggingFaceService implements AiService {

    private static final String API_URL = "https://api-inference.huggingface.co/models/gpt2";
    private static final String API_TOKEN = System.getenv("HF_TOKEN");

    @Override
    public List<String> getKeywordsFromMood(String moodText) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Create the prompt for the model based on the user's mood
        String prompt = "Suggest 5 song titles for this mood: \"" + moodText + "\"\n\n1.";

        // Prepare the JSON request body
        JSONObject json = new JSONObject();
        json.put("inputs", prompt);
        String requestBody = json.toString();

        // Build the HTTP POST request to the HuggingFace API
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + API_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Handle non-200 responses (error cases)
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to connect to HuggingFace API: " + response.body());
        }

        // Get the raw response body
        String body = response.body();
        System.out.println("Response Body: " + body);

        // Parse the JSON response to get the "generated_text" field
        String generated = new org.json.JSONArray(body).getJSONObject(0).getString("generated_text");

        // Extract lines that look like numbered song titles (e.g., "1. Song Name")
        List<String> keywords = new ArrayList<>();
        for (String line : generated.split("\n")) {
            line = line.trim();
            if (line.matches("^\\d+\\.\\s+.+")) {
                // Remove the numbering (e.g., "1. ") and keep only the song title
                keywords.add(line.substring(line.indexOf(' ') + 1));
            }
        }

        return keywords;
    }

}
