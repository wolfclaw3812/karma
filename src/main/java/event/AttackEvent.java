package event;

import combat.Combatant;
import combat.Damage;

public class AttackEvent extends Event {
    private Combatant source;
    private Combatant target;
    private Damage damage;

    /**
     * Creates an AttackEvent with the specified source, target, and damage.    
     * @param source Combatant that is the source of the attack
     * @param target Combatant that is the target of the attack
     * @param damage Damage object representing damage dealt by attack
     */
    public AttackEvent(Combatant source, Combatant target, Damage damage){
        //super();
        this.source = source;
        this.target = target;
        this.damage = damage;
    }

    /**
     * Empty constructor that creates an AttackEvent, a dummy Source and Target, and an empty Damage object. 
     * To be used as fallback. 
     */
    public AttackEvent(){
        this.source = new Combatant();
        this.target = new Combatant();
        this.damage = new Damage();
    }
    
    public Combatant getSource() {
        return source;
    }

    public void setSource(Combatant source) {
        this.source = source;
    }

    public Combatant getTarget() {
        return target;
    }

    public void setTarget(Combatant target) {
        this.target = target;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    @Override
    public String toString(){
        return String.format("Attack: %d damage from %s to %s", damage.calculateDamage(), source, target); //TODO
    }
    
}
