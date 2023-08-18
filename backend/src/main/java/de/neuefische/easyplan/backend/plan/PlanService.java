package de.neuefische.easyplan.backend.plan;

import de.neuefische.easyplan.backend.utils.IDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final IDGenerator idGenerator;

    public Plan addPlan(PlanData planData) {
        String id = idGenerator.generateId();
        Plan plan = new Plan(id, planData.getName());
        return planRepository.save(plan);
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    public Plan getPlanById(String id) {
        return planRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Plan with id " + id + " not found"));
    }

    public void editPlan(String id, PlanData planData) {
        Plan plan = getPlanById(id);
        plan.setName(planData.getName());
        planRepository.save(plan);
    }

    public void deletePlan(String id) {
        planRepository.deleteById(id);
    }
}
