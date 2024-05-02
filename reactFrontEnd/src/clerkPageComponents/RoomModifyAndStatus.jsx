import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import './roomModifyAndStatus.css'

const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
    },
};

// Make sure to bind modal to your appElement (https://reactcommunity.org/react-modal/accessibility/)
Modal.setAppElement('#root');

function RoomModifyAndStatus({ imgLink, type, cost, numBeds, bedSize, smokingAllowed, roomNum, quality, floorNum, rooms, setRooms }) {
    const [modalIsOpen, setIsOpen] = useState(false);
    const [number, setNumber] = useState(roomNum);
    const [floor, setFloor] = useState(floorNum);
    const [numberBeds, setNumberBeds] = useState(numBeds);
    const [rate, setRate] = useState(cost);
    const [bedSizes, setBedSizes] = useState(bedSize);
    const [roomType, setRoomType] = useState(type);
    const [roomQuality, setRoomQuality] = useState(quality);
    const [smokingIsAllowed, setSmokingIsAllowed] = useState(smokingAllowed);
    const [isHidden, setIsHidden] = useState("hidden");
    const [showReservations, setShowReservations] = useState(false);
    const [reservations, setReservations] = useState([]);
    const [reservationsModalIsOpen, setReservationsModalIsOpen] = useState(false);

    function modifyRoom(e) {
        e.preventDefault();
        setNumber(parseInt(number));
        setFloor(parseInt(floor));
        setNumberBeds(parseInt(numberBeds));
        setRate(parseFloat(rate));
        let modifiedRoom = {
            "number": number,
            "numBeds": numberBeds,
            "floor": floor,
            "dailyRate": rate,
            "smokingAllowed": smokingIsAllowed,
            "bedSize": bedSizes,
            "type": roomType,
            "quality": roomQuality
        }
        let modifyRooms = [
            {
                "number": roomNum,
                "numBeds": numBeds,
                "floor": floorNum,
                "dailyRate": cost,
                "smokingAllowed": smokingAllowed,
                "bedSize": bedSize,
                "type": type,
                "quality": quality
            },
            modifiedRoom
        ]
        fetch('http://localhost:8080/room/modify', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(modifyRooms)
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    let index = rooms.findIndex(r => r.number === roomNum);
                    const newRooms = [
                        ...rooms.slice(0, index), // Elements before the one to delete
                        ...rooms.slice(index + 1) // Elements after the one to delete
                    ];
                    newRooms.push(modifiedRoom);
                    newRooms.sort((a, b) => a.number - b.number);
                    setRooms(newRooms);
                    { closeModal() }
                } else {
                    setIsHidden('');
                    console.log("Woops");
                }
            })
            .catch(error => console.error('Error creating room array:', error));
    }

    function openModal() {
        setIsHidden('hidden');
        setIsOpen(true);
    }

    function closeModal() {
        setIsOpen(false);
    }

    function checkStatus() {
        fetch(`http://localhost:8080/room/status/${number}`, {
            mode: 'cors',
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                if (data.length > 0) {
                    let reservations = data.map((reservation, index) =>
                        <li key={index}>Occupied from {reservation.startDate} to {reservation.endDate}</li>
                    );
                    setReservations(reservations);
                } else {
                    setReservations(<li>Room has no reservations.</li>);
                }
                setShowReservations(true); // Add this line
            })
            .catch(error => console.error('Error checking room status:', error));
        setReservationsModalIsOpen(true);
    }
    function closeReservationsModal() {
        setReservationsModalIsOpen(false);
    }

    return (
        <div className="box-display">
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                style={customStyles}
                contentLabel="Example Modal"
            >
                <h2 className="modal-title">Modify Room</h2>
                <button type="button" onClick={closeModal} className="close-btn">X</button>
                <form className="form-container">
                    <label htmlFor="roomNum">Room Number: </label>
                    <input type="tel"
                        id="roomNum"
                        name="roomNum"
                        defaultValue={number}
                        onChange={(e) => setNumber(e.target.value)}
                        placeholder="111"
                        required
                        pattern="^[0-9\b]+$" />
                    <label htmlFor="floor">Floor: </label>
                    <input type="tel"
                        id="floor"
                        name="floor"
                        defaultValue={floor}
                        onChange={(e) => setFloor(e.target.value)}
                        placeholder="1-3"
                        pattern="[1-3]"
                        required
                    />
                    <label htmlFor="numBeds">Beds: </label>
                    <input type="tel"
                        id="numBeds"
                        name="numBeds"
                        defaultValue={numBeds}
                        onChange={(e) => setNumberBeds(e.target.value)}
                        placeholder="1"
                        required
                        pattern="^[0-9\b]+$" />
                    <label htmlFor="rate">Daily Rate: </label>
                    <input type="tel"
                        id="rate"
                        name="rate"
                        defaultValue={rate}
                        onChange={(e) => setRate(e.target.value)}
                        placeholder="175.50"
                        required
                        pattern="^[0-9]*[.,]?[0-9]*$" />
                    <label htmlFor="bedSize">Bed Size: </label>
                    <select id="bedSize" defaultValue={bedSize} onChange={(e) => setBedSizes(e.target.value)}>
                        <option value="TWIN">Twin</option>
                        <option value="FULL">Full</option>
                        <option value="QUEEN">Queen</option>
                        <option value="KING">King</option>
                    </select>
                    <label htmlFor="type">Room Type: </label>
                    <select id="type" defaultValue={type} onChange={(e) => setRoomType(e.target.value)}>
                        <option value="SINGLE">Single</option>
                        <option value="DOUBLE">Double</option>
                        <option value="FAMILY">Family</option>
                        <option value="SUITE">Suite</option>
                        <option value="DELUXE">Deluxe</option>
                        <option value="STANDARD">Standard</option>
                    </select>
                    <label htmlFor="quality">Quality: </label>
                    <select id="quality" defaultValue={quality} onChange={(e) => setRoomQuality(e.target.value)}>
                        <option value="ECONOMY">Economy</option>
                        <option value="COMFORT">Comfort</option>
                        <option value="BUSINESS">Business</option>
                        <option value="EXECUTIVE">Executive</option>
                    </select>
                    <label>Smoking Allowed: </label>
                    <input type="checkbox"
                        value={smokingAllowed}
                        onChange={(e) => setSmokingIsAllowed(e.target.checked)} />
                    <button className="center-button"
                        onClick={modifyRoom}>
                        submit</button>
                    <div className="center">
                        <p className={isHidden + " error-msg center"}>Failed to modify, the room number may be taken, or the room you are modifying does not exist</p>
                    </div>

                </form>
            </Modal>
            <Modal
                isOpen={reservationsModalIsOpen}
                onRequestClose={closeReservationsModal}
                style={customStyles}
                contentLabel="Reservations Modal"
            >
                {/* Render the reservations inside this modal */}
                <ul>{reservations}</ul>
            </Modal>
            <img src={imgLink} alt={type} />
            <p>Room number: {roomNum}</p>
            <p>Room type: {type}</p>
            <p>Bed size: {bedSize}</p>
            <p>Beds: {numBeds}</p>
            <p>Daily rate: ${Number(cost).toFixed(2)}</p>
            <p>Smoking allowed: {smokingAllowed ? "Yes" : "No"}</p>
            <div className='clerk-room-btns'>
                <button onClick={openModal}>Modify</button>
                <button onClick={checkStatus}>Status</button>
            </div>
        </div>
    );
}

export default RoomModifyAndStatus;