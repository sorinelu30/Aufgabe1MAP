import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WesterosChronicle {

    // Clasa pentru reprezentarea unui eveniment
    static class Event {
        int id;
        String memberName;
        String house;
        String eventDescription;
        String date;

        public Event(int id, String memberName, String house, String eventDescription, String date) {
            this.id = id;
            this.memberName = memberName;
            this.house = house;
            this.eventDescription = eventDescription;
            this.date = date;
        }

        @Override
        public String toString() {
            return id + " | " + memberName + " | " + house + " | " + eventDescription + " | " + date;
        }
    }

    public static void main(String[] args) {
        String inputFile = "evenimente.tsv";

        // Citirea fisierului TSV
        List<Event> events = readTSV(inputFile);
        if (events == null || events.isEmpty()) {
            System.out.println("Fisierul nu a putut fi citit sau este gol.");
            return;
        }

        // Afisarea evenimentelor citite
        System.out.println("Evenimente citite din fisier:");
        events.forEach(System.out::println);

        // Afisarea membrilor dupa litera
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti o litera mare pentru filtrare: ");
        char filterLetter = scanner.nextLine().charAt(0);

        List<String> filteredMembers = filterMembersByLetter(events, filterLetter);
        System.out.println("\nMembri al caror nume incepe cu '" + filterLetter + "':");
        filteredMembers.forEach(System.out::println);
    }

    // Metoda pentru citirea fisierului TSV
    public static List<Event> readTSV(String fileName) {
        List<Event> events = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split("\t");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0]);
                    String memberName = parts[1];
                    String house = parts[2];
                    String eventDescription = parts[3];
                    String date = parts[4];
                    events.add(new Event(id, memberName, house, eventDescription, date));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Metoda pentru filtrarea membrilor dupa litera
    public static List<String> filterMembersByLetter(List<Event> events, char letter) {
        return events.stream()
                .map(event -> event.memberName)
                .filter(name -> name.charAt(0) == letter)
                .distinct()
                .collect(Collectors.toList());
    }
}
