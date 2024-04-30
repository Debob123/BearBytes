import Modal from 'react-modal';

Modal.setAppElement('#root'); // This line is needed for accessibility reasons

function BoxDisplay({ imgLink, type, cost, numBeds, bedSize, smokingAllowed, addedRooms, setAddedRooms, currRooms, setCurrRooms, roomNum, quality, btnAct="y"}) {

    function handleClick() {
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
    }

    return (
        <div className="box-display">
            <img src={imgLink} alt={type} />
            <p>Room number: {roomNum}</p>
            <p>Room type: {type}</p>
            <p>Bed size: {bedSize}</p>
            <p>Beds: {numBeds}</p>
            <p>Daily rate: ${cost}</p>
            <p>Smoking allowed: {smokingAllowed ? "Yes" : "No"}</p>
            {btnAct !== null && <button onClick={handleClick}>Reserve</button> }
        </div>
    );
}

export default BoxDisplay;