/*    */ package xyz.pixelatedw.mineminenomi.abilities.yami;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackWorldParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BlackWorldAbility
/*    */   extends Ability {
/* 21 */   public static final BlackWorldAbility INSTANCE = new BlackWorldAbility();
/*    */   
/* 23 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BlackWorldParticleEffect();
/* 24 */   private static final BlockProtectionRule GRIEF_RULE = (BlockProtectionRule)AirBlockProtectionRule.INSTANCE;
/*    */ 
/*    */   
/*    */   public BlackWorldAbility() {
/* 28 */     super("Black World", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("The user spreads their power to the surroundings, blinding enemies and creating pillars of Darkness");
/* 30 */     setMaxCooldown(10.0D);
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 37 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 39 */     for (int i = 0; i < 8; i++) {
/*    */       
/* 41 */       int a1 = (int)WyHelper.randomWithRange(-10, 10);
/* 42 */       int a2 = (int)WyHelper.randomWithRange(-10, 10);
/*    */       
/* 44 */       for (int j = -5; j < 5; j++) {
/*    */         
/* 46 */         AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2), ModBlocks.DARKNESS, GRIEF_RULE);
/* 47 */         AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1 + 1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2), ModBlocks.DARKNESS, GRIEF_RULE);
/* 48 */         AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2 + 1), ModBlocks.DARKNESS, GRIEF_RULE);
/* 49 */         AbilityHelper.placeBlockIfAllowed(player.world, ((int)player.getPosX() + a1 + 1), ((int)player.getPosY() + j), ((int)player.getPosZ() + a2 + 1), ModBlocks.DARKNESS, GRIEF_RULE);
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 20.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 54 */     targets.remove(player);
/*    */     
/* 56 */     for (LivingEntity target : targets) {
/*    */       
/* 58 */       target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1, false, false));
/* 59 */       target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
/*    */     } 
/*    */     
/* 62 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yami\BlackWorldAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */