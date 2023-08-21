import './App.css'
import {ChangeEvent, useEffect, useState} from "react";
import axios from "axios";
import './index.css'
import PlanListContainer from "./PlanListContainer.tsx";
import {Plan} from "./Plan.ts";
import Swal from 'sweetalert2'

export default function App() {
    const[planInput, setPlanInput] = useState<string>("");
    const[isHidden, setIsHidden] = useState<boolean>(true);
    const[planList, setPlanList] = useState<Plan[]>([]);
    const[text, setText] = useState<string>("");

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
            Swal.fire({
                icon: 'success',
                title: 'Success!',
                text: 'Your entry was saved!',
            })
            setText("");
        }else {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'You need to enter a name!',
            })
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
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'You need to enter a name!',
            })
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
      <h2>List of work schedules</h2>
      <div id="text-no-plans" style={text === "" ? {display:"none"} : {display:"block"}}>
          <p>{text}</p>
      </div>
        <button id="btn-newPlan" onClick={toggleHidden} style={!isHidden ? {display:"none"} : {display:"block"}}>New plan</button>
        <div id="form-box" style={isHidden ? {display:"none"} : {display:"block"}}>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder={"name of plan"} value={planInput} onChange={handleInputChange}/>
                <div id="btn-box">
                    <button id="button-add">Add</button>
                    <button id="button-back" onClick={toggleHidden} type={"button"}>Back</button>
                </div>
            </form>
        </div>
        <PlanListContainer plans={planList} editName={editName} deletePlan={deletePlan}/>
    </div>
  )
}
