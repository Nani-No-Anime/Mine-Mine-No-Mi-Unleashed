/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class SkypieanTraderEntity
/*    */   extends TraderEntity {
/* 19 */   private static final String[] DEFAULT_TEXTURES = new String[] { "skypiean_trader1", "skypiean_trader2" };
/* 20 */   private int dirtTradesLeft = 120;
/*    */ 
/*    */   
/*    */   public SkypieanTraderEntity(World world) {
/* 24 */     super(ModEntities.SKYPIEAN_TRADER, world, DEFAULT_TEXTURES);
/* 25 */     setCanBuyFromPlayers();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canTrade(PlayerEntity player) {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTradeTable() {
/* 37 */     return ModLootTables.SKYPIEAN_TRADER;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeAdditional(CompoundNBT nbt) {
/* 43 */     super.writeAdditional(nbt);
/* 44 */     nbt.putInt("tradesLeft", this.dirtTradesLeft);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readAdditional(CompoundNBT nbt) {
/* 50 */     super.readAdditional(nbt);
/* 51 */     this.dirtTradesLeft = nbt.getInt("tradesLeft");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean processInteract(PlayerEntity player, Hand hand) {
/* 57 */     if (!player.world.isRemote) {
/*    */       
/* 59 */       WyNetwork.sendTo(new SOpenTraderScreenPacket(getEntityId()), player);
/* 60 */       WyNetwork.sendTo(new SUpdateTraderOffersPacket(getEntityId(), this.tradeEntries, this.dirtTradesLeft), player);
/* 61 */       return true;
/*    */     } 
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeTradesLeft(int value) {
/* 68 */     if (this.dirtTradesLeft - value > 0) {
/* 69 */       this.dirtTradesLeft -= value;
/*    */     } else {
/* 71 */       this.dirtTradesLeft = 0;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setTradesLeft(int value) {
/* 76 */     this.dirtTradesLeft = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTradesLeft() {
/* 81 */     return this.dirtTradesLeft;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getExtolLeftInStock() {
/* 86 */     return CurrencyHelper.getExtolFromBelly(this.dirtTradesLeft);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTradeFailMessage() {
/* 92 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency getCurrency() {
/* 98 */     return Currency.EXTOL;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\SkypieanTraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */