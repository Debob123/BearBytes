import './clerkHeader.css'

function ClerkHeader() {
    return (
        <div className="clerk-header">
            <button className="c-header-btn">View Rooms</button>
            <button className="c-header-btn">Check in/out</button>
            <button className="c-header-btn">Modify Reservation</button>
            <button className="c-header-btn">Get Billing Info</button>
            <button className="c-header-btn">Reserve Room</button>
        </div>
    )
}

export default ClerkHeader
