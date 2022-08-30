/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ public class GenkotsuMeteorAbility extends RepeaterAbility {
/* 20 */   public static final GenkotsuMeteorAbility INSTANCE = new GenkotsuMeteorAbility();
/* 21 */   private MODE activeMode = MODE.NORMAL;
/*    */ 
/*    */   
/*    */   public GenkotsuMeteorAbility() {
/* 25 */     super("Genkotsu Meteor", AbilityHelper.getStyleCategory());
/* 26 */     setMaxCooldown(6.0D);
/* 27 */     setMaxRepeaterCount(1, 1);
/* 28 */     setDescription("Throws a cannon ball from the user's hand or multiple cannon balls in Ryuseigun mode\n\n§2SHIFT-USE§r: Switches between NORMAL and RYUSEIGUN modes");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     if (player.isSneaking()) {
/*    */       
/* 38 */       if (this.activeMode == MODE.NORMAL) {
/*    */         
/* 40 */         setMaxCooldown(10.0D);
/* 41 */         setMaxRepeaterCount(10, 4);
/* 42 */         this.activeMode = MODE.RYUSEIGUN;
/*    */       }
/*    */       else {
/*    */         
/* 46 */         setMaxCooldown(3.0D);
/* 47 */         setMaxRepeaterCount(1, 1);
/* 48 */         this.activeMode = MODE.NORMAL;
/*    */       } 
/*    */       
/* 51 */       player.sendMessage((ITextComponent)new TranslationTextComponent("Ability mode set to: " + this.activeMode, new Object[0]));
/* 52 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 53 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), player);
/* 54 */       return false;
/*    */     } 
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 62 */     if (!player.getHeldItemMainhand().getItem().equals(ModItems.CANNON_BALL)) {
/*    */       
/* 64 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CANNONBALLS, new Object[0]));
/* 65 */       endContinuity(player);
/* 66 */       return false;
/*    */     } 
/*    */     
/* 69 */     player.getHeldItemMainhand().shrink(1);
/*    */     
/* 71 */     CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(player.world, (LivingEntity)player);
/* 72 */     cannonBallProjectile.setDamage(9.0F);
/* 73 */     ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {

                 //cant find symbol fix
                 AbilityProjectileEntity proj = cannonBallProjectile;
/*    */         if (proj.ticksExisted < 2) {
/*    */           return;
/*    */         }
/*    */         
/*    */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj.getThrower(), player.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
/*    */         
/*    */         explosion.setStaticDamage(5.0F);
/*    */         explosion.setDestroyBlocks(false);
/*    */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*    */         explosion.doExplosion();
/*    */       });
/* 85 */     player.world.addEntity((Entity)cannonBallProjectile);
/* 86 */     cannonBallProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 2.0F);
/*    */     
/* 88 */     if (this.activeMode == MODE.NORMAL) {
/*    */       
/* 90 */       endContinuity(player);
/* 91 */       return false;
/*    */     } 
/*    */     
/* 94 */     return true;
/*    */   }
/*    */   
/*    */   public enum MODE
/*    */   {
/* 99 */     NORMAL, RYUSEIGUN;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\GenkotsuMeteorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */