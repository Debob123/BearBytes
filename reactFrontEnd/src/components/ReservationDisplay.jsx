import React, { useState } from 'react';
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

function ReservationDisplay({ imgLink, reservation, reservations, setReservations}) {
    const [modalIsOpen, setIsOpen] = useState(false);
    const [hidden, setHidden] = useState('hidden');
    let numDays = (new Date(reservation.endDate + "T00:00-0500") - new Date(reservation.startDate + "T00:00-0500")) / (24 * 60 * 60 * 1000);
    let timeUntilReservation = (new Date(reservation.startDate + "T00:00-0500") - new Date()) / (24 * 60 * 60 * 1000);

    /*function handleClick() {
        let room = {
            "number": roomNum,
            "bedSize": bedSize,
            "dailyRate": cost,
            "numBeds": numBeds,
            "quality": quality,
            "smokingAllowed": smokingAllowed,
            "type": type,
        }
        const newAddedRooms = [...addedRooms]
        newAddedRooms.push(room);
        setAddedRooms(newAddedRooms);
        sessionStorage.setItem('rooms', JSON.stringify(addedRooms));
        let index = currRooms.findIndex(r => r.number === room.number);
        const newRooms = [
            ...currRooms.slice(0, index), // Elements before the one to delete
            ...currRooms.slice(index + 1) // Elements after the one to delete
        ];
        setCurrRooms(newRooms);
    } */

    function openModal() {
        setIsOpen(true);
        setHidden('hidden');
    }
    
    function closeModal() {
        setIsOpen(false);
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
                // Set the array of reservations
                if(data) {
                    closeModal();
                        let index = reservations.findIndex(r => r.reservationID === reservation.reservationID);
                        const updatedReservations = [
                        ...reservations.slice(0, index), // Elements before the one to delete
                        ...reservations.slice(index + 1) // Elements after the one to delete
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
                contentLabel="Example Modal"
            >
                <button type="button" onClick={closeModal} className="close-btn">X</button>
                <h1>Cancel Reservation?</h1>
                <p className={hidden + " red"}>Error removing reservation</p>
                <p className={hidden + " red"}>Please try again or contact hotel staff</p>
                <p>Start date: {reservation.startDate}</p>
                <p>End date:   {reservation.endDate}</p>
                <p>Nights staying: {numDays}</p>
                <p>Total cost: ${reservation.rate * numDays}</p>
                <p>Rooms: {reservation.rooms.map((room) =>(
                    <span key={room.number}>{room.number} </span>
                )
                )}</p>
                {timeUntilReservation < 2 ? <p>Cancellation fee: {reservation.rate*0.8}</p> : <p>No cancellation fee</p>}
                <p>Are you sure you would like to cancel this reservation?</p>
                <div>
                    <button onClick={confirmCancel}>Confirm</button>
                </div>
            </Modal>
            <img src={imgLink} alt="reservation" />
            <p>Start date: {reservation.startDate}</p>
            <p>End date: {reservation.endDate}</p>
            <p>Daily rate: {reservation.rate}</p>
            <p>Status: {reservation.reservationStatus}</p>
            <div className="res-btns">
                <button className="res-btn">Modify</button>
                <button className="res-btn" onClick={openModal}>Cancel</button>
            </div>
        </div>
    );
}

export default ReservationDisplay;