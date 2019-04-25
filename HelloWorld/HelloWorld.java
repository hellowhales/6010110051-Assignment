import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class HelloWorld{
    JFrame gui = new JFrame("HelloWorld");
    Container c ;
    JPanel playerStatPanel ;
    JPanel monsterStatPanel ;
    JPanel battlingPlayer;
    JPanel battlingMonster;
    JPanel actionPanel ;
    JLabel pJob ;
    JLabel pLevel ;
    JLabel pHp ;
    JLabel pExp ;
    JLabel pExpToNextLevel;
    JLabel pMonsterSlained;
    JLabel idleMons;
    JLabel mName;
    JLabel mHp;
    JLabel mLevel;
    JLabel pTitle;
    JLabel mTitle;
    JButton battleButton ;
    JButton bagButton ;
    JButton exitButton ;
    JButton classChangeButton ;
    JButton selectMonsterButton ;    
    JButton usePotionButton;
    Boolean monsterSelected = false;
    String monsterID;
    Monster selectableMonster1;
    Monster selectableMonster2;
    Monster selectableMonster3;
    Monster mon = new Monster();
    JPanel statDisplay;
    Boolean damageCal = false;
    int playerdmg = 0;
    JFrame battleGUI;
    Container cBattle ;
    Boolean battleEnded = false;
    Novice player;
    Bag inventory ;
    int monsterSlained = 0;
    public static void main(String []args){
        HelloWorld game = new HelloWorld();
        game.run(game);
    }//end main
    public void run(HelloWorld game){
        inventory = new Bag();
        Novice player = new Novice();
        gui = new JFrame("HelloWorld");
        c = gui.getContentPane();
        gui.setSize(300,175);
        //c.setLayout(new GridLayout(1,3,2,2));
        c.setLayout(new BorderLayout(10,7));
        //stat display
        statDisplay = new JPanel();
        statDisplay.setLayout(new BorderLayout(5,5));
        //player panel
        playerStatPanel = new JPanel();
        playerStatPanel.setLayout(new BoxLayout(playerStatPanel,BoxLayout.Y_AXIS));
        //monster panel
        monsterStatPanel = new JPanel();
        monsterStatPanel.setLayout(new BoxLayout(monsterStatPanel,BoxLayout.Y_AXIS));
        //action Panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2,2));
        //player JLabel
        pTitle = new JLabel("|=======Player=======|");
        pJob = new JLabel("Job : "+player.getJob());
        pLevel = new JLabel("Level : "+ Integer.toString(player.getLevel()));
        pHp = new JLabel("HP : "+ Integer.toString(player.getHp()));
        pExp = new JLabel("exp : "+ Integer.toString(player.getExp()));
        pExpToNextLevel = new JLabel("Need more " + player.getExpToLevelUp() + " exps");
        pMonsterSlained = new JLabel("Monster Slained : "+ Integer.toString(monsterSlained));
        //monster JLabel
        idleMons = new JLabel("Please select monster first");
        mName = new JLabel("Monster's Name : " );
        mLevel = new JLabel();
        mHp = new JLabel();
        mTitle = new JLabel();
        //button
        battleButton = new JButton("Battle");
        bagButton = new JButton("Bag");
        exitButton = new JButton("Exit");
        classChangeButton = new JButton("Class Change");
        selectMonsterButton = new JButton("Find nearby monster");
        usePotionButton = new JButton("Use Potion");
        //action listener

        selectMonsterButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int currentMonster = 3;
                selectableMonster1 = mon.getRandom();
                selectableMonster2 = mon.getRandom();
                selectableMonster3 = mon.getRandom();
                monsterID = JOptionPane.showInputDialog(c,
                                        "1 : "  + selectableMonster1.getName() + "  Level : " + selectableMonster1.getLevel() + "  HP : " + selectableMonster1.getHp() +
                                        "\n 2 : " + selectableMonster2.getName() +"  Level : " + selectableMonster2.getLevel() + "  HP : " + selectableMonster2.getHp() +
                                        "\n 3 : " + selectableMonster3.getName() +"  Level : " + selectableMonster3.getLevel() + "  HP : " + selectableMonster3.getHp() +
                                        " \n\n Select monster to attack(Enter number)", "Found Monster", JOptionPane.NO_OPTION);
                while (Integer.parseInt(monsterID) > currentMonster || Integer.parseInt(monsterID) < 0){
                    monsterID = JOptionPane.showInputDialog(c,
                                        "1 : "  + selectableMonster1.getName() + "  Level : " + selectableMonster1.getLevel() + "  HP : " + selectableMonster1.getHp() +
                                        "\n 2 : " + selectableMonster2.getName() +"  Level : " + selectableMonster2.getLevel() + "  HP : " + selectableMonster2.getHp() +
                                        "\n 3 : " + selectableMonster3.getName() +"  Level : " + selectableMonster3.getLevel() + "  HP : " + selectableMonster3.getHp() +
                                        " \n\n Select monster to attack(Enter number)", "Found Monster", JOptionPane.NO_OPTION);
                }
                monsterSelected = true;
                if (Integer.parseInt(monsterID)==1){
                    mon = selectableMonster1;
                }
                else if (Integer.parseInt(monsterID) == 2){
                    mon = selectableMonster2;
                }
                else mon = selectableMonster3;
                game.showMonster(mon);
            }
        });
        battleButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e ){
                if (monsterSelected == false){
                    JOptionPane.showMessageDialog(c,"Please select monster first!","No monster selected",JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    game.battleRedraw(player,mon,game);
                    //monsterSelected = false;
                    game.showMonster(mon);
                }
            }
        });
        bagButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(c,inventory.strItemList(),"Items in bag",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        usePotionButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int successUsingPotion = inventory.usepotion();
                if (successUsingPotion == 0){
                    JOptionPane.showMessageDialog(c,"No more potions.","Potions run out",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    player.heal(20);
                    pHp.setText("HP : "+player.getHp());
                    playerStatPanel.revalidate();
                }
            }
        });
        classChangeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(player.getLevel() <2 ){
                    JOptionPane.showMessageDialog(c,"Job advancement available after level 2.","Cannot change class",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JFrame classChangeGUI = new JFrame("Class Changing");
                    Container cClassChange = classChangeGUI.getContentPane();
                    cClassChange.setLayout(new BoxLayout(cClassChange,BoxLayout.Y_AXIS));
                    JPanel selectionPanel = new JPanel();
                    selectionPanel.setLayout(new  GridLayout(3,1));
                    JButton changeToMagicianButton = new JButton("Magician");
                    JButton changeToSwordmanButton = new JButton("Swordman");
                    JLabel changeClassTitle = new JLabel("Choose your new class");
                    selectionPanel.add(changeClassTitle);
                    selectionPanel.add(changeToSwordmanButton);
                    selectionPanel.add(changeToMagicianButton);
                    cClassChange.add(selectionPanel);
                    classChangeGUI.setSize(200,200);
                    classChangeGUI.setLocationRelativeTo(null);
                    classChangeGUI.setVisible(true);
                    classChangeGUI.setResizable(false);
                    changeToSwordmanButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Swordman advancedPlayer = new Swordman(player);
                            player.setstat(player,"Swordman");
                            JOptionPane.showMessageDialog(cClassChange,"Changed to Swordman!","Class changed",JOptionPane.INFORMATION_MESSAGE);
                            pJob.setText("Job : " + player.getJob());
                            playerStatPanel.revalidate();
                            classChangeGUI.setVisible(false);
                        }
                    });
                    changeToMagicianButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Magician advancedPlayer = new Magician(player);
                            player.setstat(player,"Magician");
                            JOptionPane.showMessageDialog(cClassChange,"Changed to Magician!","Class changed",JOptionPane.INFORMATION_MESSAGE);
                            pJob.setText("Job : " + player.getJob());
                            playerStatPanel.revalidate();
                            classChangeGUI.setVisible(false);
                        }
                    });
                }
            }
        });
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                gui.setVisible(false);
                gui.dispose();
                
            }
        });
        //monster panel
        monsterStatPanel.add(idleMons);
        monsterStatPanel.add(selectMonsterButton);
        monsterStatPanel.add(usePotionButton);
        //player panel
        playerStatPanel.add(pTitle);
        playerStatPanel.add(pJob);
        playerStatPanel.add(pLevel);
        playerStatPanel.add(pHp);
        playerStatPanel.add(pExp);
        playerStatPanel.add(pExpToNextLevel);
        playerStatPanel.add(pMonsterSlained);
        //stat display panel
        statDisplay.add(playerStatPanel,BorderLayout.WEST);
        statDisplay.add(monsterStatPanel,BorderLayout.CENTER);
        //action panel
        actionPanel.add(battleButton);
        actionPanel.add(bagButton);
        actionPanel.add(classChangeButton);
        actionPanel.add(exitButton);
        //container
        c.add(statDisplay,BorderLayout.WEST);
        c.add(actionPanel,BorderLayout.SOUTH);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.setResizable(false);
    }
    public Novice getNovice(){
        return this.player;
    }
    public void battleRedraw(Novice player,Monster mon,HelloWorld g){
        battleGUI = new JFrame("Battle");
        cBattle = battleGUI.getContentPane();
        cBattle.setLayout(new BorderLayout(10,7));
        //panel
        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new GridLayout(2,2));
        //button
        JButton normalAtkButton = new JButton("Normal Attack");
        JButton skill1Button = new JButton();
        JButton skill2Button = new JButton();
        JButton runButton = new JButton("Run");
        //add
        damageCal = false;
        skillPanel.add(normalAtkButton);
        if (player.getJob().equals("Swordman")){
            skill1Button.setText("Tackle");
            skill2Button.setText("Heavy Slash");
            skillPanel.add(skill1Button);
            skillPanel.add(skill2Button);
        }
        else if (player.getJob().equals("Magician")){
            skill1Button.setText("Fire Ball");
            skill2Button.setText("Fire Blast");
            skillPanel.add(skill1Button);
            skillPanel.add(skill2Button);
        }
        //action listener
        normalAtkButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playerdmg = player.normalAttack();
                battling(player,mon,g);
            }
        });
        skill1Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (player.getJob().equals("Swordman")){
                    playerdmg = player.normalAttack()+player.tackle();
                }
                else if (player.getJob().equals("Magician")){
                    playerdmg = player.normalAttack()+player.fireBall();
                }
                battling(player,mon,g);
            }
        });
        skill2Button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (player.getJob().equals("Swordman")){
                    playerdmg = player.normalAttack()+player.heavySlash();
                }
                else if (player.getJob().equals("Magician")){
                    playerdmg = player.normalAttack()+player.fireBlast();
                }
                battling(player,mon,g);
            }
        });
        runButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                battleGUI.setVisible(false);
                battleEnded = true;
                monsterSelected = false;
                System.out.println(battleEnded);
                //redraw monster panel
                monsterStatPanel.removeAll();
                monsterStatPanel.add(idleMons);
                monsterStatPanel.add(selectMonsterButton);
                monsterStatPanel.add(usePotionButton);
                statDisplay.add(playerStatPanel,BorderLayout.WEST);
                statDisplay.add(monsterStatPanel,BorderLayout.CENTER);
                statDisplay.revalidate();
            }
        });
        skillPanel.add(runButton);
        cBattle.add(playerStatPanel,BorderLayout.WEST);
        cBattle.add(monsterStatPanel,BorderLayout.EAST);
        cBattle.add(skillPanel,BorderLayout.SOUTH);
        battleGUI.setSize(500,500);
        battleGUI.pack();
        battleGUI.setResizable(false);
        battleGUI.setLocationRelativeTo(null);
        battleGUI.setVisible(true);
    }
    public void battling(Novice player,Monster mon,HelloWorld g){
        int monsterdmg = mon.getAtk();
        //set new player stat
        player = player.takeDamage(player,monsterdmg);
        pHp.setText("HP : " + player.getHp());
        //set new monster stat
        mon.takeDamage(playerdmg);
        mHp.setText("HP : " + mon.getHp());
        monsterStatPanel.revalidate();
        //add to panel
        playerStatPanel.revalidate();
        //won battle
        if (mon.getHp() <= 0){
            JOptionPane.showMessageDialog(cBattle,mon.getName()+ " died.","Won!",JOptionPane.INFORMATION_MESSAGE);
            player = player.gainEXP(player,mon.getExp());
            pExp.setText("exp : " + player.getExp());
            pLevel.setText("Level : " + player.getLevel());
            pHp.setText("HP : " + player.getHp());
            pExpToNextLevel.setText("Need more " + player.getExpToLevelUp() + " exps");
            monsterSlained++;
            pMonsterSlained.setText("Monster Slained : "+monsterSlained);
            battleGUI.setVisible(false);
            inventory.getpotion();
            battleEnded = true;
            monsterSelected = false;
            System.out.println(battleEnded);
            //redraw monster panel
            monsterStatPanel.removeAll();
            monsterStatPanel.add(idleMons);
            monsterStatPanel.add(selectMonsterButton);
            monsterStatPanel.add(usePotionButton);
            statDisplay.add(playerStatPanel,BorderLayout.WEST);
            statDisplay.add(monsterStatPanel,BorderLayout.CENTER);
            statDisplay.revalidate();
        }
    }
    public void showMonster(Monster m){
        monsterStatPanel.removeAll();
        monsterStatPanel.setVisible(false);
        mTitle.setText("|=======Monster========|");
        mName.setText("Name : " + m.getName());
        mLevel.setText("Level : " + m.getLevel());
        mHp.setText("HP : " + m.getHp());
        monsterStatPanel.add(mTitle);
        monsterStatPanel.add(mName);
        monsterStatPanel.add(mLevel);
        monsterStatPanel.add(mHp);
        monsterStatPanel.setVisible(true);
    }
}