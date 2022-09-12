package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.IProperty;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceAgeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class IceAgeAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new IceAgeAbility();
  
  public static final ParticleEffect PARTICLES = (ParticleEffect)new IceAgeParticleEffect(); private static final BlockProtectionRule PROTECTION_RULE;
  static {
    PROTECTION_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID })).addReplaceRules((world, pos, state) -> {
          if (state.getBlock().equals(Blocks.SNOW) && ((Integer)state.get((IProperty)SnowBlock.LAYERS)).intValue() > 5) {
            world.setBlockState(pos, Blocks.BLUE_ICE.getDefaultState());
            return true;
          } 
          return false;
        });
  }
  
  private List<LivingEntity> targets = new ArrayList<>();
  private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();

  
  public IceAgeAbility() {
    super("Ice Age", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setMaxChargeTime(5.0D);
    setCancelable();
    setDescription("Freezes a large area around the user and everyone inside of it");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::endChargingEvent;
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
      AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.BLUE_ICE, 3, PROTECTION_RULE);
      for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class )) {
        
        if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
          
          this.targets.add(target);
          EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 400, 0);
          target.addPotionEffect(instance);
          ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
          PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
        } 
      } 
      
      iterator.remove();
    } 
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    this.blockPlacingHelper.clearList();
    int range = 64;
    
    player.world.playMovingSound(null, (Entity)player, ModSounds.ICE_AGE_SFX, SoundCategory.PLAYERS, 5.0F, 1.0F);
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    for (int i = -range; i < range; i++) {
      for (int j = -9; j < 9; j++) {
        for (int k = -range; k < range; k++) {
          
          double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          double posY = player.getPosY() + j;
          
          double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          
          if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
          {
            
            this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
        } 
      } 
    }  return true;
  }

  
  private boolean endChargingEvent(PlayerEntity player) {
    if (getChargeTime() > 50) {
      return false;
    }
    this.targets.clear();
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    
    setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
    return true;
  }
}


