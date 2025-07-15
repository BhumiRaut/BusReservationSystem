package BusReservation.BusResevation;

import db.DBConnection;
import model.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {

    public void save(Reservation r) throws SQLException {
        String sql = "INSERT INTO reservations(bus_no, passanger_name) VALUES(?,?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.busNo);
            ps.setString(2, r.passengerName);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    r.id = keys.getInt(1);
                }
            }
        }
    }

    public List<Reservation> getAll() throws SQLException {
        String sql = "SELECT id, bus_no, passanger_name FROM reservations";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            List<Reservation> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Reservation(
                    rs.getInt("id"),
                    rs.getInt("bus_no"),
                    rs.getString("passanger_name")
                ));
            }
            return list;
        }
    }
}