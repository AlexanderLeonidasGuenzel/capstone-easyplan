package de.neuefische.easyplan.backend.plan;

import de.neuefische.easyplan.backend.utils.IDGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class PlanServiceTest {
    PlanRepository planRepository = spy(PlanRepository.class);
    IDGenerator idGenerator = spy(IDGenerator.class);
    PlanService planService = new PlanService(planRepository, idGenerator);

    @Test
    void addPlanShouldReturnPlanWithGeneratedId() {
        //GIVEN
        PlanData planData = new PlanData("planName");
        Plan expected = new Plan("1", "planName");
        //WHEN
        when(idGenerator.generateId()).thenReturn("1");
        when(planRepository.save(expected)).thenReturn(expected);
        Plan actual = planService.addPlan(planData);
        //THEN
        assertEquals(expected, actual);
    }

}