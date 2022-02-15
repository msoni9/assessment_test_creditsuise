package helpers;

import models.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;


public class DAO {
    private static final Log logger = LogFactory.getLog(DAO.class);

    private static final String tableName = "Events";

    private static Connection connection;

    public DAO() throws SQLException {
        String connectionString = "jdbc:hsqldb:file:hsqldb/logs";

        logger.info("Opening database connection at < hsqldb/logs >");
        connection = DriverManager.getConnection(connectionString, "SA", "");
    }

    public void createEventsTable() throws SQLException {
        String createEvents = "CREATE TABLE IF NOT EXISTS Events (id VARCHAR(50) NOT NULL, duration FLOAT NOT NULL, " +
                "type VARCHAR(50), host VARCHAR(50), alert BOOLEAN NOT NULL)";

        logger.info("Creating Events table");
        connection.createStatement().executeUpdate(createEvents);
    }

    void writeEvent(Event event) throws SQLException {
        String addEvent = "INSERT INTO " + tableName + " (id, duration, type, host, alert)  VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(addEvent);
        preparedStatement.setString(1, event.getId());
        preparedStatement.setFloat(2, event.getDuration());
        preparedStatement.setString(3, event.getType());
        preparedStatement.setString(4, event.getHost());
        preparedStatement.setBoolean(5, event.isAlert());

        preparedStatement.executeUpdate();
    }

    public void closeDatabase() throws SQLException {
        logger.info("Closing DB connection.");
        connection.close();
    }

    public void selectAll() throws SQLException {
        String getAll = "SELECT * FROM " + tableName;

        logger.debug("Retrieving all DB entries in < " + tableName + " > table.");
        ResultSet resultSet = connection.createStatement().executeQuery(getAll);

        while (resultSet.next()) {
            if (resultSet.getBoolean(5)) {
                logger.debug("Alert for EventID <" + resultSet.getString(1) + ">");
            }
        }
    }

    public void deleteAll() throws SQLException {
        String deleteAll = "DELETE FROM " + tableName;

        logger.warn("Deleting all entries in DB table < " + tableName + " >.");
        connection.createStatement().executeUpdate(deleteAll);
    }
}

