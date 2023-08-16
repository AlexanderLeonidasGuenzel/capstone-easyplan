package de.neuefische.easyplan.backend.plan;
;
import de.neuefische.easyplan.backend.utils.IDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.test.annotation.DirtiesContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
class PlanServiceIntegrationTest {

    @MockBean
    private PlanRepository planRepository;

    @Autowired
    private PlanService planService;

    @MockBean
    private IDGenerator idGenerator;

    @Test
    @DirtiesContext
    void testAddPlan() {

        String generatedId = "generatedId";
        PlanData planData = new PlanData("New Plan");
        Plan mockPlan = new Plan(generatedId, planData.getName());

        when(idGenerator.generateId()).thenReturn(generatedId);
        when(planRepository.save(any(Plan.class))).thenReturn(mockPlan);

        Plan addedPlan = planService.addPlan(planData);

        assertNotNull(addedPlan);
        assertEquals(generatedId, addedPlan.getId());
        assertEquals("New Plan", addedPlan.getName());

        verify(idGenerator, times(1)).generateId();
        verify(planRepository, times(1)).save(any(Plan.class));
    }
    @Test
    @DirtiesContext
    void testGetAllPlans() {

        Plan plan1 = new Plan("id1", "Plan 1");
        Plan plan2 = new Plan("id2", "Plan 2");
        List<Plan> mockPlans = Arrays.asList(plan1, plan2);
        when(planRepository.findAll()).thenReturn(mockPlans);

        List<Plan> retrievedPlans = planService.getAllPlans();

        assertNotNull(retrievedPlans);
        assertEquals(2, retrievedPlans.size());

        Plan retrievedPlan1 = retrievedPlans.get(0);
        Plan retrievedPlan2 = retrievedPlans.get(1);

        assertEquals("id1", retrievedPlan1.getId());
        assertEquals("Plan 1", retrievedPlan1.getName());

        assertEquals("id2", retrievedPlan2.getId());
        assertEquals("Plan 2", retrievedPlan2.getName());

        verify(planRepository, times(1)).findAll();
    }

    @Test
    @DirtiesContext
    void testGetPlanById() {

        String id = "1";
        Plan mockPlan = new Plan(id, "example-name");
        when(planRepository.findById(id)).thenReturn(Optional.of(mockPlan));

        Plan retrievedPlan = planService.getPlanById(id);

        assertNotNull(retrievedPlan);
        assertEquals(id, retrievedPlan.getId());
        assertEquals("example-name", retrievedPlan.getName());

        verify(planRepository, times(1)).findById(id);
    }
}
