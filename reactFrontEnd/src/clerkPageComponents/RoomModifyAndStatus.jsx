import React, { useState, useEffect } from 'react';
import './roomModifyAndStatus.css'


function RoomModifyAndStatus({ imgLink, type, cost, numBeds, bedSize, smokingAllowed, roomNum, quality}) {

    return (
        <div className="box-display">
            <img src={imgLink} alt={type} />
            <p>Room number: {roomNum}</p>
            <p>Room type: {type}</p>
            <p>Bed size: {bedSize}</p>
            <p>Beds: {numBeds}</p>
            <p>Daily rate: {cost}</p>
            <p>Smoking allowed: {smokingAllowed ? "Yes" : "No"}</p>
            <div className='clerk-room-btns'>
                <button>Modify</button> 
                <button>Status</button> 
            </div>
        </div>
    );
}

export default RoomModifyAndStatus;