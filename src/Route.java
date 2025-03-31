import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route {

    // Lista cu locatii (randuriile impare din fisier).
    List<String> locations = new ArrayList<>();
    // Lista cu distanta intre locatii (randuriile pare).
    List<Integer> locationTimes = new ArrayList<>();

    Map<String, Train> trains = new HashMap<>();
    public Route(List<String> locations, List<Integer> locationTimes) {
        this.locations = locations;
        this.locationTimes = locationTimes;
    }

    // Getter locatii din lista.
    public List<String> getLocations() {
        return locations;
    }

    // Getter timp din lista. Aduna toate valorile de timp dintre locatii.
    public Time getTime(String start, String end) {
        int startIndex = locations.indexOf(start);
        int endIndex = locations.indexOf(end);
        if(startIndex == endIndex) return Time.ZERO;

        if(startIndex > endIndex) {
            int aux = startIndex;
            startIndex = endIndex;
            endIndex = aux;
        }

        int returneaza = 0;
        for(int i=startIndex;i<endIndex;i++) {
            returneaza += locationTimes.get(i);
        }
        return new Time(returneaza);
    }

    public void addTrain(Train train) {
        trains.put(train.getName(), train);
    }

    // Getter ID tren.
    public Train getTrain(String name) {
        return trains.get(name);
    }

    // Creeaza o lista cu trenurile valide pentru ruta aleasa.
    public List<Train> getTrains(String start, String end) {
        List<Train> validTrains = new ArrayList<>();
        for(Train train : trains.values()) {
            // Verifica directia rutei
            if(train.getTimeAt(start).getValue() > train.getTimeAt(end).getValue()) {
                continue;
            }
            validTrains.add(train);
        }
        return validTrains;
    }
}