import java.io.*;
import java.nio.file.*;
import java.util.*;

public class WesterosChronicle {
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

        // Citirea evenimentelor din fișier
        List<Event> events = readTSV(inputFile);
        if (events == null || events.isEmpty()) {
            System.out.println("Fișierul nu a putut fi citit sau este gol.");
            return;
        }

        // Afișăm evenimentele citite
        System.out.println("Evenimente citite din fișier:");
        events.forEach(System.out::println);
    }

    // Citirea pentru TSV
    private static List<Event> readTSV(String fileName) {
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
}
