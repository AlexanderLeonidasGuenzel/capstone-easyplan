package de.neuefische.easyplan.backend.plan;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;


@WebMvcTest(PlanController.class)
class PlanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanService planService;

    @Test
    void testAddPlan() throws Exception {

        PlanData planData = new PlanData();
        planData.setName("Test Plan");

        Plan addedPlan = new Plan();
        addedPlan.setId("1");
        addedPlan.setName(planData.getName());

        Mockito.when(planService.addPlan(Mockito.any(PlanData.class))).thenReturn(addedPlan);
        String jsonRequest = "{\"name\": \"Test Plan\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/plan")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllPlans() throws Exception {

        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("1", "Test Plan1"));
        plans.add(new Plan("2", "Test Plan2"));

        Mockito.when(planService.getAllPlans()).thenReturn(plans);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPlanById() throws Exception {

        String planId = "1";
        Plan plan = new Plan("1", "Test Plan1");
        Mockito.when(planService.getPlanById(planId)).thenReturn(plan);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan/{id}", planId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testEditPlan() throws Exception {

        String planId = "1";
        PlanData planData = new PlanData("Test Plan");
        Mockito.doNothing().when(planService).editPlan(Mockito.eq(planId), Mockito.any(PlanData.class));

        String jsonRequest = "{\"name\": \"Test Plan\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/plan/{id}", planId)
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeletePlan() throws Exception {

        String planId = "1";
        Mockito.doNothing().when(planService).deletePlan(planId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plan/{id}", planId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(planService).deletePlan(planId);
    }
}
