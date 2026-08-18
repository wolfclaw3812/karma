package combat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CombatTests {
    @Test
    void testDamage(){
        Damage damage = new Damage(10.0);// 10
        damage.modBaseDamageAdd(5.0); // 15
        damage.modBaseDamageMult(2.0); // 30
        damage.modBonusDamageAdd(3.0); // 33
        damage.modBonusDamageMult(2.0); //66
        damage.modFinalDamageAdd(1.0); // 67
        damage.modFinalDamageMult(2.0); // 134
        assertEquals(134.0, damage.calculateDamage(), 0.001);
    }

    @Test
    @DisplayName("Determine basic functionality of attacks")
    void testAttackBasic(){
        Combatant attacker = new Combatant();
        Combatant defender = new Combatant();
        double baseDamage = 10.0;
        Attack attack = new Attack(attacker, defender, baseDamage);
        
        assertEquals(10.0, attack.getDamage().calculateDamage(), 0.001);
    }

    @Test
    @DisplayName("Determine add and mult modifier functionality for defending and attacking")
    void testAttackAdvanced(){
        Combatant attacker = new Combatant();
        CombatModifier mod = new CombatModifier();
        mod.addEffect("damage.modBaseDamageAdd(5.0)");
        attacker.addCombatModifier(mod);

        Combatant defender = new Combatant();
        CombatModifier defMod = new CombatModifier();
        defMod.addEffect("damage.modBonusDamageMult(-0.5)");
        double baseDamage = 10.0;

        Attack attack = new Attack(attacker, defender, baseDamage);
        
        assertEquals(7.5, attack.getDamage().calculateDamage(), 0.001);
    }
}
