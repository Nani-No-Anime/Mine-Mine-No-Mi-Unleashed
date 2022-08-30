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
/*     */ public class DosFleurClutchAbility
/*     */   extends Ability implements IAnimatedAbility {
/*  27 */   public static final DosFleurClutchAbility INSTANCE = new DosFleurClutchAbility();
/*     */ 
/*     */   
/*     */   public DosFleurClutchAbility() {
/*  31 */     super("Dos Fleur: Clutch", AbilityHelper.getDevilFruitCategory());
/*  32 */     setDescription("Sprouts two hands to cover the opponent, and then bends them backward with bone-breaking results.");
/*  33 */     setMaxCooldown(6.0D);
/*     */     
/*  35 */     this.onUseEvent = this::onUseEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onUseEvent(PlayerEntity player) {
/*  40 */     int distance = 20;
/*     */     
/*  42 */     MilFleurAbility ability = (MilFleurAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)MilFleurAbility.INSTANCE);
/*  43 */     boolean hasAbility = (ability != null && ability.isContinuous());
/*     */     
/*  45 */     if (hasAbility) {
/*     */       
/*  47 */       distance = 30;
/*  48 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, distance, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  49 */       targets.remove(player);
/*  50 */       for (LivingEntity target : targets)
/*     */       {
/*  52 */         clutch(player, (Entity)target, 10.0F);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  57 */       HanaGenericEntity proj = new HanaGenericEntity(player.world, (LivingEntity)player);
/*  58 */       proj.onEntityImpactEvent = (target -> clutch(player, (Entity)target, 8.0F));
/*     */ 
/*     */       
/*  61 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 6.0F, 0.0F);
/*  62 */       player.world.addEntity((Entity)proj);
/*     */     } 
/*     */     
/*  65 */     MilFleurAbility.spawnBlossomEffect((LivingEntity)player);
/*     */     
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clutch(PlayerEntity player, @Nullable Entity entity, float damage) {
/*  72 */     if (entity != null && entity instanceof LivingEntity) {
/*     */       
/*  74 */       LivingEntity target = (LivingEntity)entity;
/*     */       
/*  76 */       target.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 1));
/*  77 */       EffectInstance inst = new EffectInstance(ModEffects.HANA_HANDS, 20, 0, false, false);
/*  78 */       target.addPotionEffect(inst);
/*  79 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), inst));
/*     */       
/*  81 */       HanaHandsEntity clutch = new HanaHandsEntity(player.world, this);
/*  82 */       clutch.setWarmup(5);
/*  83 */       clutch.setCaster((LivingEntity)player);
/*  84 */       clutch.setTarget(target);
/*  85 */       clutch.setDamage(damage);
/*  86 */       player.world.addEntity((Entity)clutch);
/*     */       
/*  88 */       MilFleurAbility.PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void enableMilFleurMode() {
/*  94 */     setCustomTexture("mil_fleur_clutch");
/*  95 */     setDisplayName("Mil Fleur: Clutch");
/*     */   }
/*     */ 
/*     */   
/*     */   public void disableMilFleurMode() {
/* 100 */     setCustomTexture("");
/* 101 */     setDisplayName("Dos Fleur: Clutch");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimation getAnimation() {
/* 107 */     return (IAnimation)CrossedArmsAnimation.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnimationActive() {
/* 113 */     return (isOnCooldown() && getCooldown() > getMaxCooldown() - 15.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\DosFleurClutchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */