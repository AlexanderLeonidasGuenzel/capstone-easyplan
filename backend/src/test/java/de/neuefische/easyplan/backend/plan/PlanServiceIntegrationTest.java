package de.neuefische.easyplan.backend.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class PlanServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddPlan() throws Exception {
        String requestBody = """
                {"id":"1", "name":"week-1"}
                """;
        ResultActions resultActions = mockMvc.perform(post("/api/plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.name").value("week-1"));
    }

}
