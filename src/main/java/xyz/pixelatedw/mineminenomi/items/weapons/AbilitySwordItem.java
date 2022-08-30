/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class AbilitySwordItem extends CoreSwordItem {
/* 16 */   private Ability ability = null;
/*    */ 
/*    */   
/*    */   public AbilitySwordItem(Ability ability, int damage) {
/* 20 */     this(ability, damage, -2.4F);
/* 21 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilitySwordItem(Ability ability, int damage, float attackSpeed) {
/* 26 */     super((new Item.Properties()).maxStackSize(1).defaultMaxDamage(-1), damage, attackSpeed);
/* 27 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 33 */     super.inventoryTick(itemStack, world, entity, itemSlot, isSelected);
/* 34 */     if (entity instanceof PlayerEntity && this.ability != null) {
/*    */       
/* 36 */       PlayerEntity owner = (PlayerEntity)entity;
/* 37 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */       
/* 39 */       boolean deleteSword = true;
/*    */       
/* 41 */       for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*    */         
/* 43 */         if (ability != null && ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility && this.ability.equals(ability))
/*    */         {
/*    */           
/* 46 */           if (ability.isContinuous())
/* 47 */             deleteSword = false; 
/*    */         }
/*    */       } 
/* 50 */       if (deleteSword) {
/* 51 */         owner.inventory.deleteStack(itemStack);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/* 58 */     if (entityItem.isAlive())
/* 59 */       entityItem.remove(); 
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEnchantable(ItemStack stack) {
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\AbilitySwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */