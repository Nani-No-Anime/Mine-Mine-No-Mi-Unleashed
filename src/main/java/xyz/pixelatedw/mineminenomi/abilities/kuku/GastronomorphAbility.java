package xyz.pixelatedw.mineminenomi.abilities.kuku;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.HashSet;
import java.util.Iterator;

public class GastronomorphAbility extends ChargeableAbility {
  public static final GastronomorphAbility INSTANCE = new GastronomorphAbility();
  
  private static final BakuMunchParticleEffect PARTICLES = new BakuMunchParticleEffect();
  public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
  
  private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();

  
  public GastronomorphAbility() {
    super("Gastronomorph", AbilityHelper.getDevilFruitCategory());
    setDescription("Turns the surroundings into cake sponge blocks");
    setMaxChargeTime(3.0D);
    setMaxCooldown(15.0D);
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
    
    HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
    
    int finished = blockList.size() / 100;
    for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
      
      BlockPos blockPos = iterator.next();
      if (finished-- < 0) {
        break;
      }
      BlockPos pos = new BlockPos((Vec3i)blockPos);
      AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.SPONGE_CAKE, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE);
      
      iterator.remove();
    } 
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    this.blockPlacingHelper.clearList();
    int range = 32;
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    for (int i = -range; i < range; i++) {
      for (int j = -9; j < 9; j++) {
        for (int k = -range; k < range; k++) {
          
          double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          double posY = player.getPosY() + j;
          
          double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          
          BlockPos pos = new BlockPos(posX, posY, posZ);
          
          if (!player.world.getBlockState(pos).isAir() && player.world.getBlockState(pos.up()).isAir())
          {
            
            this.blockPlacingHelper.addBlockPos(pos, i * i + j * j + k * k); } 
        } 
      } 
    }  return true;
  }
}


