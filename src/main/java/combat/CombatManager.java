package combat;

import java.util.ArrayList;
import java.util.List;

import eventListener.CombatListeners;
import eventListener.DamageListener;
import eventListener.CombatListeners.PreDamageListener;

public class CombatManager {
    private final List<DamageListener> damageListeners = new ArrayList<>();
    private final List<PreDamageListener> preDamageEventListeners = new ArrayList<>();
    public CombatManager(){

    }

    public void addDamageEventListener(DamageListener listener){
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }else{
            damageListeners.add(listener);
        }
    }

    public void removeDamageEventListener(CombatListeners.DamageListener listener){
        damageListeners.remove(listener);
    }

    public void dealDamage(Combatant source, Combatant target, Damage damage){
        DamageEvent event = new DamageEvent(source, target, damage);
        
        // Notify listeners
        // pre-damage events
        for (PreDamageListener listener : preDamageEventListeners) {
            listener.onPreDamage(event);
        }
        target.takeDamage(damage);
        for (DamageListener listener : damageListeners) {
            listener.onDamage(event);
        }
        
    }

}
