package combat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class ActiveSkill {
    private JsonNode skillData;
    private String name;
    private String description;
    private List<String> effects = new ArrayList<>();

    public ActiveSkill() {
    }
 
    public ActiveSkill(JsonNode skillData) {
        this(skillData.get("name").asText(), skillData.get("description").asText());
        this.skillData = skillData;
        
        JsonNode effectsNode = skillData.path("effects");

        if (effectsNode.isArray()) {
            for (JsonNode node : effectsNode) {
                effects.add(node.asText());
            }
        }
        
    }

    public ActiveSkill(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public void ActivateSkill(Combatant user, Combatant target) {
        JsonNode root = mapper.readTree(effects);

        // DamageEffect effect = switch (typeString) {
        //     case "PHYSICAL" -> mapper.treeToValue(root, PhysicalDamage.class);
        //     case "MAGIC"    -> mapper.treeToValue(root, MagicDamage.class);
        //     default         -> throw new IllegalArgumentException("Unknown type: " + typeString);
        // };    

    } 

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
