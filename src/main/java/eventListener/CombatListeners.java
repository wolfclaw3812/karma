package eventListener ;

import combat.Combatant;
import combat.Damage;

public final class CombatListeners{
    public record DamageEvent(Combatant source, Combatant target, Damage damage) {}
    @FunctionalInterface
    public interface DamageListener {
        void onDamage(DamageEvent event);
    }

    public record PreDamageEvent(Combatant source, Combatant target, Damage damage) {}
    @FunctionalInterface
    public interface PreDamageEventListener {
        void onPreDamage(PreDamageEvent event);
    }
}