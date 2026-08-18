package eventListener;

import java.util.Iterator;

import event.Event;
import event.AttackEvent;

public class AttackListener extends Listener {

    void onDamage(AttackEvent event){
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

    @Override
    public void onActivate(Event event) {
        if (event instanceof AttackEvent damageEvent){
            onDamage(damageEvent);
        }else{
            System.err.println("Error in "+event.toString());
        }
        
    }
    
}

    


