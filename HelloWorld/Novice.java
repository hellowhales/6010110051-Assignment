import java.util.*;
public class Novice{
    private String job ;
    private int hp;
    private int def;
    private int atk;
    private int exp;
    private int level;
    private int expToNextLevel;
    public Novice(){
        job = "Novice";
        hp = 100;
        def = 0;
        atk = 10;
        level = 1;
        exp = 0;
        expToNextLevel = 15;
    }
    public void heal(int healhp){
        hp = hp + healhp;
        System.out.println(job + " healed!");
        System.out.println("Restores " + healhp +" HP");
    }
    public int normalAttack(){
        return this.atk;
    }
    public String getJob(){
        return job;
    }
    public int getDef(){
        return def;
    }
    public int getAtk(){
        return atk;
    }
    public Novice getAll(){
        return this;
    }
    public int getLevel(){
        return level;
    }
    public Novice takeDamage(Novice player,int damage){
        System.out.println(player.job + " took " + damage + " damage.");
        player.hp -= damage;
        return player;
    }
    public Novice gainEXP(Novice player,int exp){
        player.exp += exp;
        System.out.println(player.job +" gained "+exp+" exps.");
        if(player.exp >= player.expToNextLevel){
            System.out.println(player.job +" level up!");
            player.level++;
            player.hp += 50;
            player.atk += 1;
            player.def += 1;
            player.exp -= expToNextLevel;
            player.expToNextLevel += 5;
        }
        return player;
    }
    public void setstat(Novice a,String nextJob){
        Novice tmp = a.getAll();
        job = nextJob;
        hp = tmp.hp;
        def = tmp.def;
        atk = tmp.atk;
        level = tmp.level;
        exp = tmp.exp;
        expToNextLevel = tmp.expToNextLevel;
    }
    public void status(){
        System.out.println("================ Current Status =================");
        System.out.println("HP : " + hp + "    " + "Level : " + level + "    "+"exp : "+exp + "  Class : " + job);
        System.out.println("=================================================");
    }
    public static void pause(){
        try
        {
            Thread.sleep(2500);
        }
            catch(InterruptedException e){
        }
    }
}