import GuestHeader from '../components/GuestHeader';
import getSessionStorage from '../authentication/GetSessionStorage';
import { useNavigate } from 'react-router-dom';
import {useState, useEffect} from 'react';
import './styles/reservationPage.css'
import BoxDisplay from '../components/BoxDisplay.jsx';
import '../components/display.css';
import singleRoom from '../images/hotelRoom1.jpg'
import Button from '../components/Button';
const button = <Button text="Reserve"/>

function ReservationPage() {
    const [rooms, setRooms] = useState([]);
    const [addedRooms, setAddedRooms] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        renderRooms();
    }, []);

    const [isLoading, setLoading] = useState(true);
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
        setRooms(data);
        setLoading(false);
        })
        .catch(error => console.error('Error creating room array:', error));
    }

    const handleConfirm = () => {
        if(addedRooms.length === 0) {
            console.log("No reservation selected");
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

    return (
        <div>
            <GuestHeader/>
            <h1 className="content-start">Available rooms</h1>
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
                </div> };
            <div className="footer">
                <button className="footer-btn" onClick={handleRemove}>Remove Last Room Added</button>
                <button className="footer-btn" onClick={handleConfirm}>Confirm Reservation</button>
            </div>
        </div>
    );
}

export default ReservationPage;