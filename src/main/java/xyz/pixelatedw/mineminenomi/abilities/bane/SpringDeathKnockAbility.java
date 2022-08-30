/*    */ package xyz.pixelatedw.mineminenomi.abilities.bane;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bane.SpringDeathKnockProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SpringDeathKnockAbility extends Ability {
/* 12 */   public static final Ability INSTANCE = new SpringDeathKnockAbility();
/*    */ 
/*    */   
/*    */   public SpringDeathKnockAbility() {
/* 16 */     super("Spring Death Knock", AbilityHelper.getDevilFruitCategory());
/* 17 */     setMaxCooldown(4.0D);
/* 18 */     setDescription("By turning the user's arm into a spring and compressing it, they can launch a powerful punch");
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     SpringDeathKnockProjectile proj = new SpringDeathKnockProjectile(player.world, (LivingEntity)player);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
/*    */     
/* 29 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bane\SpringDeathKnockAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */