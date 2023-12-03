package Assignment3;
//ResidentManager.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ResidentManager {
    private List<Resident> residents;

    public ResidentManager() {
        residents = new ArrayList<>();
    }

    // Method to load residents from a file
    public void loadResidentsFromFile(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            // Skip the first line as it contains the number of residents
            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(" ");
                String phoneNumber = parts[0];
                String name = parts[1];
                residents.add(new Resident(name, phoneNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a resident to the manager
    public void addResident(Resident resident) {
        residents.add(resident);
    }

    // Method to search for a resident by their PhoneNumber
    public Resident searchResident(String phoneNumber) {
        for (Resident resident : residents) {
            if (resident.getPhoneNumber().equals(phoneNumber)) {
                return resident;
            }
        }
        return null; // Resident not found
    }

    // Method to get a resident's name by their ID
    public String getName(String id) {
        Resident resident = searchResident(id);
        return (resident != null) ? resident.getName() : null;
    }
}//End of the ResidentManager.java