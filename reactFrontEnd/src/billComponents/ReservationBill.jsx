import './bill.css'

function ReservationBill({billedReservations, bill})  {

  function calculateDaysStayed(reservation) {
    let daysStayed = (Date.parse(reservation.endDate) - Date.parse(reservation.startDate)) / (1000 * 3600 * 24)
    return daysStayed;
  }

  return (
    <div>
      <div className="bill-container">
        <p className="category-text">Reservations</p>
        
        {billedReservations.map((reservation) => (
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