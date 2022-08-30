/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public abstract class TempoAbility
/*    */   extends Ability
/*    */ {
/*    */   protected ICanUse canUseCheck = (player, check) -> false;
/*    */   
/*    */   public TempoAbility(String name, APIConfig.AbilityCategory category) {
/* 15 */     super(name, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseTempo(PlayerEntity player, ICanUse check) {
/* 20 */     return (check != null) ? check.canUse(player, check) : this.canUseCheck.canUse(player, check);
/*    */   }
/*    */   
/*    */   public static interface ICanUse extends Serializable {
/*    */     boolean canUse(PlayerEntity param1PlayerEntity, ICanUse param1ICanUse);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\TempoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */