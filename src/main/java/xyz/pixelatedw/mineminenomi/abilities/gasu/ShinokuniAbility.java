/*     */ package xyz.pixelatedw.mineminenomi.abilities.gasu;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.potion.Potions;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class ShinokuniAbility extends ZoanAbility {
/*  31 */   public static final ShinokuniAbility INSTANCE = new ShinokuniAbility();
/*     */   
/*  33 */   private static final AbilityAttributeModifier REACH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("1f8cc62b-ad78-4b25-b19c-76d23dd27517"), (Ability)INSTANCE, "Shinokuni Reach Modifier", 5.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  34 */   private static final AbilityAttributeModifier KNOCKBACK_RESISTANCE = (new AbilityAttributeModifier(UUID.fromString("84033b39-98f7-4312-acc5-92809e9732b1"), (Ability)INSTANCE, "Shinokuni Knockback Resistance Modifier", 2.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  35 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("8a048300-6f4b-11eb-9439-0242ac130002"), (Ability)INSTANCE, "Shinokuni Damage Reduction Modifier", 0.2D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  36 */   private static final AbilityAttributeModifier HEALTH_BOOST = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Shinokuni Health Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  37 */   private static final AbilityAttributeModifier STRENGTH_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), (Ability)INSTANCE, "Shinokuni Strength Modifier", 7.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*     */   
/*  39 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new HaoshokuHakiParticleEffect(2);
/*     */   
/*     */   public Potion effect;
/*     */ 
/*     */   
/*     */   public ShinokuniAbility() {
/*  45 */     super("Shinokuni", AbilityHelper.getDevilFruitCategory());
/*  46 */     setDescription("Transforms into a gas giant releasing toxic gas near the user, whatever §2Potion§r the user holds when transforminng is absorbed by Shinokuni and used its in area of effects");
/*  47 */     setThreshold(30.0D);
/*     */     
/*  49 */     addZoanModifier(PlayerEntity.REACH_DISTANCE, (AttributeModifier)REACH_MODIFIER);
/*  50 */     addZoanModifier(ModAttributes.ATTACK_RANGE, (AttributeModifier)REACH_MODIFIER);
/*  51 */     addZoanModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, (AttributeModifier)KNOCKBACK_RESISTANCE);
/*  52 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
/*  53 */     addZoanModifier(SharedMonsterAttributes.MAX_HEALTH, (AttributeModifier)HEALTH_BOOST);
/*  54 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)STRENGTH_MODIFIER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onStartContinuityEvent(PlayerEntity player) {
/*  60 */     if (!super.onStartContinuityEvent(player)) {
/*  61 */       return false;
/*     */     }
/*  63 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, new Class[] { LivingEntity.class });
/*  64 */     list.remove(player);
/*  65 */     this.effect = PotionUtils.getPotionFromItem(player.getHeldItemMainhand());
/*     */     
/*  67 */     if (!this.effect.equals(Potions.EMPTY)) {
/*     */       
/*  69 */       player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
/*  70 */       list.forEach(target -> applyEffects(player, target));
/*  71 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int time) {
/*  80 */     super.duringContinuityEvent(player, time);
/*     */     
/*  82 */     if (this.effect.equals(Potions.EMPTY)) {
/*     */       return;
/*     */     }
/*  85 */     if (time % 100 == 0) {
/*     */       
/*  87 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*  88 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, new Class[] { LivingEntity.class });
/*  89 */       list.remove(player);
/*  90 */       for (LivingEntity target : list)
/*     */       {
/*  92 */         applyEffects(player, target);
/*     */       }
/*     */     } 
/*     */     
/*  96 */     if (time % 2 != 0) {
/*     */       return;
/*     */     }
/*  99 */     for (int i = 0; i < 10; i++) {
/*     */       ParticleType type;
/* 101 */       double offsetX = WyHelper.randomDouble() * 2.0D;
/* 102 */       double offsetY = WyHelper.randomDouble() * 2.0D;
/* 103 */       double offsetZ = WyHelper.randomDouble() * 2.0D;
/*     */ 
/*     */       
/* 106 */       if (i % 5 == 0) {
/* 107 */         type = ModParticleTypes.GASU2;
/*     */       } else {
/* 109 */         type = ModParticleTypes.GASU;
/*     */       } 
/* 111 */       GenericParticleData data = new GenericParticleData(type);
/* 112 */       data.setMotion(0.0D, 0.1D, 0.0D);
/* 113 */       data.setLife(40);
/* 114 */       data.setSize(3.0F);
/* 115 */       WyHelper.spawnParticles(data, (ServerWorld)player.world, player.getPosX() + offsetX, player.getPosY() + offsetY, player.getPosZ() + offsetZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyEffects(PlayerEntity player, LivingEntity entity) {
/* 121 */     for (EffectInstance inst : this.effect.getEffects()) {
/*     */       
/* 123 */       boolean isAlly = FactionHelper.getSameGroupPredicate((LivingEntity)player).test(entity);
/*     */       
/* 125 */       if (isAlly && inst.getPotion().isBeneficial()) {
/* 126 */         entity.addPotionEffect(new EffectInstance(inst.getPotion(), inst.getDuration(), inst.getAmplifier()));
/*     */       }
/* 128 */       if (!isAlly && !inst.getPotion().isBeneficial()) {
/* 129 */         entity.addPotionEffect(new EffectInstance(inst.getPotion(), inst.getDuration(), inst.getAmplifier()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEndContinuityEvent(PlayerEntity player) {
/* 136 */     if (!super.onEndContinuityEvent(player)) {
/* 137 */       return false;
/*     */     }
/* 139 */     int cooldown = (int)(5L + Math.round(this.continueTime / 18.0D));
/* 140 */     setMaxCooldown(cooldown);
/*     */     
/* 142 */     this.effect = null;
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/* 149 */     return (ZoanInfo)ShinokuniZoanInfo.INSTANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gasu\ShinokuniAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */