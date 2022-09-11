package xyz.pixelatedw.mineminenomi.abilities.suna;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.GroundDeathParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;

public class GroundDeathAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new GroundDeathAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundDeathParticleEffect();
  private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
  
  private List<LivingEntity> targets = new ArrayList<>();

  
  public GroundDeathAbility() {
    super("Ground Death", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setMaxChargeTime(3.0D);
    setCancelable();
    
    setDescription("Dries out the surrounding ground turning everythingg into sand");
    this.onStartChargingEvent = this::onStartCharging;
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    this.targets.clear();
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int i) {
    if (player.isWet()) {
      endCharging(player);
    }
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
    
    HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
    
    int finished = blockList.size() / 100;
    for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
      
      BlockPos blockPos = iterator.next();
      if (finished-- < 0) {
        break;
      }
      BlockPos pos = new BlockPos((Vec3i)blockPos);
      
      boolean isWater = (player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getMaterial() == Material.WATER);
      AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), isWater ? Blocks.AIR : Blocks.SAND, 3, GRIEF_RULE);
      
      for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player),  LivingEntity.class )) {
        
        if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
          
          this.targets.add(target);
          AbilityHelper.createFilledCube(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 1, (int)Math.round(target.getPosYEye() - target.getPosY()), 1, Blocks.SAND, GRIEF_RULE);
          target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 3, false, false));
          PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
        } 
      } 
      
      iterator.remove();
    } 
  }

  
  private boolean onStartCharging(PlayerEntity player) {
    DesertGirasoleAbility ability = (DesertGirasoleAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility(DesertGirasoleAbility.INSTANCE);
    
    if (ability != null && ability.isCharging()) return false;
    
    if (!player.onGround) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
      return false;
    } 
    
    this.blockPlacingHelper.clearList();
    
    int range = SunaHelper.isFruitBoosted(player) ? 42 : 32;
    
    for (int i = -range; i < range; i++) {
      for (int j = -8; j < 6; j++) {
        for (int k = -range; k < range; k++) {
          
          double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          double posY = player.getPosY() + j;
          
          double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
          
          if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
          {
            
            this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
        } 
      } 
    }  return !player.isWet();
  }
}


