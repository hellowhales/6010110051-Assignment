import  java.util.*;
public class InputReader{
    private Scanner reader;
    public InputReader(){
        reader = new Scanner ( System.in );
    }
    public String getinput(){
        String inputLine = reader.nextLine();
        return inputLine.trim().toLowerCase();
    }
}