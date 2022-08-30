/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.WaxCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class DoruDoruNoYakataAbility extends Ability {
/* 15 */   public static final DoruDoruNoYakataAbility INSTANCE = new DoruDoruNoYakataAbility();
/*    */ 
/*    */   
/*    */   public DoruDoruNoYakataAbility() {
/* 19 */     super("Doru Doru no Yakata", AbilityHelper.getDevilFruitCategory());
/*    */     
/* 21 */     setMaxCooldown(40.0D);
/* 22 */     setDescription("The user creates a few wax copies of themselves (Use the Color Palette for color on the clones)");
/*    */     
/* 24 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 29 */     for (int i = 0; i < 7; i++) {
/*    */       
/* 31 */       int offsetX = (int)WyHelper.randomWithRange(-2, 2);
/* 32 */       int offsetZ = (int)WyHelper.randomWithRange(-2, 2);
/* 33 */       WaxCloneEntity clone = new WaxCloneEntity(player.world);
/* 34 */       clone.setPositionAndRotation(player.getPosX() + offsetX, player.getPosY(), player.getPosZ() + offsetZ, 180.0F, 0.0F);
/* 35 */       clone.setOwner(player.getUniqueID());
/* 36 */       if (player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
/*    */         
/* 38 */         clone.setTextured();
/* 39 */         for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/*    */           
/* 41 */           ItemStack stack = player.getItemStackFromSlot(slot);
/* 42 */           clone.setItemStackToSlot(slot, stack);
/*    */         } 
/*    */       } 
/* 45 */       player.world.addEntity((Entity)clone);
/*    */     } 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruNoYakataAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */