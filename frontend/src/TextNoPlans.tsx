import './TextNoPlans.css'

type TextNoPlansProps = {
    text: string;
}

export default function TextNoPlans(props: TextNoPlansProps) {
    return (
        <div className="TextNoPlans">
            <div id="text-no-plans" style={props.text === "" ? {display:"none"} : {display:"block"}}><p>{props.text}</p></div>
        </div>
    )
}