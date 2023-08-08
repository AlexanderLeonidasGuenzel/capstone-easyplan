import './PlanListContainer.css';
import PlanCard from "./PlanCard";
import {Plan} from "./Plan.ts";


type PlanList = {
    plans: Plan[];

}

const plany: Plan = {id: "1", name: "cw-34"};
const plany2: Plan = {id: "2", name: "cw-35"};

const plans: Plan[] = [plany, plany2];

const p: PlanList = {plans: plans};

export default function PlanListContainer({plans}: PlanList) {
    return (
        <div className="container">
            {
                plans.map((plan) => (
                    <PlanCard key={plan.id} id={plan.id} name={plan.name}/>
                ))
            }
        </div>
    )
}
// <PlanCard key={plan.id} id={plan.id} name={plan.name}/>