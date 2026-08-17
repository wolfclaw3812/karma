package engine;
import combat.*;

import java.util.ArrayList;
public class EngineCharacter {
    double health = 20.0;
    double shield = 0.0;
    int maxActiveSkills = 2;
    int maxPassiveSkills = 0;
    int level = 0;
    ArrayList<ActiveSkill> activeSkills = new ArrayList<>();
    ArrayList<PassiveSkill> passiveSkills = new ArrayList<>();
    public EngineCharacter(){}
    public boolean addActiveSkill(ActiveSkill activeSkill){
        if (activeSkills.size() < maxActiveSkills){
            activeSkills.add(activeSkill);
            return true;
        } else {
            return false;
        }
    }
    public boolean addPassiveSkill(PassiveSkill passiveSkill){
        if (passiveSkills.size() < maxPassiveSkills){
            passiveSkills.add(passiveSkill);
            return true;
        } else {
            return false;
        }
    }

    public void breakthrough(){
        level++;
        maxPassiveSkills+=2;
        maxActiveSkills+=2;
        health += level * 10;
        health *= 1.1;
    }

    public double getHealth() {
        return health;
    }

    public double getShield() {
        return shield;
    }

    public int getMaxActiveSkills() {
        return maxActiveSkills;
    }

    public int getMaxPassiveSkills() {
        return maxPassiveSkills;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<ActiveSkill> getActiveSkills() {
        return activeSkills;
    }

    public ArrayList<PassiveSkill> getPassiveSkills() {
        return passiveSkills;
    }

    public String getName() {
        return "Player";
    }




}
