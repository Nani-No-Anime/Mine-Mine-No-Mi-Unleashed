/*     */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaGenericEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.HanaHandsEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.animations.CrossedArmsAnimation;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class SeisFleurSlapAbility
/*     */   extends Ability implements IAnimatedAbility {
/*  28 */   public static final SeisFleurSlapAbility INSTANCE = new SeisFleurSlapAbility();
/*     */ 
/*     */   
/*     */   public SeisFleurSlapAbility() {
/*  32 */     super("Seis Fleur: Slap", AbilityHelper.getDevilFruitCategory());
/*  33 */     setDescription("Slaps the enemy pushing them few blocks back and dealing some damage.");
/*  34 */     setMaxCooldown(5.0D);
/*     */     
/*  36 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onUseEvent(PlayerEntity player) {
/*  41 */     int distance = 10;
/*     */     
/*  43 */     MilFleurAbility ability = (MilFleurAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)MilFleurAbility.INSTANCE);
/*  44 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*     */     
/*  46 */     if (hasAbility) {
/*  47 */       distance = 20;
/*     */     }
/*  49 */     int slapPower = hasAbility ? 5 : 3;
/*     */     
/*  51 */     if (hasAbility) {
/*     */       
/*  53 */       distance = 30;
/*  54 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, distance, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  55 */       targets.remove(player);
/*  56 */       for (LivingEntity target : targets)
/*     */       {
/*  58 */         slap(player, (Entity)target, slapPower);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  63 */       HanaGenericEntity proj = new HanaGenericEntity(player.world, (LivingEntity)player);
/*  64 */       proj.onEntityImpactEvent = (target -> slap(player, (Entity)target, slapPower));
/*     */ 
/*     */       
/*  67 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 6.0F, 0.0F);
/*  68 */       player.world.addEntity((Entity)proj);
/*     */     } 
/*     */     
/*  71 */     MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
/*     */     
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void slap(PlayerEntity player, @Nullable Entity entity, float slapPower) {
/*  78 */     if (entity != null && entity instanceof LivingEntity) {
/*     */       
/*  80 */       LivingEntity target = (LivingEntity)entity;
/*     */       
/*  82 */       HanaHandsEntity slap = new HanaHandsEntity(player.world, this);
/*  83 */       slap.setWarmup(0);
/*  84 */       slap.setCaster((LivingEntity)player);
/*  85 */       slap.setTarget(target);
/*  86 */       slap.setDamage(6.0F);
/*  87 */       slap.setSlap();
/*  88 */       player.world.addEntity((Entity)slap);
/*     */       
/*  90 */       Vec3d dirVec = player.getPositionVec().subtract(target.getPositionVec()).normalize();
/*  91 */       target.setMotion(-dirVec.x * 3.0D, player.getMotion().getY() + 0.1D, -dirVec.z * 3.0D);
/*  92 */       target.velocityChanged = true;
/*     */       
/*  94 */       EffectInstance inst = new EffectInstance(ModEffects.HANA_HANDS, 5, 0, false, false);
/*  95 */       target.addPotionEffect(inst);
/*  96 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), inst));
/*     */       
/*  98 */       MilFleurAbility.PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableMilFleurMode() {
/* 104 */     setCustomTexture("mil_fleur_slap");
/* 105 */     setDisplayName("Mil Fleur: Slap");
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableMilFleurMode() {
/* 110 */     setCustomTexture("");
/* 111 */     setDisplayName("Seis Fleur: Slap");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 117 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 123 */     return (isOnCooldown() && getCooldown() > getMaxCooldown() - 15.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\SeisFleurSlapAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */