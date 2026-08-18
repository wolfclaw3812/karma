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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getShield() {
        return shield;
    }

    public void setShield(double shield) {
        this.shield = shield;
    }

    public ArrayList<ActiveSkill> getActiveSkills() {
        return activeSkills;
    }

    public void setActiveSkills(ArrayList<ActiveSkill> activeSkills) {
        this.activeSkills = activeSkills;
    }

    public ArrayList<PassiveSkill> getPassiveSkills() {
        return passiveSkills;
    }

    public void setPassiveSkills(ArrayList<PassiveSkill> passiveSkills) {
        this.passiveSkills = passiveSkills;
    }

    public void setCombatModifiers(ArrayList<CombatModifier> combatModifiers) {
        this.combatModifiers = combatModifiers;
    }

    
}
