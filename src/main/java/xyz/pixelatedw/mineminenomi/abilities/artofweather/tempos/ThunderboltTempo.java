/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class ThunderboltTempo extends TempoAbility {
/*  9 */   public static final ThunderboltTempo INSTANCE = new ThunderboltTempo();
/*    */ 
/*    */   
/*    */   public ThunderboltTempo() {
/* 13 */     super("Thunderbolt Tempo", AbilityHelper.getStyleCategory());
/* 14 */     setDescription("Weather Cloud + Thunder Ball\nHits an enemy below the cloud with lightning bolt");
/* 15 */     setCustomTexture("tempo");
/* 16 */     setMaxCooldown(10.0D);
/* 17 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 22 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderboltTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */