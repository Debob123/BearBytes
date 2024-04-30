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
        let startingDate = new Date(start);
        let endingDate = new Date(end);

        startingDate.setMinutes(startingDate.getMinutes() - startingDate.getTimezoneOffset());
        endingDate.setMinutes(endingDate.getMinutes() - endingDate.getTimezoneOffset());

        console.log("Button Pressed!");

        console.log(`Start date: ${startingDate}, End date: ${endingDate}`); // Add this line



        if (!isNaN(startingDate) && !isNaN(endingDate) && end > start && startingDate > currDate) {
            let dateRange = [start, end]
            sessionStorage.setItem('dates', JSON.stringify(dateRange));
            console.log("Dates confirmed!");
            navigate('/reservation');
        }
    }

    return (
        <div>
            <GuestNavigation />
            <div className="content">
                <h1 className="center bottom-gap">Choose dates for your stay</h1>
                <form className="form-grid">
                    <div className="grid-element">
                        <label htmlFor="start_date">Start date:</label>
                        <input
                            type="date"
                            id="start_date"
                            name="startDate"
                            onChange={(e) => setStart(e.target.value)}
                            required />
                    </div>
                    <div className="grid-element">
                        <label htmlFor="end_date">End date:</label>
                        <input
                            type="date"
                            id="end_date"
                            name="endDate"
                            onChange={(e) => setEnd(e.target.value)}
                            required />
                    </div>
                    <button className="grid-element grid-btn" onClick={handleDateConfirm}>Confirm Dates</button>
                </form>
            </div>
        </div>
    );
}

export default RoomSearch;