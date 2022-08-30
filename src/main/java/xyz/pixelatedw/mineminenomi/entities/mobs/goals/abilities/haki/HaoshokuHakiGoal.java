/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.PrioritizedGoal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HaoshokuHakiGoal extends CooldownGoal {
/*  25 */   public static final ParticleEffect PARTICLES_1 = (ParticleEffect)new HaoshokuHakiParticleEffect(1);
/*  26 */   public static final ParticleEffect PARTICLES_2 = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
/*  27 */   public static final ParticleEffect PARTICLES_3 = (ParticleEffect)new HaoshokuHakiParticleEffect(3);
/*     */   
/*     */   private OPEntity entity;
/*     */   private float hakiXP;
/*     */   
/*     */   public HaoshokuHakiGoal(OPEntity entity, float hxp) {
/*  33 */     super(entity, 120, entity.getRNG().nextInt(10));
/*  34 */     this.entity = entity;
/*  35 */     this.entity.addThreat(20);
/*  36 */     this.hakiXP = hxp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCooldown() {
/*  42 */     super.endCooldown();
/*  43 */     this.entity.setCurrentGoal(null);
/*  44 */     this.entity.setPreviousGoal((Goal)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  49 */     if (!super.shouldExecute()) {
/*  50 */       return false;
/*     */     }
/*  52 */     if (this.entity.getAttackTarget() == null) {
/*  53 */       return false;
/*     */     }
/*  55 */     if (!this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget())) {
/*  56 */       return false;
/*     */     }
/*  58 */     if (this.entity.getDistance((Entity)this.entity.getAttackTarget()) < 5.0F) {
/*  59 */       return false;
/*     */     }
/*  61 */     execute((LivingEntity)this.entity);
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void execute(LivingEntity player) {
/*  67 */     IHakiData hakiProps = HakiDataCapability.get(player);
/*  68 */     hakiProps.alterHakiOveruse(400);
/*     */     
/*  70 */     player.world.playSound(null, player.getPosition(), ModSounds.HAKI_RELEASE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  72 */     float haoLevel = this.hakiXP / 100.0F;
/*     */     
/*  74 */     int cooldown = 0;
/*  75 */     double radius = 0.0D;
/*  76 */     int unconsciousTimer = 0;
/*     */     
/*  78 */     if (haoLevel < 1.0F) {
/*     */       
/*  80 */       radius = 10.0D;
/*  81 */       unconsciousTimer = 0;
/*  82 */       cooldown = 120;
/*  83 */       PARTICLES_1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  85 */     else if (haoLevel >= 1.0F && haoLevel < 2.5D) {
/*     */       
/*  87 */       radius = 25.0D;
/*  88 */       unconsciousTimer = 100;
/*  89 */       cooldown = 60;
/*  90 */       PARTICLES_2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  92 */     else if (haoLevel >= 2.5D) {
/*     */       
/*  94 */       radius = 40.0D;
/*  95 */       unconsciousTimer = 200;
/*  96 */       cooldown = 60;
/*  97 */       PARTICLES_3.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 100 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate(player), new Class[] { LivingEntity.class });
/*     */     
/* 102 */     targets.remove(player);
/*     */     
/* 104 */     this.maxCooldown = cooldown;
/*     */     
/* 106 */     for (LivingEntity target : targets) {
/*     */       
/* 108 */       if (unconsciousTimer > 0) {
/*     */         
/* 110 */         float targetHaoLevel = 0.0F;
/* 111 */         if (target instanceof net.minecraft.entity.player.PlayerEntity) {
/*     */           
/* 113 */           targetHaoLevel = HakiHelper.getTotalHakiExp(target) / 100.0F;
/*     */         }
/* 115 */         else if (target instanceof OPEntity) {
/*     */           
/* 117 */           float busoHaki = ((OPEntity)target).goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal() instanceof BusoshokuHakiGoal) ? 1.0F : 0.0F;
/* 118 */           float dorikiConversion = (((OPEntity)target).getDoriki() / 100);
/*     */           
/* 120 */           targetHaoLevel = busoHaki + dorikiConversion;
/*     */         } 
/*     */         
/* 123 */         if (targetHaoLevel + 0.3D >= haoLevel) {
/*     */           continue;
/*     */         }
/* 126 */         EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, unconsciousTimer, 1, false, false);
/* 127 */         target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, unconsciousTimer - 20, 1, false, false));
/* 128 */         target.addPotionEffect(instance);
/* 129 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*     */         
/*     */         continue;
/*     */       } 
/* 133 */       target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\abilities\haki\HaoshokuHakiGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */