package combat;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.DataBindingMethodResolver;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public class CombatModifier {
    JsonNode modifierData;
    ArrayList<String> effects = new ArrayList<String>();
    SimpleEvaluationContext safeContext = SimpleEvaluationContext
            .forReadOnlyDataBinding() // methods handle the mutation internally
            .withMethodResolvers(DataBindingMethodResolver.forInstanceMethodInvocation())
            .build();
    ExpressionParser parser = new SpelExpressionParser();

    public CombatModifier(){
        
    }

    public CombatModifier(JsonNode modifierData){
        this.modifierData = modifierData;
        for (JsonNode node : modifierData) {
            effects.add(node.asText());
        }
    }

    /**
     * Modifies the attack based on the effects defined in the modifierData.
     * Uses SpEL to evaluate the effects on the attack.
     * 
     * @param attack The attack object that will be modified
     * @param source The combatant who is performing the attack
     * @param target The combatant who is the target of the attack
     */
    public void modifyAttack(Attack attack, Combatant source, Combatant target){
        for (String effect : effects){
            parser.parseExpression(effect).getValue(safeContext, attack);
            // using the object as the second parameter in getValue modifies the object directly
            // do not use attack.damage.mod, use directly damage.mod
            // so skip over the root object
        }
    }

    public void modifyDefense(Attack attack, Combatant source, Combatant target){
        
    }

    public ArrayList<String> getEffects() {
        return effects;
    }

    public void addEffect(String effect) {
        effects.add(effect);
    }
    
}
