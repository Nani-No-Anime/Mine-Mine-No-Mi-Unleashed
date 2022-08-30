/*     */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class ElectricalMissileAbility extends Ability implements IMultiTargetAbility {
/*  26 */   public static final ElectricalMissileAbility INSTANCE = new ElectricalMissileAbility();
/*     */   
/*     */   private static final int COOLDOWN = 9;
/*     */   private boolean used = false;
/*     */   
/*     */   public ElectricalMissileAbility() {
/*  32 */     super("Electrical Missile", AbilityHelper.getRacialCategory());
/*  33 */     setMaxCooldown(9.0D);
/*  34 */     setDescription("The user dashes forward and rapidly electrifies the enemy");
/*     */     
/*  36 */     this.onUseEvent = this::onUseEvent;
/*  37 */     this.duringCooldownEvent = this::duringCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  42 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  43 */       return false;
/*     */     }
/*  45 */     this.used = false;
/*  46 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/*  47 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/*     */     
/*  49 */     if (!eleclawEnabled) {
/*     */       
/*  51 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_ELECLAW, new Object[0]));
/*  52 */       return false;
/*     */     } 
/*     */     
/*  55 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/*  56 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*     */     
/*  58 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)player);
/*  59 */     if (sulongEnabled)
/*  60 */       if (stats.isBunnyMink()) {
/*  61 */         setDisplayName("Comet Rabbit");
/*  62 */       } else if (stats.isLionMink()) {
/*  63 */         setDisplayName("Aka Byobu");
/*  64 */       } else if (stats.isDogMink()) {
/*  65 */         setDisplayName("Inu Odoshi");
/*     */       } else {
/*  67 */         setDisplayName("Electrical Missile");
/*     */       }  
/*  69 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, sulongEnabled ? 5.0D : 4.0D, sulongEnabled ? 5.0D : 4.0D);
/*  70 */     player.setMotion(speed.x, 0.2D, speed.z);
/*  71 */     player.velocityChanged = true;
/*  72 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*     */     
/*  74 */     clearTargets();
/*  75 */     setMaxCooldown(sulongEnabled ? 4.5D : 9.0D);
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/*  81 */     EleclawAbility eleclawAbility = (EleclawAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)EleclawAbility.INSTANCE);
/*  82 */     boolean eleclawEnabled = (eleclawAbility != null && eleclawAbility.isContinuous());
/*     */     
/*  84 */     SulongAbility sulongAbility = (SulongAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)SulongAbility.INSTANCE);
/*  85 */     boolean sulongEnabled = (sulongAbility != null && sulongAbility.isContinuous());
/*     */     
/*  87 */     if (canDealDamage()) {
/*     */       
/*  89 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  90 */       list.remove(player);
/*  91 */       this.used = true;
/*     */       
/*  93 */       for (LivingEntity entity : list) {
/*     */         
/*  95 */         if (isTarget(entity) && player.canEntityBeSeen((Entity)entity) && eleclawEnabled)
/*     */         {
/*  97 */           float damage = 20.0F * (sulongEnabled ? 2.5F : 1.0F);
/*  98 */           entity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this), damage);
/*  99 */           entity.addPotionEffect(new EffectInstance(ModEffects.PARALYSIS, 40, 0, false, false, true));
/*     */         }
/*     */       
/*     */       } 
/* 103 */     } else if (this.used && eleclawEnabled) {
/*     */       
/* 105 */       eleclawAbility.reduceUsage(player, 1);
/* 106 */       this.used = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDealDamage() {
/* 112 */     return (this.cooldown > getMaxCooldown() * 0.9D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\ElectricalMissileAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */