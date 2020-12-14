package drunk.homebrew.forge.of.empires.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class TransmitBuildings {
    Yaml yaml = new Yaml(new Constructor(LoadProperties.class));
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.yaml");
    LoadProperties yamlData = yaml.load(stream);




    public String transmitBuildings() throws JsonProcessingException {
        MapToJson mtj = new MapToJson();
        String jsonString = mtj.mapToJson(yamlData);

        return jsonString;
    }
}
