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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.CoolBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class CoolBallAbility extends Ability {
/* 22 */   public static final CoolBallAbility INSTANCE = new CoolBallAbility();
/*    */   
/* 24 */   private static final ParticleEffect CHARGE_PARTICLES = (ParticleEffect)new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#0055FF"), ModParticleTypes.HIE);
/*    */ 
/*    */   
/*    */   public CoolBallAbility() {
/* 28 */     super("Cool Ball", AbilityHelper.getStyleCategory());
/* 29 */     setDescription("Launch a Cool Ball from your Clima Tact to use for different Tempos\n\n§2SHIFT-USE§r: Loads the ball into the clima tact");
/* 30 */     setMaxCooldown(1.0D);
/* 31 */     addInPool(new AbilityPool[] { AbilityPool.WEATHER_BALLS });
/*    */     
/* 33 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 38 */     if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
/*    */       
/* 40 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
/* 41 */       return false;
/*    */     } 
/*    */     
/* 44 */     ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 45 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/*    */     
/* 47 */     player.getHeldItemMainhand().damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */ 
/*    */     
/* 51 */     if (player.isSneaking()) {
/*    */       
/* 53 */       climaTact.chargeWeatherBall(player.getHeldItemMainhand(), "C");
/* 54 */       ArtOfWeatherHelper.checkForTempo(player, CHARGE_PARTICLES);
/*    */     }
/*    */     else {
/*    */       
/* 58 */       CoolBallProjectile proj = new CoolBallProjectile(player.world, (LivingEntity)player);
/* 59 */       proj.setLocationAndAngles(trace.getHitVec().getX(), player.getPosY() + player.getEyeHeight() - 0.5D, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
/* 60 */       proj.setMotion(0.0D, 0.3D, 0.0D);
/* 61 */       player.world.addEntity((Entity)proj);
/*    */     } 
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\CoolBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */