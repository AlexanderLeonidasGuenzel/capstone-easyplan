import  './PlanCard.css'
import {ChangeEvent, Fragment, useState} from "react";

export type PlanCardProps = {
    id: string,
    name: string,
    editName: (id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) => void
    deletePlan: (id: string) => void
}

export default function PlanCard(props: PlanCardProps) {

    const [isPTag, setPTag] = useState(true);
    const [nameInput, setNameInput] = useState("");

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setNameInput(event.target.value);
    }

    function handleBack() {
        setPTag(true);
        setNameInput("")
    }

    function handleSave() {
        props.editName(props.id, nameInput, setNameInput, setPTag);
    }

    function handleDelete() {
        props.deletePlan(props.id);
    }

    return (
        <div className="card">
            <Fragment>
                {isPTag
                    ? (<p>{props.name}</p>)
                    : (<input type="text" placeholder={props.name} value={nameInput} onChange={handleInputChange}/>)
                }
            </Fragment>
            <Fragment>
                {isPTag
                    ? (<div className="btn-box">
                        <button id="btn-edit" onClick={() => setPTag(false)}>Edit</button>
                        <button id="btn-delete" onClick={handleDelete}>Delete</button>
                        </div>)
                    :
                    (
                        <div className="btn-box">
                        <button id="btn-back" onClick={handleBack}>Back</button>
                        <button id="btn-save" onClick={handleSave}>Save</button>
                        </div>
                    )
                }
            </Fragment>
        </div>
    )
}