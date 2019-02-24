import java.util.*;
public class HelloWorld{
    Monster mon = new Monster();
    public static void main(String []args){
        HelloWorld game = new HelloWorld();
        Bag inventory = new Bag();
        InputReader sc = new InputReader();
        Novice player = new Novice();
        String command;
        ArrayList<String> allcommands = new ArrayList<String>();
        allcommands.add("status");
        allcommands.add("heal");
        allcommands.add("attack");
        allcommands.add("classchange");
        allcommands.add("item");
        allcommands.add("getpotion");
        allcommands.add("usepotion");
        allcommands.add("help");
        allcommands.add("exit");
        //start
        System.out.println("All available commands.");
        System.out.println(allcommands);
        command = game.inputNovice(player);
        //player control
        while ( !(command.equals("exit") ) ){
            if ( command.equals("status") )
                player.status();
            else if ( command.equals("heal") )
                player.heal(20);
            else if ( command.equals("attack") ){
                System.out.println("Which monster to attack ? or Cancel");
                game.mon.printAllMonsters();
                System.out.print("Attack : ");
                command = sc.getinput();
                if ( command.equals("slime") )
                    player = game.attack(player,game.mon.Slime());
                else if ( command.equals("cancel") || command.equals("exit"))
                    System.out.println("Cancelled.");
                else 
                    System.out.println("Monster " + command + " Not Found.");
            }
            else if ( command.equals("item") )
                inventory.itemlist();
            else if ( command.equals("help" )){
                System.out.println("All available commands.");
                System.out.println(allcommands);}
            else if ( command.equals("getpotion") )
                inventory.getpotion();
            else if ( command.equals("usepotion") ){
                if ( inventory.usepotion() == 1) {
                player.heal(15);}
                }
            else if ( command.equals("classchange"))
                player = game.classChange(player);
            else
                System.out.println("Command " + command + " Not Found.") ;
            command = game.inputNovice(player);
        }
        //end of player control
    }//end main
    public Novice attack(Novice player , Monster mon){
        ArrayList<String> allcommands = new ArrayList<String>();
        allcommands.add("Run");
        allcommands.add("Attack");
        if (player.getJob().equals("Swordman") )
            for (String skill : ((Swordman)player).getSkills()){
                allcommands.add(skill);
            }
        else if (player.getJob().equals("Magician") )
            for (String skill : ((Magician)player).getSkills()){
                allcommands.add(skill);
            }
        String command;
        int mon_hp = mon.getHp(mon);
        int playerdmg = 0;
        int totalDamageToPlayer;
        int damageCal = 0;
        System.out.println("Encounters the " + mon.getName(mon) +"!.");
        System.out.println("All available commands.");
        System.out.println(allcommands);
        while (mon_hp > 0 ){
            System.out.println("==========" + mon.getName(mon) + "==========");
            System.out.println("HP : " + mon_hp + "    " + "Level : " + mon.getLevel(mon) );
            System.out.println("=========================");
            command = inputNovice(player);
            if (command.equals("run")|| command.equals("exit")){
                System.out.println("Run away from monster.");
                break;
            }
            else if ( command.equals("attack") ){
                playerdmg = player.normalAttack();
                damageCal = 1;
            }
            else if ( command.equals("heavyslash")){
                playerdmg = player.normalAttack() + ((Swordman)player).heavySlash();
                damageCal = 1;
            }
            else if ( command.equals("tackle")){
                playerdmg = player.normalAttack() + ((Swordman)player).tackle();
                damageCal = 1;
            }
            else if ( command.equals("fireball")){
                playerdmg = player.normalAttack() + ((Magician)player).fireBall();
                damageCal = 1;
            }
            else if ( command.equals("fireblast")){
                playerdmg = player.normalAttack() + ((Magician)player).fireBlast();
                damageCal = 1;
            }
            //damage calculation
            else 
                System.out.println("Command " + command + " Not Found.");
            if ( damageCal == 1){ //true for calculation damage or false for wrong commands
                mon_hp -= playerdmg;
                System.out.println(mon.getName(mon) + " took " + playerdmg + " damage.");
                totalDamageToPlayer = mon.getAtk(mon) - player.getDef();
                if (totalDamageToPlayer < 0)
                    totalDamageToPlayer = 0;
                player = player.takeDamage(player,totalDamageToPlayer);
                if (mon_hp <= 0){
                    System.out.println(mon.getName(mon) + " died.");
                    player.gainEXP(player,mon.getExp(mon));
                }
            }
        }
    return player;
    }
    public Novice classChange(Novice prejob){
        if (prejob.getLevel() <= 1){
            System.out.println("Job advancement available after level 2.");
            System.out.println("Current " + prejob.getJob() + " level : " + prejob.getLevel());
            return prejob;
        }
        else{
            ArrayList<String> alljobs = new ArrayList<String>();
            alljobs.add("Swordman");
            alljobs.add("Magician");
            System.out.println("Select advancement job.");
            System.out.println(alljobs);
            InputReader sc = new InputReader();
            String job = sc.getinput().trim();
            if ( job.equals("swordman")){
                Swordman advancedPlayer = new Swordman(prejob);
                return advancedPlayer;
                }
            else if ( job.equals("magician")){
                Magician advancedPlayer = new Magician(prejob);
                return advancedPlayer;
                } 
            else 
                System.out.println("Wrong job name.");
            return prejob;
        }
    }
    public String inputNovice(Novice player){
        InputReader sc = new InputReader();
        String a;
        System.out.println("What should "+ player.getJob() +" do?");
        System.out.print(">>> ");
        a = sc.getinput().trim();
        return a;
    }
}