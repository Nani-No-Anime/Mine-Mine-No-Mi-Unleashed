/*    */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.passive.AnimalEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class ForewarnAbility extends Ability {
/* 14 */   public static final ForewarnAbility INSTANCE = new ForewarnAbility();
/*    */ 
/*    */   
/*    */   public ForewarnAbility() {
/* 18 */     super("Animal Forewarning", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Allows the user to communicate with nearby animals and learn when the next rain or clear weathers will come");
/* 20 */     this.onUseEvent = this::onUseEvent;
/* 21 */     setMaxCooldown(10.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 26 */     List<AnimalEntity> nearby = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { AnimalEntity.class });
/*    */     
/* 28 */     if (nearby.size() <= 0) {
/* 29 */       return true;
/*    */     }
/* 31 */     if (nearby.size() > 0 && !player.world.isRemote) {
/*    */       
/* 33 */       int rainWeather = player.world.getWorldInfo().getRainTime();
/* 34 */       int clearWeather = player.world.getWorldInfo().getClearWeatherTime();
/*    */       
/* 36 */       if (clearWeather == 0) {
/* 37 */         player.sendMessage((ITextComponent)new StringTextComponent("Next rain will happen in " + (player.world.getWorldInfo().getRainTime() / 1200) + " minutes"));
/* 38 */       } else if (rainWeather == 0) {
/* 39 */         player.sendMessage((ITextComponent)new StringTextComponent("Next clear weather will happen in " + (player.world.getWorldInfo().getThunderTime() / 1200) + " minutes"));
/*    */       } 
/*    */     } 
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\ForewarnAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */