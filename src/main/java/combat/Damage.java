package combat;

public class Damage {
    // The following values are calculated in order of listing.
    private double baseDamage = 0.0; // original attack
    private double baseDamageAdd = 0.0; // base value added to original attack
    private double baseDamageMult = 1.0; // base multiplier applied to base damage
    private double bonusDamageAdd = 0.0; // global damage additions
    private double bonusDamageMult = 1.0; // global damage multipliers
    private double finalDamageMult = 1.0; // final multiplier
    private double finalDamageAdd = 0.0; // final value added

    public Damage() {}

    public Damage(double baseDamage) {
        this.baseDamage = baseDamage;
    }

    /**
     * Calculates the final damage based on the base damage and various modifiers.
     * ((base + baseAdd) * baseMult + bonusAdd) * bonusMult) * finalMult + finalAdd
     * @return The final calculate damage value, or 0 if the damage would be negative
     */
    public double calculateDamage() {
        double damage = baseDamage;
        damage += baseDamageAdd;
        damage *= baseDamageMult;
        damage += bonusDamageAdd;
        damage *= bonusDamageMult;
        damage *= finalDamageMult;
        damage += finalDamageAdd;
        return Math.max(0.0, damage);
    }

    public double getBaseDamage() { return baseDamage; }
    public void setBaseDamage(double baseDamage) { this.baseDamage = baseDamage; }
    public void modBaseDamage(double amount) { this.baseDamage += amount; }

    public double getBaseDamageAdd() { return baseDamageAdd; }
    public void setBaseDamageAdd(double baseDamageAdd) { this.baseDamageAdd = baseDamageAdd; }
    public void modBaseDamageAdd(double amount) { this.baseDamageAdd += amount; }

    public double getBaseDamageMult() { return baseDamageMult; }
    public void setBaseDamageMult(double baseDamageMult) { this.baseDamageMult = baseDamageMult; }
    public void modBaseDamageMult(double amount) { this.baseDamageMult += amount; }

    public double getBonusDamageAdd() { return bonusDamageAdd; }
    public void setBonusDamageAdd(double bonusDamageAdd) { this.bonusDamageAdd = bonusDamageAdd; }
    public void modBonusDamageAdd(double amount) { this.bonusDamageAdd += amount; }

    public double getBonusDamageMult() { return bonusDamageMult; }
    public void setBonusDamageMult(double bonusDamageMult) { this.bonusDamageMult = bonusDamageMult; }
    public void modBonusDamageMult(double amount) { this.bonusDamageMult += amount; }

    public double getFinalDamageAdd() { return finalDamageAdd; }
    public void setFinalDamageAdd(double finalDamageAdd) { this.finalDamageAdd = finalDamageAdd; }
    public void modFinalDamageAdd(double amount) { this.finalDamageAdd += amount; }

    public double getFinalDamageMult() { return finalDamageMult; }
    public void setFinalDamageMult(double finalDamageMult) { this.finalDamageMult = finalDamageMult; }
    public void modFinalDamageMult(double amount) { this.finalDamageMult += amount; }
}
