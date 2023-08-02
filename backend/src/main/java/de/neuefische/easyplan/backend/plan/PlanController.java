package de.neuefische.easyplan.backend.plan;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan")
public class PlanController {

        private final PlanService planService;

        public PlanController(PlanService planService) {
            this.planService = planService;
        }

        @PostMapping
        public Plan addPlan(@RequestBody Plan plan) {
            return planService.addPlan(plan);
        }
}
