/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.goal.PrioritizedGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class HaoshokuHakiAbility extends ChargeableAbility {
/*  29 */   public static final HaoshokuHakiAbility INSTANCE = new HaoshokuHakiAbility();
/*  30 */   public static final ParticleEffect PARTICLES_1 = (ParticleEffect)new HaoshokuHakiParticleEffect(1);
/*  31 */   public static final ParticleEffect PARTICLES_2 = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
/*  32 */   public static final ParticleEffect PARTICLES_3 = (ParticleEffect)new HaoshokuHakiParticleEffect(3);
/*     */ 
/*     */   
/*     */   public HaoshokuHakiAbility() {
/*  36 */     super("Haoshoku Haki", AbilityHelper.getHakiCategory());
/*  37 */     setDescription("A burst of the unique Conqueror's haki, that knocks out enemies that are weaker than the user");
/*  38 */     setMaxChargeTime(3.0D);
/*     */     
/*  40 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  45 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*  46 */     hakiProps.alterHakiOveruse(1200);
/*     */     
/*  48 */     player.world.playSound(null, player.getPosition(), ModSounds.HAKI_RELEASE_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */     
/*  50 */     float haoLevel = HakiHelper.getTotalHakiExp((LivingEntity)player) / 100.0F;
/*     */     
/*  52 */     int cooldown = 0;
/*  53 */     double radius = 0.0D;
/*  54 */     int unconsciousTimer = 0;
/*     */     
/*  56 */     if (haoLevel < 1.0F) {
/*     */       
/*  58 */       radius = 10.0D;
/*  59 */       unconsciousTimer = 0;
/*  60 */       cooldown = 120;
/*  61 */       PARTICLES_1.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  63 */     else if (haoLevel >= 1.0F && haoLevel < 2.5D) {
/*     */       
/*  65 */       radius = 25.0D;
/*  66 */       unconsciousTimer = 100;
/*  67 */       cooldown = 60;
/*  68 */       PARTICLES_2.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  70 */     else if (haoLevel >= 2.5D) {
/*     */       
/*  72 */       radius = 40.0D;
/*  73 */       unconsciousTimer = 200;
/*  74 */       cooldown = 60;
/*  75 */       PARTICLES_3.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/*  78 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  79 */     targets.remove(player);
/*     */     
/*  81 */     for (LivingEntity target : targets) {
/*     */       
/*  83 */       if (unconsciousTimer > 0) {
/*     */         
/*  85 */         float targetHaoLevel = 0.0F;
/*  86 */         if (target instanceof PlayerEntity) {
/*     */           
/*  88 */           float hakiLevel = HakiHelper.getTotalHakiExp(target) / 100.0F;
/*  89 */           float dorikiConversion = EntityStatsCapability.get(target).getDoriki() / 10000.0F;
/*  90 */           targetHaoLevel = hakiLevel + dorikiConversion;
/*     */         }
/*  92 */         else if (target instanceof OPEntity) {
/*     */           
/*  94 */           float busoHaki = ((OPEntity)target).goalSelector.getRunningGoals().anyMatch(goal -> goal.getGoal() instanceof xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.haki.BusoshokuHakiGoal) ? 1.0F : 0.0F;
/*  95 */           float dorikiConversion = ((OPEntity)target).getDoriki() / 100.0F;
/*     */           
/*  97 */           targetHaoLevel = busoHaki + dorikiConversion;
/*     */         } 
/*     */ 
/*     */         
/* 101 */         if (targetHaoLevel + 0.5D >= haoLevel) {
/*     */           continue;
/*     */         }
/* 104 */         EffectInstance instance = new EffectInstance(ModEffects.UNCONSCIOUS, unconsciousTimer, 1, false, false);
/* 105 */         target.addPotionEffect(new EffectInstance(ModEffects.ABILITY_OFF, unconsciousTimer - 20, 0, false, false));
/* 106 */         target.addPotionEffect(instance);
/* 107 */         ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*     */         
/*     */         continue;
/*     */       } 
/* 111 */       target.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
/*     */     } 
/*     */ 
/*     */     
/* 115 */     setMaxCooldown(cooldown);
/* 116 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\HaoshokuHakiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */