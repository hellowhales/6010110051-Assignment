import java.util.*;
public class Magician extends Novice{
    ArrayList<String>skills ;
    public Magician(Novice prejob){
        super();
        super.setstat(prejob,"Magician");
        skills = new ArrayList<String>();
        skills.add("FireBall");
        skills.add("FireBlast");
    }
    public int fireBlast(){
        int skillDamage = 10;
        return skillDamage;
    }
    public int fireBall(){
        int skillDamage = 5;
        return skillDamage;
    }
    public ArrayList<String> getSkills(){
        return skills;
    }
}