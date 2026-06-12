import Combat.ActiveSkill;
import Combat.PassiveSkill;

import java.util.ArrayList;
abstract class Character {
    int health;
    int maxActiveSkills = 2;
    int maxPassiveSkills = 0;
    ArrayList<ActiveSkill> activeSkills = new ArrayList<>();
    ArrayList<PassiveSkill> passiveSkills = new ArrayList<>();
    public Character(){}
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
}
