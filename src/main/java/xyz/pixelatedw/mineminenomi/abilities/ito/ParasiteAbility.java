package xyz.pixelatedw.mineminenomi.abilities.ito;


import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.List;

public class ParasiteAbility
  extends Ability {
  public static final ParasiteAbility INSTANCE = new ParasiteAbility();

  
  public ParasiteAbility() {
    super("Parasite", AbilityHelper.getDevilFruitCategory());
    setDescription("By attaching your strings to nearby enemies, they get completely immobilized");
    setMaxCooldown(30.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    TorikagoAbility ability = (TorikagoAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)TorikagoAbility.INSTANCE);
    
    if (ability != null && ability.isEntityInThisTorikago((Entity)player)) {
      
      List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
      targets.remove(player);
      for (LivingEntity target : targets) {
        
        if (!(target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity) && ability.isEntityInThisTorikago((Entity)target)) {
          
          target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 300, 0, false, false));
          target.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 300, 0, false, false));
        } 
      } 
      setMaxCooldown(30.0D);
    }
    else {
      
      EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 16.0D);
      if (trace.getType().equals(RayTraceResult.Type.ENTITY) && trace.getEntity() instanceof LivingEntity) {
        
        LivingEntity target = (LivingEntity)trace.getEntity();
        if (!(target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity)) {
          
          target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 300, 0, false, false));
          target.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 300, 0, false, false));
          setMaxCooldown(15.0D);
        } 
      } else {
        
        return false;
      } 
    } 
    return true;
  }
}


