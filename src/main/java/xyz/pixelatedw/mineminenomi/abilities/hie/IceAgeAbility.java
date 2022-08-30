/*     */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;

/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.SnowBlock;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceAgeParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class IceAgeAbility extends ChargeableAbility {
/*  32 */   public static final Ability INSTANCE = (Ability)new IceAgeAbility();
/*     */   
/*  34 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new IceAgeParticleEffect(); private static final BlockProtectionRule PROTECTION_RULE;
/*     */   static {
/*  36 */     PROTECTION_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID })).addReplaceRules((world, pos, state) -> {
/*     */           if (state.getBlock().equals(Blocks.SNOW) && ((Integer)state.get((IProperty)SnowBlock.LAYERS)).intValue() > 5) {
/*     */             world.setBlockState(pos, Blocks.BLUE_ICE.getDefaultState());
/*     */             return true;
/*     */           } 
/*     */           return false;
/*     */         });
/*     */   }
/*     */   
/*  45 */   private List<LivingEntity> targets = new ArrayList<>();
/*  46 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*     */ 
/*     */   
/*     */   public IceAgeAbility() {
/*  50 */     super("Ice Age", AbilityHelper.getDevilFruitCategory());
/*  51 */     setMaxCooldown(20.0D);
/*  52 */     setMaxChargeTime(5.0D);
/*  53 */     setCancelable();
/*  54 */     setDescription("Freezes a large area around the user and everyone inside of it");
/*     */     
/*  56 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  57 */     this.duringChargingEvent = this::duringChargingEvent;
/*  58 */     this.onEndChargingEvent = this::endChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {
/*  63 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*     */     
/*  65 */     HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/*  67 */     int finished = blockList.size() / 100;
/*  68 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/*     */       
/*  70 */       BlockPos blockPos = iterator.next();
/*  71 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*  74 */       BlockPos pos = new BlockPos((Vec3i)blockPos);
/*  75 */       AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.BLUE_ICE, 3, PROTECTION_RULE);
/*  76 */       for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), LivingEntity.class )) {
/*     */         
/*  78 */         if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
/*     */           
/*  80 */           this.targets.add(target);
/*  81 */           EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 400, 0);
/*  82 */           target.addPotionEffect(instance);
/*  83 */           ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*  84 */           PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/*  88 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  94 */     this.blockPlacingHelper.clearList();
/*  95 */     int range = 64;
/*     */     
/*  97 */     player.world.playMovingSound(null, (Entity)player, ModSounds.ICE_AGE_SFX, SoundCategory.PLAYERS, 5.0F, 1.0F);
/*  98 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     
/* 100 */     for (int i = -range; i < range; i++) {
/* 101 */       for (int j = -9; j < 9; j++) {
/* 102 */         for (int k = -range; k < range; k++) {
/*     */           
/* 104 */           double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/* 105 */           double posY = player.getPosY() + j;
/*     */           
/* 107 */           double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/* 109 */           if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
/*     */           {
/*     */             
/* 112 */             this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
/*     */         } 
/*     */       } 
/* 115 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean endChargingEvent(PlayerEntity player) {
/* 120 */     if (getChargeTime() > 50) {
/* 121 */       return false;
/*     */     }
/* 123 */     this.targets.clear();
/* 124 */     float time = getChargeTime() / getMaxChargeTime();
/* 125 */     float multiplier = 1.0F - time;
/*     */     
/* 127 */     setCooldown((int)(getMaxCooldown() / 20.0D * multiplier));
/* 128 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceAgeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */