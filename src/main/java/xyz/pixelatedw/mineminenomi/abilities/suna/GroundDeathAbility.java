/*     */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
import java.util.List;

/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.GroundDeathParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.BlockPlacingHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class GroundDeathAbility extends ChargeableAbility {
/*  35 */   public static final Ability INSTANCE = (Ability)new GroundDeathAbility();
/*     */   
/*  37 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GroundDeathParticleEffect();
/*  38 */   private final BlockPlacingHelper blockPlacingHelper = new BlockPlacingHelper();
/*  39 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE });
/*     */   
/*  41 */   private List<LivingEntity> targets = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public GroundDeathAbility() {
/*  45 */     super("Ground Death", AbilityHelper.getDevilFruitCategory());
/*  46 */     setMaxCooldown(10.0D);
/*  47 */     setMaxChargeTime(3.0D);
/*  48 */     setCancelable();
/*     */     
/*  50 */     setDescription("Dries out the surrounding ground turning everythingg into sand");
/*  51 */     this.onStartChargingEvent = this::onStartCharging;
/*  52 */     this.duringChargingEvent = this::duringChargingEvent;
/*  53 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  58 */     this.targets.clear();
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int i) {
/*  64 */     if (player.isWet()) {
/*  65 */       endCharging(player);
/*     */     }
/*  67 */     player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 2, 1, false, false));
/*     */     
/*  69 */     HashSet<BlockPos> blockList = this.blockPlacingHelper.getBlockList();
/*     */     
/*  71 */     int finished = blockList.size() / 100;
/*  72 */     for (Iterator<BlockPos> iterator = blockList.iterator(); iterator.hasNext(); ) {
/*     */       
/*  74 */       BlockPos blockPos = iterator.next();
/*  75 */       if (finished-- < 0) {
/*     */         break;
/*     */       }
/*  78 */       BlockPos pos = new BlockPos((Vec3i)blockPos);
/*     */       
/*  80 */       boolean isWater = (player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getMaterial() == Material.WATER);
/*  81 */       AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), isWater ? Blocks.AIR : Blocks.SAND, 3, GRIEF_RULE);
/*     */       
/*  83 */       for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(pos, player.world, 1.5D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player),  LivingEntity.class )) {
/*     */         
/*  85 */         if (!this.targets.contains(target) && target.getUniqueID() != player.getUniqueID()) {
/*     */           
/*  87 */           this.targets.add(target);
/*  88 */           AbilityHelper.createFilledCube(target.world, target.getPosX(), target.getPosY(), target.getPosZ(), 1, (int)Math.round(target.getPosYEye() - target.getPosY()), 1, Blocks.SAND, GRIEF_RULE);
/*  89 */           target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 3, false, false));
/*  90 */           PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/*  94 */       iterator.remove();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartCharging(PlayerEntity player) {
/* 100 */     DesertGirasoleAbility ability = (DesertGirasoleAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility(DesertGirasoleAbility.INSTANCE);
/*     */     
/* 102 */     if (ability != null && ability.isCharging()) return false;
/*     */     
/* 104 */     if (!player.onGround) {
/*     */       
/* 106 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_GROUND, new Object[] { getName() }));
/* 107 */       return false;
/*     */     } 
/*     */     
/* 110 */     this.blockPlacingHelper.clearList();
/*     */     
/* 112 */     int range = SunaHelper.isFruitBoosted(player) ? 42 : 32;
/*     */     
/* 114 */     for (int i = -range; i < range; i++) {
/* 115 */       for (int j = -8; j < 6; j++) {
/* 116 */         for (int k = -range; k < range; k++) {
/*     */           
/* 118 */           double posX = player.getPosX() + i + ((i < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || i > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/* 119 */           double posY = player.getPosY() + j;
/*     */           
/* 121 */           double posZ = player.getPosZ() + k + ((k < -WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F)) || k > WyHelper.randomWithRange((int)(range * 0.5F), (int)(range * 0.75F))) ? WyHelper.randomWithRange(-5, 5) : 0.0D);
/*     */           
/* 123 */           if (!player.world.getBlockState(new BlockPos(posX, posY, posZ)).isAir((IBlockReader)player.world, new BlockPos(posX, posY, posZ)))
/*     */           {
/*     */             
/* 126 */             this.blockPlacingHelper.addBlockPos(new BlockPos(posX, posY, posZ), i * i + j * j + k * k); } 
/*     */         } 
/*     */       } 
/* 129 */     }  return !player.isWet();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\GroundDeathAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */