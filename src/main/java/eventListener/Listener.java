package eventListener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.DataBindingMethodResolver;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

public abstract class Listener implements ListenerInterface{
    private List<String> effects = new ArrayList<String>();
    SimpleEvaluationContext safeContext = SimpleEvaluationContext
            .forReadOnlyDataBinding() // methods handle the mutation internally
            .withMethodResolvers(DataBindingMethodResolver.forInstanceMethodInvocation())
            .build();
    ExpressionParser parser = new SpelExpressionParser();
    int remainingActivations = 0;

    protected List<String> getEffects(){
        return this.effects;
    }

    protected void setEffects(List<String> effects){
        this.effects = effects;
    }
    
}
