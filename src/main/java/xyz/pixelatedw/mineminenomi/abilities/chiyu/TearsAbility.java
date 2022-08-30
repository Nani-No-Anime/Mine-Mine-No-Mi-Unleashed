/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.HurtPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ 
/*    */ public class TearsAbility extends HurtPassiveAbility {
/* 14 */   public static final TearsAbility INSTANCE = new TearsAbility();
/*    */ 
/*    */   
/*    */   public TearsAbility() {
/* 18 */     super("Tears", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Each time the user is hurt their Watering Can gets filled with tears, which can be drank to heal themselves");
/* 20 */     hideInGUI(false);
/*    */     
/* 22 */     this.onHurtEvent = this::onHurtEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onHurtEvent(LivingEntity entity, Entity source) {
/* 27 */     if (!(entity instanceof PlayerEntity)) {
/* 28 */       return true;
/*    */     }
/* 30 */     PlayerEntity attacked = (PlayerEntity)entity;
/*    */     
/* 32 */     ItemStack waterCan = null;
/* 33 */     for (int i = 0; i < attacked.inventory.mainInventory.size(); i++) {
/*    */       
/* 35 */       if (attacked.inventory.mainInventory.get(i) != null && ((ItemStack)attacked.inventory.mainInventory.get(i)).getItem() == ModItems.WATERING_CAN) {
/*    */         
/* 37 */         waterCan = (ItemStack)attacked.inventory.mainInventory.get(i);
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 42 */     if (waterCan == null) {
/* 43 */       return true;
/*    */     }
/* 45 */     if (!waterCan.hasTag()) {
/* 46 */       waterCan.setTag(new CompoundNBT());
/*    */     }
/* 48 */     int bonusTears = (int)(getAmount() / 3.0F);
/* 49 */     if (bonusTears <= 0) {
/* 50 */       bonusTears = 1;
/*    */     }
/* 52 */     int tears = waterCan.getTag().getInt("tears");
/* 53 */     waterCan.getTag().putInt("tears", tears + bonusTears);
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\TearsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */