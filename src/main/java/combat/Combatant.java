package combat;

import java.util.ArrayList;
import engine.EngineCharacter;

public class Combatant {
    String name;
    double health;
    double shield;
    ArrayList<ActiveSkill> activeSkills = new ArrayList<ActiveSkill>();
    ArrayList<PassiveSkill> passiveSkills = new ArrayList<PassiveSkill>();
    ArrayList<CombatModifier> combatModifiers = new ArrayList<CombatModifier>();

    public Combatant(){}

    public Combatant(EngineCharacter player){
        this.name = player.getName();
        this.health = player.getHealth();
        this.shield = player.getShield();
        this.activeSkills = player.getActiveSkills();
        this.passiveSkills = player.getPassiveSkills();
    }

    public void takeDamage(Damage damage){
        double finalDamage = damage.calculateDamage();
        if (shield > 0){
            if (shield >= finalDamage){
                shield -= finalDamage;
            } else {
                finalDamage -= shield;
                shield = 0;
                health -= finalDamage;
            }
        } else {
            health -= finalDamage;
        }
    }

    public ArrayList<CombatModifier> getCombatModifiers() {
        return combatModifiers;
    }

    public void addCombatModifier(CombatModifier modifier) {
        combatModifiers.add(modifier);
    }
}
