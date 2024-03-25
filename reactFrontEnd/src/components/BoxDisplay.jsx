import Button from './Button';
import './display.css'

function BoxDisplay({imgLink, title="Title", cost="$1", btn=<Button />, btn2=null}) {
    return ( 
    <div className="box-display">
        <img src={imgLink} />
        <h3>{title}</h3>
        <p>{cost}</p>
        <div className="flex-container">
          {btn !== null && btn}
          {btn2 !== null && btn2}
        </div>
    </div>
    )
}

export default BoxDisplay;