/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenCharacterCreatorScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CharacterCreatorItem
/*    */   extends Item
/*    */ {
/*    */   public CharacterCreatorItem() {
/* 21 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 27 */     if (!world.isRemote) {
/*    */       
/* 29 */       boolean hasRandomizedRace = CommonConfig.INSTANCE.getRaceRandomizer();
/* 30 */       WyNetwork.sendTo(new SOpenCharacterCreatorScreenPacket(hasRandomizedRace), player);
/*    */     } 
/* 32 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\CharacterCreatorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */