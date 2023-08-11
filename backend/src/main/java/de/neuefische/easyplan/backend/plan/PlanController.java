package de.neuefische.easyplan.backend.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public Plan addPlan(@RequestBody PlanData plan) {
        return planService.addPlan(plan);
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("{id}")
    public Plan getPlanById(@PathVariable String id) {
        return planService.getPlanById(id);
    }

    @PutMapping("{id}")
    public void editPlan(@PathVariable String id, @RequestBody PlanData planData) {
        planService.editPlan(id, planData);
    }
}
