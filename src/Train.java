import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Train {

    public static class Seat {
        int wagon;
        int seatNumber;

        // Constructor vagon - loc.
        public Seat(int wagon, int seatNumber) {
            this.wagon = wagon;
            this.seatNumber = seatNumber;
        }

        // Verifica daca s-a ales acelasi loc si vagon.
        @Override
        public boolean equals(Object obj) {
            return ((Seat)obj).wagon == wagon && ((Seat)obj).seatNumber == seatNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(wagon, seatNumber);
        }

        @Override
        public String toString() {
            return "Seat{" +
                    "wagon=" + wagon +
                    ", seatNumber=" + seatNumber +
                    '}';
        }
    }

    private String name;
    private Route route;
    private int wagonCount;
    private Time startTime;
    private String startLocation;
    private Set<Seat> occupiedSeats = new HashSet<>();

    // Constructor pentru valorile din fisierul "trains.txt".
    public Train(String name, Route route, int wagonCount, Time startTime, String startLocation) {
        this.name = name;
        this.route = route;
        this.wagonCount = wagonCount;
        this.startTime = startTime;
        this.startLocation = startLocation;
    }

    public String getName() {
        return name;
    }

    // Returneaza timpul calatoriei
    public Time getTimeAt(String location) {
        Time travelTime = route.getTime(startLocation, location);
        return startTime.add(travelTime);
    }

    // Verifica daca este ocupat.
    public boolean isSeatTaken(Seat seat) {
        return occupiedSeats.contains(seat);
    }

    // Retine locul.
    public void occupy(Seat seat) {
        occupiedSeats.add(seat);
    }

    // Printeaza in consola cum arata un vagon cu nr locurilor, si pune XX daca locul a fost cumparat deja.
    public void printWagon(int wagon) {
        System.out.println(" /============================\\");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 4; j++) {
                Seat seat = new Seat(wagon, ((i - 1) * 4 + j));
                System.out.print("| " + (isSeatTaken(seat) ? "XX" : String.format("%2d", seat.seatNumber)) + " ");
            }
            System.out.println("|");
        }
        System.out.println(" \\============================/");
    }
}