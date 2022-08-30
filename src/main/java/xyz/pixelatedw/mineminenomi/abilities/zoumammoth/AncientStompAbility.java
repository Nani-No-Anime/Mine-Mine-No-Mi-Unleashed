/*     */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*     */ 
/*     */ public class AncientStompAbility extends RepeaterAbility implements IFormRequiredAbility {
/*  33 */   public static final AncientStompAbility INSTANCE = new AncientStompAbility();
/*     */   
/*  35 */   private static final GreatStompParticleEffect PARTICLES = new GreatStompParticleEffect();
/*     */   
/*     */   private static final int RADIUS = 5;
/*     */   
/*     */   private Iterator<BlockPos> targetedBlocks;
/*     */   
/*     */   public AncientStompAbility() {
/*  42 */     super("Ancient Stomp", AbilityHelper.getDevilFruitCategory());
/*  43 */     setDescription("By stomping the ground in front of the user ground shockwaves are created dealing damage to all nearby enemies");
/*  44 */     setMaxCooldown(10.0D);
/*  45 */     setMaxRepeaterCount(12, 8);
/*     */     
/*  47 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  48 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  53 */     Predicate<BlockPos> predicate = pos -> (player.world.getBlockState(pos.up()).isAir() && pos.getY() > player.getPosY() - 3.0D);
/*  54 */     Vec3d look = player.getPositionVec().add(player.getLookVec().mul(7.0D, 1.0D, 7.0D));
/*  55 */     BlockPos ogPos = new BlockPos(look.getX(), player.getPosY(), look.getZ());
/*  56 */     List<BlockPos> poses = WyHelper.getNearbyBlocks(ogPos, (IWorld)player.world, 5, predicate, (List)ImmutableList.of(Blocks.AIR));
/*  57 */     this.targetedBlocks = WyHelper.shuffle(poses).stream().limit(10L).iterator();
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  63 */     if (this.targetedBlocks == null || !this.targetedBlocks.hasNext()) {
/*  64 */       return false;
/*     */     }
/*  66 */     if (!canUse(player)) {
/*     */       
/*  68 */       endContinuity(player);
/*  69 */       return false;
/*     */     } 
/*     */     
/*  72 */     BlockPos pos = this.targetedBlocks.next();
/*     */     
/*  74 */     List<LivingEntity> list = WyHelper.getEntitiesNear(pos, player.world, 7.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  75 */     list.remove(player);
/*  76 */     Iterator<LivingEntity> iter = list.iterator();
/*     */     
/*  78 */     while (iter.hasNext()) {
/*     */       
/*  80 */       LivingEntity target = iter.next();
/*  81 */       target.addPotionEffect(new EffectInstance(ModEffects.DIZZY, 20, 0));
/*  82 */       target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 10.0F);
/*  83 */       target.setMotion(target.getMotion().add(0.0D, 0.25D, 0.0D));
/*  84 */       target.velocityChanged = true;
/*     */     } 
/*     */     
/*  87 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 7, p -> FoliageBlockProtectionRule.INSTANCE.isPresent(player.world.getBlockState(p)), (List)ImmutableList.of(Blocks.AIR));
/*  88 */     for (BlockPos p : blocks) {
/*     */       
/*  90 */       BlockState blockState1 = player.world.getBlockState(new BlockPos(p.getX(), p.getY(), p.getZ()));
/*  91 */       for (int i = 0; i < 150; i++) {
/*     */         
/*  93 */         double offsetX = WyHelper.randomDouble();
/*  94 */         double offsetY = WyHelper.randomDouble();
/*  95 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  97 */         ((ServerWorld)player.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState1), p
/*     */             
/*  99 */             .getX() + offsetX, p
/* 100 */             .getY() + offsetY, p
/* 101 */             .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */       
/* 104 */       AbilityHelper.placeBlockIfAllowed(player.world, p.getX(), p.getY(), p.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
/*     */     } 
/*     */     
/* 107 */     BlockState blockState = player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
/* 108 */     if (blockState.getMaterial().isSolid())
/*     */     {
/* 110 */       for (int i = 0; i < 150; i++) {
/*     */         
/* 112 */         double x = WyHelper.randomDouble();
/* 113 */         double z = WyHelper.randomDouble();
/*     */         
/* 115 */         ((ServerWorld)player.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
/* 116 */             .getX() + WyHelper.randomWithRange(-3, 3) + x, (pos
/* 117 */             .getY() + 1), pos
/* 118 */             .getZ() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 129 */     return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */