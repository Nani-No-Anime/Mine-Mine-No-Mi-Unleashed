/*     */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.IBlockReader;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackHoleParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class BlackHoleAbility extends ChargeableAbility {
/*  27 */   public static final BlackHoleAbility INSTANCE = new BlackHoleAbility();
/*     */   
/*  29 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BlackHoleParticleEffect();
/*     */   
/*  31 */   private List<LivingEntity> targets = new ArrayList<>();
/*  32 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */ 
/*     */   
/*     */   public BlackHoleAbility() {
/*  36 */     super("Black Hole", AbilityHelper.getDevilFruitCategory());
/*  37 */     setMaxCooldown(20.0D);
/*  38 */     setMaxChargeTime(5.0D);
/*  39 */     setCancelable();
/*     */     
/*  41 */     setDescription("The user spreads darkness over the target area, which engulfs and suffocates anyone and anything inside of it");
/*  42 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  43 */     this.duringChargingEvent = this::duringChargingEvent;
/*  44 */     this.onEndChargingEvent = this::endChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {
/*  49 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*     */     
/*  51 */     HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/*  53 */     int finished = blockList.size() / 100;
/*  54 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/*     */       
/*  56 */       BlockPos blockPos = iterator.next();
/*  57 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*  60 */       BlockPos pos = new BlockPos((Vec3i)blockPos);
/*  61 */       AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), ModBlocks.DARKNESS, 3, DefaultProtectionRules.CORE_FOLIAGE_ORE);
/*  62 */       for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class )) {
/*     */         
/*  64 */         if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
/*     */           
/*  66 */           this.targets.add(target);
/*  67 */           EffectInstance instance = new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 400, 0);
/*  68 */           target.addPotionEffect(instance);
/*  69 */           ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*  70 */           PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/*  74 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  80 */     this.blockPlacingHelper.clearList();
/*  81 */     int range = 32;
/*  82 */     float spread = 0.65F;
/*     */     
/*  84 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/*  86 */     for (int i = -range; i < range; i++) {
/*  87 */       for (int j = -6; j < 3; j++) {
/*  88 */         for (int k = -range; k < range; k++) {
/*     */           
/*  90 */           double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F))) || i > WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
/*  91 */           double posY = player.getPosY() + j;
/*     */           
/*  93 */           double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F))) || k > WyHelper.randomWithRange((int)(range * spread), (int)(range * (spread + 0.35F)))) ? WyHelper.randomWithRange(-2, 2) : 0.0D);
/*     */           
/*  95 */           if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
/*     */           {
/*     */             
/*  98 */             this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
/*     */         } 
/*     */       } 
/* 101 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean endChargingEvent(PlayerEntity player) {
/* 106 */     this.targets.clear();
/* 107 */     float time = getChargeTime() / getMaxChargeTime();
/* 108 */     float multiplier = 1.0F - time;
/*     */     
/* 110 */     setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
/* 111 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackHoleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */