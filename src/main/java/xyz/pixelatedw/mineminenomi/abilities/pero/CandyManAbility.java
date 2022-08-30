/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class CandyManAbility extends PunchAbility {
/* 17 */   public static final CandyManAbility INSTANCE = new CandyManAbility();
/*    */ 
/*    */   
/*    */   public CandyManAbility() {
/* 21 */     super("Candy Man", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Traps the target inside hardened candy, immobilizing and suffocating them");
/* 23 */     setMaxCooldown(30.0D);
/*    */     
/* 25 */     this.onHitEntityEvent = this::onHitEntity;
/* 26 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 31 */     EffectInstance instance = new EffectInstance(ModEffects.CANDY_STUCK, 400, 1, false, false);
/* 32 */     target.addPotionEffect(instance);
/* 33 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance));
/* 34 */     WyHelper.spawnParticles(ParticleTypes.ENCHANT, (ServerWorld)player.world, player.getPosX(), player.getPosY() + 0.8D, player.getPosZ());
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 39 */     return 5.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyManAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */