package BusReservation.BusResevation;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;

    public Bus(int busNo, boolean ac, int capacity) {
        this.busNo = busNo;
        this.ac = ac;
        this.capacity = capacity;
    }

    public int getBusNo() { return busNo; }
    public boolean isAc()    { return ac; }
    public int getCapacity() { return capacity; }

    public void setCapacity(int cap) { this.capacity = cap; }

    public void displayInfo() {
        System.out.printf("Bus %d | AC: %s | Seats: %d%n",
            busNo, ac ? "Yes" : "No", capacity);
    }
}