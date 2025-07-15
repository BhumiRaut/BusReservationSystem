package BusReservation.BusResevation;

import db.DBConnection;
import model.Bus;

import java.sql.*;
import java.util.*;
import java.util.function.Predicate;

public class BusRepository {

    public List<Bus> getAllBuses() throws SQLException {
        String sql = "SELECT bus_no, ac, capacity FROM buses";
        try (Connection c = DBConnection.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            List<Bus> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Bus(
                    rs.getInt("bus_no"),
                    rs.getBoolean("ac"),
                    rs.getInt("capacity")
                ));
            }
            return list;
        }
    }

    public Bus getBusByNumber(int busNo) throws SQLException {
        String sql = "SELECT bus_no, ac, capacity FROM buses WHERE bus_no = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, busNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bus(
                        rs.getInt("bus_no"),
                        rs.getBoolean("ac"),
                        rs.getInt("capacity")
                    );
                }
                return null;
            }
        }
    }

    public void updateCapacity(int busNo, int newCap) throws SQLException {
        String sql = "UPDATE buses SET capacity = ? WHERE bus_no = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, newCap);
            ps.setInt(2, busNo);
            ps.executeUpdate();
        }
    }

    public List<Bus> filterBuses(Predicate<Bus> pred) throws SQLException {
        List<Bus> all = getAllBuses();
        List<Bus> filtered = new ArrayList<>();
        for (Bus b : all) {
            if (pred.test(b)) filtered.add(b);
        }
        return filtered;
    }
}