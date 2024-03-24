import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';

// Define custom styles for the modal
const customStyles = {
    content: {
        width: '30%', // make the modal 3 times smaller
        height: '30%',
        margin: 'auto',
    },
};

Modal.setAppElement('#root'); // This line is needed for accessibility reasons

function BoxDisplay({ imgLink, title, cost, availability }) {
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [isAvailable, setIsAvailable] = useState(true);

    function openModal() {
        setModalIsOpen(true);
    }

    function closeModal() {
        setModalIsOpen(false);
    }

    function addToCart() {
        // Add your logic to add the room to the cart
    }

    function checkAvailability() {
        // Check if availability is an array before calling includes on it
        if (Array.isArray(availability)) {
            setIsAvailable(availability.includes(startDate) && availability.includes(endDate));
        } else {
            setIsAvailable(false);
        }
    }

    // Call checkAvailability whenever startDate or endDate changes
    useEffect(() => {
        checkAvailability();
    }, [startDate, endDate]);

    return (
        <div className="box-display">
            <img src={imgLink} alt={title} />
            <h2>{title}</h2>
            <p>{cost}</p>
            <button onClick={openModal}>Reserve</button>
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel="Reservation Modal"
                style={customStyles}
            >
                <h2>Reserve {title} Room</h2>
                <label>
                    Start Date:
                    <input type="date" value={startDate} onChange={e => setStartDate(e.target.value)} />
                </label>
                <label>
                    End Date:
                    <input type="date" value={endDate} onChange={e => setEndDate(e.target.value)} />
                </label>
                {isAvailable ? <button onClick={addToCart}>Add to Cart</button> : <p>Room is not available on these dates.</p>}
                <button onClick={closeModal}>Close</button>
            </Modal>
        </div>
    );
}

export default BoxDisplay;