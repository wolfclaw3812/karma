package combat;

import java.util.ArrayList;
import java.util.List;

import eventListener.CombatListeners;
import eventListener.AttackListener;
import eventListener.CombatListeners.PreDamageListener;

public class CombatManager {
    private final List<AttackListener> damageListeners = new ArrayList<>();
    private final List<PreDamageListener> preDamageEventListeners = new ArrayList<>();
    public CombatManager(){

    }

    public void addDamageEventListener(AttackListener listener){
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }else{
            damageListeners.add(listener);
        }
    }

    public void removeDamageEventListener(AttackListener.DamageListener listener){
        damageListeners.remove(listener);
    }

    public void dealDamage(Combatant source, Combatant target, Damage damage){
        AttackEvent event = new AttackEvent(source, target, damage);
        
        // Notify listeners
        // pre-damage events
        for (PreDamageListener listener : preDamageEventListeners) {
            listener.onPreDamage(event);
        }
        target.takeDamage(damage);
        for (AttackListener listener : damageListeners) {
            listener.onDamage(event);
        }
        
    }

}
