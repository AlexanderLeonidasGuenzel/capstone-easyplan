import './App.css'
import {ChangeEvent, useEffect, useState} from "react";
import axios from "axios";
import './index.css'
import PlanListContainer from "./PlanListContainer.tsx";
import {Plan} from "./Plan.ts";

export default function App() {
    const[planInput, setPlanInput] = useState("");
    const[isHidden, setIsHidden] = useState(true);
    const[planList, setPlanList] = useState<Plan[]>([]);
    const[text, setText] = useState("");

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        setPlanInput(event.target.value);
    }

    function handleSubmit(event: ChangeEvent<HTMLFormElement>)  {
        event.preventDefault();
        if(planInput !== ""){
            axios.post('/api/plan', {
                name: planInput
            }).then(function (response) {
                setPlanList([...planList, response.data])})
                .catch(function (error) {
                    console.log(error);
                });
            setIsHidden(true);
            alert("plan successfully added");
            setText("");
        }else {
            alert("please enter a name for the plan")
        }
        setPlanInput("");
    }

    function getPlans() {
        axios.get('/api/plan')
            .then(function (response) {
                setPlanList(response.data)
                if(response.data.length === 0){
                    setText("There are no existing plans!")
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    function editName(id: string, nameInput: string, setNameInput: (input: string) => void, setPTag: (value: boolean) => void) {
        if (nameInput !== "") {
            axios.put('/api/plan/' + id, {
                name: nameInput
            })
                .then(() => {
                    setNameInput("");
                    setPTag(true);
                    getPlans();
                })
                .catch(function () {
                    console.log("plan not found");
                });
        } else {
            alert("please enter a name for the plan");
        }
    }

    function deletePlan(id: string) {
        axios.delete('/api/plan/' + id)
            .then(() => {
                getPlans();
            })
            .catch(function () {
                console.log("plan not found");
            });
    }

    useEffect(() => {
        getPlans()
    }, []);


    function toggleHidden() {
        setIsHidden(!isHidden);
    }

  return (
    <div id="app-content">
      <h2>Your work schedules</h2>
      <div id="text-no-plans">
          {text}
      </div>
        <PlanListContainer plans={planList} editName={editName} deletePlan={deletePlan}/>
        <button id="btn-newPlan" onClick={toggleHidden} style={!isHidden ? {display:"none"} : {display:"block"}}>new plan</button>
        <div id="form-box" style={isHidden ? {display:"none"} : {display:"block"}}>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder={"name of plan"} value={planInput} onChange={handleInputChange}/>
                <button id="button-add">add</button>
            </form>
            <button id="button-back" onClick={toggleHidden}>back</button>
        </div>
    </div>
  )
}

