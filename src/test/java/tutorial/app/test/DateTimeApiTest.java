package tutorial.app.test;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class DateTimeApiTest {

    @Test
    public void test() throws JsonParseException, JsonMappingException, IOException {
        
        String jsonString =
                "{\"user_name\":\"Tanaka\", \"created_at\":\"2016-05-24 11:11:11.999\"}";
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
        SampleBean bean  = mapper.readValue(jsonString, SampleBean.class);
        
        System.out.println(bean.getName());
        System.out.println(bean.getCreatedAt());
    }
}
