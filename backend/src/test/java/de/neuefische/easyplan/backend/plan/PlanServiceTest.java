package de.neuefische.easyplan.backend.plan;

import de.neuefische.easyplan.backend.utils.IDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

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

    }

    @Test
    void getPlanByIdShouldReturnPlanWithGivenId() {
        //GIVEN
        Plan plan1 = new Plan("1", "planName1");
        //WHEN
        when(planRepository.findById("1")).thenReturn(Optional.of(plan1));
        //THEN
        Assertions.assertEquals(plan1, planService.getPlanById("1"));

    }

    @Test
    void getPlanByIdShouldThrowExceptionIfPlanDoesNotExist() {
        //GIVEN
        //WHEN
        when(planRepository.findById("1")).thenReturn(Optional.empty());
        //THEN
        assertThrows(IllegalArgumentException.class, () -> planService.getPlanById("1"));

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

    }

    @Test
    void testDeletePlanSuccess() {
        // Arrange
        String planId = "somePlanId";

        // Act
        planService.deletePlan(planId);
        when(planRepository.existsById(planId)).thenReturn(false);

        // Assert
        Assertions.assertFalse(planRepository.existsById(planId));
    }

    @Test
    void testDeletePlanNotFound() {
        // Arrange
        String planId = "nonExistentPlanId";
        doThrow(new EmptyResultDataAccessException(1)).when(planRepository).deleteById(planId);

        // Act and Assert
        org.junit.jupiter.api.Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                () -> planService.deletePlan(planId)
        );
    }
}
