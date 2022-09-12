package xyz.pixelatedw.mineminenomi.api.helpers.abilities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.*;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.*;
import xyz.pixelatedw.mineminenomi.abilities.blackleg.*;
import xyz.pixelatedw.mineminenomi.abilities.brawler.*;
import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.abilities.ito.SoraNoMichiAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.*;
import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.*;
import xyz.pixelatedw.mineminenomi.api.abilities.*;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.*;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.particles.effects.common.GuardParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.HakiGuardParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;




public class AbilityHelper
{
  @Nullable
  public static IAnimatedAbility getActiveAnimationAbility(LivingEntity entity) {
    IAbilityData abilityProps = AbilityDataCapability.get(entity);
    List<Ability> abilities = new ArrayList<>();
    abilities.addAll(Arrays.<Ability>asList(abilityProps.getEquippedAbilities(IAnimatedAbility.IS_ACTIVE)));
    abilities.addAll(abilityProps.getUnlockedAbilities(IAnimatedAbility.IS_ACTIVE));
    Optional<Ability> optional = abilities.stream().findFirst();
    if (optional.isPresent()) {
      
      IAnimatedAbility ability = (IAnimatedAbility)optional.get();
      return ability;
    } 
    
    return null;
  }

  
  public static void enableAbilities(PlayerEntity player, Predicate<Ability> check) {
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability ability : abilityData.getEquippedAbilities(check)) {
      
      if (ability != null && ability.isDisabled()) {
        
        ability.startCooldown(player);
        if (!player.world.isRemote) {
          WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), (LivingEntity)player);
        }
      } 
    } 
  }
  
  public static void disableAbilities(PlayerEntity player, int duration, Predicate<Ability> check) {
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
    
    for (Ability ability : abilityData.getEquippedAbilities(check)) {
      
      if (ability != null && !ability.isDisabled()) {
        
        if (ability instanceof ContinuousAbility && ability.getState() == Ability.State.CONTINUOUS)
          ((ContinuousAbility)ability).endContinuity(player); 
        if (ability instanceof RepeaterAbility && ability.getState() == Ability.State.CONTINUOUS)
          ((RepeaterAbility)ability).setRepeaterCount(((RepeaterAbility)ability).getMaxRepeaterCount()); 
        if (ability instanceof ChargeableAbility && ability.getState() == Ability.State.CHARGING) {
          
          ((ChargeableAbility)ability).setChargeTime(((ChargeableAbility)ability).getMaxChargeTime() / 20);
          ability.startCooldown(player);
        } 
        ability.startDisable(duration);
        if (!player.world.isRemote) {
          WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), (LivingEntity)player);
        }
      } 
    } 
  }
  
  public static boolean canUseMomentumAbility(PlayerEntity player) {
    return (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() > 0.0D);
  }


  
  @Deprecated
  public static void setAirJumps(PlayerEntity player, int value) {
    GeppoAbility geppo = (GeppoAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)GeppoAbility.INSTANCE);
    if (geppo != null) {
      geppo.airJumps = value;
    }
    SoraNoMichiAbility soraNoMichi = (SoraNoMichiAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SoraNoMichiAbility.INSTANCE);
    if (soraNoMichi != null) {
      soraNoMichi.airJumps = value;
    }
    SkywalkAbility SkyWalk = (SkywalkAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SkywalkAbility.INSTANCE);
    if (SkyWalk != null) {
      SkyWalk.airJumps = value;
    }
  }
  
  @Nullable
  public static AbilityOverlay getCurrentOverlay(PlayerEntity player) {
    AbilityOverlay overlay = null;
    Ability[] list = AbilityDataCapability.get((LivingEntity)player).getEquippedAbilities();
    for (Ability ability : list) {
      
      if (ability != null && (!(ability instanceof ContinuousAbility) || ability.isContinuous()))
      {
        
        if (ability instanceof IPunchOverlayAbility) {
          overlay = ((IPunchOverlayAbility)ability).getPunchOverlay((LivingEntity)player);
        } else if (ability instanceof IBodyOverlayAbility) {
          overlay = ((IBodyOverlayAbility)ability).getBodyOverlay();
        }  } 
    } 
    return overlay;
  }

  
  public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, BlockProtectionRule instance) {
    return placeBlockIfAllowed(world, posX, posY, posZ, toPlace, 2, instance);
  }

  
  public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, BlockState toPlace, int flag, BlockProtectionRule rule) {
    BlockPos pos = new BlockPos(posX, posY, posZ);
    if (World.isOutsideBuildHeight(pos)) {
      return false;
    }
    BlockState currentBlockState = world.getBlockState(pos);
    
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    boolean inProtectedAreaFlag = worldData.isInsideRestrictedArea((int)posX, (int)posY, (int)posZ);
    boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
    boolean isGriefBypass = rule.getBypassGriefRule();
    
    if ((!isGriefBypass && inProtectedAreaFlag) || (!isGriefBypass && isGriefDisabled)) {
      return false;
    }
    if (rule.check(world, pos, currentBlockState)) {
      
      WyHelper.setBlockStateInChunk(world, pos, toPlace, flag);
      
      return true;
    } 
    
    return false;
  }

  
  public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, BlockProtectionRule rule) {
    return placeBlockIfAllowed(world, posX, posY, posZ, toPlace.getDefaultState(), flag, rule);
  }
  
  public static void causeDamageWithPiercing(LivingEntity target, DamageSource source, float damage, float pierce) {
    DamageSource piercingSource;
    if (source.isUnblockable()) {
      
      target.attackEntityFrom(source, damage);
      
      return;
    } 
    
    if (source instanceof IndirectEntityDamageSource) {
      piercingSource = (new IndirectEntityDamageSource(source.getDamageType(), source.getImmediateSource(), source.getTrueSource())).setDamageBypassesArmor();
    } else if (source instanceof EntityDamageSource) {
      piercingSource = (new EntityDamageSource(source.getDamageType(), source.getTrueSource())).setDamageBypassesArmor();
    } else {
      piercingSource = (new DamageSource(source.getDamageType())).setDamageBypassesArmor();
    } 
    if (source.isFireDamage())
      piercingSource.setFireDamage(); 
    if (source.isExplosion())
      piercingSource.setExplosion(); 
    if (source.isProjectile()) {
      piercingSource.setProjectile();
    }
    target.attackEntityFrom(piercingSource, damage * pierce);
    target.hurtTime = target.hurtResistantTime = 0;
    target.attackEntityFrom(source, damage * (1.0F - pierce));
  }

  
  public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
    return createEmptyCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
  }

  
  public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule) {
    List<BlockPos> blockPositions = new ArrayList<>();
    for (int x = -sizeX; x <= sizeX; x++) {
      
      for (int y = -sizeY; y <= sizeY; y++) {
        
        for (int z = -sizeZ; z <= sizeZ; z++) {
          
          if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ) {
            
            BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
            if (placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, flags, rule))
              blockPositions.add(pos); 
          } 
        } 
      } 
    } 
    return blockPositions;
  }

  
  public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
    List<BlockPos> blockPositions = new ArrayList<>();
    for (int x = -sizeX; x <= sizeX; x++) {
      for (int y = -sizeY; y <= sizeY; y++) {
        for (int z = -sizeZ; z <= sizeZ; z++) {
          
          BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
          if (placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, rule))
            blockPositions.add(pos); 
        } 
      } 
    }  return blockPositions;
  }

  
  public static List<BlockPos> createSphereWithProtection(World world, BlockPos center, int radiusXZ, int radiusY, Block block, int flags, BlockProtectionRule rule) {
    int x0 = center.getX();
    int y0 = center.getY();
    int z0 = center.getZ();
    
    List<BlockPos> blockPositions = new ArrayList<>();
    for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
      
      for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
        
        for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
          
          double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
          
          if (distance < (radiusXZ * radiusY)) {
            
            BlockPos pos = new BlockPos(x, y, z);
            int posDifference = center.getY() - pos.getY();
            boolean fallingProtection = (Math.sqrt(pos.distanceSq((Vec3i)center.down(posDifference))) > 2.5D);
            if (fallingProtection && placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), block, flags, rule)) {
              blockPositions.add(pos);
            }
          } 
        } 
      } 
    } 
    return blockPositions;
  }

  
  public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
    return createSphere(world, center, radiusXZ, radiusXZ, hollow, block, flags, rule);
  }

  
  public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
    return createSphere(world, center, radiusXZ, radiusY, hollow, block, null, flags, rule);
  }

  
  public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, @Nullable BlockProtectionRule.IReplaceBlockRule replaceTest, int flags, BlockProtectionRule rule) {
    int x0 = center.getX();
    int y0 = center.getY();
    int z0 = center.getZ();
    
    List<BlockPos> blockPositions = new ArrayList<>();
    for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
      
      for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
        
        for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
          
          double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
          
          if (distance < (radiusXZ * radiusY) && (!hollow || distance >= ((radiusXZ - 1) * (radiusXZ - 1)))) {
            
            BlockPos pos = new BlockPos(x, y, z);
            BlockState state = world.getBlockState(pos);






            
            if (replaceTest == null || replaceTest.replace(world, pos, state))
            {
              
              if (placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), block, flags, rule))
                blockPositions.add(pos); 
            }
          } 
        } 
      } 
    } 
    return blockPositions;
  }

  
  @Deprecated
  public static List<BlockPos> createEmptySphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
    return createSphere(world, new BlockPos(posX, posY, posZ), size, true, block, 2, rule);
  }

  
  @Deprecated
  public static List<BlockPos> createFilledSphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
    return createSphere(world, new BlockPos(posX, posY, posZ), size, false, block, 2, rule);
  }

  
  public static double[] propulsion(LivingEntity entity, double extraMX, double extraMZ) {
    double mX = (-MathHelper.sin(entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
    double mZ = (MathHelper.cos(entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
    
    double f2 = MathHelper.sqrt(mX * mX + (entity.getMotion()).y * (entity.getMotion()).y + mZ * mZ);
    mX /= f2;
    mZ /= f2;
    mX += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
    mZ += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
    mX *= extraMX;
    mZ *= extraMZ;
    
    return new double[] { mX, mZ };
  }




  
  public static ExplosionAbility newExplosion(Entity entity, World world, double posX, double posY, double posZ, float size) {
    return new ExplosionAbility(entity, world, posX, posY, posZ, size);
  }

  
  public static boolean canUseBrawlerAbilities(LivingEntity entity) {
    return entity.getHeldItemMainhand().isEmpty();
  }

  
  public static boolean canUseSwordsmanAbilities(LivingEntity entity) {
    boolean hasSwordInHand = ItemsHelper.isSword(entity.getHeldItemMainhand());
    if (entity instanceof PlayerEntity) {
      
      IAbilityData abilityProps = AbilityDataCapability.get(entity);
      Ability sparClawAbility = abilityProps.getEquippedAbility((Ability)SparClawAbility.INSTANCE);
      
      boolean hasSparClaw = (sparClawAbility != null && sparClawAbility.isContinuous());
      
      return (hasSwordInHand || hasSparClaw);
    } 

    
    return hasSwordInHand;
  }





  
  @Deprecated
  public static boolean checkForRestriction(World world, int posX, int posY, int posZ) {
    ExtendedWorldData worldData = ExtendedWorldData.get(world);
    
    if (worldData.isInsideRestrictedArea(posX, posY, posZ)) {
      return true;
    }
    return false;
  }

  
  public static boolean isAffectedByWater(LivingEntity entity) {
    boolean isUnderWater = entity.areEyesInFluid(FluidTags.WATER, true);
    boolean waterAbove = (entity.world.getBlockState(entity.getPosition().up()).getBlock() == Blocks.WATER);
    boolean inWater = (entity.world.getBlockState(entity.getPosition()).getBlock() == Blocks.WATER);
    boolean waterUnder = (entity.world.getBlockState(entity.getPosition().down()).getBlock() == Blocks.WATER);
    int total = 0;
    if (waterAbove)
      total++; 
    if (inWater)
      total++; 
    if (waterUnder)
      total++; 
    boolean hasWaterUnder = (entity.isInWater() && total >= 2);
    
    return (entity.getRidingEntity() == null && (isUnderWater || hasWaterUnder));
  }

  
  public static boolean isNearbyKairoseki(PlayerEntity player) {
    return (WyHelper.isBlockNearby((LivingEntity)player, 1, new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS
        }) || ItemsHelper.hasKairosekiItem((LivingEntity)player) || 
      isAffectedByWater((LivingEntity)player));
  }

  
  public static boolean verifyIfAbilityIsBanned(Ability a) {
    for (String str : CommonConfig.INSTANCE.getBannedAbilities()) {
      
      if (WyHelper.getResourceName(str).contains(WyHelper.getResourceName(a.getName()))) {
        return true;
      }
    } 
    return false;
  }


  
  public static Predicate<Ability> getAbilityFromCategoryPredicate(APIConfig.AbilityCategory category) {
    return ability -> (ability.getUnlockType() == AbilityUnlock.COMMAND) ? false : ((ability.getCategory() == category));
  }










  
  public static void validateDevilFruitMoves(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      return;
    }
    IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    ItemStack df = DevilFruitHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
    if (df != null && !df.isEmpty()) {
      
      if (devilFruitProps.hasYamiPower()) {
        
        ItemStack yami = DevilFruitHelper.getDevilFruitItem("yami_yami");
        for (Ability a : ((AkumaNoMiItem)yami.getItem()).abilities) {
          if (verifyIfAbilityIsBanned(a)) {
            abilityProps.removeUnlockedAbility(a);
          } else if (!verifyIfAbilityIsBanned(a) && !abilityProps.hasUnlockedAbility(a)) {
            abilityProps.addUnlockedAbility(a);
          } 
        } 
      }  for (Ability a : ((AkumaNoMiItem)df.getItem()).abilities) {
        if (verifyIfAbilityIsBanned(a)) {
          abilityProps.removeUnlockedAbility(a);
        } else if (!verifyIfAbilityIsBanned(a) && !abilityProps.hasUnlockedAbility(a)) {
          abilityProps.addUnlockedAbility(a);
        } 
      } 
    } else {
      abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getDevilFruitCategory()));
    } 
  }

  
  public static void validateRacialMoves(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      return;
    }
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getRacialCategory()));
    DorikiEvent e = new DorikiEvent(player, 0);
    MinecraftForge.EVENT_BUS.post((Event)e);

    
    Predicate<Ability> hakiPredicate = ability -> (ability.getUnlockType() == AbilityUnlock.COMMAND) ? false : (



      
      (HakiHelper.isHaoshokuBorn(player) && ability instanceof xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility) ? false : ((ability.getCategory() == getHakiCategory())));







    
    abilityProps.clearUnlockedAbilities(hakiPredicate);
    HakiExpEvent e2 = new HakiExpEvent(player, 0.0F, HakiType.HARDENING);
    MinecraftForge.EVENT_BUS.post((Event)e2);
  }

  
  public static void validateStyleMoves(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      return;
    }
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getStyleCategory()));
    
    if (props.isSwordsman()) {
      
      validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, (Ability)ShiShishiSonsonAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, YakkodoriAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_03, SanbyakurokujuPoundHoAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_04, OTatsumakiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_05, (Ability)HiryuKaenAbility.INSTANCE);
    }
    else if (props.isSniper()) {
      
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_01, KaenBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_02, KemuriBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_03, TokuyoAburaBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_03, TetsuBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_04, SakuretsuSabotenBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_05, NemuriBoshiAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.SNIPER_TRIAL_06, (Ability)RenpatsuNamariBoshiAbility.INSTANCE);
    }
    else if (props.isDoctor()) {
      
      validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_01, (Ability)FirstAidAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_02, FailedExperimentAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_03, (Ability)MedicBagExplosionAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_03, (Ability)DopingAbility.INSTANCE);
    }
    else if (props.isWeatherWizard()) {
      
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)HeatBallAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)CoolBallAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)WeatherCloudTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)ThunderBallAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)ThunderboltTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)RainTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)MirageTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)FogTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_03, (Ability)ThunderstormTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_03, (Ability)ThunderLanceTempo.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_04, (Ability)GustSwordAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_04, (Ability)WeatherEggAbility.INSTANCE);
    }
    else if (props.isBlackLeg()) {
      
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_01, (Ability)ConcasseAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_02, (Ability)ExtraHachisAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_03, (Ability)AntiMannerKickCourseAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_03, (Ability)PartyTableKickCourseAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_04, (Ability)SkywalkAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_05, (Ability)DiableJambeAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_05, (Ability)BienCuitGrillShotAbility.INSTANCE);
    }
    else if (props.isBrawler()) {
      
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_01, (Ability)SuplexAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_02, (Ability)SpinningBrawlAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_03, (Ability)GenkotsuMeteorAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_04, (Ability)HakaiHoAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_05, (Ability)JishinHoAbility.INSTANCE);
      validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_06, (Ability)KingPunchAbility.INSTANCE);
    } 
  }

  
  public static void validateFactionMoves(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
      return;
    }
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    List<Ability> tempAblList = new ArrayList<>();
    
    if (props.isMarine())
      for (Ability a : ModAbilities.MARINE_ABILITIES) {
        if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
          tempAblList.add(a); 
      }  
    abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getFactionCategory()));
    
    for (Ability a : tempAblList)
      abilityProps.addUnlockedAbility(a); 
    tempAblList.clear();
  }

  
  private static void validateQuestAbility(PlayerEntity player, Quest quest, Ability ability) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    IQuestData questProps = QuestDataCapability.get(player);
    
    boolean hasQuestFinished = (CommonConfig.INSTANCE.isQuestProgressionEnabled() && questProps.hasFinishedQuest(quest));
    boolean isBanned = verifyIfAbilityIsBanned(ability);
    
    if (hasQuestFinished && !isBanned) {
      abilityProps.addUnlockedAbility(ability);
    }
  }
  
  public static boolean isTargetBlockingAbility(LivingEntity attacker, LivingEntity target) {
    IDevilFruit targetDevilFruitData = DevilFruitCapability.get(target);
    
    boolean isLogia = (targetDevilFruitData.isLogia() && !HakiHelper.hasHardeningActive(attacker));
    boolean isGuarding = target.getActivePotionEffects().stream().anyMatch(instance -> instance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect);
    boolean hasShield = target.isActiveItemStackBlocking();
    
    float targetHardening = HakiDataCapability.get(target).getBusoshokuHardeningHakiExp();
    float attackerHardening = HakiDataCapability.get(attacker).getBusoshokuHardeningHakiExp();
    
    if (targetDevilFruitData.hasDevilFruit()) {
      targetHardening = (float)(targetHardening + WyHelper.randomWithRange(-5, 5));
    }
    if (DevilFruitCapability.get(attacker).hasDevilFruit()) {
      attackerHardening = (float)(attackerHardening + WyHelper.randomWithRange(-5, 5));
    }
    int hakiExpLimit = CommonConfig.INSTANCE.getHakiExpLimit();
    targetHardening = MathHelper.clamp(targetHardening, 0.0F, hakiExpLimit);
    attackerHardening = MathHelper.clamp(targetHardening, 0.0F, hakiExpLimit);
    
    boolean hasHigherHardening = (targetHardening > attackerHardening);
    
    if (hasShield) {
      attacker.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + attacker.world.rand.nextFloat() * 0.4F);
    } else if (isGuarding) {
      
      attacker.world.playSound(null, attacker.getPosition(), ModSounds.GUARD, SoundCategory.PLAYERS, 0.8F, 1.0F);
      (new GuardParticleEffect()).spawn(attacker.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    else if (hasHigherHardening) {
      
      attacker.world.playSound(null, attacker.getPosition(), ModSounds.HAKI_GUARD, SoundCategory.PLAYERS, 0.8F, 1.0F);
      (new HakiGuardParticleEffect()).spawn(attacker.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    else if (isLogia) {

      
      Objects.requireNonNull(LogiaInvulnerabilityAbility.class);
      
      Objects.requireNonNull(LogiaInvulnerabilityAbility.class); AbilityDataCapability.get(target).getUnlockedAbilities(getDevilFruitCategory()).stream().filter(LogiaInvulnerabilityAbility.class::isInstance).limit(1L).map(LogiaInvulnerabilityAbility.class::cast)
        .forEach(abl -> abl.spawnParticles((Entity)target, false));
    } 
    
    return (isGuarding || hasShield || isLogia || hasHigherHardening);
  }

  
  public static boolean isTargetUsingBlockingPotion(LivingEntity target) {
    return target.getActivePotionEffects().stream().anyMatch(instance -> instance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect);
  }

  
  public static void addFrostbite(LivingEntity target, @Nullable LivingEntity player, int duration) {
    if (target == null) {
      return;
    }
    EffectInstance effect = target.getActivePotionEffect(ModEffects.FROSTBITE);
    EffectInstance frozen = target.getActivePotionEffect(ModEffects.FROZEN);
    
    if (frozen != null && effect != null) {
      
      EffectInstance effectInstance = new EffectInstance(ModEffects.FROZEN, duration, 0);
      ((EffectInstanceMixin)frozen).setDuration((int)(effect.getDuration() + duration / 2.5F));
      if (player != null && player instanceof ServerPlayerEntity) {
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), (effect == null) ? effectInstance : effect));
      }
      return;
    } 
    EffectInstance instance = new EffectInstance(ModEffects.FROSTBITE, duration, 0);
    
    if (effect == null) {
      target.addPotionEffect(instance);
    } else {
      ((EffectInstanceMixin)effect).setDuration(effect.getDuration() + duration);
    } 
    if (player != null && player instanceof ServerPlayerEntity) {
      ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), (effect == null) ? instance : effect));
    }
  }
  
  public static void reduceEffect(EffectInstance effect, double reduction) {
    if (effect == null) {
      return;
    }
    try {
      double duration = effect.getDuration() / reduction;
      ((EffectInstanceMixin)effect).setDuration((int)duration);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  
  @Deprecated
  public static boolean isBlockInsideRoom(LivingEntity entity, BlockPos vector3d) {
    for (int i = -20; i < 20; i++) {
      for (int j = -20; j < 20; j++) {
        for (int k = -20; k < 20; k++) {
          
          if (entity.world.getBlockState(new BlockPos(vector3d.getX() + i, vector3d.getY() + j, vector3d.getZ() + k)).getBlock() == ModBlocks.OPE_MID)
            return true; 
        } 
      } 
    } 
    return false;
  }

  
  public static void slowEntityFall(LivingEntity player) {
    slowEntityFall(player, 5);
  }

  
  public static void slowEntityFall(LivingEntity player, int duration) {
    player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, duration, 0));
  }

  
  public static boolean isJumping(LivingEntity entity) {
    Field f = ObfuscationReflectionHelper.findField(LivingEntity.class, "field_70703_bu");
    
    try {
      return f.getBoolean(entity);
    }
    catch (IllegalAccessException e) {
      
      e.printStackTrace();
      
      return false;
    } 
  }
  
  public static void setPose(LivingEntity entity, Pose pose) {
    Method method = ObfuscationReflectionHelper.findMethod(Entity.class, "func_213301_b", new Class[] { Pose.class });
    
    try {
      method.setAccessible(true);
      method.invoke(entity, new Object[] { pose });
    }
    catch (Exception e) {
      
      e.printStackTrace();
    } 
  }

  
  public static APIConfig.AbilityCategory getDevilFruitCategory() {
    return APIConfig.AbilityCategory.valueOf("DEVIL_FRUIT");
  }

  
  public static APIConfig.AbilityCategory getRacialCategory() {
    return APIConfig.AbilityCategory.valueOf("RACIAL");
  }

  
  public static APIConfig.AbilityCategory getStyleCategory() {
    return APIConfig.AbilityCategory.valueOf("STYLE");
  }

  
  public static APIConfig.AbilityCategory getHakiCategory() {
    return APIConfig.AbilityCategory.valueOf("HAKI");
  }

  
  public static APIConfig.AbilityCategory getFactionCategory() {
    return APIConfig.AbilityCategory.valueOf("FACTION");
  }

  
  public static APIConfig.AbilityCategory getEquipmentCategory() {
    return APIConfig.AbilityCategory.valueOf("EQUIPMENT");
  }
}


