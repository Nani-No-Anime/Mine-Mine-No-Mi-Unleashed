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
/*     */ import net.minecraft.potion.Effects;
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
/*     */ public class SeisFleurTwistAbility
/*     */   extends Ability implements IAnimatedAbility {
/*  28 */   public static final SeisFleurTwistAbility INSTANCE = new SeisFleurTwistAbility();
/*     */ 
/*     */   
/*     */   public SeisFleurTwistAbility() {
/*  32 */     super("Seis Fleur: Twist", AbilityHelper.getDevilFruitCategory());
/*  33 */     setDescription("The six arms sprout from around a target's body and then twists it around.");
/*  34 */     setMaxCooldown(5.0D);
/*     */     
/*  36 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onUseEvent(PlayerEntity player) {
/*  41 */     int distance = 20;
/*     */     
/*  43 */     MilFleurAbility ability = (MilFleurAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)MilFleurAbility.INSTANCE);
/*  44 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*     */     
/*  46 */     if (hasAbility) {
/*     */       
/*  48 */       distance = 30;
/*  49 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, distance, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  50 */       targets.remove(player);
/*  51 */       for (LivingEntity target : targets)
/*     */       {
/*  53 */         twist(player, (Entity)target, 8.0F);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  58 */       HanaGenericEntity proj = new HanaGenericEntity(player.world, (LivingEntity)player);
/*  59 */       proj.onEntityImpactEvent = (target -> twist(player, (Entity)target, 6.0F));
/*     */ 
/*     */       
/*  62 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 6.0F, 0.0F);
/*  63 */       player.world.addEntity((Entity)proj);
/*     */     } 
/*     */     
/*  66 */     MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
/*     */     
/*  68 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void twist(PlayerEntity player, @Nullable Entity entity, float damage) {
/*  73 */     if (entity != null && entity instanceof LivingEntity) {
/*     */       
/*  75 */       LivingEntity target = (LivingEntity)entity;
/*     */       
/*  77 */       target.rotationYaw += 180.0F;
/*  78 */       target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1));
/*  79 */       EffectInstance inst = new EffectInstance(ModEffects.HANA_HANDS, 20, 0, false, false);
/*  80 */       target.addPotionEffect(inst);
/*  81 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), inst));
/*     */       
/*  83 */       HanaHandsEntity clutch = new HanaHandsEntity(player.world, this);
/*  84 */       clutch.setWarmup(5);
/*  85 */       clutch.setCaster((LivingEntity)player);
/*  86 */       clutch.setTarget(target);
/*  87 */       clutch.setDamage(damage);
/*  88 */       player.world.addEntity((Entity)clutch);
/*     */       
/*  90 */       MilFleurAbility.PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableMilFleurMode() {
/*  96 */     setCustomTexture("mil_fleur_twist");
/*  97 */     setDisplayName("Mil Fleur: Twist");
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableMilFleurMode() {
/* 102 */     setCustomTexture("");
/* 103 */     setDisplayName("Seis Fleur: Twist");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 109 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 115 */     return (isOnCooldown() && getCooldown() > getMaxCooldown() - 15.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\SeisFleurTwistAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */