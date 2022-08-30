/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraft.world.storage.loot.LootFunction;
/*    */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*    */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*    */ 
/*    */ 
/*    */ public class SetInfiniteStockFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected SetInfiniteStockFunction(ILootCondition[] conditionsIn) {
/* 18 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 24 */     stack.getOrCreateTag().putBoolean("isInfinite", true);
/* 25 */     stack.setCount(1);
/* 26 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 31 */     return builder(condition -> new SetInfiniteStockFunction(condition));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetInfiniteStockFunction>
/*    */   {
/*    */     public Serializer() {
/* 41 */       super(new ResourceLocation("mineminenomi:infinite_stock"), SetInfiniteStockFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, SetInfiniteStockFunction functionClazz, JsonSerializationContext serializationContext) {
/* 47 */       super.serialize(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetInfiniteStockFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 53 */       return new SetInfiniteStockFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetInfiniteStockFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */