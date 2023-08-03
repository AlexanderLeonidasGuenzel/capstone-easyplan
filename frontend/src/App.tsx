import './App.css'
import {ChangeEvent, useState} from "react";
import axios from "axios";

function App() {
    const[planInput, setPlanInput] = useState("");
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

  return (
    <>
      <h1>Welcome</h1>
      <h2>working plans</h2>
      <p className="">
        There are no existing plans!
      </p>
        <button>new plan</button>
        <form onSubmit={handleSubmit}>
            <label>name </label>
            <input type="text" placeholder={"week-1"} value={planInput} onChange={handleChange}/>
            <button>add</button>
        </form>
    </>
  )
}





export default App
