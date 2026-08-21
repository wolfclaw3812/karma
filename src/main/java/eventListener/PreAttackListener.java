package eventListener;
import java.util.Iterator;

import event.AttackEvent;
import event.Event;
public class PreAttackListener extends Listener{
    
    public void beforeAttack(AttackEvent event) {
        safeContext.setVariable("source", event.getSource());
        safeContext.setVariable("target", event.getTarget());
        safeContext.setVariable("damage", event.getDamage());

        Iterator<String> itr = getEffects().iterator();
        while(itr.hasNext()){ 
            String effect = itr.next();
            // runs the code under the effect
            parser.parseExpression(effect).getValue(safeContext);
        }
        reduceActivationCount();
    }

    @Override
    public void onActivate(Event event) {
        if (event instanceof AttackEvent attackEvent) {
            beforeAttack(attackEvent);
        }
    }

    @Override
    public void reduceActivationCount() {
        this.remainingActivations--;
    }
}
