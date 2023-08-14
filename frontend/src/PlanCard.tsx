import  './PlanCard.css'

export type PlanCardProps = {
    id: string,
    name: string,
}

export default function PlanCard(props: PlanCardProps) {
    return (
        <div className="card">
            <p>{props.name}</p>
            <button id="btn-edit">edit</button>
        </div>
    )
}