/*    */ package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class BiganAbility extends PunchAbility implements IFormRequiredAbility {
/* 14 */   public static final BiganAbility INSTANCE = new BiganAbility();
/*    */ 
/*    */   
/*    */   public BiganAbility() {
/* 18 */     super("Bigan", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Hits using the hardened giraffe nose");
/* 20 */     setMaxCooldown(5.0D);
/*    */     
/* 22 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 27 */     return 25.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 33 */     return new ZoanInfo[] { (ZoanInfo)GiraffeHeavyZoanInfo.INSTANCE, (ZoanInfo)GiraffeWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilitie\\ushigiraffe\BiganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */