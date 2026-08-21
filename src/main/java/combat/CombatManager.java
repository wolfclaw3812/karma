package combat;

import java.util.ArrayList;
import java.util.List;

import event.AttackEvent;
import eventListener.PreAttackListener;
import eventListener.PostAttackListener;

public class CombatManager {
    private final List<PostAttackListener> postAttackListeners = new ArrayList<>();
    private final List<PreAttackListener> preAttackListeners = new ArrayList<>();
    public CombatManager(){

    }

    public void addPostAttackListener(PostAttackListener listener){
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }else{
            postAttackListeners.add(listener);
        }
    }

    public void removePostAttackListener(PostAttackListener listener){
        postAttackListeners.remove(listener);
    }

    public void makeAttack(Combatant source, Combatant target, Damage damage){
        makeAttack(new AttackEvent(source, target, damage));
    }

    public void makeAttack(AttackEvent event){
        Combatant source = event.getSource();
        Combatant target = event.getTarget();
        Damage damage = event.getDamage();
        
        // Notify pre-attack listeners
        for (PreAttackListener listener : preAttackListeners) {
            listener.onActivate(event);
        }

        // Attack happens proper
        target.takeDamage(damage);

        // Notify post-attack listeners
        for (PostAttackListener listener : postAttackListeners) {
            listener.onActivate(event);
        }
    }

}
