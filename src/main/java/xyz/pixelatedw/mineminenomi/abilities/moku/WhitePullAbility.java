/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class WhitePullAbility
/*    */   extends Ability {
/* 15 */   public static final WhitePullAbility INSTANCE = new WhitePullAbility();
/*    */ 
/*    */   
/*    */   public WhitePullAbility() {
/* 19 */     super("White Pull", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Pulls all nearby entities surrounded by smoke towards the user.");
/* 21 */     setMaxCooldown(10.0D);
/* 22 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onUseEvent(PlayerEntity player) {
/* 27 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 28 */     list.stream().filter(entity -> entity.isPotionActive(ModEffects.SMOKE)).forEach(entity -> {
/*    */           entity.removePotionEffect(ModEffects.SMOKE);
/*    */           
/*    */           entity.setPosition(player.getPosX(), player.getPosY(), player.getPosZ());
/*    */         });
/*    */     
/* 34 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\WhitePullAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */