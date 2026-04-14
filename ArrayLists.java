import java.util.ArrayList;

public class ArrayLists{
    public static void main(String [] args){

        ArrayList<String> names = new ArrayList<String>();
        names.add("Agness Machasa");
        names.add("Houghton Chibozyi");
        names.add("Raymond Mwape");
        names.add("Cleriss Nkonde");
        names.add("Dianne Hachaya");
/* Other important ArrayList methods you can use */
        //names.set(0, "Mom");
        //names.remove(4);
        //names.clear();

        for(int i = 0; i < names.size();  i++){
            System.out.println(names.get(i));
        }
    }
}