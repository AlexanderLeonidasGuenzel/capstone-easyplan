import  './PlanCard.css'

export type PlanCardProps = {
    id: number,
    name: string,

}
export default function PlanCard(props: PlanCardProps) {
    return (
        <div className="card">
            <h3>{props.name}</h3>
        </div>
    )
}