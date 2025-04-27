package ai;

import java.util.ArrayList;
import java.util.List;

public class HuggingFaceService implements AiService {

    @Override
    public List<String> getKeywordsFromMood(String moodText) throws Exception {
        //first : return dummy word without call the server
        List<String> dummyKeywords = new ArrayList<>();
        dummyKeywords.add("calm");
        dummyKeywords.add("chill");
        dummyKeywords.add("relax");
        return dummyKeywords;
    }
}
