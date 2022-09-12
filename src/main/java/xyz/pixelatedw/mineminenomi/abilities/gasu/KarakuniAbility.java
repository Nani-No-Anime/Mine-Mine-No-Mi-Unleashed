package xyz.pixelatedw.mineminenomi.abilities.gasu;


import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

import java.util.List;

public class KarakuniAbility extends ContinuousAbility implements IParallelContinuousAbility {
  public static final KarakuniAbility INSTANCE = new KarakuniAbility();

  
  public KarakuniAbility() {
    super("Karakuni", AbilityHelper.getDevilFruitCategory());
    setDescription("Removes the oxygen around the user, suffocating and weakening everyone in the vicinity");
    setMaxCooldown(30.0D);
    setThreshold(5.0D);
    
    this.duringContinuityEvent = this::duringContinuity;
  }
  
  private void duringContinuity(PlayerEntity player, int i) {
    World world = player.world;
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    int radius = ShinokuniZoanInfo.INSTANCE.isActive((LivingEntity)player) ? 12 : 9;

    
    if (i % 2 == 0) {
      
      List<LivingEntity> entities = WyHelper.getEntitiesNear(player.getPosition(), world, radius);
      entities.remove(player);
      List<BlockPos> blocks = WyHelper.getNearbyBlocks((LivingEntity)player, radius);
      
      for (LivingEntity entity : entities) {
        
        if (entity.canBreatheUnderwater()) {
          continue;
        }
        entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 500, 2, false, false));
        entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 500, 1, false, false));
        entity.setAir(entity.getAir() - 50);
        int airLeft = entity.getAir();
        if (airLeft <= 0)
        {
          if (entity.getHealth() > 8.0F) {
            entity.attackEntityFrom(DamageSource.DROWN, 8.0F);
          } else {
            
            EffectInstance effect = new EffectInstance(ModEffects.UNCONSCIOUS, 200, 1);
            entity.addPotionEffect(effect);
            if (player instanceof ServerPlayerEntity) {
              ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(entity.getEntityId(), effect));
            }
          } 
        }
        if (entity.isBurning()) {
          entity.extinguish();
        }
      } 
      if (player.isBurning()) {
        player.extinguish();
      }
      for (BlockPos blockPos : blocks) {
        
        BlockPos blockUp = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
        if (world.getBlockState(blockPos).getBlock() == Blocks.FIRE && world.getBlockState(blockUp).getBlock() == Blocks.AIR) {
          
          world.playEvent(player, 1009, blockPos, 0);
          world.removeBlock(blockPos, false);
        } 
      } 
    } 
  }
}


