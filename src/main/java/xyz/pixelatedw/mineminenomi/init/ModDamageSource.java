package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class ModDamageSource
  extends DamageSource {
  public static final ModDamageSource FIRE = new ModDamageSource("onFire", false, false, false);
  public static final ModDamageSource LIGHTNING_BOLT = new ModDamageSource("lightningBolt", false, false, false);
  public static final ModDamageSource MAGMA = new ModDamageSource("lava", false, true, false);
  public static final ModDamageSource SPECIAL = new ModDamageSource("special", false, false, false);
  public static final DamageSource DEVILS_CURSE = (new ModDamageSource("devils_curse")).setDamageIsAbsolute();
  
  private boolean markSlashDamage = false;
  
  private boolean isInternal = false;
  private boolean isLogiaInvulBypass = false;
  
  public ModDamageSource(String damageTypeIn, boolean bypassArmor, boolean isFireDamage, boolean isExplosive) {
    super(damageTypeIn);
    
    if (bypassArmor)
      setDamageBypassesArmor(); 
    if (isFireDamage)
      setFireDamage(); 
    if (isExplosive)
      setExplosion(); 
  }
  
  public ModDamageSource(String damageType) {
    this(damageType, false, false, false);
  }
  
  public ModDamageSource causeIndirectDamageFromSource(ThrowableEntity entity) {
    ModIndirectEntityDamageSource modIndirectEntityDamageSource = new ModIndirectEntityDamageSource(getDamageType(), (Entity)entity, (Entity)entity.getThrower());
    if (isFireDamage())
      modIndirectEntityDamageSource.setFireDamage(); 
    if (isExplosion())
      modIndirectEntityDamageSource.setExplosion(); 
    if (isUnblockable()) {
      modIndirectEntityDamageSource.setDamageBypassesArmor();
    }
    return (ModDamageSource)modIndirectEntityDamageSource;
  }
  
  public ModDamageSource causeEntityDamageFromSource(Entity entity) {
    ModEntityDamageSource modEntityDamageSource = new ModEntityDamageSource(getDamageType(), entity);
    return setSourceProprieties((ModDamageSource)modEntityDamageSource);
  }

  
  public ModDamageSource setDamageBypassingLogiaInvulnerability() {
    this.isLogiaInvulBypass = true;
    return this;
  }

  
  public boolean isDamageBypassingLogiaInvulnerability() {
    return this.isLogiaInvulBypass;
  }

  
  public boolean isSlashDamage() {
    return this.markSlashDamage;
  }

  
  public ModDamageSource markDamageAsSlash() {
    this.markSlashDamage = true;
    return this;
  }

  
  public boolean isInternalDamage() {
    return this.isInternal;
  }

  
  public ModDamageSource setInternalDamage() {
    this.isInternal = true;
    setDamageBypassesArmor();
    return this;
  }
  
  public ModDamageSource getSource() {
    return setSourceProprieties(new ModDamageSource(getDamageType()));
  }
  
  private ModDamageSource setSourceProprieties(ModDamageSource s) {
    if (isFireDamage())
      s.setFireDamage(); 
    if (isExplosion())
      s.setExplosion(); 
    if (isUnblockable())
      s.setDamageBypassesArmor(); 
    return s;
  }

  
  public ModDamageSource setProjectile() {
    return setSourceProprieties((ModDamageSource)super.setProjectile());
  }

  
  public static AbilityDamageSource causeAbilityDamage(LivingEntity player, Ability ability) {
    return new AbilityDamageSource("ability", (Entity)player, ability);
  }


  
  public static AbilityDamageSource causeAbilityDamage(LivingEntity player, Ability ability, String damageType) {
    return new AbilityDamageSource(damageType, (Entity)player, ability);
  }
}


