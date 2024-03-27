import React, { useState, useEffect } from 'react';
import './roomModifyAndStatus.css'


function RoomModifyAndStatus({ imgLink, title, cost}) {

    return (
        <div className="box-display">
            <img src={imgLink} alt={title} />
            <h2>{title}</h2>
            <p>{cost}</p>
            <div className='clerk-room-btns'>
                <button>Modify</button> 
                <button>Status</button> 
            </div>
        </div>
    );
}

export default RoomModifyAndStatus;