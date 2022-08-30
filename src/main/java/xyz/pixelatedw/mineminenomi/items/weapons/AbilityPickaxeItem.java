/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.IItemTier;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.PickaxeItem;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class AbilityPickaxeItem extends PickaxeItem {
/* 20 */   private Ability ability = null;
/*    */ 
/*    */   
/*    */   public AbilityPickaxeItem(Ability ability, AbilityItemTier tier, int attackDamage, float attackSpeed) {
/* 24 */     super((IItemTier)tier, attackDamage, attackSpeed, new Item.Properties());
/* 25 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 31 */     super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
/* 32 */     if (entity instanceof PlayerEntity && this.ability != null) {
/*    */       
/* 34 */       PlayerEntity owner = (PlayerEntity)entity;
/* 35 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */       
/* 37 */       boolean deletePicaxe = true;
/*    */       
/* 39 */       for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*    */         
/* 41 */         if (ability != null && ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility && this.ability.equals(ability))
/*    */         {
/*    */           
/* 44 */           if (ability.isContinuous())
/* 45 */             deletePicaxe = false; 
/*    */         }
/*    */       } 
/* 48 */       if (deletePicaxe) {
/* 49 */         owner.inventory.deleteStack(itemStack);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack stack, BlockState state) {
/* 56 */     if (stack.getItem() == ModWeapons.DORU_PICKAXE && state.getBlock() == ModBlocks.WAX)
/*    */     {
/* 58 */       return 999.0F;
/*    */     }
/* 60 */     return super.getDestroySpeed(stack, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEnchantable(ItemStack stack) {
/* 66 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\AbilityPickaxeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */