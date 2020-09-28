package br.com.pipa.userscorepointscontrol;

import br.com.pipa.userscorepointscontrol.controller.ScoreController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ScoreController.class)
@WithMockUser
public class ScoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addUserScore() throws Exception {
        String mockRequest = "{\"userId\" : 1 , \"points\": 10 }";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user-score-points-control/score")
                .accept(MediaType.APPLICATION_JSON).content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    public void getUserScore() throws Exception {
        String mockRequest = "1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user-score-points-control/{userId}/position")
                .param("userId",mockRequest);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }


}
