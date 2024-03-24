import Button from './Button';
import './display.css'

function BoxDisplay({imgLink, title="Title", cost="$1", btn=<Button/>}) {
    return ( 
    <div className="box-display">
        <img src={imgLink} />
        <h3>{title}</h3>
        <p>{cost}</p>
        {btn}
    </div>
    )
}

export default BoxDisplay;