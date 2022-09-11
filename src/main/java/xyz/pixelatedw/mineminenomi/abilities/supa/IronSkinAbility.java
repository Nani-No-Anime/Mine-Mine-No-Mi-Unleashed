package xyz.pixelatedw.mineminenomi.abilities.supa;

import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;


public class IronSkinAbility
  extends DamagedPassiveAbility
{
  public static final IronSkinAbility INSTANCE = new IronSkinAbility();

  
  public IronSkinAbility() {
    super("Iron Skin", AbilityHelper.getDevilFruitCategory());
    setDescription("Makes the user immune to slash based attacks until a certain level");
    this.onDamagedEvent = this::onDamagedEvent;
  }

  
  public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
    boolean isSlashDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isSlashDamage());
    boolean isSwordDamage = false;
    
    if (damageSource instanceof net.minecraft.util.EntityDamageSource) {
      
      Entity source = damageSource.getTrueSource();
      if (source != null && source.isAlive() && source instanceof LivingEntity) {
        
        LivingEntity livingSource = (LivingEntity)source;
        boolean mainHand = (!livingSource.getHeldItemMainhand().isEmpty() && ItemsHelper.isSword(livingSource.getHeldItemMainhand()));
        boolean offHand = (!livingSource.getHeldItemOffhand().isEmpty() && ItemsHelper.isSword(livingSource.getHeldItemOffhand()));
        if (mainHand || offHand)
          isSwordDamage = true; 
      } 
      if (damageSource.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) {
        
        damageSource.getImmediateSource().remove();
        return true;
      } 
    } 
    float weaponDamage = Math.max(ItemsHelper.getItemDamage(entity.getHeldItemMainhand()), ItemsHelper.getItemDamage(entity.getHeldItemMainhand()));
    boolean result = (!isSlashDamage && !isSwordDamage && 14.0F > weaponDamage);
    
    if (!result) {
      entity.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
    }
    return result;
  }
}


