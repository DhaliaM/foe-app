package drunk.homebrew.forge.of.empires.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MapToJson {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapToJson.class);

    public String mapToJson(LoadProperties yamlData) throws JsonProcessingException{

        Map<String, Buildings> eventBuildings = yamlData.getBuildings();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT );

        String jsonString = mapper.writeValueAsString(eventBuildings);
        //String jsonString = eventBuildings.toString();

        return jsonString;
    }
}
