import ClerkRoomDisplay from '../clerkPageComponents/ClerkRoomDisplay';
import ClerkNavigation from '../clerkPageComponents/ClerkNavigation';
import Modal from 'react-modal';
import { useState } from 'react';
import './styles/clerkRoomPage.css';

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

function ClerkRoomPage() {
    const [modalIsOpen, setIsOpen] = useState(false);
    const [number, setNumber] = useState('');
    const [floor, setFloor] = useState('');
    const [numBeds, setNumBeds] = useState('');
    const [rate, setRate] = useState('');
    const [bedSize, setBedSize] = useState('TWIN');
    const [type, setType] = useState('SINGLE');
    const [quality, setQuality] = useState('ECONOMY');
    const [smokingAllowed, setSmokingAllowed] = useState(false);
    const [rooms, setRooms] = useState([]);

    function openModal() {
        setIsOpen(true);
    }
    
    function closeModal() {
        setNumber('');
        setFloor('');
        setNumBeds('');
        setRate('');
        setBedSize('TWIN');
        setType('SINGLE');
        setQuality('ECONOMY');
        setSmokingAllowed(false);
        setIsOpen(false);
    }

    function handleSubmit(e) {
        e.preventDefault();
        setNumber(parseInt(number));
        setFloor(parseInt(floor));
        setNumBeds(parseInt(numBeds));
        setRate(parseFloat(rate));
        let room = {
            "number": number,
            "numBeds": numBeds,
            "floor": floor,
            "dailyRate": rate,
            "smokingAllowed": smokingAllowed,
            "bedSize": bedSize,
            "type": type,
            "quality": quality
        }
        fetch('http://localhost:8080/room/add', {
        mode: 'cors',
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(room)
        })
        .then(response => response.json())
        .then(data => {
        if(data) {
            let newRooms = [...rooms]
            newRooms.push(room);
            newRooms.sort((a,b) => a.number - b.number);
            setRooms(newRooms);
        } else {
            console.log("Woops");
        }
        {closeModal()}
        })
        .catch(error => console.error('Error creating room array:', error));
    }

    return (
        <div>
            <ClerkNavigation/>
            <div className="content">
                <h1 className='text-center clerk-label'>Room Management</h1>
                <h2 className='text-center'>Room missing? <button onClick={openModal} className="room-button">Add a room</button></h2>
                <Modal
                    isOpen={modalIsOpen}
                    onRequestClose={closeModal}
                    style={customStyles}
                    contentLabel="Example Modal"
                >
                    <h2 className="modal-title">Add A Room</h2>
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
                           pattern="^[0-9\b]+$"/>
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
                           onChange={(e) => setNumBeds(e.target.value)} 
                           placeholder="1" 
                           required
                           pattern="^[0-9\b]+$"/>
                        <label htmlFor="rate">Daily Rate: </label>
                        <input type="tel"
                           id="rate"
                           name="rate" 
                           defaultValue={rate} 
                           onChange={(e) => setRate(e.target.value)} 
                           placeholder="175.50" 
                           required
                           pattern="^[0-9]*[.,]?[0-9]*$"/>
                        <label htmlFor="bedSize">Bed Size: </label>
                        <select id="bedSize" defaultValue={bedSize} onChange={(e) => setBedSize(e.target.value)}>
                            <option value="TWIN">Twin</option>
                            <option value="FULL">Full</option>
                            <option value="QUEEN">Queen</option>
                            <option value="KING">King</option>
                        </select>
                        <label htmlFor="type">Room Type: </label>
                        <select id="type" defaultValue={type} onChange={(e) => setType(e.target.value)}>
                            <option value="SINGLE">Single</option>
                            <option value="DOUBLE">Double</option>
                            <option value="FAMILY">Family</option>
                            <option value="SUITE">Suite</option>
                            <option value="DELUXE">Deluxe</option>
                            <option value="STANDARD">Standard</option>
                        </select>
                        <label htmlFor="quality">Quality: </label>
                        <select id="quality" defaultValue={quality} onChange={(e) => setQuality(e.target.value)}>
                            <option value="ECONOMY">Economy</option>
                            <option value="COMFORT">Comfort</option>
                            <option value="BUSINESS">Business</option>
                            <option value="EXECUTIVE">Executive</option>
                        </select>
                        <label>Smoking Allowed: </label>
                        <input type="checkbox"
                           value={smokingAllowed} 
                           onChange={(e) => setSmokingAllowed(e.target.checked)}/>
                        <button className="center-button" 
                            onClick={handleSubmit}
                            disabled={!number || !floor || !numBeds || !rate || !bedSize || !type || !quality}
                            >
                                submit</button>
                    </form>
                </Modal>
                <ClerkRoomDisplay 
                    rooms={rooms}
                    setRooms={setRooms}
                    />
            </div>
        </div>
    )
} 

export default ClerkRoomPage;
