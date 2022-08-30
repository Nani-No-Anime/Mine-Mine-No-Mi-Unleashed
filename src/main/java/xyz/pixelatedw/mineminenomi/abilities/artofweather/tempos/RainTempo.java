/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class RainTempo extends TempoAbility {
/* 10 */   public static final RainTempo INSTANCE = new RainTempo();
/*    */ 
/*    */   
/*    */   public RainTempo() {
/* 14 */     super("Rain Tempo", AbilityHelper.getStyleCategory());
/* 15 */     setDescription("Weather Cloud + 3 Cool Balls\nCreates rain");
/* 16 */     setCustomTexture("tempo");
/* 17 */     setMaxCooldown(10.0D);
/*    */     
/* 19 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 24 */     WorldInfo worldinfo = player.world.getWorldInfo();
/* 25 */     worldinfo.setRaining(true);
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\RainTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */