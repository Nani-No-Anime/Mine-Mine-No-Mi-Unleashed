/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ 
/*    */ public class MarineTraderEntity extends TraderEntity {
/* 17 */   private static final String[] DEFAULT_TEXTURES = new String[] { "marine_trader1", "marine_trader2" };
/*    */ 
/*    */   
/*    */   public MarineTraderEntity(World world) {
/* 21 */     super(ModEntities.MARINE_TRADER, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canTrade(PlayerEntity player) {
/* 27 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 28 */     return (props.isMarine() || props.isBountyHunter());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTradeTable() {
/* 34 */     return ModLootTables.MARINE_TRADER;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTradeFailMessage() {
/* 40 */     return (new TranslationTextComponent(ModI18n.TRADER_NO_PIRATE, new Object[0])).getFormattedText();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency getCurrency() {
/* 46 */     return Currency.BELLY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MarineTraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */