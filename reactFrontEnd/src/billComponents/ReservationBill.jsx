import './bill.css'

<<<<<<< HEAD
function ReservationBill({billedReservations, bill})  {
=======
function ReservationBill() {

  const user = getSessionStorage('user', null);
  const [reservations, setReservations] = useState([]);

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
>>>>>>> main

  function calculateDaysStayed(reservation) {
    let daysStayed = (Date.parse(reservation.endDate) - Date.parse(reservation.startDate)) / (1000 * 3600 * 24)
    return daysStayed;
  }

<<<<<<< HEAD
=======
  useEffect(() => {
    if (user !== null) {
      renderReservations();
    }
  }, []);

  let reservationTotal = 0.0;
  reservations.forEach(r => reservationTotal += r.rooms.reduce((sum, { dailyRate }) => sum + dailyRate, 0) * calculateDaysStayed(r));

>>>>>>> main
  return (
    <div>

      <div className="bill-container">
        <p className="category-text">Reservations</p>
<<<<<<< HEAD
        
        {billedReservations.map((reservation) => (
=======

        {reservations.map((reservation) => (
>>>>>>> main
          <>
            <p>Time of Stay: {reservation.startDate} - {reservation.endDate}</p>
            <ul>
              {reservation.rooms.map((room) => (
                <li className="purchased-products">
                  <p className="product-name-text">-Room {room.number} {room.bedSize}</p>
                  <p className="product-price-text">${room.dailyRate.toFixed(2)} X {calculateDaysStayed(reservation)} nights</p>
                </li>
              ))}
            </ul>
          </>
        ))}
        <p>Cost of Stay Total: ${(Math.round(bill.reservationTotal * 100) / 100).toFixed(2)}</p>
      </div>
    </div>
  )
}

export default ReservationBill;