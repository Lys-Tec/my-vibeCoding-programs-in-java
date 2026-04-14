//import java.util.Scanner;
import java.io.FileWriter;/* needed for the write to be able to reflect the print methods to the file*/
import java.io.*; // Needed for file I/O classes
/**
 * 
 *  Class WriteToFile for the printing of names to an empty file
 * @author Chibozyi Houghton
 * @author .................
 *   
 */
public class PrintWriteToFile{
	/* Main method for the class WriteToFile*/
	
	public static void main(String [] args){

try (PrintWriter write = new PrintWriter(new FileWriter("ListOfNames.txt"))){
	String name;
	name = "Houghton chibozyi";
	int age;
	age = 23;
	write.println("Heyyy There!!!!!!!!!!");
	write.printf("My name is %s!\n", name);
	write.printf("I am %d years old\n", age);
	write.println("This is my Family");
	write.println("Eniah Machasa");
	write.println("Chisenga Machasa");
	write.println("Fewdays Machasa");
	write.println("Gonely Machasa");
	write.println("Agness Machasa jr");
	write.println("Royce Machasa");
	write.println("Agness Machasa Nkonde");
	write.println("Cleriss Nkonde Mbunda");
	write.println("Raymond Mwape");
	write.println("Diana Hachaya");
	write.println("Joel Musukwa");
	

System.out.println("Done writing....");

	} catch (IOException e) {
	System.out.println("an error occured:" + e.getMessage());
		}
	}
}


