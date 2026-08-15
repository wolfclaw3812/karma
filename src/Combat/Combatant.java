package Combat;

import java.util.ArrayList;
import Engine.EngineCharacter;

public class Combatant {
    String name;
    int health;
    int shield;
    ArrayList<ActiveSkill> activeSkills = new ArrayList<ActiveSkill>();
    ArrayList<PassiveSkill> passiveSkills = new ArrayList<PassiveSkill>();

    public Combatant(){}

    public Combatant(EngineCharacter player){
        this.name = player.getName();
        this.health = player.getHealth();
        this.shield = player.getShield();
        this.activeSkills = player.getActiveSkills();
        this.passiveSkills = player.getPassiveSkills();
    }
}
