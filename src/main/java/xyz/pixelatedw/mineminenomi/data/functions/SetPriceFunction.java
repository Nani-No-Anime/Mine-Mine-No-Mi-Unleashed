/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.storage.loot.IRandomRange;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraft.world.storage.loot.LootFunction;
/*    */ import net.minecraft.world.storage.loot.RandomRanges;
/*    */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*    */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class SetPriceFunction
/*    */   extends LootFunction
/*    */ {
/*    */   private IRandomRange range;
/*    */   
/*    */   protected SetPriceFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
/* 23 */     super(conditionsIn);
/* 24 */     this.range = rang;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 30 */     stack.getOrCreateTag().putInt("price", WyHelper.round(this.range.generateInt(context.getRandom())));
/* 31 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange range) {
/* 36 */     return builder(condition -> new SetPriceFunction(condition, range));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetPriceFunction>
/*    */   {
/*    */     public Serializer() {
/* 46 */       super(new ResourceLocation("mineminenomi:set_price"), SetPriceFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, SetPriceFunction functionClazz, JsonSerializationContext serializationContext) {
/* 52 */       super.serialize(object, functionClazz, serializationContext);
/* 53 */       object.add("price_range", RandomRanges.serialize(functionClazz.range, serializationContext));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetPriceFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 59 */       IRandomRange range = RandomRanges.deserialize(object.get("price_range"), deserializationContext);
/* 60 */       return new SetPriceFunction(conditionsIn, range);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetPriceFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */