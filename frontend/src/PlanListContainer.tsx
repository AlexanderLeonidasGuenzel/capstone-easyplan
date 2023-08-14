import './PlanListContainer.css';
import PlanCard from "./PlanCard";
import {Plan} from "./Plan.ts";


type PlanListProps = {
    plans: Plan[];

}



export default function PlanListContainer(props: PlanListProps) {

    return (
        <div className="container">
            {
                props.plans.map((plan) => (
                    <PlanCard key={plan.id} id={plan.id} name={plan.name}/>
                ))
            }
        </div>
    )
}