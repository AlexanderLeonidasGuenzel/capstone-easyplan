import './App.css'
import {ChangeEvent, useState} from "react";
import axios from "axios";

function App() {
    const[planInput, setPlanInput] = useState("");
    const[isHidden, setIsHidden] = useState(true);
    function handleChange(event: ChangeEvent<HTMLInputElement>) {
        setPlanInput(event.target.value);
    }
    function handleSubmit(event: ChangeEvent<HTMLFormElement>)  {
        event.preventDefault();
        axios.post('/api/plan', {
            name: planInput
        })
            .catch(function (error) {
                console.log(error);
            });
        setPlanInput("");
    }

    function toggleShowForm() {
        setIsHidden(!isHidden);
    }

  return (
    <>
      <h1>Welcome</h1>
      <h2>working plans</h2>
      <p className="">
        There are no existing plans!
      </p>
        <button onClick={toggleShowForm}>new plan</button>
        <div style={isHidden ? {display:"none"} : {display:"block"}}>
            <form onSubmit={handleSubmit}>
                <label>name </label>
                <input type="text" placeholder={"week-1"} value={planInput} onChange={handleChange}/>
                <button>add</button>
            </form>
        </div>

    </>
  )
}





export default App
