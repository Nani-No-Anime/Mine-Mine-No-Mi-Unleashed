/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasole2ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasoleParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class DesertGirasoleAbility extends ChargeableAbility {
/*  27 */   public static final Ability INSTANCE = (Ability)new DesertGirasoleAbility();
/*  28 */   BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */   
/*  30 */   private static final ParticleEffect PARTICLES1 = (ParticleEffect)new DesertGirasoleParticleEffect();
/*  31 */   private static final ParticleEffect PARTICLES2 = (ParticleEffect)new DesertGirasole2ParticleEffect();
/*     */ 
/*     */   
/*     */   public DesertGirasoleAbility() {
/*  35 */     super("Desert Girasole", AbilityHelper.getDevilFruitCategory());
/*  36 */     setMaxCooldown(30.0D);
/*  37 */     setMaxChargeTime(5.0D);
/*  38 */     setDescription("A giant pit of quicksand will be formed with the sand being taken away by underground rivers \n\nThis can only be used on sand!");
/*     */     
/*  40 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  41 */     this.duringChargingEvent = this::duringChargingEvent;
/*  42 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  47 */     GroundDeathAbility ability = (GroundDeathAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility(GroundDeathAbility.INSTANCE);
/*     */     
/*  49 */     int range = SunaHelper.isFruitBoosted(player) ? 24 : 12;
/*     */     
/*  51 */     for (int i = -range; i < range; i++) {
/*  52 */       for (int j = -8; j < 8; j++) {
/*  53 */         for (int k = -range; k < range; k++) {
/*     */           
/*  55 */           double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange(8, 12) || i > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*  56 */           double posY = player.getPosY() + j;
/*  57 */           double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange(8, 12) || k > WyHelper.randomWithRange(8, 12)) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/*  59 */           if (player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock().equals(Blocks.SAND))
/*  60 */             this.blockPlacingHelper.addBlockPos(posX, posY, posZ, i * i + j * j + k * k); 
/*     */         } 
/*     */       } 
/*  63 */     }  if (this.blockPlacingHelper.getBlockList().size() > 0) {
/*     */       
/*  65 */       PARTICLES1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } else {
/*     */       
/*  68 */       return false;
/*     */     } 
/*  70 */     if (!player.onGround) {
/*     */       
/*  72 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/*  73 */       return false;
/*     */     } 
/*     */     
/*  76 */     return ((ability == null || !ability.isCharging()) && !player.isWet());
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/*  81 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1, false, false));
/*     */     
/*  83 */     HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/*  85 */     int finished = blockList.size() / 100;
/*  86 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/*     */       
/*  88 */       BlockPos blockPos = iterator.next();
/*  89 */       if (finished-- < 0)
/*     */         break; 
/*  91 */       BlockPos pos = new BlockPos((Vec3i)blockPos);
/*  92 */       AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.SUNA_SAND, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
/*  93 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  99 */     PARTICLES2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\DesertGirasoleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */