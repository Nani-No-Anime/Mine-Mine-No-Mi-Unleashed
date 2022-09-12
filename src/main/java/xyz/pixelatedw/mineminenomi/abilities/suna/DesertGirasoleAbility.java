package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasole2ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasoleParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

import java.util.HashSet;
import java.util.Iterator;

public class DesertGirasoleAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new DesertGirasoleAbility();
  BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
  
  private static final ParticleEffect PARTICLES1 = (ParticleEffect)new DesertGirasoleParticleEffect();
  private static final ParticleEffect PARTICLES2 = (ParticleEffect)new DesertGirasole2ParticleEffect();

  
  public DesertGirasoleAbility() {
    super("Desert Girasole", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(30.0D);
    setMaxChargeTime(5.0D);
    setDescription("A giant pit of quicksand will be formed with the sand being taken away by underground rivers \n\nThis can only be used on sand!");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    GroundDeathAbility ability = (GroundDeathAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility(GroundDeathAbility.INSTANCE);
    
    int range = SunaHelper.isFruitBoosted(player) ? 24 : 12;
    
    for (int i = -range; i < range; i++) {
      for (int j = -8; j < 8; j++) {
        for (int k = -range; k < range; k++) {
          
          double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange(8, 12) || i > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          double posY = player.getPosY() + j;
          double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange(8, 12) || k > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          
          if (player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock().equals(Blocks.SAND))
            this.blockPlacingHelper.addBlockPos(posX, posY, posZ, i * i + j * j + k * k); 
        } 
      } 
    }  if (this.blockPlacingHelper.getBlockList().size() > 0) {
      
      PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    } else {
      
      return false;
    } 
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 
    
    return ((ability == null || !ability.isCharging()) && !player.isWet());
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTime) {
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
    
    HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
    
    int finished = blockList.size() / 100;
    for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
      
      BlockPos blockPos = iterator.next();
      if (finished-- < 0)
        break; 
      BlockPos pos = new BlockPos((Vec3i)blockPos);
      AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.SUNA_SAND, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
      iterator.remove();
    } 
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    
    return true;
  }
}


