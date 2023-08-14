import './PlanListContainer.css';
import PlanCard from "./PlanCard";
import {Plan} from "./Plan.ts";

type PlanListProps = {
    plans: Plan[];
    saveName: (id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) => void
}

export default function PlanListContainer(props: PlanListProps) {

    return (
        <div className="container">
            {
                props.plans.map(({id, name}) => (
                    <PlanCard key={id} id={id} name={name} saveName={props.saveName}/>
                ))
            }
        </div>
    )
}