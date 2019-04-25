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
    public Monster Wolf(){
        Monster monster = new Monster();
        monster.name = "Wolf";
        monster.hp = 30;
        monster.def = 2;
        monster.atk = 3;
        monster.level =  2;
        monster.exp_reward = 8;
        monster.item_reward = "Potion";
        return monster;
    }
    public Monster Dragon(){
        Monster monster = new Monster();
        monster.name = "Dragon";
        monster.hp = 50;
        monster.def = 5;
        monster.atk = 6;
        monster.level =  3;
        monster.exp_reward = 20;
        monster.item_reward = "Potion";
        return monster;
    }
    public int getHp(){
        return this.hp;
    }
    public int getAtk(){
        return this.atk;
    }
    public int getLevel(){
        return this.level;
    }
    public String strGetLevel(){
        return this.level+"";
    }
    public String getName(){
        return this.name;
    }
    public int getExp(){
        return this.exp_reward;
    }
    public Monster findMonster(int id){
        if (id == 1)
            return this.Slime();
        else if (id == 2)
            return this.Wolf();
        else if (id == 3)
            return this.Dragon();
        else
            return null;
    }
    public Monster takeDamage(int damage){
        this.hp -= damage;
        return this;
    }
    public Monster getRandom(){
        Random rand = new Random();
        int n = rand.nextInt(3);
        n+=1;
        System.out.println("Random : "+n);
        return this.findMonster(n);
    }
}