/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.ArtOfWeatherHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.ThunderBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class ThunderBallAbility extends Ability {
/* 22 */   public static final ThunderBallAbility INSTANCE = new ThunderBallAbility();
/* 23 */   private static final ParticleEffect CHARGE_PARTICLES = (ParticleEffect)new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#FFFFFF"), ModParticleTypes.GORO_YELLOW);
/*    */ 
/*    */   
/*    */   public ThunderBallAbility() {
/* 27 */     super("Thunder Ball", AbilityHelper.getStyleCategory());
/* 28 */     setDescription("Launch a Thunder Ball from your Clima Tact to use for different Tempos\n\n§2SHIFT-USE§r: Loads the ball into the clima tact");
/* 29 */     setMaxCooldown(1.0D);
/* 30 */     addInPool(new AbilityPool[] { AbilityPool.WEATHER_BALLS });
/*    */     
/* 32 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 37 */     if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
/*    */       
/* 39 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
/* 40 */       return false;
/*    */     } 
/*    */     
/* 43 */     ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 44 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/*    */     
/* 46 */     player.getHeldItemMainhand().damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */ 
/*    */     
/* 50 */     if (player.isSneaking()) {
/*    */       
/* 52 */       climaTact.chargeWeatherBall(player.getHeldItemMainhand(), "T");
/* 53 */       ArtOfWeatherHelper.checkForTempo(player, CHARGE_PARTICLES);
/*    */     }
/*    */     else {
/*    */       
/* 57 */       ThunderBallProjectile proj = new ThunderBallProjectile(player.world, (LivingEntity)player);
/* 58 */       proj.setLocationAndAngles(trace.getHitVec().getX(), player.getPosY() + player.getEyeHeight() - 0.5D, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
/* 59 */       proj.setMotion(0.0D, 0.3D, 0.0D);
/* 60 */       player.world.addEntity((Entity)proj);
/*    */     } 
/*    */ 
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\ThunderBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */