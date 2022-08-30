/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraft.world.storage.loot.LootFunction;
/*    */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*    */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ public class EncyclopediaCompletionFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected EncyclopediaCompletionFunction(ILootCondition[] conditionsIn) {
/* 28 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 34 */     int attempts = context.getRandom().nextInt(5) + 1;
/* 35 */     for (int i = 0; i < attempts; i++) {
/*    */       
/* 37 */       AkumaNoMiItem fruit = (AkumaNoMiItem)WyHelper.shuffle(new ArrayList(ModValues.devilfruits)).stream().findFirst().orElse(null);
/* 38 */       if (fruit != null) {
/*    */ 
/*    */         
/* 41 */         DFEncyclopediaEntry template = fruit.getRandomElements((World)context.getWorld());
/*    */         
/* 43 */         Optional<Integer> shape = Optional.empty();
/* 44 */         Optional<Color> baseColor = Optional.empty();
/* 45 */         Optional<Color> stemColor = Optional.empty();
/*    */         
/* 47 */         int rolls = context.getRandom().nextInt(2);
/* 48 */         if (context.getRandom().nextInt(128) == 0)
/* 49 */           rolls = 3; 
/* 50 */         if (rolls < 3) {
/*    */ 
/*    */           
/* 53 */           for (int j = 0; j < rolls; j++) {
/*    */             
/* 55 */             int rand = context.getRandom().nextInt(3);
/* 56 */             if (rand == 0) {
/* 57 */               shape = template.getShape();
/* 58 */             } else if (rand == 1) {
/* 59 */               baseColor = template.getBaseColor();
/*    */             } else {
/* 61 */               stemColor = template.getStemColor();
/*    */             } 
/*    */           } 
/*    */         } else {
/*    */           
/* 66 */           shape = template.getShape();
/* 67 */           baseColor = template.getBaseColor();
/* 68 */           stemColor = template.getStemColor();
/*    */         } 
/*    */         
/* 71 */         if (shape.isPresent() || baseColor.isPresent() || stemColor.isPresent()) {
/*    */ 
/*    */           
/* 74 */           String key = DevilFruitHelper.getDevilFruitKey(fruit);
/* 75 */           DFEncyclopediaEntry clue = new DFEncyclopediaEntry(shape, baseColor, stemColor);
/* 76 */           DFEncyclopediaItem.addFruitClues(stack, key, clue);
/* 77 */           stack.setCount(1);
/*    */         } 
/*    */       } 
/* 80 */     }  return stack;
/*    */   }
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<EncyclopediaCompletionFunction>
/*    */   {
/*    */     public Serializer() {
/* 87 */       super(new ResourceLocation("mineminenomi:encyclopedia_completion"), EncyclopediaCompletionFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, EncyclopediaCompletionFunction functionClazz, JsonSerializationContext serializationContext) {
/* 93 */       super.serialize(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public EncyclopediaCompletionFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 99 */       return new EncyclopediaCompletionFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\EncyclopediaCompletionFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */