/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.GroundParticlesEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class JishinHoAbility extends Ability {
/* 19 */   public static final JishinHoAbility INSTANCE = new JishinHoAbility();
/*    */   
/* 21 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(7, 100);
/*    */ 
/*    */   
/*    */   public JishinHoAbility() {
/* 25 */     super("Jishin Ho", AbilityHelper.getStyleCategory());
/* 26 */     setDescription("Punches the ground to cause a quake that damages everyone around");
/* 27 */     setMaxCooldown(15.0D);
/*    */     
/* 29 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 34 */     if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
/*    */       
/* 36 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
/* 37 */       return false;
/*    */     } 
/*    */     
/* 40 */     for (int i = 0; i < 30; i++) {
/* 41 */       PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/* 43 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 7.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 44 */     targets.removeIf(entity -> !entity.onGround);
/* 45 */     targets.remove(player);
/*    */     
/* 47 */     targets.stream().filter(target -> (target != null && target.isAlive())).forEach(target -> {
/*    */           target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this, "player"), 25.0F);
/*    */           
/*    */           target.setMotion(0.0D, 0.75D, 0.0D);
/*    */           
/*    */           target.velocityChanged = true;
/*    */         });
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\JishinHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */