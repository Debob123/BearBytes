import { useState, useEffect } from 'react';
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
    const [selectedFloor, setSelectedFloor] = useState(null);
    const [selectedBeds, setSelectedBeds] = useState(null);
    const [smoking, setSmoking] = useState(null);
    const [bedType, setBedType] = useState(null);
    const [roomType, setRoomType] = useState(null);
    const [qualityLevel, setQualityLevel] = useState(null);
    const [allRooms, setAllRooms] = useState([]);


    useEffect(() => {
        let filteredRooms = allRooms.filter(room =>
            (selectedFloor === null || room.floor === selectedFloor) &&
            (selectedBeds === null || room.numBeds === selectedBeds) &&
            (smoking === null || room.smokingAllowed === smoking) &&
            (bedType === null || bedType === room.bedSize) &&
            (roomType === null || roomType === room.type)
        );
        setRooms(filteredRooms);
    }, [selectedFloor, selectedBeds, smoking, bedType, roomType, allRooms]);

    // Fetch all of the available rooms
    const renderRooms = (e) => {
        e.preventDefault();
        setAddedRooms([]);
        let currDate = new Date();
        let startingDate = new Date(start + "T00:00:00-0500");
        let endingDate = new Date(end + "T00:00:00-0500");

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
                    setAllRooms(data);
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

    const handleFloorChange = (event) => {
        setSelectedFloor(event.target.value === 'All' ? null : parseInt(event.target.value));
    }

    const handleBedChange = (event) => {
        setSelectedBeds(event.target.value === 'All' ? null : parseInt(event.target.value));
    }

    const handleSmokingChange = (event) => {
        setSmoking(event.target.value === "Both" ? null : event.target.value === "Yes");
    }

    const handleBedTypeChange = (event) => {
        setBedType(event.target.value === 'All' ? null : event.target.value);
    }

    const handleRoomTypeChange = (event) => {
        setRoomType(event.target.value === 'All' ? null : event.target.value);
    }

    return (
        <div>
            <ClerkNavigation />
            <div className="content">

                <h1 className="center-text" style={{ fontSize: '4em' }} >Guest Reservation</h1>
                <div className='floor-number-filter' style={{ display: 'flex', justifyContent: 'center', flexWrap: 'wrap' }}>
                    <label htmlFor='floor-select'>Select Floor: </label>
                    <select id="floor-select" onChange={handleFloorChange} value={selectedFloor === null ? 'All' : selectedFloor}>
                        <option value="All">All Floors</option>
                        <option value="1">1st Floor</option>
                        <option value="2">2nd Floor</option>
                        <option value="3">3rd Floor</option>
                    </select>
                    <div style={{ margin: '0 10px' }}></div>
                    <label htmlFor='bed-number-select'>   Select Number of Beds: </label>

                    <select id="bed-select" onChange={handleBedChange} value={selectedBeds === null ? 'All' : selectedBeds}>
                        <option value="All">Any</option>
                        <option value="1">1 Bed</option>
                        <option value="2">2 Beds</option>
                        <option value="3">3 Beds</option>
                    </select>
                    <div style={{ margin: '0 10px' }}></div>

                    <label htmlFor='smoking-select'>   Select Smoking: </label>
                    <select id="smoking-select" onChange={handleSmokingChange} value={smoking === null ? "Both" : smoking ? "Yes" : "No"}>
                        <option value="Both">Both</option>
                        <option value="Yes">Yes</option>
                        <option value="No">No</option>
                    </select>
                    <div style={{ margin: '0 10px' }}></div>

                    <label htmlFor='bed-type-select'>   Select Bed Type: </label>
                    <select id="bed-type-select" onChange={handleBedTypeChange} value={bedType === null ? "All" : bedType}>
                        <option value="All">Any</option>
                        <option value="FULL">Full</option>
                        <option value="KING">King</option>
                        <option value="QUEEN">Queen</option>
                        <option value="TWIN">Twin</option>
                    </select>
                    <div style={{ margin: '0 10px' }}></div>

                    <label htmlFor='room-type-select'>   Select Room Type: </label>
                    <select id="room-type-select" onChange={handleRoomTypeChange} value={roomType === null ? "All" : roomType}>
                        <option value="All">Any</option>
                        <option value="SINGLE">Single</option>
                        <option value="DOUBLE">Double</option>
                        <option value="FAMILY">Family</option>
                        <option value="SUITE">Suite</option>
                        <option value="STANDARD">Standard</option>
                        <option value="DELUXE">Deluxe</option>
                    </select>
                </div>
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