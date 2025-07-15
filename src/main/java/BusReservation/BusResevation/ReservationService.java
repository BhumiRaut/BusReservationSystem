package BusReservation.BusResevation;

import model.*;
import repository.*;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private BusRepository busRepo = new BusRepository();
    private ReservationRepository resRepo = new ReservationRepository();

    public void reserve(int busNo, String name) {
        try {
            Bus bus = busRepo.getBusByNumber(busNo);
            if (bus == null) {
                System.out.println("❌ Bus not found");
                return;
            }
            if (bus.getCapacity() <= 0) {
                System.out.println("❌ No seats available");
                return;
            }
            busRepo.updateCapacity(busNo, bus.getCapacity() - 1);
            Reservation r = new Reservation(busNo, name);
            resRepo.save(r);
            System.out.println("✅ Reserved " + name + " on bus " + busNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showBookings() {
        try {
            List<Reservation> all = resRepo.getAll();
            if (all.isEmpty()) {
                System.out.println("📭 No reservations found");
            } else {
                all.forEach(System.out::println);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
