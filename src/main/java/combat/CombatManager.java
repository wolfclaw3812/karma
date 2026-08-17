package combat;

import eventlistener.*;
import java.util.ArrayList;
import java.util.List;

import eventlistener.*;



public class CombatManager {
    private final List<CombatListeners> damageEventListeners = new ArrayList<>();

    public CombatManager(){

    }

    public void addDamageEventListener(CombatListeners listener){
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }else{
            damageEventListeners.add(listener);
        }
    }

    public void removeDamageEventListener(CombatListeners listener){
        damageEventListeners.remove(listener);
    }

    public void dealDamage(Combatant source, Combatant target, Damage damage){
        DamageEvent event = new DamageEvent(source, target, damage);
        
        // Notify listeners
        // pre-damage events
        for (PreDamageEventListener listener : preDamageEventListeners) {
            listener.onPreDamage(event);
        }
        target.takeDamage(damage);
        for (CombatListeners listener : damageEventListeners) {
            listener.onDamage(event);
        }
        
    }

}
