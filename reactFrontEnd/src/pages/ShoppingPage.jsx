import GuestNavigation from '../components/GuestNavigation'
import ProductDisplay from '../shopComponents/ProductDisplay';
import ShopHeader from '../shopComponents/ShopHeader';
import { useState, useEffect } from 'react'
import getSessionStorage from '../authentication/GetSessionStorage';
import '../shopComponents/shopDisplay.css';



function ShoppingPage() {

    const user = getSessionStorage('user', null);
    const [reservations, setReservations] = useState([]);
    const [currentlyStaying, setCurrentlyStaying] = useState(false);

    function renderReservations() {
        let body = JSON.stringify(user.username);

        fetch('http://localhost:8080/reservation/getReservations', {
        mode: 'cors',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
            body: body
        })
        .then(response => response.json())
        .then(data => {
            setReservations(data);
        })
    }

    function isCurrentlyStaying()  {
        reservations.map((reservation) => {
            const start = new Date(reservation.startDate);
            const end = new Date(reservation.endDate);
            // CHANGE DATE TO TIME IN BETWEEN RESERVATIONS TO ACCESS SHOP
            // EXAMPLE: 
            //const current = new Date('2024-05-27');
            const current = new Date();
            if((current.valueOf() >= start.valueOf()) && (current.valueOf() <= end.valueOf()))  {
                setCurrentlyStaying(true);
            }
        });
    }


    useEffect(() => {
        if(user !== null)  {
            renderReservations();
        }
    },[]);

    useEffect(() => {
        isCurrentlyStaying();
    });


    return (
        <div>
            <GuestNavigation/>
            <ShopHeader />
            {currentlyStaying ? 
                <ProductDisplay /> :
                <p className="not-staying-message">Must be currently staying in a room to access shop!</p>
            }
        </div>
    );
}

export default ShoppingPage;