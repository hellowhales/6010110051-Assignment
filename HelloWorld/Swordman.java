import java.util.*;
public class Swordman extends Novice{
    ArrayList<String>skills ;
    public Swordman(Novice prejob){
        super();
        super.setstat(prejob,"Swordman");
        skills = new ArrayList<String>();
        skills.add("HeavySlash");
        skills.add("Tackle");
    }
    public int heavySlash(){
        int skillDamage = 10;
        return skillDamage;
    }
    public int tackle(){
        int skillDamage = 5;
        return skillDamage;
    }
    public ArrayList<String> getSkills(){
        return skills;
    }
}