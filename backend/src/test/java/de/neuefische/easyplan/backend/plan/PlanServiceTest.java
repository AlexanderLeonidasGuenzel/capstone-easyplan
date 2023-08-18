package de.neuefische.easyplan.backend.plan;

import de.neuefische.easyplan.backend.utils.IDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PlanServiceTest {
    private PlanService planService;
    private PlanRepository planRepository;
    private IDGenerator idGenerator;

    @BeforeEach
    void setup() {
         planRepository = spy(PlanRepository.class);
         idGenerator = spy(IDGenerator.class);
         planService = new PlanService(planRepository, idGenerator);
    }

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
        Assertions.assertEquals(expected, actual);
        verify(planRepository).save(expected);
    }

    @Test
    void getAllPlansShouldReturnAllPlans() {
        //GIVEN
        Plan plan1 = new Plan("1", "planName1");
        Plan plan2 = new Plan("2", "planName2");
        //WHEN
        when(planRepository.findAll()).thenReturn(List.of(plan1, plan2));
        //THEN
        Assertions.assertEquals(List.of(plan1, plan2), planService.getAllPlans());
        verify(planRepository).findAll();
    }

    @Test
    void getPlanByIdShouldReturnPlanWithGivenId() {
        //GIVEN
        Plan plan1 = new Plan("1", "planName1");
        //WHEN
        when(planRepository.findById("1")).thenReturn(Optional.of(plan1));
        //THEN
        Assertions.assertEquals(plan1, planService.getPlanById("1"));
        verify(planRepository).findById("1");
    }

    @Test
    void getPlanByIdShouldThrowExceptionIfPlanDoesNotExist() {
        //GIVEN
        //WHEN
        when(planRepository.findById("1")).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> planService.getPlanById("1"));
        verify(planRepository).findById("1");
    }

    @Test
    void editPlanShouldReturnEditedPlan() {
        // Arrange
        String id = "exampleId";
        PlanData planData = new PlanData();
        planData.setName("New Plan Name");

        Plan existingPlan = new Plan();
        existingPlan.setId(id);
        existingPlan.setName("Old Plan Name");

        when(planRepository.findById(id)).thenReturn(Optional.of(existingPlan));

        // Act
        planService.editPlan(id, planData);

        // Assert
        Assertions.assertEquals(planData.getName(), existingPlan.getName());
        verify(planRepository).save(existingPlan);
    }

    @Test
    void deletePlanShouldDeletePlanWithGivenId() {
        //GIVEN
        Plan plan1 = new Plan("1", "planName1");
        //WHEN
        when(planRepository.findById("1")).thenReturn(Optional.of(plan1));
        //THEN
        planService.deletePlan("1");
        verify(planRepository).deleteById("1");
    }

    @Test
    void givenPlanNotEqualsExpectedPlanAfterDelete() {

        //GIVEN
        Plan plan1 = new Plan("1", "planName1");
        Plan plan2 = new Plan("2", "planName2");
        Plan plan3 = new Plan("3", "planName3");
        List<Plan> givenPlan = List.of(plan1, plan2, plan3);
        List<Plan> expectedListAfterDelete = List.of(plan2, plan3);

        //WHEN
        when(planRepository.findAll()).thenReturn(expectedListAfterDelete);

        //THEN
        planService.deletePlan("1");

        List<Plan> actualListAfterDelete = planService.getAllPlans();

        Assertions.assertNotEquals(givenPlan, actualListAfterDelete);
        Assertions.assertEquals(expectedListAfterDelete, actualListAfterDelete);

        verify(planRepository).findAll();
        verify(planRepository).deleteById("1");
    }
}

