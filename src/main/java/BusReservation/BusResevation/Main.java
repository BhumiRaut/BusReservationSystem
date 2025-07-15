package BusReservation.BusResevation;

import service.ReservationService;
import repository.BusRepository;
import model.Bus;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BusRepository repo = new BusRepository();
        ReservationService svc = new ReservationService();

        while (true) {
            System.out.println("\n1. View All Buses");
            System.out.println("2. Filter Buses");
            System.out.println("3. Reserve Bus");
            System.out.println("4. Show Bookings");
            System.out.println("5. Exit");
            System.out.print("> Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
            case 1:
                for (Bus b : repo.getAllBuses()) b.displayInfo();
                break;
            case 2:
                System.out.println("1-AC  2-Non‑AC  3‑Capacity>N");
                int fo = scanner.nextInt();
                List<Bus> list;
                if (fo == 1) list = repo.filterBuses(Bus::isAc);
                else if (fo == 2) list = repo.filterBuses(b -> !b.isAc());
                else {
                    System.out.print("N: "); int n = scanner.nextInt();
                    list = repo.filterBuses(b -> b.getCapacity() > n);
                }
                list.forEach(Bus::displayInfo);
                break;
            case 3:
                System.out.print("Bus no: "); int bn = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Name: "); String nm = scanner.nextLine();
                svc.reserve(bn, nm);
                break;
            case 4:
                svc.showBookings();
                break;
            case 5:
                System.out.println("👋 Bye!");
                return;
            default:
                System.out.println("Invalid");
            }
        }
    }
}
