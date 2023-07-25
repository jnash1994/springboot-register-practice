package Nash.example.register.controller;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
//        RequestBuilder  requestBuilder= MockMvcRequestBuilders
//               .get("https://jsonplaceholder.typicode.com/posts/1");
//         mockMvc.perform( requestBuilder).andExpect(MockMvcResultMatchers.status().is(404));

      RequestBuilder  requestBuilder2= MockMvcRequestBuilders
              .post("http://localhost:8080/user/fortest/{id}",3);
          mockMvc.perform( requestBuilder2)
                  .andExpect(MockMvcResultMatchers.status().is(200))
                  .andExpect(jsonPath("$.name",equalTo("幹")))
                  .andExpect(jsonPath("$.age",equalTo(40)));



    }

}