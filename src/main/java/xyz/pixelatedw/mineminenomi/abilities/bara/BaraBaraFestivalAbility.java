/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
import java.util.UUID;

/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.EntityRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraFestivalEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.BaraFestivalZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ 
/*     */ public class BaraBaraFestivalAbility extends ZoanAbility {
/*  38 */   public static final BaraBaraFestivalAbility INSTANCE = new BaraBaraFestivalAbility();
/*     */   
/*  40 */   private static final AbilityAttributeModifier STEP_HEIGHT = (new AbilityAttributeModifier(UUID.fromString("0349c517-823a-4f20-8a95-b9a0e3787c47"), (Ability)INSTANCE, "Bara Bara Festival Modifier", 1.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*  42 */   private LivingEntity grabbedEntity = null;
/*  43 */   private BaraFestivalEntity baraFestival = null;
/*  44 */   private Mode activeMode = Mode.ATTACK;
/*     */ 
/*     */   
/*     */   public BaraBaraFestivalAbility() {
/*  48 */     super("Bara Bara Festival", AbilityHelper.getDevilFruitCategory());
/*  49 */     setDescription("Splits the user's body in several small parts that constantly hurt and slow down the target in ATTACK mode or swarm around the user acting as a shield in SHIELD mode\n\n§2SHIFT-USE§r: Switches between ATTACK and SHIELD modes");
/*  50 */     setMaxCooldown(15.0D);
/*  51 */     setThreshold(8.0D);
/*     */     
/*  53 */     addZoanModifier(ModAttributes.STEP_HEIGHT, (AttributeModifier)STEP_HEIGHT);
/*     */     
/*  55 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  56 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*  57 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  63 */     if (player.isSneaking()) {
/*     */       
/*  65 */       if (this.activeMode == Mode.ATTACK) {
/*  66 */         this.activeMode = Mode.SHIELD;
/*     */       } else {
/*  68 */         this.activeMode = Mode.ATTACK;
/*     */       } 
/*  70 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
/*  71 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*  72 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/*  73 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, (Ability)this), (LivingEntity)player);
/*     */       
/*  75 */       return false;
/*     */     } 
/*     */     
/*  78 */     if (this.activeMode == Mode.ATTACK) {
/*     */       
/*  80 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 12.0D);
/*  81 */       if (mop instanceof EntityRayTraceResult) {
/*     */         
/*  83 */         EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult)mop;
/*  84 */         if (entityRayTraceResult.getEntity() instanceof LivingEntity)
/*     */         {
/*  86 */           LivingEntity e = (LivingEntity)entityRayTraceResult.getEntity();
/*  87 */           if (!e.isAlive() || (DevilFruitCapability.get(e).isLogia() && !HakiHelper.hasHardeningActive((LivingEntity)player)) || AbilityHelper.isTargetBlockingAbility((LivingEntity)player, e)) {
/*     */             
/*  89 */             endContinuity(player);
/*  90 */             return false;
/*     */           } 
/*     */           
/*  93 */           this.grabbedEntity = e;
/*  94 */           this.baraFestival = new BaraFestivalEntity(player.world);
/*  95 */           this.baraFestival.setPositionAndRotation(this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY() + 1.0D, this.grabbedEntity.getPosZ(), 0.0F, 0.0F);
/*  96 */           this.baraFestival.setTarget(this.grabbedEntity);
/*  97 */           this.baraFestival.setOwner(player.getUniqueID());
/*  98 */           player.world.addEntity((Entity)this.baraFestival);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 103 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NO_TARGET, new Object[] { getName() }));
/* 104 */         return false;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 109 */       this.grabbedEntity = (LivingEntity)player;
/* 110 */       this.baraFestival = new BaraFestivalEntity(player.world);
/* 111 */       this.baraFestival.setPositionAndRotation(this.grabbedEntity.getPosX(), this.grabbedEntity.getPosY() + 1.0D, this.grabbedEntity.getPosZ(), 0.0F, 0.0F);
/* 112 */       this.baraFestival.setTarget(this.grabbedEntity);
/* 113 */       this.baraFestival.setOwner(player.getUniqueID());
/* 114 */       player.world.addEntity((Entity)this.baraFestival);
/*     */     } 
/*     */     
/* 117 */     return super.onStartContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int continuousTime) {
/* 123 */     if (this.activeMode == Mode.ATTACK) {
/*     */       
/* 125 */       if (this.grabbedEntity == null || !this.grabbedEntity.isAlive() || player.getDistanceSq((Entity)this.grabbedEntity) > 1500.0D) {
/*     */         
/* 127 */         endContinuity(player);
/*     */         
/*     */         return;
/*     */       } 
/* 131 */       if (continuousTime % 20 == 0)
/*     */       {
/* 133 */         this.grabbedEntity.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this, "player"), 2.0F);
/* 134 */         this.grabbedEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 1));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 139 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 3.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 140 */       targets.remove(player);
/*     */       
/* 142 */       for (LivingEntity target : targets) {
/*     */         
/* 144 */         target.attackEntityFrom((DamageSource)AbilityDamageSource.causeAbilityDamage((LivingEntity)player, (Ability)this), 6.0F);
/* 145 */         Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D).normalize();
/* 146 */         double power = 2.0D;
/* 147 */         double xSpeed = -dist.x * power;
/* 148 */         double zSpeed = -dist.z * power;
/* 149 */         target.setMotion(-xSpeed, 0.20000000298023224D, -zSpeed);
/* 150 */         target.velocityChanged = true;
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 5, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEndContinuityEvent(PlayerEntity player) {
/* 160 */     if (this.baraFestival != null)
/* 161 */       this.baraFestival.remove(); 
/* 162 */     return super.onEndContinuityEvent(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 168 */     return (ZoanInfo)BaraFestivalZoanInfo.INSTANCE;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 173 */     ATTACK, SHIELD;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraBaraFestivalAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */