import React, { useState, useEffect} from 'react';
import Modal from 'react-modal';

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

Modal.setAppElement('#root'); // This line is needed for accessibility reasons

function CheckInAndOut({ imgLink, reservation, reservations, setReservations}) {
    const [modalIsOpen, setIsOpen] = useState(false);
    const [modifyIsOpen, setModifyOpen] = useState(false);
    const [roomsIsOpen, setRoomsOpen] = useState(false);
    const [hidden, setHidden] = useState('hidden');
    const [roomErrHidden, setRoomErrHidden] = useState('hidden');
    const [dateErrHidden, setDateErrHidden] = useState('hidden');
    const [unknownErrHidden, setUnknownErrHidden] = useState('hidden');
    const [start, setStart] = useState(reservation.startDate);
    const [end, setEnd] = useState(reservation.endDate);
    const [availableRooms, setAvailableRooms] = useState([]);
    const [newReservation, setNewReservation] = useState(reservation);
    const [unavailableRooms, setUnavailableRooms] = useState([]);
    const [status, setStatus] = useState(reservation.reservationStatus);

    let numDays = (new Date(reservation.endDate + "T00:00-0500") - new Date(reservation.startDate + "T00:00-0500")) / (24 * 60 * 60 * 1000);
    let timeUntilReservation = (new Date(reservation.startDate + "T00:00-0500") - new Date()) / (24 * 60 * 60 * 1000);

    useEffect(() => {
        const storedStart = sessionStorage.getItem('reservationStartDate');
        const storedEnd = sessionStorage.getItem('reservationEndDate');
        const storedStatus = sessionStorage.getItem('reservationStatus');

        if (storedStart && storedEnd && storedStatus){
            setStart(storedStart);
            setEnd(storedEnd);
            setStatus(storedStatus);
        }

        let modifiedRes = {...newReservation};
        modifiedRes.startDate = start;
        modifiedRes.endDate = end;
        setNewReservation(modifiedRes);
        reservation.reservationStatus = storedStatus;
    }, [reservation.startDate, reservation.endDate]);

    useEffect(() => {
        sessionStorage.setItem('reservationStartDate', start);
        sessionStorage.setItem('reserationEndDate', end);
        sessionStorage.setItem('reservationStatus', status);
    }, [start, end, status]);

    useEffect(() => {
        if(!isNaN(new Date(start + "T00:00-0500")) && !isNaN(new Date(end + "T00:00-0500")) && end > start) {
            getAvailableRooms();
        }
        let modifiedRes = {...newReservation};
        modifiedRes.startDate = start;
        modifiedRes.endDate = end;
        setNewReservation(modifiedRes);
    }, [start, end])

    function openModal() {
        setIsOpen(true);
        setHidden('hidden');
    }
    
    function closeModal() {
        setIsOpen(false);
    }

    function openModify() {
        setModifyOpen(true);
        setRoomErrHidden('hidden');
        setDateErrHidden('hidden');
        setHidden('hidden');
    }

    function closeModify() {
        setModifyOpen(false);
    }

    function closeRooms() {
        setRoomsOpen(false);
    }

    function openRooms() {
        setRoomsOpen(true);
    }

    function removeRoom(room) {
        if(newReservation.rooms.length > 1) {
            let modifiedRes = {...newReservation};
            let newAvailableRooms = [...availableRooms];

            let index = modifiedRes.rooms.findIndex(r => r.number === room.number);
            modifiedRes.rooms = [...modifiedRes.rooms.slice(0, index), ...modifiedRes.rooms.slice(index + 1)];
            modifiedRes.rate -= room.dailyRate;
            setNewReservation(modifiedRes);

            newAvailableRooms.push(room);
            newAvailableRooms.sort((a,b) => a.number - b.number);
            setAvailableRooms(newAvailableRooms);
        }
    }

    function addRoom(room) {
        let modifiedRes = {...newReservation};

        let index = availableRooms.findIndex(r => r.number === room.number);
        setAvailableRooms([...availableRooms.slice(0, index), ...availableRooms.slice(index+1)])

        modifiedRes.rooms.push(room)
        modifiedRes.rooms.sort((a,b) => a.number - b.number);
        modifiedRes.rate += room.dailyRate;
        setNewReservation(modifiedRes);
    }

    function confirmModifications() {
        setRoomErrHidden('hidden');
        setDateErrHidden('hidden');
        let currDate = new Date();
        let startingDate = new Date(start + "T00:00-0500");
        let endingDate = new Date(end + "T00:00-0500");
        if( start.length === 10 && end.length === 10 && !isNaN(startingDate) && !isNaN(endingDate) && end > start && startingDate > currDate) {
            let modifiedRes = {...reservation};
            modifiedRes.startDate = start;
            modifiedRes.endDate = end;
            modifiedRes.reservationStatus = status;
            fetch('http://localhost:8080/reservation/modify', {
                mode: 'cors',
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify([reservation, modifiedRes])
            })
            .then(response => response.json())
            .then(data => {
                if(data.length === 0) {
                    closeModify();
                    let index = reservations.findIndex(r => r.reservationID === reservation.reservationID);
                    const updatedReservations = [...reservations];
                    updatedReservations[index] = modifiedRes;
                    setReservations(updatedReservations);
                } else if(data.length === 1 && data[0] === -1) {
                    setUnknownErrHidden('');
                } else {
                    setRoomErrHidden('');
                    setUnavailableRooms(data);
                }
            })
            .catch(error => console.error('Error deleting reservation: ', error));

            /*//Update status of each room to the selected status
            const modifiedRes = {...newReservation};
            modifiedRes.rooms.forEach(room => {
                room.status = status;
            })
            setNewReservation(modifiedRes);*/
        } else {
            setDateErrHidden('');
        }
    }

    function getAvailableRooms() {
        fetch('http://localhost:8080/room/getAvailableRooms', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify([start, end])
        })
        .then(response => response.json())
        .then(data => {
            newReservation.rooms.forEach(room => {
                let index = data.findIndex(r => r.number === room.number);
                if(index > -1) {
                    data = ([...data.slice(0, index), ...data.slice(index+1)])
                }
            })
            setAvailableRooms(data);
        })
        .catch(error => console.error('Error creating room array:', error));
    }

    function confirmCancel() {
        fetch('http://localhost:8080/reservation/remove', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reservation)
        })
        .then(response => response.json())
        .then(data => {
            if(data) {
                closeModal();
                let index = reservations.findIndex(r => r.reservationID === reservation.reservationID);
                const updatedReservations = [
                    ...reservations.slice(0, index),
                    ...reservations.slice(index + 1)
                ];
                setReservations(updatedReservations);
            } else {
                setHidden('');
            }
        })
        .catch(error => console.error('Error deleting reservation: ', error));
    }

    return (
        <div className="res-display">
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                style={customStyles}
                contentLabel="Cancel Reservation"
            >
                <button type="button" onClick={closeModal} className="close-btn">X</button>
                <h1>Cancel Reservation?</h1>
                <p className={hidden + " red"}>Error removing reservation</p>
                <p className={hidden + " red"}>Please try again or contact hotel staff</p>
                <p>Start date: {reservation.startDate}</p>
                <p>End date:   {reservation.endDate}</p>
                <p>Nights staying: {numDays}</p>
                <p>Total cost: ${reservation.rate * numDays}</p>
                <p>Rooms: {reservation.rooms.map((room) => (
                    <span key={room.number}>{room.number} </span>
                ))}</p>
                {timeUntilReservation > 0 ?
                    timeUntilReservation < 2 ? <p>Cancellation fee: {reservation.rate * 0.8}</p> : <p>No cancellation fee</p>
                    : <p>You cannot cancel a reservation after the start date</p>}
                {timeUntilReservation > 0 && (<div>
                    <p>Are you sure you would like to cancel this reservation?</p>
                    <div>
                        <button onClick={confirmCancel}>Confirm</button>
                    </div>
                </div>)}
            </Modal>

            <Modal
                isOpen={modifyIsOpen}
                onRequestClose={closeModify}
                style={customStyles}
                contentLabel="Modify Reservation"
            >
                <button type="button" onClick={closeModify} className="close-btn">X</button>
                <div>
                    <h1>Modify Reservation</h1>
                    <p className={hidden + " red"}>Error modifying reservation</p>
                    <div className="top-margin">
                        <label htmlFor="start_date">Start date: </label>
                        <input
                            type="text"
                            id="start_date"
                            name="startDate"
                            placeholder="YYYY-MM-DD"
                            pattern="\d{4}-\d{2}-\d{2}"
                            onChange={(e) => setStart(e.target.value)}
                            defaultValue={start}
                            required />
                    </div>
                    <div className="top-margin">
                        <label htmlFor="end_date">End date:</label>
                        <input
                            type="text"
                            id="end_date"
                            name="endDate"
                            placeholder="YYYY-MM-DD"
                            pattern="\d{4}-\d{2}-\d{2}"
                            onChange={(e) => setEnd(e.target.value)}
                            defaultValue={end}
                            required />
                    </div>
                    <p>Nights staying: {numDays}</p>
                    <p>Total cost: ${newReservation.rate * numDays}</p>
                    <div>Rooms:
                        <div>
                            <div className="grid-container">
                                <div className="grid-item">number</div>
                                <div className="grid-item">beds</div>
                                <div className="grid-item">quality</div>
                                <div className="grid-item">rate</div>
                                <div className="grid-item">status</div>
                            </div>
                            {newReservation.rooms.map((room) => (
                                <div className="grid-container" key={room.number}>
                                    <span className="grid-item">{room.number}</span>
                                    <span className="grid-item">{room.numBeds}</span>
                                    <span className="grid-item">{room.quality}</span>
                                    <span className="grid-item">{room.dailyRate}</span>
                                    <select
                                        id={`status-${reservation.reservationStatus}`}
                                        value={reservation.reservationStatus}
                                        onChange={(e) => setStatus(e.target.value)}
                                    >
                                        <option value="Reserved">Reserved</option>
                                        <option value="Checked In">Checked In</option>
                                        <option value="Checked Out">Checked Out</option>
                                        <option value="Cancelled">Cancelled</option>
                                    </select>
                                </div>
                            ))}
                        </div>
                    </div>
                    <div className="room-line"><button className="center room-add" onClick={openRooms}>Add Room</button></div>
                    <div className="flex-row"><p>Done?</p> <button className="conf-modify"onClick={confirmModifications}>Confirm Modifications</button> </div>
                    <div className={"red " + roomErrHidden}>Unable to confirm modifications because these</div>
                    <div className={"red " + roomErrHidden}>rooms are unavailable on selected dates: </div>
                    <div className={"red " + unknownErrHidden}>Unknown error occurred, please contact staff</div>
                    {unavailableRooms.length !== 0 && unavailableRooms[0] !== -1 && unavailableRooms.map((room) => (<span className={"red " + roomErrHidden}>{room} </span>))}
                    <div className={"red " + dateErrHidden}>Please ensure your dates are correct</div>
                </div>
            </Modal>

            <Modal
                isOpen={roomsIsOpen}
                onRequestClose={closeRooms}
                style={customStyles}
                contentLabel="Add rooms"
            >
                <button type="button" onClick={closeRooms} className="close-btn">X</button>
                <div>
                    <h1>Select new room</h1>
                    <div>Rooms:
                        {availableRooms.length === 0 ? <div> No rooms available</div> :
                            <div>
                                <div className="grid-container">
                                    <div className="grid-item">number</div>
                                    <div className="grid-item">beds</div>
                                    <div className="grid-item">quality</div>
                                    <div className="grid-item">rate</div>
                                    <div className="grid-item">add</div>
                                </div>
                                {availableRooms.map((room) => (
                                    <div className="grid-container" key={room.number}>
                                        <span className="grid-item">{room.number}</span>
                                        <span className="grid-item">{room.numBeds}</span>
                                        <span className="grid-item">{room.quality}</span>
                                        <span className="grid-item">{room.dailyRate}</span>
                                        <button className="room-btn" onClick={() => addRoom(room)}>Add</button></div>
                                ))}
                            </div>
                        }
                    </div>
                </div>
            </Modal>
            <img src={imgLink} alt="reservation" />
            <p>Start date: {reservation.startDate}</p>
            <p>End date: {reservation.endDate}</p>
            <p>Daily rate: {reservation.rate}</p>
            <p>Status: {reservation.reservationStatus}</p>
            <div className="res-btns">
                <button className="res-btn" onClick={openModify}>Modify</button>
                <button className="res-btn" onClick={openModal}>Cancel</button>
            </div>
        </div>
    );
}

export default CheckInAndOut;
