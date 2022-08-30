/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ito.TamaitoProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class TamaitoAbility extends Ability {
/* 12 */   public static final TamaitoAbility INSTANCE = new TamaitoAbility();
/*    */ 
/*    */   
/*    */   public TamaitoAbility() {
/* 16 */     super("Tamaito", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("The user shoots a small bundle of strings, acting like a bullet");
/* 18 */     setMaxCooldown(1.5D);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     TamaitoProjectile proj = new TamaitoProjectile(player.world, (LivingEntity)player);
/* 26 */     player.world.addEntity((Entity)proj);
/* 27 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.2F);
/*    */     
/* 29 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\TamaitoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */