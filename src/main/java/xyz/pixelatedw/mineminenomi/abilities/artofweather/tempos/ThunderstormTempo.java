/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class ThunderstormTempo extends TempoAbility {
/*  9 */   public static final ThunderstormTempo INSTANCE = new ThunderstormTempo();
/*    */ 
/*    */   
/*    */   public ThunderstormTempo() {
/* 13 */     super("Thunderstorm Tempo", AbilityHelper.getStyleCategory());
/* 14 */     setDescription("Thunderbolt Tempo + 2 Thunder Balls\nHits multiple enemies below the cloud with multiple lightning bolts");
/* 15 */     setCustomTexture("tempo");
/* 16 */     setMaxCooldown(15.0D);
/*    */     
/* 18 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 23 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderstormTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */