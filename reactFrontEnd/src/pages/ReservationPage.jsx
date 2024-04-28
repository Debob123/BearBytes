import getSessionStorage from '../authentication/GetSessionStorage';
import { useNavigate } from 'react-router-dom';
import {useState, useEffect} from 'react';
import './styles/reservationPage.css'
import BoxDisplay from '../components/BoxDisplay.jsx';
import '../components/display.css';
import singleRoom from '../images/hotelRoom1.jpg'
import Button from '../components/Button';
import GuestNavigation from '../components/GuestNavigation.jsx';

function ReservationPage() {
    const button = <Button text="Reserve"/>
    const [rooms, setRooms] = useState([]);
    const [addedRooms, setAddedRooms] = useState([]);
    const [isLoading, setLoading] = useState(true);
    const [selectedFloor, setSelectedFloor] = useState(null);
    const [selectedBeds, setSelectedBeds] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        renderRooms();
    }, [selectedFloor, selectedBeds]);

    // Fetch all of the available rooms
    function renderRooms() {
        fetch('http://localhost:8080/room/getAvailableRooms', {
        mode: 'cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(getSessionStorage('dates',null))
        })
        .then(response => response.json())
        .then(data => {
        // Set the array of room objects
        let filteredRooms = data.filter(room => (selectedFloor === null || room.floor === selectedFloor) &&
            selectedBeds === null || room.numBeds === selectedBeds);
        setRooms(filteredRooms);
        setLoading(false);
        })
        .catch(error => console.error('Error creating room array:', error));
    }

    const handleConfirm = () => {
        if(addedRooms.length === 0) {
            console.log("No rooms selected");
        } else {
            sessionStorage.setItem('rooms', JSON.stringify(addedRooms));
            navigate("/confirmReservationPage");
        }
    }

    const handleRemove = () => {
        if(addedRooms.length > 0) {
            let r = addedRooms.pop();
   
            let newRooms = [...rooms];
            newRooms.push(r);
            newRooms.sort((a,b) => a.number - b.number);
            setRooms(newRooms);
            sessionStorage.setItem('rooms', JSON.stringify(addedRooms));
        }
    }

    const handleFloorChange = (event) => {
        setSelectedFloor(event.target.value === 'All' ? null : parseInt(event.target.value));
    }

    const handleBedChange = (event) => {
        setSelectedBeds(event.target.value === 'All' ? null : parseInt(event.target.value));
    }

    return (
        <div>
            <GuestNavigation/>
            <div className="content">
                <h1 className='center-text'>Available rooms</h1>
                <div className='floor-number-filter'>
                    <label htmlFor='floor-select'>Select Floor: </label>
                    <select id="floor-select" onChange={handleFloorChange} value={selectedFloor === null ? 'All' : selectedFloor}>
                        <option value="All">All Floors</option>
                        <option value="1">1st Floor</option>
                        <option value="2">2nd Floor</option>
                        <option value="3">3rd Floor</option>
                    </select>
                    <label htmlFor='bed-number-select'>   Select Number of Beds: </label>
                    <select id="bed-select" onChange={handleBedChange} value={selectedBeds === null ? 'All' : selectedBeds}>
                        <option value="All">Any</option>
                        <option value="1">1 Bed</option>
                        <option value="2">2 Beds</option>
                        <option value="3">3 Beds</option>
                    </select>
                </div>
                    {isLoading ? <div>Loading...</div> 
                    : <div className="display"> 
                       { rooms.length !== 0 ? rooms.map((room) =>
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
                            btn={button}/> 
                        )  : <div>No rooms available</div>}  
                    </div> }
                <div className="footer">
                    <button className="footer-btn" onClick={handleRemove}>Remove Last Room Added</button>
                    <button className="footer-btn" onClick={handleConfirm}>Confirm Reservation</button>
                </div>
            </div>
        </div>
    );
}

export default ReservationPage;