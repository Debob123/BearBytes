import './bill.css'


function CancelationBill({cancelledReservations, bill})  {

  return (
    <div>
      <div className="bill-container">
        {cancelledReservations != null && cancelledReservations.length !== 0 ?
          <>
            <p className="category-text">Cancelations</p>
            {cancelledReservations.map((reservation) => (
              <>
                <p>Time of Stay: {reservation.startDate} - {reservation.endDate}</p>
                <ul>
                  {reservation.rooms.map((room) =>(
                    <li className="purchased-products">
                      <p className="product-name-text">-Room {room.number} {room.bedSize}</p>
                    </li>
                  ))}
                  <li className="purchased-products">
                    <p className="product-name-text">Cancelation fee: </p>
                    <p className="product-price-text">${reservation.cancellationFee.toFixed(2)}</p>
                  </li>
                </ul>
              </>
            ))}
            <p>Total Cancelation Fees: ${(Math.round(bill.cancelationTotal * 100) / 100).toFixed(2)}</p>
          </>
          :true}
        </div>
      </div>
  )
}

export default CancelationBill;