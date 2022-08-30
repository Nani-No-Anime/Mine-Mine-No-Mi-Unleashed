/*    */ package xyz.pixelatedw.mineminenomi.abilities.moku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SmokeLaunchAbility
/*    */   extends Ability {
/* 17 */   public static final SmokeLaunchAbility INSTANCE = new SmokeLaunchAbility();
/*    */ 
/*    */   
/*    */   public SmokeLaunchAbility() {
/* 21 */     super("Smoke Launch", AbilityHelper.getDevilFruitCategory());
/* 22 */     setDescription("Launches all nearby entities surrounded by smoke in the sky.");
/* 23 */     setMaxCooldown(15.0D);
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onUseEvent(PlayerEntity player) {
/* 29 */     List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 30 */     list.stream().filter(entity -> entity.isPotionActive(ModEffects.SMOKE)).forEach(entity -> {
/*    */           entity.removePotionEffect(ModEffects.SMOKE);
/*    */           
/*    */           entity.addPotionEffect(new EffectInstance(Effects.LEVITATION, 200, 1));
/*    */         });
/*    */     
/* 36 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\moku\SmokeLaunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */