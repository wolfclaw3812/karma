package Event;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EventReader {
    File file;
    ArrayList<File> fileSources = new ArrayList<File>(); // because what if I need ALL of them
    public EventReader(File file){
        this.file = file;
    }

    public Event findEvent(int eventID){
        Event event = new Event(eventID);

        // Using try-with-resources automatically closes the file reader even if an error occurs
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Trim trailing/leading whitespace and check if the line starts with our prefix
                if (line.trim().startsWith("/")){
                    continue;
                }
                if (line.trim().startsWith(Integer.toString(eventID))) {
                    String[] split = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    try {
                        EventAction ea;
                        //split[0];//eventID, we know this from the parameter
                        int eventActionID = Integer.parseInt(split[1]);//eventActionID
                        int eventActionType = Integer.parseInt(split[2]);//eventActionType
                        String eventActionName = split[3];//eventActionName
                        String eventActionContent = split[4];//eventActionContent
                        ea = switch (eventActionType) {
                            case 0 -> new EventActionDialogue();
                            case 1 -> new EventActionCombat();
                            default -> new EventActionDialogue("Error","Something seems to have gone wrong.");
                        };
                        ea.setActionID(eventActionID);
                        ea.setActionType(eventActionType);
                        ea.setActionName(eventActionName);
                        ea.setActionContent(eventActionContent);
                        event.addEventAction(ea);
                    } catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }

                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        return event;
    }
}
