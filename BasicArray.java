//********************************************************************
// BasicArray.java Java Foundations
//
// Demonstrates basic array declaration and use.
//********************************************************************
public class BasicArray
{
 //-----------------------------------------------------------------
 // Creates an array, fills it with various integer values,
 // modifies one value, then prints them out.
 //-----------------------------------------------------------------
 public static void main (String[] args){

                    final int LIMIT = 20,
                     MULTIPLE = 5;
                    int[] list = new int[LIMIT];

 // Initialize the array values
 for (int index = 0; index < LIMIT; index++)
 list[index] = index * MULTIPLE;
 

  // change one array value
  list[0] = 100000;
 
 // Print the array values
 for (int value : list)
 System.out.print (value + " ");
 }
}