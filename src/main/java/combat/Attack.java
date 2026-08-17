package combat;

public class Attack {
    private Damage damage;
    public Attack(Combatant source, Combatant target, double baseDamage){
        this.damage = new Damage(baseDamage);
        for(CombatModifier modifier : source.getCombatModifiers()){
            modifier.modifyAttack(this, source, target);
        }
        for (CombatModifier modifier : target.getCombatModifiers()){
            modifier.modifyDefense(this, source, target);
        }
    }

    public Damage getDamage(){
        return this.damage;
    }
    
}
