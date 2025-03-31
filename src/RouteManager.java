import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RouteManager {

    // Lista finala care nu se mai poate crea din nou.
    private final List<Route> routes = new ArrayList<>();

    /* Citeste din fisierul "routes.txt", sare peste spatii, iar atunci cand citeste cifre
    le transforma din string in valoare creand o lista de int-uri. */
    public RouteManager(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                List<Integer> times = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toList();
                routes.add(new Route(List.of(parts), times));
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
    }

    // Returneaza destinatiile posibile in ordine alfabetica, si elimina din lista care se afisieaza locul de plecare.
    public Set<String> getDestinations(String departure) {
        Set<String> returneaza = new TreeSet<>();
        for(Route route : routes) {
            if(route.getLocations().contains(departure))
                returneaza.addAll(route.getLocations());
        }
        returneaza.remove(departure);
        return returneaza;
    }

    // Returneaza ruta cu plecare si destinatie.
    public Route getRoute(String departure, String destination) {
        for(Route route : routes) {
            var locations = route.getLocations();
            if(locations.contains(departure) && locations.contains(destination)) {
                return route;
            }
        }
        return null;
    }

    // Returneaza numarul rutei.
    public Route getRoute(int idx) {
        return routes.get(idx);
    }
}