package xyz.pixelatedw.mineminenomi.events.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;



@EventBusSubscriber(modid = "mineminenomi")
public class AbilityProtectionEvents
{
  @SubscribeEvent
  public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
    if (!(event.getWorld()).isRemote) {
      
      ExtendedWorldData worldData = ExtendedWorldData.get(event.getWorld());
      
      Vec3d pos = event.getExplosion().getPosition();
      if (worldData.isInsideRestrictedArea((int)pos.x, (int)pos.y, (int)pos.z)) {
        
        event.getAffectedBlocks().clear();
        event.getAffectedEntities().clear();
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onAbilityHit(ProjectileHitEvent event) {
    if (event.getHit().getType() == RayTraceResult.Type.ENTITY) {
      
      EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getHit();
      
      if (entityHit.getEntity() instanceof LivingEntity) {
        
        LivingEntity hitEntity = (LivingEntity)entityHit.getEntity();
        
        if (AbilityHelper.checkForRestriction(hitEntity.world, (int)hitEntity.getPosX(), (int)hitEntity.getPosY(), (int)hitEntity.getPosZ()))
        {
          event.setCanceled(true);
        }
      } 
    } 
  }
}


