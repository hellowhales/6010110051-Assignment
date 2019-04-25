import java.util.ArrayList;
public class Bag{
    private int capacity;
    ArrayList<Item> items;
    public Bag(){
        items = new ArrayList<Item>();
        items.add(new Item(0));
        items.add(new Item(0));
    }
    public void itemlist(){
        System.out.println("Current items in bag.");
        for (Item i : items){
            System.out.println(i.getItemName());
        }
    }
    public String strItemList(){
        String tmp = "";
        int id = 1;
        for (Item i : items){
            tmp = tmp + Integer.toString(id) +  ". " + i.getItemName() + "\n";
            id++;
        }
        return tmp;
    }
    public void getpotion(){
        items.add(new Item(0));
        System.out.println("Got one potion!");
    }
    public int usepotion(){
        int potionIndex ;
        for (Item i : items){
            if (i.getItemName().equals("Potion")){
                potionIndex = items.indexOf(i);
                items.remove(potionIndex);
                System.out.println("Used one potion!");
                return 1;
            }
        }
        System.out.println("No more potion");
        return 0;
    }
}