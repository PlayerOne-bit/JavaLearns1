import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
public class FileHandler {
    private static final String FILE_NAME = "data.txt";
    public static void save(String name, int age) throws IOException 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) 
        {
            bw.write(name + "," + age);
            bw.newLine();
        }
    }
    public static void read() throws IOException 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] parts = line.split(",");
                System.out.println("Name: " + parts[0] + ", Age: " + parts[1]);
            }
        }
    }
    public static void delete(String nameToDelete) throws IOException 
    {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");
        try 
        (
        	BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ) 
        {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                String name = parts[0];
                if (name.equalsIgnoreCase(nameToDelete)) 
                {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            if (found) 
            {
                System.out.println("Deleted: " + nameToDelete);
            } else {
                System.out.println(nameToDelete + " not found.");
            }
        }
        if (!inputFile.delete()) 
        {
            System.out.println("Could not delete original file.");
            return;
        }
        if (!tempFile.renameTo(inputFile)) 
        {
            System.out.println("Could not rename temp file.");
        }
    }
    public static void main(String[] args) throws IOException 
    {
        try(Scanner scan = new Scanner(System.in);)
        {
	        System.out.println("Input to insert in data.txt >> ");
	        save(scan.next(),scan.nextInt());
	        System.out.println("Stored Records:");
	        read();
	        System.out.println("Input to remove in data.txt >> ");
	        delete(scan.next());
	        System.out.println("Stored Records:");
	        read();
        }
        catch(Exception e) 
        {
        	System.out.println("Error: "+e);
        }
        finally 
        {
        	System.out.println("Program ends!");
        }
    }
}
