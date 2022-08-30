/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class FogTempo extends TempoAbility {
/* 17 */   public static final FogTempo INSTANCE = new FogTempo();
/*    */ 
/*    */   
/*    */   public FogTempo() {
/* 21 */     super("Fog Tempo", AbilityHelper.getStyleCategory());
/* 22 */     setDescription("2 Charged Heat Balls + Charged Cool Ball\nCreates a thick fog at the user's position");
/* 23 */     setMaxCooldown(10.0D);
/* 24 */     setCustomTexture("tempo");
/*    */     
/* 26 */     this.onUseEvent = this::onUseEvent;
/* 27 */     this.canUseCheck = this::canUseCheck;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) {
/* 32 */     if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
/*    */       
/* 34 */       ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 35 */       String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
/* 36 */       return tempoCombo.equalsIgnoreCase("HHC");
/*    */     } 
/*    */     
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 44 */     MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
/* 45 */     smokeCloud.setLife(100);
/* 46 */     smokeCloud.setLocationAndAngles(player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0F, 0.0F);
/* 47 */     smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
/* 48 */     smokeCloud.setThrower((LivingEntity)player);
/* 49 */     player.world.addEntity((Entity)smokeCloud);
/*    */     
/* 51 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D);
/* 52 */     targets.remove(player);
/*    */     
/* 54 */     for (LivingEntity entity : targets)
/*    */     {
/* 56 */       entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
/*    */     }
/*    */     
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\FogTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */