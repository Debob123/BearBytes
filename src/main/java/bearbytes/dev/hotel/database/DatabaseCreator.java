package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.floor.Room;

import java.sql.*;

/**
 * The DatabaseCreator class starts the database for the hotel, allowing for
 * data to be stored in it.
 */
public class DatabaseCreator {
    // The driver of the connected database.
    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    // The connection string of the database.
    private static final String DB_CONNECTION = "jdbc:derby:myDB;create=true";

    // The user String for the database.
    private static final String DB_USER = "";

    // The password String for the database.
    private static final String DB_PASSWORD = "";

    /**
     * The main function deletes all old database tables if they exist, and then
     * creates the necessary tables for the hotel's website to start.
     * 
     * @param args A list of string arguments that could be passed, unused.
     */
    public static void main(String[] args) {
        deleteTables();
        createTables();
    }

    /**
     * Gets the Connection object that connects to the database.
     * 
     * @return The connection to the database.
     */
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /**
     * Adds the default rooms to the hotel database.
     * 
     * @param c The connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static void addDefaultRooms(Connection c) throws SQLException {
        int[] roomNums = { 101, 102, 103, 104, 105, 106 };
        int[] floors = { 1, 1, 1, 1, 1, 1 };
        int[] numBeds = { 1, 2, 1, 2, 4, 1 };
        double[] rates = { 175.25, 200.00, 150.00, 200.00, 400.00, 100.00 };
        boolean[] smokingAllowed = { true, true, false, false, false, true };
        String[] bedSizes = { "TWIN", "FULL", "QUEEN", "FULL", "KING", "QUEEN" };
        String[] roomTypes = { "SINGLE", "DOUBLE", "SINGLE", "DOUBLE", "FAMILY", "SINGLE" };
        String[] qualities = { "ECONOMY", "ECONOMY", "BUSINESS", "ECONOMY", "COMFORT", "EXECUTIVE" };
        String room = "INSERT INTO APP.Rooms(roomNumber,floor,numBeds,dailyRate,smokingAllowed,bedSize,type,quality) values(?,?,?,?,?,?,?,?)";

        for (int i = 0; i < roomNums.length; i++) {
            PreparedStatement ps = c.prepareStatement(room);

            ps.setInt(1, roomNums[i]);
            ps.setInt(2, floors[i]);
            ps.setInt(3, numBeds[i]);
            ps.setDouble(4, rates[i]);
            ps.setBoolean(5, smokingAllowed[i]);
            ps.setString(6, bedSizes[i]);
            ps.setString(7, roomTypes[i]);
            ps.setString(8, qualities[i]);
            ps.executeUpdate();
        }
    }

    /**
     * Adds the default accounts to the database.
     * 
     * @param c The connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static void addDefaultAccounts(Connection c) throws SQLException {
        String sql = "INSERT INTO APP.GuestAccounts(username, password) values(?,?)";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "guest");
        ps.setString(2, AccountAuthenticator.hashPassword("password"));
        ps.executeUpdate();
        ps = c.prepareStatement(sql);
        ps.setString(1, "guest1");
        ps.setString(2, AccountAuthenticator.hashPassword("notpassword"));
        ps.executeUpdate();
        sql = "INSERT INTO APP.ClerkAccounts(username, password) values(?,?)";
        ps = c.prepareStatement(sql);
        ps.setString(1, "clerk");
        ps.setString(2, AccountAuthenticator.hashPassword("password"));
        ps.executeUpdate();
        sql = "INSERT INTO APP.ManagerAccounts(username, password) values(?,?)";
        ps = c.prepareStatement(sql);
        ps.setString(1, "manager");
        ps.setString(2, AccountAuthenticator.hashPassword("password"));
        ps.executeUpdate();
    }

    /**
     * Adds all default products to the hotel's database.
     * 
     * @param dbConnection The Connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static void addDefaultProducts(Connection dbConnection) throws SQLException {
        String[] names = { "Tropical Shirt", "Shark Necklace", "Locally Made Vases", "Beach Hat",
                "Sunglasses", "Beach Towels", "Locally Crafted Seashell Bracelet", "Beach Umbrella" };
        double[] prices = { 25.00, 15.00, 50.00, 20.00, 10.00, 30.00, 15.00, 25.00 };
        // String[] images =
        // {"tropicalShirt","sharkNecklace","vase","beachHat","sunglasses","beachTowels","shellBracelet","umbrella"};

        String[] images = { "tropical-shirt.jpg", "shark-necklace.jpg", "vase.jpg",
                "beach-hat.jpg", "sunglasses.jpg", "beach-towels.jpg",
                "seashell-bracelet.jpg", "beach-umbrella.jpg", };
        String room = "INSERT INTO APP.Products(name,price,image) values(?,?,?)";

        for (int i = 0; i < names.length; i++) {
            PreparedStatement ps = dbConnection.prepareStatement(room);

            ps.setString(1, names[i]);
            ps.setDouble(2, prices[i]);
            ps.setString(3, images[i]);

            ps.executeUpdate();
        }
    }

    // Deletes all the tables in the database.
    public static void deleteTables() {
        String[] deleteTables = {
                "DROP TABLE APP.Reservations",
                "DROP TABLE APP.GuestAccounts",
                "DROP TABLE APP.ClerkAccounts",
                "DROP TABLE APP.ManagerAccounts",
                "DROP TABLE APP.GuestInfo",
                "DROP TABLE APP.Bookings",
                "DROP TABLE APP.Floors",
                "DROP TABLE APP.Rooms",
                "DROP TABLE APP.Products",
                "DROP TABLE APP.Orders",
                "DROP TABLE APP.OrderItems"
        };
        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // execute the SQL stetement
            for (String delete : deleteTables) {
                statement.execute(delete);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (Exception e) {

            }
        }
    }

    // Creates all the base tables for the hotel.
    public static void createTables() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = getDBConnection();
            statement = connection.createStatement();

            System.out.println("You made it, take control your database now!");
            String createReservationsTableSQL = "CREATE TABLE APP.Reservations(" + "reservationID INTEGER NOT NULL, "
                    + "username VARCHAR(225) NOT NULL, " + "startDate VARCHAR(225) NOT NULL, "
                    + "endDate VARCHAR(225) NOT NULL, " + "PRIMARY KEY (reservationID) " + ")";

            String createGuestAccountsTableSQL = "CREATE TABLE APP.GuestAccounts(" + "username VARCHAR(225) NOT NULL, "
                    + "password VARCHAR(225) NOT NULL, " + "PRIMARY KEY (username) " + ")";

            String createClerkAccountsTableSQL = "CREATE TABLE APP.ClerkAccounts(" + "username VARCHAR(225) NOT NULL, "
                    + "password VARCHAR(225) NOT NULL, " + "PRIMARY KEY (username) " + ")";

            String createManagerAccountsTableSQL = "CREATE TABLE APP.ManagerAccounts("
                    + "username VARCHAR(225) NOT NULL, "
                    + "password VARCHAR(225) NOT NULL, " + "PRIMARY KEY (username) " + ")";

            String createGuestInfoTableSQL = "CREATE TABLE APP.GuestInfo(" + "name VARCHAR(225) NOT NULL, "
                    + "address VARCHAR(225) NOT NULL, " + "username VARCHAR(225) NOT NULL, "
                    + "cardNum VARCHAR(225), " + "cardExp VARCHAR(225), "
                    + "PRIMARY KEY (username) " + ")";

            String createBookingsTableSQL = "CREATE TABLE APP.Bookings(" + "bookingID INTEGER NOT NULL, "
                    + "reservationID INTEGER NOT NULL, " + "roomNumber INTEGER NOT NULL, "
                    + "PRIMARY KEY (bookingID) " + ")";

            String createFloorsTableSQL = "CREATE TABLE APP.Floors(" + "floorID INTEGER NOT NULL, "
                    + "theme VARCHAR(225) NOT NULL, " + "PRIMARY KEY (floorID) " + ")";

            String createRoomsTableSQL = "CREATE TABLE APP.Rooms(" + "roomNumber INTEGER NOT NULL, "
                    + "floor INTEGER NOT NULL, " + "numBeds INTEGER NOT NULL, "
                    + "dailyRate FLOAT NOT NULL, " + "smokingAllowed SMALLINT NOT NULL, "
                    + "bedSize VARCHAR(225) NOT NULL, " + "type VARCHAR(225) NOT NULL, "
                    + "quality VARCHAR(225) NOT NULL, " + "PRIMARY KEY (roomNumber) " + ")";

            String createProductsTableSQL = "CREATE TABLE APP.Products("
                    + "productID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "name VARCHAR(225) NOT NULL, " + "price INTEGER NOT NULL, "
                    + "image VARCHAR(225) NOT NULL, " + "PRIMARY KEY (productID) " + ")";

            String createOrdersTableSQL = "CREATE TABLE APP.Orders("
                    + "orderID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "purchaseDate VARCHAR(225) NOT NULL, " + "subtotal DOUBLE NOT NULL, "
                    + "PRIMARY KEY (orderID) " + ")";

            String createOrderItemsTableSQL = "CREATE TABLE APP.OrderItems("
                    + "orderID INTEGER NOT NULL, " + "productID INTEGER NOT NULL" + ")";

            statement.execute(createReservationsTableSQL);
            statement.execute(createGuestAccountsTableSQL);
            statement.execute(createClerkAccountsTableSQL);
            statement.execute(createManagerAccountsTableSQL);
            statement.execute(createGuestInfoTableSQL);
            statement.execute(createBookingsTableSQL);
            statement.execute(createFloorsTableSQL);
            statement.execute(createRoomsTableSQL);
            statement.execute(createProductsTableSQL);
            statement.execute(createOrdersTableSQL);
            statement.execute(createOrderItemsTableSQL);

            addDefaultRooms(connection);
            addDefaultAccounts(connection);
            addDefaultProducts(connection);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
