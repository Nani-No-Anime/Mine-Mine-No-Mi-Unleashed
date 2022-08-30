/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public abstract class PunchAbility
/*    */   extends ContinuousAbility
/*    */ {
/*    */   private boolean isStoppingAfterHit = true;
/*    */   protected IOnHitEffect onHitEffectEvent = (player, target) -> {
/*    */     
/*    */     };
/*    */   protected IOnHitEntity onHitEntityEvent = (player, target) -> 0.0F;
/*    */   
/*    */   public PunchAbility(String name, APIConfig.AbilityCategory category) {
/* 24 */     super(name, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStoppingAfterHit(boolean flag) {
/* 29 */     this.isStoppingAfterHit = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStoppingAfterHit() {
/* 34 */     return this.isStoppingAfterHit;
/*    */   }
/*    */ 
/*    */   
/*    */   public DamageSource getPunchDamageSource(PlayerEntity player) {
/* 39 */     return (DamageSource)ModDamageSource.causeAbilityDamage((LivingEntity)player, this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void hitEffect(PlayerEntity player, LivingEntity target) {
/* 47 */     this.onHitEffectEvent.hitEffect(player, target);
/*    */     
/* 49 */     if (this.isStoppingAfterHit) {
/* 50 */       endContinuity(player);
/*    */     }
/*    */   }
/*    */   
/*    */   public float hitEntity(PlayerEntity player, LivingEntity target) {
/* 55 */     float result = this.onHitEntityEvent.onHitEntity(player, target);
/*    */     
/* 57 */     if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/* 58 */       WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
/*    */     }
/* 60 */     return result;
/*    */   }
/*    */   
/*    */   public static interface IOnHitEntity extends Serializable {
/*    */     float onHitEntity(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
/*    */   }
/*    */   
/*    */   public static interface IOnHitEffect extends Serializable {
/*    */     void hitEffect(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\PunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */