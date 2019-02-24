import java.util.*;
public class Monster{
    private String name;
    private int hp;
    private int def;
    private int atk;
    private int level;
    private int exp_reward;
    private String item_reward;
    public void printAllMonsters(){
        ArrayList<String> allMonsters = new ArrayList<String>();
        allMonsters.add("Slime");
        System.out.println(allMonsters);
    }
    public Monster Slime(){
        Monster monster = new Monster();
        monster.name = "Slime";
        monster.hp = 20;
        monster.def = 0;
        monster.atk = 2;
        monster.level =  1;
        monster.exp_reward = 5;
        monster.item_reward = "Potion";
        return monster;
    }
    public int getHp(Monster mon){
        return mon.hp;
    }
    public int getAtk(Monster mon){
        return mon.atk;
    }
    public int getLevel(Monster mon){
        return mon.level;
    }
    public String getName(Monster mon){
        return mon.name;
    }
    public int getExp(Monster mon){
        return mon.exp_reward;
    }
}