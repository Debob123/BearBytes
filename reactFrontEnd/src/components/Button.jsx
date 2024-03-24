function Button({text="Button", color="black", fontSize=12}) {
    const buttonStyle = {
        color: color,
        fontSize: fontSize + "px",
        borderRadius: "10px",
    };

    return (
        <button style={buttonStyle}>
            {text}
        </button>
    )
}

export default Button;