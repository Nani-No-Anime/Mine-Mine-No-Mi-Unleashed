/*     */ package xyz.pixelatedw.mineminenomi.api.helpers.abilities;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.IndirectEntityDamageSource;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.LogiaInvulnerabilityAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.GustSwordAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.WeatherEggAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderstormTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.AntiMannerKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.BienCuitGrillShotAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ConcasseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.DiableJambeAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.ExtraHachisAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.PartyTableKickCourseAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.blackleg.SkywalkAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.GenkotsuMeteorAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.HakaiHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.JishinHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.KingPunchAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SpinningBrawlAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.brawler.SuplexAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.DopingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ito.SoraNoMichiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.NemuriBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TetsuBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sniper.TokuyoAburaBoshiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.HiryuKaenAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.GuardParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.HakiGuardParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbilityHelper
/*     */ {
/*     */   @Nullable
/*     */   public static IAnimatedAbility getActiveAnimationAbility(LivingEntity entity) {
/* 134 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 135 */     List<Ability> abilities = new ArrayList<>();
/* 136 */     //abilities.addAll(abilityProps.getEquippedAbilities(IAnimatedAbility.IS_ACTIVE));
/* 137 */     abilities.addAll(abilityProps.getUnlockedAbilities(IAnimatedAbility.IS_ACTIVE));
/* 138 */     Optional<Ability> optional = abilities.stream().findFirst();
/* 139 */     if (optional.isPresent()) {
/*     */       
/* 141 */       IAnimatedAbility ability = (IAnimatedAbility)optional.get();
/* 142 */       return ability;
/*     */     } 
/*     */     
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void enableAbilities(PlayerEntity player, Predicate<Ability> check) {
/* 150 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 152 */     for (Ability ability : abilityData.getEquippedAbilities(check)) {
/*     */       
/* 154 */       if (ability != null && ability.isDisabled()) {
/*     */         
/* 156 */         ability.startCooldown(player);
/* 157 */         if (!player.world.isRemote) {
/* 158 */           WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), (LivingEntity)player);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void disableAbilities(PlayerEntity player, int duration, Predicate<Ability> check) {
/* 165 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 167 */     for (Ability ability : abilityData.getEquippedAbilities(check)) {
/*     */       
/* 169 */       if (ability != null && !ability.isDisabled()) {
/*     */         
/* 171 */         if (ability instanceof ContinuousAbility && ability.getState() == Ability.State.CONTINUOUS)
/* 172 */           ((ContinuousAbility)ability).endContinuity(player); 
/* 173 */         if (ability instanceof RepeaterAbility && ability.getState() == Ability.State.CONTINUOUS)
/* 174 */           ((RepeaterAbility)ability).setRepeaterCount(((RepeaterAbility)ability).getMaxRepeaterCount()); 
/* 175 */         if (ability instanceof ChargeableAbility && ability.getState() == Ability.State.CHARGING) {
/*     */           
/* 177 */           ((ChargeableAbility)ability).setChargeTime(((ChargeableAbility)ability).getMaxChargeTime() / 20);
/* 178 */           ability.startCooldown(player);
/*     */         } 
/* 180 */         ability.startDisable(duration);
/* 181 */         if (!player.world.isRemote) {
/* 182 */           WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, ability), (LivingEntity)player);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean canUseMomentumAbility(PlayerEntity player) {
/* 189 */     return (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() > 0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void setAirJumps(PlayerEntity player, int value) {
/* 196 */     GeppoAbility geppo = (GeppoAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)GeppoAbility.INSTANCE);
/* 197 */     if (geppo != null) {
/* 198 */       geppo.airJumps = value;
/*     */     }
/* 200 */     SoraNoMichiAbility soraNoMichi = (SoraNoMichiAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SoraNoMichiAbility.INSTANCE);
/* 201 */     if (soraNoMichi != null) {
/* 202 */       soraNoMichi.airJumps = value;
/*     */     }
/* 204 */     SkywalkAbility SkyWalk = (SkywalkAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SkywalkAbility.INSTANCE);
/* 205 */     if (SkyWalk != null) {
/* 206 */       SkyWalk.airJumps = value;
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static AbilityOverlay getCurrentOverlay(PlayerEntity player) {
/* 212 */     AbilityOverlay overlay = null;
/* 213 */     Ability[] list = AbilityDataCapability.get((LivingEntity)player).getEquippedAbilities();
/* 214 */     for (Ability ability : list) {
/*     */       
/* 216 */       if (ability != null && (!(ability instanceof ContinuousAbility) || ability.isContinuous()))
/*     */       {
/*     */         
/* 219 */         if (ability instanceof IPunchOverlayAbility) {
/* 220 */           overlay = ((IPunchOverlayAbility)ability).getPunchOverlay((LivingEntity)player);
/* 221 */         } else if (ability instanceof IBodyOverlayAbility) {
/* 222 */           overlay = ((IBodyOverlayAbility)ability).getBodyOverlay();
/*     */         }  } 
/*     */     } 
/* 225 */     return overlay;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, BlockProtectionRule instance) {
/* 230 */     return placeBlockIfAllowed(world, posX, posY, posZ, toPlace, 2, instance);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, BlockState toPlace, int flag, BlockProtectionRule rule) {
/* 235 */     BlockPos pos = new BlockPos(posX, posY, posZ);
/* 236 */     if (World.isOutsideBuildHeight(pos)) {
/* 237 */       return false;
/*     */     }
/* 239 */     BlockState currentBlockState = world.getBlockState(pos);
/*     */     
/* 241 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/* 242 */     boolean inProtectedAreaFlag = worldData.isInsideRestrictedArea((int)posX, (int)posY, (int)posZ);
/* 243 */     boolean isGriefDisabled = !CommonConfig.INSTANCE.isAbilityGriefingEnabled();
/* 244 */     boolean isGriefBypass = rule.getBypassGriefRule();
/*     */     
/* 246 */     if ((!isGriefBypass && inProtectedAreaFlag) || (!isGriefBypass && isGriefDisabled)) {
/* 247 */       return false;
/*     */     }
/* 249 */     if (rule.check(world, pos, currentBlockState)) {
/*     */       
/* 251 */       WyHelper.setBlockStateInChunk(world, pos, toPlace, flag);
/*     */       
/* 253 */       return true;
/*     */     } 
/*     */     
/* 256 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, BlockProtectionRule rule) {
/* 261 */     return placeBlockIfAllowed(world, posX, posY, posZ, toPlace.getDefaultState(), flag, rule);
/*     */   }
/*     */   
/*     */   public static void causeDamageWithPiercing(LivingEntity target, DamageSource source, float damage, float pierce) {
/*     */     DamageSource piercingSource;
/* 266 */     if (source.isUnblockable()) {
/*     */       
/* 268 */       target.attackEntityFrom(source, damage);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 273 */     if (source instanceof IndirectEntityDamageSource) {
/* 274 */       piercingSource = (new IndirectEntityDamageSource(source.getDamageType(), source.getImmediateSource(), source.getTrueSource())).setDamageBypassesArmor();
/* 275 */     } else if (source instanceof EntityDamageSource) {
/* 276 */       piercingSource = (new EntityDamageSource(source.getDamageType(), source.getTrueSource())).setDamageBypassesArmor();
/*     */     } else {
/* 278 */       piercingSource = (new DamageSource(source.getDamageType())).setDamageBypassesArmor();
/*     */     } 
/* 280 */     if (source.isFireDamage())
/* 281 */       piercingSource.setFireDamage(); 
/* 282 */     if (source.isExplosion())
/* 283 */       piercingSource.setExplosion(); 
/* 284 */     if (source.isProjectile()) {
/* 285 */       piercingSource.setProjectile();
/*     */     }
/* 287 */     target.attackEntityFrom(piercingSource, damage * pierce);
/* 288 */     target.hurtTime = target.hurtResistantTime = 0;
/* 289 */     target.attackEntityFrom(source, damage * (1.0F - pierce));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/* 294 */     return createEmptyCube(world, posX, posY, posZ, sizeX, sizeY, sizeZ, 2, blockToPlace, rule);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, int flags, Block blockToPlace, BlockProtectionRule rule) {
/* 299 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 300 */     for (int x = -sizeX; x <= sizeX; x++) {
/*     */       
/* 302 */       for (int y = -sizeY; y <= sizeY; y++) {
/*     */         
/* 304 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*     */           
/* 306 */           if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ) {
/*     */             
/* 308 */             BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
/* 309 */             if (placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, flags, rule))
/* 310 */               blockPositions.add(pos); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 315 */     return blockPositions;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule) {
/* 320 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 321 */     for (int x = -sizeX; x <= sizeX; x++) {
/* 322 */       for (int y = -sizeY; y <= sizeY; y++) {
/* 323 */         for (int z = -sizeZ; z <= sizeZ; z++) {
/*     */           
/* 325 */           BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
/* 326 */           if (placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, rule))
/* 327 */             blockPositions.add(pos); 
/*     */         } 
/*     */       } 
/* 330 */     }  return blockPositions;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createSphereWithProtection(World world, BlockPos center, int radiusXZ, int radiusY, Block block, int flags, BlockProtectionRule rule) {
/* 335 */     int x0 = center.getX();
/* 336 */     int y0 = center.getY();
/* 337 */     int z0 = center.getZ();
/*     */     
/* 339 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 340 */     for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
/*     */       
/* 342 */       for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
/*     */         
/* 344 */         for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
/*     */           
/* 346 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*     */           
/* 348 */           if (distance < (radiusXZ * radiusY)) {
/*     */             
/* 350 */             BlockPos pos = new BlockPos(x, y, z);
/* 351 */             int posDifference = center.getY() - pos.getY();
/* 352 */             boolean fallingProtection = (Math.sqrt(pos.distanceSq((Vec3i)center.down(posDifference))) > 2.5D);
/* 353 */             if (fallingProtection && placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), block, flags, rule)) {
/* 354 */               blockPositions.add(pos);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 360 */     return blockPositions;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/* 365 */     return createSphere(world, center, radiusXZ, radiusXZ, hollow, block, flags, rule);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, int flags, BlockProtectionRule rule) {
/* 370 */     return createSphere(world, center, radiusXZ, radiusY, hollow, block, null, flags, rule);
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<BlockPos> createSphere(World world, BlockPos center, int radiusXZ, int radiusY, boolean hollow, Block block, @Nullable BlockProtectionRule.IReplaceBlockRule replaceTest, int flags, BlockProtectionRule rule) {
/* 375 */     int x0 = center.getX();
/* 376 */     int y0 = center.getY();
/* 377 */     int z0 = center.getZ();
/*     */     
/* 379 */     List<BlockPos> blockPositions = new ArrayList<>();
/* 380 */     for (int y = y0 - radiusY; y <= y0 + radiusY; y++) {
/*     */       
/* 382 */       for (int x = x0 - radiusXZ; x <= x0 + radiusXZ; x++) {
/*     */         
/* 384 */         for (int z = z0 - radiusXZ; z <= z0 + radiusXZ; z++) {
/*     */           
/* 386 */           double distance = ((x0 - x) * (x0 - x) + (z0 - z) * (z0 - z) + (y0 - y) * (y0 - y));
/*     */           
/* 388 */           if (distance < (radiusXZ * radiusY) && (!hollow || distance >= ((radiusXZ - 1) * (radiusXZ - 1)))) {
/*     */             
/* 390 */             BlockPos pos = new BlockPos(x, y, z);
/* 391 */             BlockState state = world.getBlockState(pos);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 399 */             if (replaceTest == null || replaceTest.replace(world, pos, state))
/*     */             {
/*     */               
/* 402 */               if (placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), block, flags, rule))
/* 403 */                 blockPositions.add(pos); 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 409 */     return blockPositions;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static List<BlockPos> createEmptySphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
/* 415 */     return createSphere(world, new BlockPos(posX, posY, posZ), size, true, block, 2, rule);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static List<BlockPos> createFilledSphere(World world, int posX, int posY, int posZ, int size, Block block, BlockProtectionRule rule) {
/* 421 */     return createSphere(world, new BlockPos(posX, posY, posZ), size, false, block, 2, rule);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double[] propulsion(LivingEntity entity, double extraMX, double extraMZ) {
/* 426 */     double mX = (-MathHelper.sin(entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
/* 427 */     double mZ = (MathHelper.cos(entity.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(entity.rotationPitch / 180.0F * 3.1415927F)) * 0.4D;
/*     */     
/* 429 */     double f2 = MathHelper.sqrt(mX * mX + (entity.getMotion()).y * (entity.getMotion()).y + mZ * mZ);
/* 430 */     mX /= f2;
/* 431 */     mZ /= f2;
/* 432 */     mX += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 433 */     mZ += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0D;
/* 434 */     mX *= extraMX;
/* 435 */     mZ *= extraMZ;
/*     */     
/* 437 */     return new double[] { mX, mZ };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ExplosionAbility newExplosion(Entity entity, World world, double posX, double posY, double posZ, float size) {
/* 445 */     return new ExplosionAbility(entity, world, posX, posY, posZ, size);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canUseBrawlerAbilities(LivingEntity entity) {
/* 450 */     return entity.getHeldItemMainhand().isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canUseSwordsmanAbilities(LivingEntity entity) {
/* 455 */     boolean hasSwordInHand = ItemsHelper.isSword(entity.getHeldItemMainhand());
/* 456 */     if (entity instanceof PlayerEntity) {
/*     */       
/* 458 */       IAbilityData abilityProps = AbilityDataCapability.get(entity);
/* 459 */       Ability sparClawAbility = abilityProps.getEquippedAbility((Ability)SparClawAbility.INSTANCE);
/*     */       
/* 461 */       boolean hasSparClaw = (sparClawAbility != null && sparClawAbility.isContinuous());
/*     */       
/* 463 */       return (hasSwordInHand || hasSparClaw);
/*     */     } 
/*     */ 
/*     */     
/* 467 */     return hasSwordInHand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean checkForRestriction(World world, int posX, int posY, int posZ) {
/* 477 */     ExtendedWorldData worldData = ExtendedWorldData.get(world);
/*     */     
/* 479 */     if (worldData.isInsideRestrictedArea(posX, posY, posZ)) {
/* 480 */       return true;
/*     */     }
/* 482 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isAffectedByWater(LivingEntity entity) {
/* 487 */     boolean isUnderWater = entity.areEyesInFluid(FluidTags.WATER, true);
/* 488 */     boolean waterAbove = (entity.world.getBlockState(entity.getPosition().up()).getBlock() == Blocks.WATER);
/* 489 */     boolean inWater = (entity.world.getBlockState(entity.getPosition()).getBlock() == Blocks.WATER);
/* 490 */     boolean waterUnder = (entity.world.getBlockState(entity.getPosition().down()).getBlock() == Blocks.WATER);
/* 491 */     int total = 0;
/* 492 */     if (waterAbove)
/* 493 */       total++; 
/* 494 */     if (inWater)
/* 495 */       total++; 
/* 496 */     if (waterUnder)
/* 497 */       total++; 
/* 498 */     boolean hasWaterUnder = (entity.isInWater() && total >= 2);
/*     */     
/* 500 */     return (entity.getRidingEntity() == null && (isUnderWater || hasWaterUnder));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isNearbyKairoseki(PlayerEntity player) {
/* 505 */     return (WyHelper.isBlockNearby((LivingEntity)player, 1, new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS
/* 506 */         }) || ItemsHelper.hasKairosekiItem((LivingEntity)player) || 
/* 507 */       isAffectedByWater((LivingEntity)player));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean verifyIfAbilityIsBanned(Ability a) {
/* 512 */     for (String str : CommonConfig.INSTANCE.getBannedAbilities()) {
/*     */       
/* 514 */       if (WyHelper.getResourceName(str).contains(WyHelper.getResourceName(a.getName()))) {
/* 515 */         return true;
/*     */       }
/*     */     } 
/* 518 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Predicate<Ability> getAbilityFromCategoryPredicate(APIConfig.AbilityCategory category) {
/* 524 */     return ability -> (ability.getUnlockType() == AbilityUnlock.COMMAND) ? false : ((ability.getCategory() == category));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void validateDevilFruitMoves(PlayerEntity player) {
/* 538 */     if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*     */       return;
/*     */     }
/* 541 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 542 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 544 */     ItemStack df = DevilFruitHelper.getDevilFruitItem(devilFruitProps.getDevilFruit());
/* 545 */     if (df != null && !df.isEmpty()) {
/*     */       
/* 547 */       if (devilFruitProps.hasYamiPower()) {
/*     */         
/* 549 */         ItemStack yami = DevilFruitHelper.getDevilFruitItem("yami_yami");
/* 550 */         for (Ability a : ((AkumaNoMiItem)yami.getItem()).abilities) {
/* 551 */           if (verifyIfAbilityIsBanned(a)) {
/* 552 */             abilityProps.removeUnlockedAbility(a);
/* 553 */           } else if (!verifyIfAbilityIsBanned(a) && !abilityProps.hasUnlockedAbility(a)) {
/* 554 */             abilityProps.addUnlockedAbility(a);
/*     */           } 
/*     */         } 
/* 557 */       }  for (Ability a : ((AkumaNoMiItem)df.getItem()).abilities) {
/* 558 */         if (verifyIfAbilityIsBanned(a)) {
/* 559 */           abilityProps.removeUnlockedAbility(a);
/* 560 */         } else if (!verifyIfAbilityIsBanned(a) && !abilityProps.hasUnlockedAbility(a)) {
/* 561 */           abilityProps.addUnlockedAbility(a);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 565 */       abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getDevilFruitCategory()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void validateRacialMoves(PlayerEntity player) {
/* 571 */     if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*     */       return;
/*     */     }
/* 574 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 576 */     abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getRacialCategory()));
/* 577 */     DorikiEvent e = new DorikiEvent(player, 0);
/* 578 */     MinecraftForge.EVENT_BUS.post((Event)e);
/*     */ 
/*     */     
/* 581 */     Predicate<Ability> hakiPredicate = ability -> (ability.getUnlockType() == AbilityUnlock.COMMAND) ? false : (
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 586 */       (HakiHelper.isHaoshokuBorn(player) && ability instanceof xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility) ? false : ((ability.getCategory() == getHakiCategory())));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 595 */     abilityProps.clearUnlockedAbilities(hakiPredicate);
/* 596 */     HakiExpEvent e2 = new HakiExpEvent(player, 0.0F, HakiType.HARDENING);
/* 597 */     MinecraftForge.EVENT_BUS.post((Event)e2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void validateStyleMoves(PlayerEntity player) {
/* 602 */     if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*     */       return;
/*     */     }
/* 605 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 606 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 608 */     abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getStyleCategory()));
/*     */     
/* 610 */     if (props.isSwordsman()) {
/*     */       
/* 612 */       validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, (Ability)ShiShishiSonsonAbility.INSTANCE);
/* 613 */       validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, YakkodoriAbility.INSTANCE);
/* 614 */       validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_03, SanbyakurokujuPoundHoAbility.INSTANCE);
/* 615 */       validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_04, OTatsumakiAbility.INSTANCE);
/* 616 */       validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_05, (Ability)HiryuKaenAbility.INSTANCE);
/*     */     }
/* 618 */     else if (props.isSniper()) {
/*     */       
/* 620 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_01, KaenBoshiAbility.INSTANCE);
/* 621 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_02, KemuriBoshiAbility.INSTANCE);
/* 622 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_03, TokuyoAburaBoshiAbility.INSTANCE);
/* 623 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_03, TetsuBoshiAbility.INSTANCE);
/* 624 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_04, SakuretsuSabotenBoshiAbility.INSTANCE);
/* 625 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_05, NemuriBoshiAbility.INSTANCE);
/* 626 */       validateQuestAbility(player, ModQuests.SNIPER_TRIAL_06, (Ability)RenpatsuNamariBoshiAbility.INSTANCE);
/*     */     }
/* 628 */     else if (props.isDoctor()) {
/*     */       
/* 630 */       validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_01, (Ability)FirstAidAbility.INSTANCE);
/* 631 */       validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_02, FailedExperimentAbility.INSTANCE);
/* 632 */       validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_03, (Ability)MedicBagExplosionAbility.INSTANCE);
/* 633 */       validateQuestAbility(player, ModQuests.DOCTOR_TRIAL_03, (Ability)DopingAbility.INSTANCE);
/*     */     }
/* 635 */     else if (props.isWeatherWizard()) {
/*     */       
/* 637 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)HeatBallAbility.INSTANCE);
/* 638 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)CoolBallAbility.INSTANCE);
/* 639 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_01, (Ability)WeatherCloudTempo.INSTANCE);
/* 640 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)ThunderBallAbility.INSTANCE);
/* 641 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)ThunderboltTempo.INSTANCE);
/* 642 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)RainTempo.INSTANCE);
/* 643 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)MirageTempo.INSTANCE);
/* 644 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_02, (Ability)FogTempo.INSTANCE);
/* 645 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_03, (Ability)ThunderstormTempo.INSTANCE);
/* 646 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_03, (Ability)ThunderLanceTempo.INSTANCE);
/* 647 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_04, (Ability)GustSwordAbility.INSTANCE);
/* 648 */       validateQuestAbility(player, ModQuests.ART_OF_WEATHER_TRIAL_04, (Ability)WeatherEggAbility.INSTANCE);
/*     */     }
/* 650 */     else if (props.isBlackLeg()) {
/*     */       
/* 652 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_01, (Ability)ConcasseAbility.INSTANCE);
/* 653 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_02, (Ability)ExtraHachisAbility.INSTANCE);
/* 654 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_03, (Ability)AntiMannerKickCourseAbility.INSTANCE);
/* 655 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_03, (Ability)PartyTableKickCourseAbility.INSTANCE);
/* 656 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_04, (Ability)SkywalkAbility.INSTANCE);
/* 657 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_05, (Ability)DiableJambeAbility.INSTANCE);
/* 658 */       validateQuestAbility(player, ModQuests.BLACK_LEG_TRIAL_05, (Ability)BienCuitGrillShotAbility.INSTANCE);
/*     */     }
/* 660 */     else if (props.isBrawler()) {
/*     */       
/* 662 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_01, (Ability)SuplexAbility.INSTANCE);
/* 663 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_02, (Ability)SpinningBrawlAbility.INSTANCE);
/* 664 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_03, (Ability)GenkotsuMeteorAbility.INSTANCE);
/* 665 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_04, (Ability)HakaiHoAbility.INSTANCE);
/* 666 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_05, (Ability)JishinHoAbility.INSTANCE);
/* 667 */       validateQuestAbility(player, ModQuests.BRAWLER_TRIAL_06, (Ability)KingPunchAbility.INSTANCE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void validateFactionMoves(PlayerEntity player) {
/* 673 */     if (!CommonConfig.INSTANCE.isAbilityFraudChecksEnabled()) {
/*     */       return;
/*     */     }
/* 676 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 677 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 679 */     List<Ability> tempAblList = new ArrayList<>();
/*     */     
/* 681 */     if (props.isMarine())
/* 682 */       for (Ability a : ModAbilities.MARINE_ABILITIES) {
/* 683 */         if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
/* 684 */           tempAblList.add(a); 
/*     */       }  
/* 686 */     abilityProps.clearUnlockedAbilities(getAbilityFromCategoryPredicate(getFactionCategory()));
/*     */     
/* 688 */     for (Ability a : tempAblList)
/* 689 */       abilityProps.addUnlockedAbility(a); 
/* 690 */     tempAblList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void validateQuestAbility(PlayerEntity player, Quest quest, Ability ability) {
/* 695 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/* 696 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 698 */     boolean hasQuestFinished = (CommonConfig.INSTANCE.isQuestProgressionEnabled() && questProps.hasFinishedQuest(quest));
/* 699 */     boolean isBanned = verifyIfAbilityIsBanned(ability);
/*     */     
/* 701 */     if (hasQuestFinished && !isBanned) {
/* 702 */       abilityProps.addUnlockedAbility(ability);
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isTargetBlockingAbility(LivingEntity attacker, LivingEntity target) {
/* 707 */     IDevilFruit targetDevilFruitData = DevilFruitCapability.get(target);
/*     */     
/* 709 */     boolean isLogia = (targetDevilFruitData.isLogia() && !HakiHelper.hasHardeningActive(attacker));
/* 710 */     boolean isGuarding = target.getActivePotionEffects().stream().anyMatch(instance -> instance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect);
/* 711 */     boolean hasShield = target.isActiveItemStackBlocking();
/*     */     
/* 713 */     float targetHardening = HakiDataCapability.get(target).getBusoshokuHardeningHakiExp();
/* 714 */     float attackerHardening = HakiDataCapability.get(attacker).getBusoshokuHardeningHakiExp();
/*     */     
/* 716 */     if (targetDevilFruitData.hasDevilFruit()) {
/* 717 */       targetHardening = (float)(targetHardening + WyHelper.randomWithRange(-5, 5));
/*     */     }
/* 719 */     if (DevilFruitCapability.get(attacker).hasDevilFruit()) {
/* 720 */       attackerHardening = (float)(attackerHardening + WyHelper.randomWithRange(-5, 5));
/*     */     }
/* 722 */     int hakiExpLimit = CommonConfig.INSTANCE.getHakiExpLimit();
/* 723 */     targetHardening = MathHelper.clamp(targetHardening, 0.0F, hakiExpLimit);
/* 724 */     attackerHardening = MathHelper.clamp(targetHardening, 0.0F, hakiExpLimit);
/*     */     
/* 726 */     boolean hasHigherHardening = (targetHardening > attackerHardening);
/*     */     
/* 728 */     if (hasShield) {
/* 729 */       attacker.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + attacker.world.rand.nextFloat() * 0.4F);
/* 730 */     } else if (isGuarding) {
/*     */       
/* 732 */       attacker.world.playSound(null, attacker.getPosition(), ModSounds.GUARD, SoundCategory.PLAYERS, 0.8F, 1.0F);
/* 733 */       (new GuardParticleEffect()).spawn(attacker.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/* 735 */     else if (hasHigherHardening) {
/*     */       
/* 737 */       attacker.world.playSound(null, attacker.getPosition(), ModSounds.HAKI_GUARD, SoundCategory.PLAYERS, 0.8F, 1.0F);
/* 738 */       (new HakiGuardParticleEffect()).spawn(attacker.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/* 740 */     else if (isLogia) {
/*     */ 
/*     */       
/* 743 */       Objects.requireNonNull(LogiaInvulnerabilityAbility.class);
/*     */       
/* 745 */       Objects.requireNonNull(LogiaInvulnerabilityAbility.class); AbilityDataCapability.get(target).getUnlockedAbilities(getDevilFruitCategory()).stream().filter(LogiaInvulnerabilityAbility.class::isInstance).limit(1L).map(LogiaInvulnerabilityAbility.class::cast)
/* 746 */         .forEach(abl -> abl.spawnParticles((Entity)target, false));
/*     */     } 
/*     */     
/* 749 */     return (isGuarding || hasShield || isLogia || hasHigherHardening);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isTargetUsingBlockingPotion(LivingEntity target) {
/* 754 */     return target.getActivePotionEffects().stream().anyMatch(instance -> instance.getPotion() instanceof xyz.pixelatedw.mineminenomi.effects.GuardingEffect);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addFrostbite(LivingEntity target, @Nullable LivingEntity player, int duration) {
/* 759 */     if (target == null) {
/*     */       return;
/*     */     }
/* 762 */     EffectInstance effect = target.getActivePotionEffect(ModEffects.FROSTBITE);
/* 763 */     EffectInstance frozen = target.getActivePotionEffect(ModEffects.FROZEN);
/*     */     
/* 765 */     if (frozen != null && effect != null) {
/*     */       
/* 767 */       EffectInstance effectInstance = new EffectInstance(ModEffects.FROZEN, duration, 0);
/* 768 */       ((EffectInstanceMixin)frozen).setDuration((int)(effect.getDuration() + duration / 2.5F));
/* 769 */       if (player != null && player instanceof ServerPlayerEntity) {
/* 770 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), (effect == null) ? effectInstance : effect));
/*     */       }
/*     */       return;
/*     */     } 
/* 774 */     EffectInstance instance = new EffectInstance(ModEffects.FROSTBITE, duration, 0);
/*     */     
/* 776 */     if (effect == null) {
/* 777 */       target.addPotionEffect(instance);
/*     */     } else {
/* 779 */       ((EffectInstanceMixin)effect).setDuration(effect.getDuration() + duration);
/*     */     } 
/* 781 */     if (player != null && player instanceof ServerPlayerEntity) {
/* 782 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), (effect == null) ? instance : effect));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void reduceEffect(EffectInstance effect, double reduction) {
/* 787 */     if (effect == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 791 */       double duration = effect.getDuration() / reduction;
/* 792 */       ((EffectInstanceMixin)effect).setDuration((int)duration);
/* 793 */     } catch (Exception e) {
/* 794 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static boolean isBlockInsideRoom(LivingEntity entity, BlockPos vector3d) {
/* 801 */     for (int i = -20; i < 20; i++) {
/* 802 */       for (int j = -20; j < 20; j++) {
/* 803 */         for (int k = -20; k < 20; k++) {
/*     */           
/* 805 */           if (entity.world.getBlockState(new BlockPos(vector3d.getX() + i, vector3d.getY() + j, vector3d.getZ() + k)).getBlock() == ModBlocks.OPE_MID)
/* 806 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 810 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void slowEntityFall(LivingEntity player) {
/* 815 */     slowEntityFall(player, 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void slowEntityFall(LivingEntity player, int duration) {
/* 820 */     player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, duration, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isJumping(LivingEntity entity) {
/* 825 */     Field f = ObfuscationReflectionHelper.findField(LivingEntity.class, "isJumping");
/*     */     
/*     */     try {
/* 828 */       return f.getBoolean(entity);
/*     */     }
/* 830 */     catch (IllegalAccessException e) {
/*     */       
/* 832 */       e.printStackTrace();
/*     */       
/* 834 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setPose(LivingEntity entity, Pose pose) {
/* 839 */     Method method = ObfuscationReflectionHelper.findMethod(Entity.class, "setPose", new Class[] { Pose.class });
/*     */     
/*     */     try {
/* 842 */       method.setAccessible(true);
/* 843 */       method.invoke(entity, new Object[] { pose });
/*     */     }
/* 845 */     catch (Exception e) {
/*     */       
/* 847 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getDevilFruitCategory() {
/* 853 */     return APIConfig.AbilityCategory.valueOf("DEVIL_FRUIT");
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getRacialCategory() {
/* 858 */     return APIConfig.AbilityCategory.valueOf("RACIAL");
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getStyleCategory() {
/* 863 */     return APIConfig.AbilityCategory.valueOf("STYLE");
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getHakiCategory() {
/* 868 */     return APIConfig.AbilityCategory.valueOf("HAKI");
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getFactionCategory() {
/* 873 */     return APIConfig.AbilityCategory.valueOf("FACTION");
/*     */   }
/*     */ 
/*     */   
/*     */   public static APIConfig.AbilityCategory getEquipmentCategory() {
/* 878 */     return APIConfig.AbilityCategory.valueOf("EQUIPMENT");
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\abilities\AbilityHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */