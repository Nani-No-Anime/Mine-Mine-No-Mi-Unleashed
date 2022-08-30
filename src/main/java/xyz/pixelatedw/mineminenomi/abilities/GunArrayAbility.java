/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class GunArrayAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 16 */   public static final GunArrayAbility INSTANCE = new GunArrayAbility();
/*    */ 
/*    */   
/*    */   public GunArrayAbility() {
/* 20 */     super("Gun Array", AbilityHelper.getEquipmentCategory());
/* 21 */     setDescription("Automatically fires bullets from the inventory in groups of 4");
/*    */     
/* 23 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 24 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 29 */     return (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == ModArmors.WOOTZ_STEEL_ARMOR);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int passiveTime) {
/* 34 */     if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() != ModArmors.WOOTZ_STEEL_ARMOR) {
/*    */       
/* 36 */       endContinuity(player);
/*    */       
/*    */       return;
/*    */     } 
/* 40 */     if (passiveTime % 60 == 0) {
/*    */       
/* 42 */       ItemStack bulletStack = null;
/* 43 */       for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*    */         
/* 45 */         ItemStack stack = player.inventory.getStackInSlot(i);
/* 46 */         if (stack != null && !stack.isEmpty() && stack.getItem() == ModItems.BULLET && stack.getCount() >= 4) {
/*    */           
/* 48 */           bulletStack = stack;
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/* 53 */       if (bulletStack != null) {
/*    */         
/* 55 */         int projectileSpace = 1;
/*    */         
/* 57 */         int innacuracy = 1;
/* 58 */         if (player.isSprinting()) {
/* 59 */           innacuracy = 3;
/*    */         }
/* 61 */         for (int j = 0; j < 4; j++) {
/*    */           
/* 63 */           NormalBulletProjectile proj = new NormalBulletProjectile(player.world, (LivingEntity)player);
/* 64 */           proj.setLocationAndAngles(player
/* 65 */               .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/* 66 */               .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/* 67 */               .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*    */           
/* 69 */           proj.setDamage(4.0F);
/* 70 */           proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, innacuracy);
/* 71 */           player.world.addEntity((Entity)proj);
/*    */         } 
/* 73 */         bulletStack.shrink(4);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\GunArrayAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */