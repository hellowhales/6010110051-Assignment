import java.util.*;
public class Item{
    private String name;
    public Item (int itemID){
        if (itemID == 0){
            name = "Potion";
        }
        else if (itemID == 1){
            name = "Beginner's Shield";
        }
    }
    public String getItemName(){
        return name;
    }
}