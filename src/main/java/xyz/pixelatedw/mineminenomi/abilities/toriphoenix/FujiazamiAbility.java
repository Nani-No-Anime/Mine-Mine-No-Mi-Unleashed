/*     */ package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.FujizamiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ public class FujiazamiAbility
/*     */   extends ContinuousAbility
/*     */   implements IFormRequiredAbility {
/*  29 */   public static final FujiazamiAbility INSTANCE = new FujiazamiAbility();
/*     */   
/*  31 */   private static final FujizamiParticleEffect PARTICLES = new FujizamiParticleEffect();
/*     */ 
/*     */   
/*     */   public FujiazamiAbility() {
/*  35 */     super("Fujiazami", AbilityHelper.getDevilFruitCategory());
/*  36 */     setThreshold(4.0D);
/*  37 */     setMaxCooldown(16.0D);
/*  38 */     setDescription("While midair, the user forms a protective swirl of fire in front of them capable of blocking most attacks");
/*     */     
/*  40 */     this.onStartContinuityEvent = this::onStartChargingEvent;
/*  41 */     this.duringContinuityEvent = this::duringChargingEvent;
/*  42 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndContinuityEvent(PlayerEntity player) {
/*  47 */     int cooldown = 4 + Math.round(this.continueTime / 10.0F);
/*  48 */     setMaxCooldown(cooldown);
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  54 */     if (player.onGround) {
/*     */       
/*  56 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, new Object[] { getName() }));
/*  57 */       return false;
/*     */     } 
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int time) {
/*  65 */     player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 4, 8, false, false));
/*  66 */     player.addPotionEffect(new EffectInstance(ModEffects.REDUCED_FALL, 4, 1, false, false));
/*  67 */     boolean flyForm = PhoenixFlyZoanInfo.INSTANCE.isActive((LivingEntity)player);
/*     */     
/*  69 */     int range = flyForm ? 3 : 2;
/*  70 */     double boxSize = flyForm ? 1.25D : 0.8D;
/*  71 */     for (int i = 0; i < range * 2; i++) {
/*     */       
/*  73 */       double distance = i / 2.0D;
/*  74 */       Vec3d lookVec = player.getLookVec();
/*  75 */       Vec3d pos = new Vec3d(player.getPosX() + lookVec.x * distance, player.getPosY() + player.getEyeHeight() + lookVec.y * distance, player.getPosZ() + lookVec.z * distance);
/*  76 */       List<Entity> list = player.world.getEntitiesInAABBexcluding((Entity)player, new AxisAlignedBB(pos.x - boxSize, pos.y - boxSize, pos.z - boxSize, pos.x + boxSize, pos.y + boxSize * 2.0D, pos.z + boxSize), entity -> (entity != player));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  81 */       for (Entity e : list) {
/*     */         
/*  83 */         if (e instanceof LivingEntity) {
/*     */           
/*  85 */           Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/*  86 */           e.setMotion(speed.x, 0.5D, speed.z);
/*  87 */           e.velocityChanged = true; continue;
/*     */         } 
/*  89 */         if (e instanceof net.minecraft.entity.projectile.AbstractArrowEntity || e instanceof net.minecraft.entity.projectile.ThrowableEntity) {
/*     */           
/*  91 */           if (e instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)e).getDamage() > (flyForm ? 50 : 30))
/*     */             return; 
/*  93 */           e.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, range);
/*  99 */     if (time % 2 == 0)
/*     */     {
/* 101 */       PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.getPosY() + (player.getEyeHeight() / 2.0F), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 108 */     return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE, (ZoanInfo)PhoenixAssaultZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\toriphoenix\FujiazamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */