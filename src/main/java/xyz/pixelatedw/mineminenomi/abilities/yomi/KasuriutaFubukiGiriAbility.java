/*    */ package xyz.pixelatedw.mineminenomi.abilities.yomi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.yomi.KasuriutaFubukiGiriParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class KasuriutaFubukiGiriAbility extends Ability implements IMultiTargetAbility {
/* 27 */   public static final KasuriutaFubukiGiriAbility INSTANCE = new KasuriutaFubukiGiriAbility();
/*    */   
/* 29 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new KasuriutaFubukiGiriParticleEffect();
/*    */ 
/*    */   
/*    */   public KasuriutaFubukiGiriAbility() {
/* 33 */     super("Kasuriuta: Fubuki Giri", AbilityHelper.getDevilFruitCategory());
/* 34 */     setDescription("A quick slash at the enemy that also freezes them");
/* 35 */     setMaxCooldown(8.0D);
/*    */     
/* 37 */     this.onUseEvent = this::onUseEvent;
/* 38 */     this.duringCooldownEvent = this::duringCooldown;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 43 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (!ItemsHelper.isSword(player.getHeldItemMainhand())) {
/*    */       
/* 48 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/* 49 */       return false;
/*    */     } 
/*    */     
/* 52 */     clearTargets();
/*    */     
/* 54 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 5.0D, 5.0D);
/* 55 */     player.setMotion(speed.x, (player.getMotion()).y, speed.z);
/* 56 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/* 63 */     if (canDealDamage()) {
/*    */       
/* 65 */       if (cooldownTimer % 2 == 0) {
/* 66 */         PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */       }
/* 68 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 69 */       list.remove(player);
/*    */       
/* 71 */       for (LivingEntity target : list) {
/*    */         
/* 73 */         if (isTarget(target) && player.canEntityBeSeen((Entity)target)) {
/*    */           
/* 75 */           target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8.0F);
/* 76 */           EffectInstance instance = new EffectInstance(ModEffects.FROZEN, 70, 0);
/* 77 */           target.addPotionEffect(instance);
/* 78 */           ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDealDamage() {
/* 86 */     return (this.cooldown > 140.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yomi\KasuriutaFubukiGiriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */