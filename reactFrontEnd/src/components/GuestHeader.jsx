import Button from "./Button"
import "./header.css"

function GuestHeader() {
    return (
        <div className="header">
            <Button text="Shop"/>
            <Button text="Reserve Room"/>
            <Button text="My Reservations"/>
            <Button text="My Bill"/>
        </div>
    )
}

export default GuestHeader
