package Combat;

import java.util.ArrayList;

public class Combatant {
    String name;
    int health;
    int shield;
    ArrayList<ActiveSkill> activeSkills = new ArrayList<ActiveSkill>();
    ArrayList<PassiveSkill> passiveSkills = new ArrayList<PassiveSkill>();

    public Combatant(){}

}
