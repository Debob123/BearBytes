import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation';
import singleRoom from '../images/hotelRoom1.jpg'
import BoxDisplay from '../components/BoxDisplay.jsx';
import Button from '../components/Button';
import "./styles/clerkModifyReservations.css"

function ClerkMakeReservation() {
    const navigate = useNavigate();
    const button = <Button text="Reserve" />
    const [rooms, setRooms] = useState([]);
    const [addedRooms, setAddedRooms] = useState([]);
    const [submitted, setSubmitted] = useState(false);
    const [start, setStart] = useState('');
    const [end, setEnd] = useState('');

    // Fetch all of the available rooms
    const renderRooms = (e) => {
        e.preventDefault();
        setAddedRooms([]);
        let currDate = new Date();
        let startingDate = new Date(start + "T00:00:00-0500");
        let endingDate = new Date(end + "T00:00:00-0500");

        startingDate.setMinutes(startingDate.getMinutes() - startingDate.getTimezoneOffset());
        endingDate.setMinutes(endingDate.getMinutes() - endingDate.getTimezoneOffset());

        console.log("Button Pressed!");



        console.log(`Start date: ${startingDate}, End date: ${endingDate}`);

        if (!isNaN(startingDate) && !isNaN(endingDate) && end > start && startingDate > currDate) {
            let dateRange = [start, end]
            sessionStorage.setItem('dates', JSON.stringify(dateRange));

            fetch('http://localhost:8080/room/getAvailableRooms', {
                mode: 'cors',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dateRange)
            })
                .then(response => response.json())
                .then(data => {
                    // Set the array of room objects
                    setRooms(data);
                    setSubmitted(true);
                })
                .catch(error => console.error('Error creating room array:', error));
        }
    }

    const handleConfirm = () => {
        if (addedRooms.length !== 0) {
            sessionStorage.setItem('rooms', JSON.stringify(addedRooms));
            navigate("/clerkConfirmReservation")
        }
    }

    const displayRooms = () => {
        return (
            submitted &&
            (rooms.length !== 0 ?
                <div>
                    <div className="display">
                        {rooms.map((room) =>
                            <BoxDisplay className="box-display"
                                key={room.number}
                                imgLink={singleRoom}
                                type={room.type}
                                cost={room.dailyRate}
                                numBeds={room.numBeds}
                                bedSize={room.bedSize}
                                smokingAllowed={room.smokingAllowed}
                                addedRooms={addedRooms}
                                setAddedRooms={setAddedRooms}
                                currRooms={rooms}
                                setCurrRooms={setRooms}
                                roomNum={room.number}
                                quality={room.quality}
                                btn={button} />
                        )
                        }
                    </div>
                    <div className="footer">
                        <button className="footer-btn" onClick={handleRemove}>Remove Last Room Added</button>
                        <button className="footer-btn" onClick={handleConfirm}>Confirm Reservation</button>
                    </div>
                </div> :
                <p>There are no rooms available on these dates</p>)
        )
    }

    const handleRemove = () => {
        if (addedRooms.length > 0) {
            let r = addedRooms.pop();

            let newRooms = [...rooms];
            newRooms.push(r);
            newRooms.sort((a, b) => a.number - b.number);
            setRooms(newRooms);
            sessionStorage.setItem('rooms', JSON.stringify(addedRooms));
        }
    }

    return (
        <div>
            <ClerkNavigation />
            <div className="content">
                <h1 className="center-text" style={{ fontSize: '4em' }} >Guest Reservation</h1>
                <div className="center-input top-space">
                    <label htmlFor="start_date">Start date:</label>
                    <input
                        type="date"
                        id="start_date"
                        name="startDate"
                        onChange={(e) => setStart(e.target.value)}
                        required />
                    <label htmlFor="end_date">End date:</label>
                    <input
                        type="date"
                        id="end_date"
                        name="endDate"
                        onChange={(e) => setEnd(e.target.value)}
                        required />
                    <button className="submit-btn" onClick={renderRooms}>Submit</button>
                </div>
                {displayRooms()}
            </div>
        </div>
    )
}

export default ClerkMakeReservation