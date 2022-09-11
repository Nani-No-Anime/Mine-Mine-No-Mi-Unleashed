package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.beta.BetaCoatingAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;




@EventBusSubscriber(modid = "mineminenomi")
public class BetaPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() instanceof net.minecraft.entity.player.PlayerEntity) {
      
      LivingEntity entity = event.getEntityLiving();
      IAbilityData abilityProps = AbilityDataCapability.get(event.getEntityLiving());
      Ability BetaCoating = abilityProps.getEquippedAbility((Ability)BetaCoatingAbility.INSTANCE);
      boolean hasBetaCoatingEnabled = (BetaCoating != null && BetaCoating.isContinuous());
      if (hasBetaCoatingEnabled) {
        
        boolean isNearBlock = false;
        AxisAlignedBB bb = entity.getBoundingBox().grow(1.0D, 1.0D, 1.0D);
        int mX = MathHelper.floor(bb.minX);
        int mY = MathHelper.floor(bb.minY);
        int mZ = MathHelper.floor(bb.minZ);
        for (int y2 = mY; y2 < bb.maxY; y2++) {
          
          for (int x2 = mX; x2 < bb.maxX; x2++) {
            
            for (int z2 = mZ; z2 < bb.maxZ; z2++) {
              
              BlockPos tmp = new BlockPos(x2, y2, z2);
              BlockState state = entity.world.getBlockState(tmp);
              if (state.isSolid()) {
                
                isNearBlock = true;
                
                break;
              } 
            } 
          } 
        } 
        if ((entity.collidedHorizontally && !entity.collidedVertically) || (entity.isCrouching() && isNearBlock)) {
          
          double climbSpeed = Math.min(0.1D, (entity.getLookVec()).y * 0.5D);
          if (entity.isCrouching()) {
            entity.setMotion((entity.getMotion()).x, 0.0D, (entity.getMotion()).z);
          } else {
            entity.setMotion((entity.getMotion()).x, climbSpeed, (entity.getMotion()).z);
          }  entity.fallDistance = 0.0F;
        } 
      } 
    } 
  }
}


