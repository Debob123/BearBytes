import RoomModifyAndStatus from './RoomModifyAndStatus.jsx';
import singleRoom from '../images/hotelRoom1.jpg'
import { useState, useEffect } from 'react'



function ClerkRoomDisplay({rooms, setRooms}) {
    useEffect(() => {
        renderRooms();
    }, []);

    const [isLoading, setLoading] = useState(true);
    // Fetch all of the available rooms
    function renderRooms() {
        fetch('http://localhost:8080/room/getRooms', {
        mode: 'cors',
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
        })
        .then(response => response.json())
        .then(data => {
        // Set the array of room objects
        setRooms(data);
        setLoading(false);
        })
        .catch(error => console.error('Error creating room array:', error));
    }

    if(isLoading) {
        return <div>Loading...</div>
    }
    return (
        <div className="display">
            {rooms.length !== 0 ? rooms.map((room) =>(
                <RoomModifyAndStatus className="room-display" key={room.number} 
                rooms = {rooms}
                setRooms = {setRooms}
                imgLink={singleRoom} 
                type={room.type} 
                cost={room.dailyRate} 
                numBeds={room.numBeds}
                bedSize={room.bedSize}
                smokingAllowed={room.smokingAllowed}
                roomNum={room.number}
                quality={room.quality}
                floorNum={room.floor}/>
            )): <div>No rooms available</div>}
        </div>
    );
}

export default ClerkRoomDisplay;