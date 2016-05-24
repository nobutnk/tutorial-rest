package tutorial.app.test;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class DateTimeApiTest {
    
    @Inject
    ObjectMapper objectMapper;

    @Test
    public void test() throws JsonParseException, JsonMappingException, IOException {
        
        String jsonString =
                "{\"user_name\":\"Tanaka\", \"created_at\":\"2016-05-24 11:11:11.999\"}";
        
        SampleBean bean  = objectMapper.readValue(jsonString, SampleBean.class);
        
        System.out.println(bean.getName());
        System.out.println(bean.getCreatedAt());
    }
}
