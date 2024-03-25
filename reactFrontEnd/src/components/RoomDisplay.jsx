import { v4 as uuidv4 } from 'uuid';
import BoxDisplay from './BoxDisplay.jsx';
import './display.css';
import singleRoom from '../images/hotelRoom1.jpg'
import doubleRoom from '../images/doubleRoom1.jpg'
import Button from './Button';
const button = <Button text="Reserve"/>

const rooms = [
    {
        imgLink: singleRoom,
        roomType: "Single",
        rate: "$150",
        id: uuidv4()
    },
    {
        imgLink: doubleRoom,
        roomType: "Double",
        rate: "$220",
        id: uuidv4()
    },
    {
        imgLink: singleRoom,
        roomType: "Single",
        rate: "$170",
        id: uuidv4()
    },
    {
        imgLink: singleRoom,
        roomType: "Single",
        rate: "$120",
        id: uuidv4()
    },
    {
        imgLink: singleRoom,
        roomType: "Single",
        rate: "$140",
        id: uuidv4()
    },
    {
        imgLink: doubleRoom,
        roomType: "Double",
        rate: "$200",
        id: uuidv4()
    },
    {
        imgLink: doubleRoom,
        roomType: "Double",
        rate: "$300",
        id: uuidv4()
    },
    {
        imgLink: doubleRoom,
        roomType: "Double",
        rate: "$280",
        id: uuidv4()
    },
]


function RoomDisplay() {
    return (
        <div className="display">
            {rooms.map((room) =>(
                <BoxDisplay className="box-display" key={room.id} imgLink={room.imgLink} title={room.roomType} cost={room.rate} btn={button}/>
            ))}
        </div>
    );
}

export default RoomDisplay;