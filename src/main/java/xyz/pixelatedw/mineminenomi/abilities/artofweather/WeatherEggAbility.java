/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherEggProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class WeatherEggAbility extends Ability {
/* 16 */   public static final WeatherEggAbility INSTANCE = new WeatherEggAbility();
/*    */ 
/*    */   
/*    */   public WeatherEggAbility() {
/* 20 */     super("Weather Egg", AbilityHelper.getStyleCategory());
/* 21 */     setDescription("Instantly creates a Weather Cloud as if combining a Cool Ball and a Heat Ball");
/* 22 */     setMaxCooldown(16.0D);
/*    */     
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 29 */     if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand())) {
/*    */       
/* 31 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT, new Object[0]));
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 36 */     if (climaTact.getLevel() < 3) {
/*    */       
/* 38 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
/* 39 */       return false;
/*    */     } 
/*    */     
/* 42 */     EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.5D);
/* 43 */     WeatherEggProjectile proj = new WeatherEggProjectile(player.world, (LivingEntity)player);
/* 44 */     proj.setLocationAndAngles(trace.getHitVec().getX(), player.getPosY() + player.getEyeHeight() - 0.5D, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
/* 45 */     proj.setMotion(0.0D, 0.3D, 0.0D);
/* 46 */     player.world.addEntity((Entity)proj);
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\WeatherEggAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */