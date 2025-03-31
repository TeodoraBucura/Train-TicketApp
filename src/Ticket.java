import java.io.*;

public class Ticket {

    private final String departure;
    private final String destination;
    private final Train train;
    private final Train.Seat seat;

    public Ticket(Train train, String departure, String destination, Train.Seat seat) {
        this.departure = departure;
        this.destination = destination;
        this.train = train;
        this.seat = seat;
    }

    // Afisarea in fisierul "ticket.txt" a biletului.
    public void printToFile() {

        try {
            File file = new File("ticket.txt");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter writer = new PrintWriter(br);
            writer.println("BILET CFR");
            writer.println("Tren: " + train.getName());
            writer.println("Ruta: " + departure + " -> " + destination);
            writer.println("Ora Plecare: " + train.getTimeAt(departure) + " -> " + train.getTimeAt(destination));
            writer.println("Durata: " + (train.getTimeAt(destination).getValue() - train.getTimeAt(departure).getValue()) + " minute");
            writer.println("Vagon: " + seat.wagon + " Loc: " + seat.seatNumber + "\n");

            writer.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Eroare la generarea fișierului bilet: " + e.getMessage());
        }
    }
}