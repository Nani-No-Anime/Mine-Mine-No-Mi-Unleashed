/*    */ package xyz.pixelatedw.mineminenomi.abilities.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.GomuHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoElephantGunProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoJetPistolProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoKongProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.gomu.GomuGomuNoPistolProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class GomuGomuNoPistolAbility extends Ability {
/* 22 */   public static final GomuGomuNoPistolAbility INSTANCE = new GomuGomuNoPistolAbility();
/*    */   
/* 24 */   private Mode mode = Mode.NORMAL;
/*    */ 
/*    */   
/*    */   public GomuGomuNoPistolAbility() {
/* 28 */     super("Gomu Gomu no Pistol", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("The user stretches their arm to hit the opponent");
/* 30 */     setMaxCooldown(1.5D);
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/*    */     
/* 37 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 39 */     AbilityProjectileEntity projectile = null;
/* 40 */     float speed = 2.0F;
/* 41 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 43 */     if (GomuHelper.hasGearFourthActive(props)) {
/*    */       
/* 45 */       GomuGomuNoKongProjectile gomuGomuNoKongProjectile = new GomuGomuNoKongProjectile(player.world, (LivingEntity)player);
/* 46 */       gomuGomuNoKongProjectile.setCollisionSize(2.5D);
/* 47 */       speed = 1.8F;
/* 48 */       if (this.mode != Mode.GEAR_FOURTH)
/*    */       {
/* 50 */         setMaxCooldown(4.0D);
/* 51 */         setDisplayName("Gomu Gomu no Culverin");
/* 52 */         this.mode = Mode.GEAR_FOURTH;
/*    */       }
               projectile = gomuGomuNoKongProjectile;
/*    */     
/* 55 */     } else if (GomuHelper.hasGearThirdActive(props)) {
/*    */       
/* 57 */       GomuGomuNoElephantGunProjectile gomuGomuNoElephantGunProjectile = new GomuGomuNoElephantGunProjectile(player.world, (LivingEntity)player);
/* 58 */       gomuGomuNoElephantGunProjectile.setCollisionSize(2.5D);
/* 59 */       speed = 1.8F;
/* 60 */       if (this.mode != Mode.GEAR_THIRD)
/*    */       {
/* 62 */         setMaxCooldown(6.0D);
/* 63 */         setDisplayName("Gomu Gomu no Elephant Gun");
/* 64 */         this.mode = Mode.GEAR_THIRD;
/*    */       }
               projectile = gomuGomuNoElephantGunProjectile;
/*    */     
/* 67 */     } else if (GomuHelper.hasGearSecondActive(props)) {
/*    */       
/* 69 */       GomuGomuNoJetPistolProjectile gomuGomuNoJetPistolProjectile = new GomuGomuNoJetPistolProjectile(player.world, (LivingEntity)player);
/* 70 */       speed = 2.5F;
/* 71 */       if (this.mode != Mode.GEAR_SECOND)
/*    */       {
/* 73 */         setMaxCooldown(1.0D);
/* 74 */         setDisplayName("Gomu Gomu no Jet Pistol");
/* 75 */         this.mode = Mode.GEAR_SECOND;
/*    */       }
               projectile = gomuGomuNoJetPistolProjectile;
/*    */     
/*    */     } else {
/*    */       
/* 80 */       GomuGomuNoPistolProjectile gomuGomuNoPistolProjectile = new GomuGomuNoPistolProjectile(player.world, (LivingEntity)player);
/* 81 */       if (this.mode != Mode.NORMAL) {
/*    */         
/* 83 */         setMaxCooldown(1.5D);
/* 84 */         setDisplayName("Gomu Gomu no Pistol");
/* 85 */         this.mode = Mode.NORMAL;
/*    */       } 
               projectile= gomuGomuNoPistolProjectile;
/*    */     } 
/*    */     
/* 89 */     player.world.addEntity((Entity)projectile);
/* 90 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 1.0F);
/* 91 */     player.world.playSound(null, player.getPosition(), ModSounds.GOMU_SFX, SoundCategory.PLAYERS, 0.8F, (float)MathHelper.clamp(player.getRNG().nextDouble() * 2.0D, 1.0D, 1.2999999523162842D));
/*    */     
/* 93 */     return true;
/*    */   }
/*    */   
/*    */   public enum Mode
/*    */   {
/* 98 */     NORMAL, GEAR_SECOND, GEAR_THIRD, GEAR_FOURTH;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\gomu\GomuGomuNoPistolAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */