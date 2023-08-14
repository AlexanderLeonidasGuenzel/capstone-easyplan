import  './PlanCard.css'
import {Fragment, useState} from "react";

export type PlanCardProps = {
    id: string,
    name: string,
}

export default function PlanCard(props: PlanCardProps) {

    const [isPTag, setPTag] = useState(true);

    return (
        <div className="card">
            <Fragment>
                {isPTag
                    ? (<p>{props.name}</p>)
                    : (<input type="text"/>)}
            </Fragment>
            <Fragment>
                {isPTag
                    ? (<button id="btn-edit" onClick={() => setPTag(false)}>edit</button>)
                    : (<div><button id="btn-back" onClick={() => setPTag(true)}>back</button><button id="btn-save">save</button></div>)}

            </Fragment>

        </div>
    )
}