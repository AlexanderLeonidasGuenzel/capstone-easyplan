import  './PlanCard.css'
import {ChangeEvent, Fragment, useState} from "react";

export type PlanCardProps = {
    id: string,
    name: string,
    saveName: (id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) => void
}

export default function PlanCard(props: PlanCardProps) {

    const [isPTag, setPTag] = useState(true);
    const [nameInput, setNameInput] = useState("");

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setNameInput(event.target.value);
    }

    return (
        <div className="card">
            <Fragment>
                {isPTag
                    ? (<p>{props.name}</p>)
                    : (<input type="text" placeholder={"new name"} value={nameInput} onChange={handleInputChange}/>)
                }
            </Fragment>
            <Fragment>
                {isPTag
                    ? (<button id="btn-edit" onClick={() => setPTag(false)}>edit</button>)
                    :
                    (
                        <div>
                        <button id="btn-back" onClick={() => setPTag(true)}>back</button>
                        <button id="btn-save" onClick={() => props.saveName(props.id, nameInput, setNameInput, setPTag)}>save</button>
                        </div>
                    )
                }
            </Fragment>
        </div>
    )
}