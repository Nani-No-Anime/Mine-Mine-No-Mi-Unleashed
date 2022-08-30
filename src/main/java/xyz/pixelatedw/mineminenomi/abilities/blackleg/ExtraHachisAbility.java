/*    */ package xyz.pixelatedw.mineminenomi.abilities.blackleg;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.ExtraHachisProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ExtraHachiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class ExtraHachisAbility extends RepeaterAbility {
/* 16 */   public static final ExtraHachisAbility INSTANCE = new ExtraHachisAbility();
/*    */   
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new ExtraHachiParticleEffect();
/*    */   
/*    */   private boolean diableJambeMode = false;
/*    */ 
/*    */   
/*    */   public ExtraHachisAbility() {
/* 24 */     super("Extra Hachis", AbilityHelper.getStyleCategory());
/* 25 */     setMaxCooldown(12.0D);
/* 26 */     setMaxRepeaterCount(20, 2);
/* 27 */     setDescription("Launches a rapid barrage of kicks");
/*    */     
/* 29 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 34 */     ExtraHachisProjectile extraHachisProjectile = new ExtraHachisProjectile(player.world, (LivingEntity)player);
/* 35 */     int projectileSpace = 2;
/* 36 */     float speed = 2.0F;
/*    */     
/* 38 */     if (this.diableJambeMode) {
/*    */       
/* 40 */       extraHachisProjectile.setDamage(10.0F);
/* 41 */       ((AbilityProjectileEntity)extraHachisProjectile).onEntityImpactEvent = (entity -> entity.setFire(2));
/*    */ 
/*    */ 
/*    */       
/* 45 */       ((AbilityProjectileEntity)extraHachisProjectile).onTickEvent = (() -> PARTICLES.spawn(player.world, extraHachisProjectile.getPosX(), extraHachisProjectile.getPosY(), extraHachisProjectile.getPosZ(), 0.0D, 0.0D, 0.0D));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 50 */     extraHachisProjectile.setLocationAndAngles(player
/* 51 */         .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/* 52 */         .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/* 53 */         .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*    */     
/* 55 */     player.world.addEntity((Entity)extraHachisProjectile);
/* 56 */     extraHachisProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 3.0F);
/*    */     
/* 58 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enableDiableJambeMode() {
/* 65 */     setDisplayName("Poêle à Frire");
/* 66 */     setCustomTexture("poele_a_frire");
/* 67 */     setMaxCooldown(15.0D);
/* 68 */     this.diableJambeMode = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableDiableJambeMode() {
/* 73 */     setDisplayName(null);
/* 74 */     setCustomTexture("");
/* 75 */     setMaxCooldown(12.0D);
/* 76 */     this.diableJambeMode = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\blackleg\ExtraHachisAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */