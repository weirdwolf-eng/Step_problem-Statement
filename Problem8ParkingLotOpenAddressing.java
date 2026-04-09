
class ParkingSlot {
    String vehicle;
    boolean occupied;

    ParkingSlot() {
        this.vehicle = null;
        this.occupied = false;
    }
}

public class Problem8ParkingLotOpenAddressing {
    private ParkingSlot[] table;
    private int size;

    public Problem8ParkingLotOpenAddressing(int size) {
        this.size = size;
        table = new ParkingSlot[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ParkingSlot();
        }
    }

    private int hash(String vehicle) {
        return Math.abs(vehicle.hashCode()) % size;
    }

    public void parkVehicle(String vehicle) {
        int index = hash(vehicle);
        int start = index;

        while (table[index].occupied) {
            index = (index + 1) % size;
            if (index == start) {
                System.out.println("Parking Full");
                return;
            }
        }

        table[index].vehicle = vehicle;
        table[index].occupied = true;
        System.out.println(vehicle + " parked at slot " + index);
    }

    public void removeVehicle(String vehicle) {
        int index = hash(vehicle);
        int start = index;

        while (table[index].occupied) {
            if (table[index].vehicle.equals(vehicle)) {
                table[index].occupied = false;
                table[index].vehicle = null;
                System.out.println(vehicle + " removed from slot " + index);
                return;
            }
            index = (index + 1) % size;
            if (index == start) break;
        }

        System.out.println("Vehicle not found");
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            if (table[i].occupied) {
                System.out.println(i + " -> " + table[i].vehicle);
            } else {
                System.out.println(i + " -> EMPTY");
            }
        }
    }

    public static void main(String[] args) {
        Problem8ParkingLotOpenAddressing app = new Problem8ParkingLotOpenAddressing(10);

        app.parkVehicle("ABC123");
        app.parkVehicle("XYZ999");
        app.parkVehicle("ABC124");

        app.display();

        app.removeVehicle("ABC123");

        app.display();
    }
}
