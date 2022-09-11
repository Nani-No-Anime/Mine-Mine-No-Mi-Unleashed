package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.abilities.sui.FreeSwimmingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

@EventBusSubscriber(modid = "mineminenomi")
public class SuiPassiveEvents
{
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.getEntityLiving() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntityLiving();
    IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (!props.hasDevilFruit(ModAbilities.SUI_SUI_NO_MI)) {
      return;
    }
    boolean flying = player.abilities.isFlying;
    boolean creative = player.isCreative();
    boolean isOnGround = player.onGround;
    BlockPos pos = player.getPosition();
    boolean isMidAir = (player.world.getBlockState(pos.up()).isAir() && player.world.getBlockState(pos.down(2)).isAir());
    
    FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityProps.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
    
    if (ability != null)
    {
      if (ability.isContinuous()) {
        
        if (player.isInWater()) {
          
          ability.isSwimming = false;
        }
        else {
          
          if (isMidAir) {
            ability.stopContinuity(player);
          }
          if (isOnGround && player.isSprinting() && !flying)
          {
            AbilityHelper.setPose((LivingEntity)player, Pose.SWIMMING);
          }
          
          player.setSwimming(true);
          boolean swimming = (player.getPose() == Pose.SWIMMING);
          Vec3d playerMotion = player.getMotion();
          
          if (!player.world.isRemote) {
            ability.isSwimming = swimming;
          }
          if (swimming)
          {
            player.noClip = true;
            player.setMotion(playerMotion.mul(1.7D, 0.75D, 1.7D));
            playerMotion = player.getMotion();
            
            if (!isEntityInsideOpaqueBlock((LivingEntity)player))
            {
              double fall = -0.15D;
              
              if (AbilityHelper.isJumping((LivingEntity)player)) {
                fall -= 0.065D;
              } else if (player.isSneaking()) {
                fall += 0.04D;
              } 
              player.setMotion(playerMotion.x, fall, playerMotion.z);
            
            }
            else
            {
              if (player.getActivePotionEffect(Effects.WATER_BREATHING) == null) {
                
                int airLeft = player.getAir();
                player.setAir(airLeft - 5);
                if (airLeft <= 0)
                {
                  if (player.ticksExisted % 10 == 0)
                  {
                    player.attackEntityFrom(DamageSource.DROWN, 2.0F);
                  }
                }
              } 
              
              if (playerMotion.y < 0.0D)
              {
                player.setMotion(playerMotion.mul(1.0D, 0.8D, 1.0D));
              }
              
              Vec3d Vector3d1 = player.getMotion();
              double d3 = (player.getLookVec()).y;
              double d4 = (d3 < -0.2D) ? 0.1D : 0.09D;
              double upAlready = (d3 - Vector3d1.y) * d4;
              
              player.setMotion(Vector3d1.add(0.0D, (d3 - Vector3d1.y) * d4, 0.0D));
              
              if (player.isSneaking())
              {
                player.setMotion(Vector3d1.add(0.0D, creative ? -0.18D : -0.08D, 0.0D));
              }
              
              if (AbilityHelper.isJumping((LivingEntity)player))
              {
                player.setMotion(player.getMotion().add(0.0D, Math.max(upAlready, 0.1D), 0.0D));
              }
              
              if (0.0D >= player.getPosY())
              {
                player.setMotion(player.getMotion().add(0.0D, 3.0D, 0.0D));
              }
            }
          
          }
        
        } 
      } else {
        
        ability.isSwimming = false;
      } 
    }
  }

  
  @SubscribeEvent
  public void onRenderBlockOverlay(RenderBlockOverlayEvent event) {
    if (event.getPlayer() == null) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
    FreeSwimmingAbility ability = (FreeSwimmingAbility)AbilityProps.getEquippedAbility((Ability)FreeSwimmingAbility.INSTANCE);
    
    if (ability != null && ability.isContinuous())
    {
      event.setCanceled(true);
    }
  }

  
  private static boolean isEntityInsideOpaqueBlock(LivingEntity e) {
    BlockPos.Mutable blockPos = new BlockPos.Mutable();
    for (int i = 0; i < 8; i++) {
      
      int j = MathHelper.floor(e.getPosY() + ((((i >> 0) % 2) - 0.5F) * 0.1F) + e.getEyeHeight());
      int k = MathHelper.floor(e.getPosX() + ((((i >> 1) % 2) - 0.5F) * e.getHeight() * 0.8F));
      int l = MathHelper.floor(e.getPosZ() + ((((i >> 2) % 2) - 0.5F) * e.getWidth() * 0.8F));
      if (blockPos.getX() != k || blockPos.getY() != j || blockPos.getZ() != l) {
        
        blockPos.setPos(k, j, l);
        if (e.world.getBlockState((BlockPos)blockPos).isSuffocating((IBlockReader)e.world, (BlockPos)blockPos))
        {
          return true;
        }
      } 
    } 
    return false;
  }
}


