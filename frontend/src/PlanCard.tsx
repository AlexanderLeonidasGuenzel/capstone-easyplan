import  './PlanCard.css'
import {ChangeEvent, Fragment, useState} from "react";
import axios from "axios";

export type PlanCardProps = {
    id: string,
    name: string,
}

export default function PlanCard(props: PlanCardProps) {

    const [isPTag, setPTag] = useState(true);
    const[nameInput, setNameInput] = useState("");

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setNameInput(event.target.value);
    }

    function saveName() {
        axios.put('/api/plan/' + props.id, {
            name: nameInput
        })
            .then(function (response) {
                console.log(response.data.name)
            })
            .catch(function () {
                console.log("plan not found");
            });
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
                        <button id="btn-save" onClick={saveName}>save</button>
                        </div>
                    )
                }
            </Fragment>
        </div>
    )
}