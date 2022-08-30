/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.IItemPropertyGetter;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BellyPouchItem
/*    */   extends Item
/*    */ {
/*    */   private IItemPropertyGetter sizeProperty;
/*    */   
/*    */   public BellyPouchItem() {
/* 50 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1)); this.sizeProperty = ((itemStack, world, livingEntity) -> { if (livingEntity == null)
/* 51 */           return 0.0F;  long belly = itemStack.getOrCreateTag().getLong("belly"); int size = 0; if (belly > 100L && belly <= 500L) { size = 1; } else if (belly > 500L) { size = 2; }  return size; }); addPropertyOverride(new ResourceLocation("size"), this.sizeProperty);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void inventoryTick(ItemStack itemStack, World world, Entity entity, int par4, boolean par5) {
/* 57 */     if (!world.isRemote)
/*    */     {
/* 59 */       if (!itemStack.hasTag())
/*    */       {
/* 61 */         itemStack.getOrCreateTag().putLong("belly", (int)WyHelper.randomWithRange(5, 100));
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 69 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 71 */     if (!world.isRemote) {
/*    */       
/* 73 */       long amount = player.getHeldItemMainhand().getOrCreateTag().getLong("belly");
/*    */       
/* 75 */       if (props.getBelly() <= 999999999L - amount) {
/*    */         
/* 77 */         props.alterBelly(amount);
/* 78 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_POUCH_BELLY_GAINED, new Object[] { Long.valueOf(amount) }));
/* 79 */         player.inventory.deleteStack(player.getHeldItemMainhand());
/*    */       } else {
/*    */         
/* 82 */         props.setBelly(999999999L);
/*    */       } 
/* 84 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/*    */     } 
/*    */     
/* 87 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
/* 93 */     if (itemStack.hasTag())
/*    */     {
/* 95 */       list.add(new StringTextComponent("Belly: " + itemStack.getOrCreateTag().getLong("belly")));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\BellyPouchItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */