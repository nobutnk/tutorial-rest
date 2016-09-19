package tutorial.api.todo;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import tutorial.domain.service.todo.TodoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(locations = {
            "classpath*:META-INF/spring/applicationContext.xml",
            "classpath*:META-INF/spring/spring-security.xml",
            "classpath*:META-INF/spring/**/*-codelist.xml"}),
    @ContextConfiguration(locations = {
            "classpath:META-INF/spring/spring-mvc-rest.xml"})
        })
@WebAppConfiguration
public class TodoRestControllerTest {
    
    @Inject
    WebApplicationContext wac;
    
    @Mock
    Mapper beanMapper;
    
    @Inject
    TodoService todoService; 
    
    @Inject
    @InjectMocks
    private TodoRestController todoRestController;

    protected MockMvc mockMvc;
    
    @Before
    public void setup() {
        
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
        
        // TODO: Serviceのmockを記述する
//        Mockito.when(todoService.findAll()).thenThrow(new BusinessException("test"));

    }

    @Test
    public void expectSuccessTodoListTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/todos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(12)))
            .andExpect(jsonPath("$[0].todoId").value("9"))
            .andExpect(jsonPath("$[0].updatedAt").value("2016-09-18 01:16:12"))
            .andExpect(jsonPath("$[0].createdAt").value("2016-09-17T01:16:12.643"))
            .andExpect(jsonPath("$[0].finished").value(false))
            .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}
