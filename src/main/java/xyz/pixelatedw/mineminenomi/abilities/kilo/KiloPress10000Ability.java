/*    */ package xyz.pixelatedw.mineminenomi.abilities.kilo;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class KiloPress10000Ability extends ContinuousAbility {
/* 24 */   public static final KiloPress10000Ability INSTANCE = new KiloPress10000Ability();
/*    */   
/* 26 */   private static final AbilityAttributeModifier KILO_PRESS_JUMP_HEIGHT = new AbilityAttributeModifier(UUID.fromString("692759d2-5d8d-4809-912d-86ad362f8f95"), (Ability)INSTANCE, "Kilo Press Jump Height Modifier", -10.0D, AttributeModifier.Operation.ADDITION);
/* 27 */   private static final AbilityAttributeModifier KILO_PRESS_KNOCKBACK = new AbilityAttributeModifier(UUID.fromString("f3597992-9268-4a40-9363-555cf06c7771"), (Ability)INSTANCE, "Kilo Press Knockback Modifier", 1.0D, AttributeModifier.Operation.ADDITION);
/*    */   
/* 29 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
/*    */   
/* 31 */   private double initialPosY = 0.0D;
/*    */ 
/*    */   
/*    */   public KiloPress10000Ability() {
/* 35 */     super("10,000 Kilo Press", AbilityHelper.getDevilFruitCategory());
/* 36 */     setDescription("Makes the user become extremely heavy, crashing down on enemies from above crushes them");
/* 37 */     setThreshold(60.0D);
/*    */     
/* 39 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 40 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 41 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 46 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).applyModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
/* 47 */     player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier((AttributeModifier)KILO_PRESS_KNOCKBACK);
/*    */     
/* 49 */     player.setMotion((player.getMotion()).x, -5.0D, (player.getMotion()).z);
/* 50 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 52 */     if (!player.onGround) {
/* 53 */       this.initialPosY = player.getPosY();
/*    */     } else {
/* 55 */       this.initialPosY = 0.0D;
/*    */     } 
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int time) {
/* 62 */     player.fallDistance = 0.0F;
/*    */     
/* 64 */     if (player.onGround && this.initialPosY > 0.0D && player.getPosY() < this.initialPosY) {
/*    */       
/* 66 */       double damage = Math.min(this.initialPosY - player.getPosY(), 80.0D);
/* 67 */       if (damage > 0.0D) {
/*    */         
/* 69 */         List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 70 */         targets.remove(player);
/* 71 */         for (LivingEntity entity : targets)
/*    */         {
/* 73 */           entity.attackEntityFrom(DamageSource.causePlayerDamage(player), (float)damage);
/*    */         }
/* 75 */         this.initialPosY = 0.0D;
/*    */       } 
/*    */       
/* 78 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 84 */     player.getAttribute(ModAttributes.JUMP_HEIGHT).removeModifier((AttributeModifier)KILO_PRESS_JUMP_HEIGHT);
/* 85 */     player.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).removeModifier((AttributeModifier)KILO_PRESS_KNOCKBACK);
/*    */     
/* 87 */     int cooldown = (int)Math.round(this.continueTime / 20.0D);
/* 88 */     setMaxCooldown(cooldown);
/*    */     
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kilo\KiloPress10000Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */