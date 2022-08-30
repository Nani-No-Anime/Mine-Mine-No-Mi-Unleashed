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
/*    */ 
/*    */ 
/*    */ public class SetBellyInPouchFunction
/*    */   extends LootFunction
/*    */ {
/*    */   private IRandomRange range;
/*    */   
/*    */   protected SetBellyInPouchFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
/* 22 */     super(conditionsIn);
/* 23 */     this.range = rang;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 29 */     stack.getOrCreateTag().putInt("belly", this.range.generateInt(context.getRandom()));
/* 30 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder(IRandomRange range) {
/* 35 */     return builder(condition -> new SetBellyInPouchFunction(condition, range));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetBellyInPouchFunction>
/*    */   {
/*    */     public Serializer() {
/* 45 */       super(new ResourceLocation("mineminenomi:set_belly_in_pouch"), SetBellyInPouchFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, SetBellyInPouchFunction functionClazz, JsonSerializationContext serializationContext) {
/* 51 */       super.serialize(object, functionClazz, serializationContext);
/* 52 */       object.add("belly_range", RandomRanges.serialize(functionClazz.range, serializationContext));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetBellyInPouchFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 58 */       IRandomRange range = RandomRanges.deserialize(object.get("belly_range"), deserializationContext);
/* 59 */       return new SetBellyInPouchFunction(conditionsIn, range);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetBellyInPouchFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */