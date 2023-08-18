package de.neuefische.easyplan.backend.plan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class PlanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlanRepository planRepository;



    @Test
    @DirtiesContext
    void testAddPlan() throws Exception {

        PlanData planData = new PlanData();
        planData.setName("Test Plan");

        Plan addedPlan = new Plan();
        addedPlan.setId("1");
        addedPlan.setName(planData.getName());

        String jsonRequest = "{\"name\": \"Test Plan\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plan")
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DirtiesContext
    void testGetAllPlans() throws Exception {

        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("1", "Test Plan1"));
        plans.add(new Plan("2", "Test Plan2"));
        planRepository.saveAll(plans);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DirtiesContext
    void testGetPlanById() throws Exception {

        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("1", "Test Plan1"));
        plans.add(new Plan("2", "Test Plan2"));
        planRepository.saveAll(plans);

        String planId = "1";
        Plan plan = new Plan("1", "Test Plan1");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan/{id}", planId))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().json("{\"id\":\"1\",\"name\":\"Test Plan1\"}")
                );

    }

    @Test
    @DirtiesContext
    void testEditPlan() throws Exception {

        String planId = "1";

        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("1", "Test Plan1"));
        plans.add(new Plan("2", "Test Plan2"));
        planRepository.saveAll(plans);
        String jsonRequest = "{\"name\": \"Test Plan\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/api/plan/{id}", planId)
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DirtiesContext
    void testDeletePlan() throws Exception {

        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("1", "Test Plan1"));
        plans.add(new Plan("2", "Test Plan2"));
        planRepository.saveAll(plans);

        String planId = "1";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plan/{id}", planId))
                .andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.content().json("{\"id\":\"1\",\"name\":\"Test Plan1\"}")
                );

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plan/{id}", planId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/plan/{id}", planId))
                    .andExpect(MockMvcResultMatchers.status().isOk()).
                    andExpect(MockMvcResultMatchers.content().json("{\"id\":\"1\",\"name\":\"Test Plan1\"}")
                    );
        } catch (Exception e) {
            System.out.println("Plan with id " + planId + " not found");
        }
    }
}
