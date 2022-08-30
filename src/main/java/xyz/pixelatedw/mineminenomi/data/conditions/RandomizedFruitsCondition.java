/*    */ package xyz.pixelatedw.mineminenomi.data.conditions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ 
/*    */ public class RandomizedFruitsCondition
/*    */   implements ILootCondition
/*    */ {
/* 15 */   private static final RandomizedFruitsCondition INSTANCE = new RandomizedFruitsCondition();
/*    */ 
/*    */   
/*    */   public static ILootCondition.IBuilder builder() {
/* 19 */     return () -> INSTANCE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean test(LootContext context) {
/* 25 */     return CommonConfig.INSTANCE.getRandomizedFruits();
/*    */   }
/*    */   
/*    */   public static class Serializer
/*    */     extends ILootCondition.AbstractSerializer<RandomizedFruitsCondition>
/*    */   {
/*    */     public Serializer() {
/* 32 */       super(new ResourceLocation("mineminenomi", "randomized_fruits"), RandomizedFruitsCondition.class);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject json, RandomizedFruitsCondition value, JsonSerializationContext context) {}
/*    */ 
/*    */ 
/*    */     
/*    */     public RandomizedFruitsCondition deserialize(JsonObject json, JsonDeserializationContext context) {
/* 43 */       return RandomizedFruitsCondition.INSTANCE;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\conditions\RandomizedFruitsCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */