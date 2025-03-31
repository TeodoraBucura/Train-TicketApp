import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RouteManager routes = new RouteManager("routes.txt");

        try (BufferedReader br = new BufferedReader(new FileReader("trains.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int routeId = Integer.parseInt(parts[0]);
                int wagonCount = Integer.parseInt(parts[2]);
                Time startTime = new Time(parts[4]);
                Route route = routes.getRoute(routeId);
                route.addTrain(new Train(parts[1],route,wagonCount,startTime,parts[3]));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        File tickets = new File("ticket.txt");
        tickets.delete();


        System.out.println("Bine ați venit la aplicația CFR de cumpărare a biletelor!");
        while(true) {
            // Pasul 1: Alegerea locației de plecare
            System.out.print("Introduceți locația de plecare: ");
            String plecare = scanner.nextLine();

            // Pasul 2: Afișarea destinațiilor disponibile
            List<String> destinations = routes.getDestinations(plecare).stream().toList();
            if (destinations.isEmpty()) {
                System.out.println("Nu există destinații disponibile pentru locația introdusă.");
                return;
            }

            System.out.println("Destinații disponibile:");
            for (int i = 0; i < destinations.size(); i++) {
                System.out.println((i + 1) + ". " + destinations.get(i));
            }
            int destinationIndex;
            while(true) {
                System.out.print("Introduceți numărul destinației: ");
                destinationIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consumă newline

                if (destinationIndex >= 0 && destinationIndex < destinations.size()) {

                    break;
                }
                System.out.println("Destinație invalidă.");
            }

            String sosire = destinations.get(destinationIndex);
            Route route = routes.getRoute(plecare, sosire);
            // Pasul 3: Alegerea orei
            List<Train> trains = route.getTrains(plecare, sosire);
            System.out.println("Trenuri disponibile:");
            for (int i = 0; i < trains.size(); i++) {
                Train train = trains.get(i);
                System.out.println((i + 1) + ". " + train.getName() + ": " + train.getTimeAt(plecare) + " -> " + train.getTimeAt(sosire));
            }

            int timeIndex;
            while(true) {
                System.out.print("Introduceți numărul trenului: ");
                timeIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consumă newline

                if (timeIndex >= 0 && timeIndex < trains.size()) break;
                System.out.println("Tren invalid.");
            }

            Train train = trains.get(timeIndex);
            Train.Seat seat;
            while(true) {
                // Pasul 4: Alegerea vagonului și locului
                System.out.print("Introduceți numărul vagonului: ");
                int vagon = scanner.nextInt();

                System.out.println("\nSchema vagonului " + vagon + ":");
                train.printWagon(vagon);

                System.out.print("Introduceți numărul locului: ");
                int loc = scanner.nextInt();

                seat = new Train.Seat(vagon, loc);
                if(!train.isSeatTaken(seat))
                    break;
                System.out.println("Locul ales nu mai este disponibil. Alegeti altul.");
            }
            train.occupy(seat);
            // Crearea biletului
            Ticket ticket = new Ticket(train, plecare, sosire, seat);

            // Tipărirea biletului
            ticket.printToFile();
            System.out.println("Biletul a fost generat cu succes! Verificați fișierul ticket.txt.");
            System.out.print("Mai doriti alt bilet? [y/n]");
            String resp = scanner.next();
            scanner.nextLine();
            if(!resp.equals("y")) break;
        }
    }
}