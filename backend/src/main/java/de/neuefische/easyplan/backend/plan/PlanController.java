package de.neuefische.easyplan.backend.plan;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("{id}")
    public Plan getPlan(@PathVariable String id) {
        return planService.getPlan(id);
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planService.getAllPlans();
    }
}
