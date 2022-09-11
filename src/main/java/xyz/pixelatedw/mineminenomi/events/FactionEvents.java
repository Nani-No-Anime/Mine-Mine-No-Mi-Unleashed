package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.events.SetPlayerDetailsEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;







@EventBusSubscriber(modid = "mineminenomi")
public class FactionEvents
{
  @SubscribeEvent
  public static void onEntityAttack(LivingHurtEvent event) {
    if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getSource().getTrueSource() instanceof PlayerEntity) {
      
      PlayerEntity attacker = (PlayerEntity)event.getSource().getTrueSource();
      LivingEntity target = event.getEntityLiving();
      
      boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
      if (sameGroup) {
        
        event.setCanceled(true);
        return;
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onProjectileHit(ProjectileHitEvent event) {
    if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getProjectile().getThrower() instanceof PlayerEntity && event.getHit().getType() == RayTraceResult.Type.ENTITY) {
      
      PlayerEntity attacker = (PlayerEntity)event.getProjectile().getThrower();
      EntityRayTraceResult hitResult = (EntityRayTraceResult)event.getHit();
      
      if (hitResult.getEntity() instanceof LivingEntity) {
        
        LivingEntity target = (LivingEntity)hitResult.getEntity();
        
        boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
        if (sameGroup) {
          
          event.setCanceled(true);
          return;
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onSetFire(SetOnFireEvent event) {
    if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getAttacker() instanceof PlayerEntity) {
      
      PlayerEntity attacker = (PlayerEntity)event.getAttacker();
      LivingEntity target = event.getEntityLiving();
      
      boolean sameGroup = FactionHelper.getSameGroupPredicate((LivingEntity)attacker).test(target);
      if (sameGroup) {
        
        event.setCanceled(true);
        return;
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onTargetSet(LivingSetAttackTargetEvent event) {
    if (CommonConfig.INSTANCE.isFriendlyDamageDisabled() && event.getTarget() instanceof PlayerEntity) {
      
      boolean sameGroup = FactionHelper.getSameGroupPredicate(event.getTarget()).test(event.getEntityLiving());
      if (sameGroup) {
        
        if (event.getEntityLiving() instanceof MobEntity)
          ((MobEntity)event.getEntityLiving()).setAttackTarget(null); 
        event.getEntityLiving().setRevengeTarget(null);
        event.getEntityLiving().setLastAttackedEntity(null);
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onPlayerJoinsWorld(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote && (event.getWorld()).dimension.getType() == DimensionType.OVERWORLD)
    {
      FactionHelper.validateFaction((PlayerEntity)event.getEntity());
    }
  }

  
  @SubscribeEvent
  public static void onStatsChoose(SetPlayerDetailsEvent event) {
    FactionHelper.validateFaction(event.getPlayer());
  }
}


