function Button({ text = "Button", color = "black", fontSize = 12, height, onClick = null, className = "" }) {
    const buttonStyle = {
        color: color,
        fontSize: fontSize + "px",
        borderRadius: "10px",
        height: height,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    };

    return (
        <button className={className} style={buttonStyle} onClick={onClick}>
            {text}
        </button>
    )
}

export default Button;