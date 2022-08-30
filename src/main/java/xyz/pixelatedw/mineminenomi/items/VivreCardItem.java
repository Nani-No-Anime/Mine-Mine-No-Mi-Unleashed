/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VivreCardItem
/*    */   extends Item
/*    */ {
/*    */   public VivreCardItem() {
/* 25 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDroppedByPlayer(ItemStack itemStack, PlayerEntity player) {
/* 31 */     VivreCardEntity vivreCard = new VivreCardEntity(player.world);
/*    */     
/* 33 */     if (itemStack.getTag() == null) {
/* 34 */       return true;
/*    */     }
/* 36 */     LivingEntity owner = (LivingEntity)((ServerWorld)player.world).getEntityByUuid(UUID.fromString(itemStack.getTag().getString("ownerUUID")));
/*    */     
/* 38 */     if (owner == null)
/* 39 */       return true; 
/* 40 */     vivreCard.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
/*    */     
/* 42 */     float f8 = MathHelper.sin(player.rotationPitch * 0.017453292F);
/* 43 */     float f2 = MathHelper.cos(player.rotationPitch * 0.017453292F);
/* 44 */     float f3 = MathHelper.sin(player.rotationYaw * 0.017453292F);
/* 45 */     float f4 = MathHelper.cos(player.rotationYaw * 0.017453292F);
/* 46 */     float f5 = Item.random.nextFloat() * 6.2831855F;
/* 47 */     float f6 = 0.02F * Item.random.nextFloat();
/* 48 */     vivreCard.setMotion((-f3 * f2 * 0.3F) + Math.cos(f5) * f6, (-f8 * 0.3F + 0.1F + (Item.random.nextFloat() - Item.random.nextFloat()) * 0.1F), (f4 * f2 * 0.3F) + Math.sin(f5) * f6);
/*    */     
/* 50 */     vivreCard.setOwner(owner.getUniqueID());
/*    */     
/* 52 */     player.world.addEntity((Entity)vivreCard);
/*    */     
/* 54 */     itemStack.setCount(0);
/*    */     
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 62 */     if (world.isRemote) {
/*    */       return;
/*    */     }
/* 65 */     if (itemStack.getTag() != null) {
/*    */       
/* 67 */       String uuidString = itemStack.getTag().getString("ownerUUID");
/* 68 */       if (Strings.isNullOrEmpty(uuidString)) {
/*    */         return;
/*    */       }
/* 71 */       LivingEntity owner = (LivingEntity)((ServerWorld)world).getEntityByUuid(UUID.fromString(uuidString));
/*    */       
/* 73 */       if (owner != null && owner.getHealth() <= 0.0F) {
/* 74 */         itemStack.setCount(0);
/*    */       }
/* 76 */     } else if (itemStack.getTag() == null || itemStack.getTag().isEmpty()) {
/*    */       
/* 78 */       setOwner(itemStack, (LivingEntity)entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(ItemStack itemStack, LivingEntity entity) {
/* 84 */     itemStack.getOrCreateTag().putString("ownerUUID", entity.getUniqueID().toString());
/* 85 */     itemStack.getOrCreateTag().putString("ownerUsername", entity.getDisplayName().getFormattedText());
/* 86 */     String itemName = itemStack.getDisplayName().getFormattedText();
/* 87 */     itemStack.setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + entity.getDisplayName().getFormattedText() + "'s " + itemName));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\VivreCardItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */