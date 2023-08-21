import React from "react";
import './NewPlan.css'

type NewPlanProps = {
    isHidden: boolean;
    toggleHidden: () => void;
    handleSubmit: (event: React.ChangeEvent<HTMLFormElement>) => void;
    planInput: string;
    handleInputChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
}

export default function NewPlan(props: NewPlanProps) {
    return (
        <div className="NewPlan">
            <button id="btn-newPlan" onClick={props.toggleHidden} style={!props.isHidden ? {display:"none"} : {display:"block"}}>New plan</button>
            <div id="form-box" style={props.isHidden ? {display:"none"} : {display:"block"}}>
                <form onSubmit={props.handleSubmit}>
                    <input type="text" placeholder={"name of plan"} value={props.planInput} onChange={props.handleInputChange}/>
                    <div id="btn-box">
                        <button id="button-add">Add</button>
                        <button id="button-back" onClick={props.toggleHidden} type={"button"}>Back</button>
                    </div>
                </form>
            </div>
        </div>
    )
}