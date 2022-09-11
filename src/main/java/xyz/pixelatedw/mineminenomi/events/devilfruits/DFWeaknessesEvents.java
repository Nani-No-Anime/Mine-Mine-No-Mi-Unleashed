package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;


@EventBusSubscriber(modid = "mineminenomi")
public class DFWeaknessesEvents
{
  @SubscribeEvent
  public static void onPlayerBreakSpeed(PlayerEvent.BreakSpeed event) {
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setNewSpeed(event.getOriginalSpeed() / 15.0F);
    }
  }
  
  @SubscribeEvent
  public static void onEntityLeftClickBlocks(PlayerInteractEvent.LeftClickBlock event) {
    if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onEntityRightClickEmpty(PlayerInteractEvent.RightClickItem event) {
    if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || event.getItemStack().getItem().equals(ModItems.BUBBLY_CORAL)) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onEntityRightClickBlocks(PlayerInteractEvent.RightClickBlock event) {
    if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onEntityBreaksBlocks(BlockEvent.BreakEvent event) {
    if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
      return;
    }
    PlayerEntity player = event.getPlayer();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onEntityPlaceBlocks(BlockEvent.EntityPlaceEvent event) {
    if (!CommonConfig.INSTANCE.areExtraWaterChecksEnabled() || !(event.getEntity() instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity)event.getEntity();
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
    
    if (props.hasDevilFruit() && AbilityHelper.isAffectedByWater((LivingEntity)player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event.getEntityLiving() != null) {
      
      LivingEntity entity = event.getEntityLiving();
      IDevilFruit props = DevilFruitCapability.get(entity);
      if (!props.hasDevilFruit()) {
        return;
      }
      if (AbilityHelper.isAffectedByWater(entity) && !entity.isPotionActive(ModEffects.BUBBLY_CORAL))
      {
        if (entity instanceof PlayerEntity && !((PlayerEntity)entity).abilities.isCreativeMode) {
          
          if (entity.isActualySwimming()) {
            entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.1D, (entity.getMotion()).z);
          } else {
            entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.04D, (entity.getMotion()).z);
          } 
          if (CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
            ((PlayerEntity)entity).addExhaustion(0.015F);
          }
        } else if (entity instanceof xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity) {
          entity.setMotion((entity.getMotion()).x, (entity.getMotion()).y - 0.04D, (entity.getMotion()).z);
        } 
      }
      if (event.getEntityLiving() instanceof PlayerEntity) {
        
        PlayerEntity player = (PlayerEntity)event.getEntityLiving();
        
        player.world.getProfiler().startSection("playerDevilFruitWeakness");
        
        boolean hasWaterWeakness = (AbilityHelper.isNearbyKairoseki(player) && !player.isPotionActive(ModEffects.BUBBLY_CORAL));
        
        if (!player.world.isRemote && CommonConfig.INSTANCE.areExtraWaterChecksEnabled()) {
          
          boolean hasExtraWaterTime = scoreCheck(player, hasWaterWeakness);
          if (hasWaterWeakness && hasExtraWaterTime) {
            return;
          }
        } 
        if (!player.world.isRemote)
        {
          if (hasWaterWeakness) {
            
            player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 20, 0));
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 0));
            player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 0));
            player.addExhaustion(0.05F);
            AbilityHelper.disableAbilities(player, 20, ability -> true);
          }
          else {
            
            AbilityHelper.enableAbilities(player, ability -> true);
          } 
        }
        
        player.world.getProfiler().endSection();
      } 
    } 
  }

  
  public static boolean scoreCheck(PlayerEntity player, boolean value) {
    Scoreboard scoreboard = player.world.getScoreboard();
    if (!scoreboard.func_197897_d().contains("watertime_mmnm"))
      scoreboard.addObjective("watertime_mmnm", ScoreCriteria.DUMMY, (ITextComponent)new StringTextComponent("waterTime"), ScoreCriteria.RenderType.INTEGER); 
    Score score = scoreboard.getOrCreateScore(player.getUniqueID().toString(), Objects.<ScoreObjective>requireNonNull(scoreboard.getObjective("watertime_mmnm")));
    
    if (value) {
      List<BlockPos> blockPosList = WyHelper.getNearbyBlocks((LivingEntity)player, 1);
      float size = (float)blockPosList.stream().map(pos -> player.world.getBlockState(pos).getBlock()).filter(block -> block.equals(Blocks.WATER)).count() / 4.0F;
      if (0.0F >= size)
        size = 1.0F; 
      score.increaseScore(Math.round(size));
      return (score.getScorePoints() <= 60);
    } 
    
    score.reset();
    return false;
  }
}


