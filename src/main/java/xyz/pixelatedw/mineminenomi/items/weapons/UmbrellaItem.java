/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.IItemPropertyGetter;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UmbrellaItem
/*    */   extends Item
/*    */ {
/*    */   private IItemPropertyGetter openProperty;
/*    */   
/*    */   public UmbrellaItem() {
/* 23 */     super((new Item.Properties()).group(ModCreativeTabs.WEAPONS).maxStackSize(1).defaultMaxDamage(500)); this.openProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
/* 24 */           return 0.0F;  boolean mainHandFlag = (livingEntity.getHeldItemMainhand() == itemStack); boolean offHandFlag = (livingEntity.getHeldItemOffhand() == itemStack); boolean isInAir = (!livingEntity.onGround && (livingEntity.getMotion()).y < 0.0D); return ((mainHandFlag || offHandFlag) && isInAir) ? 1.0F : 0.0F; }); addPropertyOverride(new ResourceLocation("open"), this.openProperty);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\UmbrellaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */