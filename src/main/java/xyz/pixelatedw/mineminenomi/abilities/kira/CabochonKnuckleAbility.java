/*    */ package xyz.pixelatedw.mineminenomi.abilities.kira;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class CabochonKnuckleAbility extends PunchAbility implements IPunchOverlayAbility {
/* 17 */   public static final CabochonKnuckleAbility INSTANCE = new CabochonKnuckleAbility();
/*    */   
/* 19 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DIAMOND_BODY);
/*    */ 
/*    */   
/*    */   public CabochonKnuckleAbility() {
/* 23 */     super("Cabochon Knuckle", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(8.0D);
/* 25 */     setDescription("A diamond covered punch, dealing damage and slight knockback");
/*    */     
/* 27 */     this.onHitEntityEvent = this::onHitEvent;
/* 28 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 33 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 34 */     target.setMotion(speed.x, player.getMotion().getY() + 0.1D, speed.z);
/* 35 */     if (target instanceof PlayerEntity) {
/* 36 */       ((ServerPlayerEntity)target).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)target));
/*    */     }
/*    */   }
/*    */   
/*    */   private float onHitEvent(PlayerEntity player, LivingEntity target) {
/* 41 */     return 15.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 47 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kira\CabochonKnuckleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */