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

    public Plan getPlan(String id) {
        return planRepository.findById(id).orElseThrow();
    }

    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }
}
