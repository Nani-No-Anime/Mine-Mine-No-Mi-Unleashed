/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.AxolotlWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AxolotlHealAbility extends Ability implements IFormRequiredAbility {
/* 24 */   public static final AxolotlHealAbility INSTANCE = new AxolotlHealAbility();
/*    */   
/* 26 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ChiyupopoParticleEffect();
/*    */ 
/*    */   
/*    */   public AxolotlHealAbility() {
/* 30 */     super("Axolotl Heal", AbilityHelper.getDevilFruitCategory());
/* 31 */     setMaxCooldown(15.0D);
/* 32 */     setDescription("Regenerates all friendlies nearby based on how many other Axolotls are around.");
/*    */     
/* 34 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 39 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, FactionHelper.getSameGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*    */     
/* 41 */     int axolots = WyHelper.getEntitiesNear(player.getPosition(), player.world, 15.0D, entity -> (!entity.equals(player) && entity instanceof PlayerEntity && DevilFruitCapability.get((LivingEntity)entity).hasDevilFruit(ModAbilities.SARA_SARA_NO_MI_AXOLOTL)), new Class[0]).size();
/* 42 */     axolots = Math.max(axolots, 3);
/* 43 */     int time = Math.max(axolots * 300, 900);
/* 44 */     targets.remove(player);
/*    */     
/* 46 */     for (LivingEntity entity : targets) {
/* 47 */       entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, time, axolots));
/*    */     }
/* 49 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 57 */     return new ZoanInfo[] { (ZoanInfo)AxolotlHeavyZoanInfo.INSTANCE, (ZoanInfo)AxolotlWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\AxolotlHealAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */