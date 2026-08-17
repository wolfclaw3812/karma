package event;

import combat.Combatant;
import combat.Damage;

public class DamageEvent extends Event {
    private Combatant source;
    private Combatant target;
    private Damage damage;

    public DamageEvent(Combatant source, Combatant target, Damage damage){
        this.source = source;
        this.target = target;
        this.damage = damage;
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
        return String.format("%d damage from %s to %s", damage.calculateDamage(), source, target); //TODO
    }
    
}
