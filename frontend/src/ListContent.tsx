import TextNoPlans from "./TextNoPlans.tsx";
import NewPlan from "./NewPlan.tsx";
import PlanListContainer from "./PlanListContainer.tsx";
import {Plan} from "./Plan.ts";
import './ListContent.css'
import React from "react";

type ListContentProps = {
   text: string;
   isHidden: boolean;
   toggleHidden: () => void;
   planInput: string;
   handleInputChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
   handleSubmit: (event: React.ChangeEvent<HTMLFormElement>) => void;
   planList: Plan[];
   editName: (id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) => void;
   deletePlan: (id: string) => void;
}

export default function ListContent(props: ListContentProps) {

    return (
        <div className="ListContent">
            <h2>List of work schedules</h2>
            <TextNoPlans text={props.text}/>
            <NewPlan isHidden={props.isHidden} toggleHidden={props.toggleHidden} planInput={props.planInput} handleInputChange={props.handleInputChange} handleSubmit={props.handleSubmit} />
            <PlanListContainer plans={props.planList} editName={props.editName} deletePlan={props.deletePlan}/>
        </div >
    )
}

