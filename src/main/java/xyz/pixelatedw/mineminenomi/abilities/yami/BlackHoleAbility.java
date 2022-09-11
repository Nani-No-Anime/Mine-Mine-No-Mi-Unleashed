package xyz.pixelatedw.mineminenomi.abilities.yami;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackHoleParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class BlackHoleAbility extends ChargeableAbility {
  public static final BlackHoleAbility INSTANCE = new BlackHoleAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new BlackHoleParticleEffect();
  
  private List<LivingEntity> targets = new ArrayList<>();
  private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();

  
  public BlackHoleAbility() {
    super("Black Hole", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setMaxChargeTime(5.0D);
    setCancelable();
    
    setDescription("The user spreads darkness over the target area, which engulfs and suffocates anyone and anything inside of it");
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
      AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.DARKNESS, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE);
      for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class )) {
        
        if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
          
          this.targets.add(target);
          EffectInstance instance = new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 400, 0);
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
    int range = 32;
    float spread = 0.65F;
    
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    for (int i = -range; i < range; i++) {
      for (int j = -6; j < 3; j++) {
        for (int k = -range; k < range; k++) {
          
          double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F))) || i > WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
          double posY = player.getPosY() + j;
          
          double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F))) || k > WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
          
          if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
          {
            
            this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
        } 
      } 
    }  return true;
  }

  
  private boolean endChargingEvent(PlayerEntity player) {
    this.targets.clear();
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    
    setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
    return true;
  }
}


