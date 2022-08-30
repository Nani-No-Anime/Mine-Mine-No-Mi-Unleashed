/*    */ package xyz.pixelatedw.mineminenomi.abilities.zou;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class GreatStompAbility
/*    */   extends Ability implements IFormRequiredAbility {
/* 20 */   public static final GreatStompAbility INSTANCE = new GreatStompAbility();
/*    */   
/* 22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
/*    */ 
/*    */   
/*    */   public GreatStompAbility() {
/* 26 */     super("Great Stomp", AbilityHelper.getDevilFruitCategory());
/* 27 */     setDescription("By stomping the ground as a full-form elephant, the user creates a shockwave");
/* 28 */     setMaxCooldown(12.0D);
/*    */     
/* 30 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 36 */     targets.remove(player);
/*    */     
/* 38 */     for (LivingEntity entity : targets) {
/*    */       
/* 40 */       entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
/* 41 */       entity.setPositionAndUpdate(entity.getPosX(), entity.getPosY() + 3.0D, entity.getPosZ());
/*    */     } 
/*    */     
/* 44 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 52 */     return new ZoanInfo[] { (ZoanInfo)ZouGuardZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zou\GreatStompAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */