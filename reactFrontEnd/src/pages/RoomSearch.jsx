import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import GuestHeader from "../components/GuestHeader";


function RoomSearch() {
    const navigate = useNavigate();
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');

    const handleDateConfirm = (e) => {
        e.preventDefault();
        if(end > start) {
            let dateRange = [start, end]
            sessionStorage.setItem('dates', JSON.stringify(dateRange));
            navigate('/reservation')
        }
    }

    return (
        <div>
            <GuestHeader/>
            <h1 className="content-start">Choose dates for your stay</h1>
            <form>
                <label htmlFor="start_date">Start date:</label>
                <input 
                    type="text" 
                    id="start_date" 
                    name="startDate"
                    placeholder="YYYY-MM-DD" 
                    pattern="(?:19|20)(?:(?:[13579][26]|[02468][048])-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))|(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31)))"
                    onChange={(e) => setStart(e.target.value)}
                    required/>
                <label htmlFor="end_date">End date:</label>
                <input 
                    type="text" 
                    id="end_date" 
                    name="endDate" 
                    placeholder="YYYY-MM-DD" 
                    pattern="(?:19|20)(?:(?:[13579][26]|[02468][048])-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))|(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31)))"
                    onChange={(e) => setEnd(e.target.value)}
                    required/>
                <button onClick={handleDateConfirm}>Confirm Dates</button>
            </form>
        </div>
    );
}

export default RoomSearch;