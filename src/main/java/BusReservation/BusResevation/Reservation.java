package BusReservation.BusResevation;


public class Reservation {
    private int id;
    private int busNo;
    private String passengerName;

    public Reservation(int id, int busNo, String passengerName) {
        this.id = id;
        this.busNo = busNo;
        this.passengerName = passengerName;
    }

    public Reservation(int busNo, String passengerName) {
        this(0, busNo, passengerName);
    }

    @Override
    public String toString() {
        return "Res#" + id + ": " + passengerName + " -> Bus " + busNo;
    }
}