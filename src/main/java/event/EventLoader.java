package event;

import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashMap;

public class EventLoader {
    HashMap<Integer, MapEvent> events;
    public EventLoader(){
        this.events = new HashMap<Integer, MapEvent>();
    }
    
    public void loadEvents(File file, int eventID){
        this.events = new HashMap<Integer, MapEvent>();
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
                            Integer eventActionID = Integer.parseInt(split[1]);//eventActionID
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
                            events.put(eventActionID, ea);
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
    }

    public HashMap<Integer, MapEvent> getEvents() {
        return events;
    } 
}
