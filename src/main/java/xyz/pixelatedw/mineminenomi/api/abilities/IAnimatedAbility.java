/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import xyz.pixelatedw.mineminenomi.api.animations.IAnimation;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public interface IAnimatedAbility
/*    */ {
/*    */   
/*    */   public static final Predicate<Ability> IS_ACTIVE = (ability -> !(ability instanceof IAnimatedAbility) ? false : ((IAnimatedAbility)ability).isAnimationActive());
/*    */   
/*    */   boolean isAnimationActive();
/*    */   
/*    */   IAnimation getAnimation();
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IAnimatedAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */