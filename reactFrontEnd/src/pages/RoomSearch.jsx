import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import GuestHeader from "../components/GuestHeader";
import './styles/roomSearch.css'


function RoomSearch() {
    const navigate = useNavigate();
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');

    const handleDateConfirm = (e) => {
        e.preventDefault();
        let currDate = new Date();
        let startingDate = new Date(start + "T00:00-0500")
        if(!isNaN(startingDate) && end > start && startingDate > currDate ) {
            let dateRange = [start, end]
            sessionStorage.setItem('dates', JSON.stringify(dateRange));
            navigate('/reservation')
        }
    }

    return (
        <div>
            <GuestHeader/>
            <h1 className="content-start">Choose dates for your stay</h1>
            <form className="form-grid">
                <div className="grid-element">
                    <label htmlFor="start_date">Start date:</label>
                    <input 
                        type="text" 
                        id="start_date" 
                        name="startDate"
                        placeholder="YYYY-MM-DD" 
                        pattern="(?:19|20)(?:(?:[13579][26]|[02468][048])-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))|(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31)))"
                        onChange={(e) => setStart(e.target.value)}
                        required/>
                </div>
                <div className="grid-element">
                    <label htmlFor="end_date">End date:</label>
                    <input 
                        type="text" 
                        id="end_date" 
                        name="endDate" 
                        placeholder="YYYY-MM-DD" 
                        pattern="(?:19|20)(?:(?:[13579][26]|[02468][048])-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))|(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31)))"
                        onChange={(e) => setEnd(e.target.value)}
                        required/>
                </div>
                <button className="grid-element grid-btn" onClick={handleDateConfirm}>Confirm Dates</button>
            </form>
        </div>
    );
}

export default RoomSearch;