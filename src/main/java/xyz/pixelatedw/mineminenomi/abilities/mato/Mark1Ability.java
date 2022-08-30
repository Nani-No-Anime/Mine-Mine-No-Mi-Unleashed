/*    */ package xyz.pixelatedw.mineminenomi.abilities.mato;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class Mark1Ability extends PunchAbility {
/* 12 */   public static final Ability INSTANCE = (Ability)new Mark1Ability();
/*    */ 
/*    */   
/*    */   public Mark1Ability() {
/* 16 */     super("Mark1", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("by touching a mob, it locks the mob as it's target until another one is touched");
/* 18 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 24 */     String targetname = target.getCommandSource().getName();
/* 25 */     player.sendStatusMessage((ITextComponent)new StringTextComponent("Right hand locked to §c" + targetname), true);
/*    */     
/* 27 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mato\Mark1Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */