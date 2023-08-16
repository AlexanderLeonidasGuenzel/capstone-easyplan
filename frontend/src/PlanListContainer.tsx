import './PlanListContainer.css';
import PlanCard from "./PlanCard";
import {Plan} from "./Plan.ts";
import {useEffect, useState} from "react";

type PlanListProps = {
    plans: Plan[];
    editName: (id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) => void
}

export default function PlanListContainer(props: PlanListProps) {

    const [isEmpty, setIsEmpty] = useState<boolean>( props.plans.length === 0);

    useEffect(() => {
        setIsEmpty(props.plans.length === 0);
    }, [props.plans]);

    return (
        <>
            {
                isEmpty? (<div></div>)
                : (<div className="container">
                        {
                            props.plans.map(({id, name}) => (
                                <PlanCard key={id} id={id} name={name} editName={props.editName}/>
                            ))
                        }
                    </div>)
            }
        </>



    )
}