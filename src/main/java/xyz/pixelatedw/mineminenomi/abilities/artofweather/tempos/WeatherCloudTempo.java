/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class WeatherCloudTempo extends TempoAbility {
/*  9 */   public static final WeatherCloudTempo INSTANCE = new WeatherCloudTempo();
/*    */ 
/*    */   
/*    */   public WeatherCloudTempo() {
/* 13 */     super("Weather Cloud Tempo", AbilityHelper.getStyleCategory());
/* 14 */     setDescription("Cool Ball + Heat Ball\nCreates a cloud above you");
/* 15 */     setCustomTexture("tempo");
/*    */     
/* 17 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\WeatherCloudTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */