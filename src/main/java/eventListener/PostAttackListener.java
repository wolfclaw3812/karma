package eventListener;

import java.util.Iterator;

import event.Event;
import event.AttackEvent;

public class PostAttackListener extends Listener {

    /**
     * Activates the event linked to this PostAttackListener. 
     * If remainingActivations is 0 or negative, nothing will happen. 
     * @param event The AttackEvent that triggered this PostAttackListener
     */
    public void onPostDamage(AttackEvent event){
        if (remainingActivations <= 0) { 
            return;
        }
        reduceActivationCount();
        safeContext.setVariable("source", event.getSource());
        safeContext.setVariable("target", event.getTarget());
        safeContext.setVariable("damage", event.getDamage());

        Iterator<String> itr = getEffects().iterator();
        while(itr.hasNext()){ 
            String effect = itr.next();
            // runs the code under the effect
            parser.parseExpression(effect).getValue(safeContext);
        }
    }

    /**
     * Activates the event linked to this PostAttackListener.
     * If remainingActivations is 0 or negative, nothing will happen.
     * This function is a generic interface function that calls the class-specific onPostDamage. 
     * @param event The Event that triggered this PostAttackListener
     */
    @Override
    public void onActivate(Event event) {
        if (event instanceof AttackEvent damageEvent){
            onPostDamage(damageEvent);
        }else{
            System.err.println("Error in "+event.toString());
        }
        
    }

    @Override
    public void reduceActivationCount() {
        this.remainingActivations--;
    }
    
}

    


