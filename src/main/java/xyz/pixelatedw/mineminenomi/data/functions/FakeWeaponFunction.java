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
/*    */ public class FakeWeaponFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected FakeWeaponFunction(ILootCondition[] conditionsIn) {
/* 18 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 24 */     stack.getOrCreateTag().putBoolean("isClone", true);
/* 25 */     return stack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static LootFunction.Builder<?> builder() {
/* 30 */     return builder(condition -> new FakeWeaponFunction(condition));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<FakeWeaponFunction>
/*    */   {
/*    */     public Serializer() {
/* 40 */       super(new ResourceLocation("mineminenomi:fake_weapon"), FakeWeaponFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, FakeWeaponFunction functionClazz, JsonSerializationContext serializationContext) {
/* 46 */       super.serialize(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public FakeWeaponFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 52 */       return new FakeWeaponFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\FakeWeaponFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */