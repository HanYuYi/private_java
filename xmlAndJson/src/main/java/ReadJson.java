import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 用三方工具Jackson.core 反序列化 JSON
 */
class Desc {
    public Long id;
    public String name;
    public Map<String, String> author;
    public String isbn;
    public List<String> tags;
    public LocalDate date;
    @JsonDeserialize(using = BigNumDeserializer.class)
    public Integer bigNum;
}

/**
 * 自定义解析
 * 必须继承 JsonDeserializer，并且在JavaBean喜庆迎字段注解
 */
class BigNumDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString != null) {
            return new Integer(valueAsString.replace("-", ""));
        }
        return null;
    }
}


public class ReadJson {
    public static void main(String[] args) {
        InputStream resourceAsStream = ReadJson.class.getResourceAsStream("/desc.json");
        // 注册一个新的JavaTimeModule来解析date
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        // 解析时如果JavaBean不存在某个属性时，不会报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            System.out.println("---------------------序列化---------------------");
            // 序列化
            Desc desc = mapper.readValue(resourceAsStream, Desc.class);
            System.out.println(desc.id);
            System.out.println(desc.name);
            System.out.println(desc.author);
            System.out.println(desc.isbn);
            System.out.println(desc.tags);
            System.out.println(desc.date);
            System.out.println(desc.bigNum);
            System.out.println("---------------------反序列化---------------------");
            // 反序列化
            String json = mapper.writeValueAsString(desc);
            System.out.println(json);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
