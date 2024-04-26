import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './styles/roomSearch.css'
import GuestNavigation from '../components/GuestNavigation';


function RoomSearch() {
    const navigate = useNavigate();
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');

    const handleDateConfirm = (e) => {
        e.preventDefault();
        let currDate = new Date();
        let startingDate = new Date(start + "T00:00-0500")
        let endingDate = new Date(end + "T00:00-0500");
        if(!isNaN(startingDate) && !isNaN(endingDate) && end > start && startingDate > currDate) {
            let dateRange = [start, end]
            sessionStorage.setItem('dates', JSON.stringify(dateRange));
            navigate('/reservation')
        }
    }

    return (
        <div>
            <GuestNavigation/>
            <div className="content">
            <h1 className="center bottom-gap">Choose dates for your stay</h1>
            <form className="form-grid">
                <div className="grid-element">
                    <label htmlFor="start_date">Start date:</label>
                    <input 
                        type="text" 
                        id="start_date" 
                        name="startDate"
                        placeholder="YYYY-MM-DD" 
                        pattern="\d{4}-\d{2}-\d{2}"
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
                        pattern="\d{4}-\d{2}-\d{2}"
                        onChange={(e) => setEnd(e.target.value)}
                        required/>
                </div>
                <button className="grid-element grid-btn" onClick={handleDateConfirm}>Confirm Dates</button>
            </form>
            </div>
        </div>
    );
}

export default RoomSearch;