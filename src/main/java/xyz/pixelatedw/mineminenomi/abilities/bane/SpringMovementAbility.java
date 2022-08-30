/*     */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class SpringMovementAbility extends PassiveAbility {
/*  22 */   public static final SpringMovementAbility INSTANCE = new SpringMovementAbility();
/*     */   
/*  24 */   public static final UUID SPRING_POWER_UUID = UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b");
/*     */   
/*     */   private boolean startedFalling = false;
/*     */ 
/*     */   
/*     */   public SpringMovementAbility() {
/*  30 */     super("Spring Movement", AbilityHelper.getDevilFruitCategory());
/*     */     
/*  32 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringPassiveEvent(PlayerEntity user) {
/*  37 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)user);
/*  38 */     SpringHopperAbility springHopper = (SpringHopperAbility)props.getEquippedAbility(SpringHopperAbility.INSTANCE);
/*  39 */     SpringSnipeAbility springSnipe = (SpringSnipeAbility)props.getEquippedAbility(SpringSnipeAbility.INSTANCE);
/*     */     
/*  41 */     if (isPaused()) {
/*     */       
/*  43 */       if (user.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(getAtribute(0)))
/*  44 */         user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(0)); 
/*  45 */       springHopper.jumpPower = 0;
/*     */       
/*     */       return;
/*     */     } 
/*  49 */     if (springSnipe != null && springSnipe.isCharging()) {
/*     */       
/*  51 */       user.setMotion(0.0D, 0.0D, 0.0D);
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     if (springHopper == null) {
/*     */       return;
/*     */     }
/*  58 */     if (springHopper.isContinuous()) {
/*     */       
/*  60 */       if (user.collidedHorizontally && springHopper.jumpPower > 2) {
/*     */         
/*  62 */         Vec3d speed = user.getLook(1.0F).mul(-2.0D, -2.0D, -2.0D);
/*  63 */         user.setMotion(speed.x, speed.y, speed.z);
/*  64 */         if (springHopper.jumpPower < 9)
/*  65 */           springHopper.jumpPower++; 
/*  66 */         user.world.playSound(user, user.getPosition(), ModSounds.SPRING_SFX, SoundCategory.PLAYERS, 0.3F, (float)MathHelper.clamp(user.getRNG().nextDouble() + 0.30000001192092896D, 0.800000011920929D, 1.5D));
/*     */       } 
/*     */       
/*  69 */       if (user.onGround) {
/*     */         
/*  71 */         this.startedFalling = true;
/*     */         
/*  73 */         if (AbilityHelper.isJumping((LivingEntity)user)) {
/*     */           
/*  75 */           if (springHopper.jumpPower > 3) {
/*     */             
/*  77 */             Vec3d speed = WyHelper.propulsion((LivingEntity)user, 0.25D + springHopper.jumpPower * 0.25D, 0.25D + springHopper.jumpPower * 0.25D);
/*  78 */             user.setMotion(speed.x, (user.getMotion()).y, speed.z);
/*     */           } 
/*     */           
/*  81 */           if (springHopper.jumpPower < 9 && springHopper.canIncreaseJumpPower) {
/*  82 */             springHopper.jumpPower++;
/*     */           }
/*  84 */           if (user.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() != springHopper.jumpPower * 1.5D) {
/*     */             
/*  86 */             user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(springHopper.jumpPower));
/*  87 */             user.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier(getAtribute(springHopper.jumpPower));
/*     */           } 
/*     */           
/*  90 */           user.world.playSound(user, user.getPosition(), ModSounds.SPRING_SFX, SoundCategory.PLAYERS, 0.3F, (float)MathHelper.clamp(user.getRNG().nextDouble() + 0.30000001192092896D, 0.800000011920929D, 1.5D));
/*  91 */           springHopper.canIncreaseJumpPower = true;
/*     */         } else {
/*     */           
/*  94 */           springHopper.jumpPower = 0;
/*     */         } 
/*     */       } else {
/*     */         
/*  98 */         if (user.collidedVertically) {
/*  99 */           springHopper.jumpPower--;
/*     */         }
/* 101 */         if (0.0D > (user.getMotion()).y) {
/*     */           
/* 103 */           if (this.startedFalling) {
/*     */             
/* 105 */             springHopper.canIncreaseJumpPower = (DevilFruitHelper.getDifferenceToFloor(user) > springHopper.jumpPower);
/* 106 */             this.startedFalling = false;
/*     */           } 
/*     */           
/* 109 */           if (springHopper.jumpPower > 3) {
/* 110 */             user.setMotion(user.getMotion().mul(1.15D, 1.15D, 1.15D));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 116 */       if (user.getAttribute(ModAttributes.JUMP_HEIGHT).hasModifier(getAtribute(0)))
/* 117 */         user.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier(getAtribute(0)); 
/* 118 */       springHopper.jumpPower = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributeModifier getAtribute(int jumpPower) {
/* 124 */     return (AttributeModifier)(new AbilityAttributeModifier(SPRING_POWER_UUID, (Ability)INSTANCE, "Spring Movement Modifier", jumpPower * 1.5D, AttributeModifier.Operation.MULTIPLY_BASE)).setSaved(false);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringMovementAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */