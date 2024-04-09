function Button({text="Button", color="black", fontSize=12, height, onClick=null}) {
    const buttonStyle = {
        color: color,
        fontSize: fontSize + "px",
        borderRadius: "10px",
        height: height 
    };

    return (
        <button style={buttonStyle} onClick={onClick}>
            {text}
        </button>
    )
}

export default Button;