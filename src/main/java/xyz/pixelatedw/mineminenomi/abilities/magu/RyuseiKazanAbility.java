/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.RyuseiKazanProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class RyuseiKazanAbility extends RepeaterAbility {
/* 13 */   public static final Ability INSTANCE = (Ability)new RyuseiKazanAbility();
/*    */ 
/*    */   
/*    */   public RyuseiKazanAbility() {
/* 17 */     super("Ryusei Kazan", AbilityHelper.getDevilFruitCategory());
/* 18 */     setDescription("Functions like 'Dai Funka', but multiple fists are launched at the opponent");
/* 19 */     setMaxCooldown(20.0D);
/* 20 */     setMaxRepeaterCount(10, 5);
/*    */     
/* 22 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 23 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 27 */     AbilityHelper.slowEntityFall((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 32 */     RyuseiKazanProjectile proj = new RyuseiKazanProjectile(player.world, (LivingEntity)player);
/* 33 */     player.world.addEntity((Entity)proj);
/* 34 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 4.0F);
/* 35 */     player.world.playSound(null, player.getPosition(), ModSounds.MAGU_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 37 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\RyuseiKazanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */