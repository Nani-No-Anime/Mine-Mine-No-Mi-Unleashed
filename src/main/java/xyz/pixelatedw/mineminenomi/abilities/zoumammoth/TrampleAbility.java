/*     */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.particles.BlockParticleData;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class TrampleAbility extends PassiveAbility implements IFormRequiredAbility {
/*  29 */   public static final TrampleAbility INSTANCE = new TrampleAbility();
/*     */   
/*  31 */   public float speed = 0.0F;
/*     */ 
/*     */   
/*     */   public TrampleAbility() {
/*  35 */     super("Trample", AbilityHelper.getDevilFruitCategory());
/*  36 */     setDescription("Running speed increases with acceleration trampling any nearby entity.");
/*  37 */     hideInGUI(false);
/*     */     
/*  39 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringPassiveEvent(PlayerEntity player) {
/*  44 */     if (player.abilities.isFlying) {
/*     */       return;
/*     */     }
/*  47 */     if (!player.isSprinting()) {
/*     */       
/*  49 */       this.speed = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/*  53 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.5D);
/*  54 */       targets.remove(player);
/*     */ 
/*     */       
/*  57 */       AncientStompAbility ability = (AncientStompAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)AncientStompAbility.INSTANCE);
/*  58 */       float maxSpeed = (ability != null && ability.isContinuous()) ? 0.1F : 0.45F;
/*  59 */       float acceleration = (ability != null && ability.isContinuous()) ? 0.001F : 0.004F;
/*     */       
/*  61 */       acceleration *= (this.speed > 0.0F) ? (1.0F - this.speed / maxSpeed) : 1.0F;
/*  62 */       if (player.moveForward <= 0.0F || player.collidedHorizontally)
/*  63 */         acceleration = -maxSpeed / 10.0F; 
/*  64 */       this.speed = MathHelper.clamp(this.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 20.0F) : 0.0F, maxSpeed);
/*     */       
/*  66 */       int d2 = (player.moveForward > 0.0F) ? 1 : 0;
/*     */       
/*  68 */       Vec3d vec = player.getLookVec();
/*  69 */       double x = vec.x * this.speed * d2;
/*  70 */       double z = vec.z * this.speed * d2;
/*  71 */       player.setMotion(x, (player.getMotion()).y, z);
/*     */       
/*  73 */       if (!player.world.isRemote) {
/*     */         
/*  75 */         List<BlockPos> blocks = WyHelper.getNearbyBlocks(player.getPosition(), (IWorld)player.world, 5, pos -> FoliageBlockProtectionRule.INSTANCE.isPresent(player.world.getBlockState(pos)), (List)ImmutableList.of(Blocks.AIR));
/*     */         
/*  77 */         for (BlockPos pos : blocks) {
/*     */           
/*  79 */           BlockState blockState = player.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
/*  80 */           for (int i = 0; i < 150; i++) {
/*     */             
/*  82 */             double offsetX = WyHelper.randomDouble();
/*  83 */             double offsetY = WyHelper.randomDouble();
/*  84 */             double offsetZ = WyHelper.randomDouble();
/*     */             
/*  86 */             ((ServerWorld)player.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
/*     */                 
/*  88 */                 .getX() + offsetX, pos
/*  89 */                 .getY() + offsetY, pos
/*  90 */                 .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */           
/*  93 */           AbilityHelper.placeBlockIfAllowed(player.world, pos.getX(), pos.getY(), pos.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
/*     */         } 
/*     */         
/*  96 */         for (LivingEntity target : targets) {
/*     */           
/*  98 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.0D, 2.0D);
/*  99 */           target.attackEntityFrom(DamageSource.causePlayerDamage(player), 6.0F);
/* 100 */           target.setMotion(speed.x, 0.2D, speed.z);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 109 */     return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\TrampleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */