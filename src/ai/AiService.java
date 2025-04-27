package ai;

import java.util.List;

public interface AiService {
    List<String> getKeywordsFromMood(String moodText) throws Exception;
}
