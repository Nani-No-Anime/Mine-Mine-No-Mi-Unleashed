/*    */ package xyz.pixelatedw.mineminenomi.data.functions;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.storage.loot.LootContext;
/*    */ import net.minecraft.world.storage.loot.LootFunction;
/*    */ import net.minecraft.world.storage.loot.conditions.ILootCondition;
/*    */ import net.minecraft.world.storage.loot.functions.ILootFunction;
/*    */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class SetFruitClueFunction
/*    */   extends LootFunction
/*    */ {
/*    */   protected SetFruitClueFunction(ILootCondition[] conditionsIn) {
/* 30 */     super(conditionsIn);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack doApply(ItemStack stack, LootContext context) {
/* 36 */     AkumaNoMiItem fruit = (AkumaNoMiItem)WyHelper.shuffle(new ArrayList(ModValues.devilfruits)).stream().findFirst().orElse(null);
/* 37 */     if (fruit != null) {
/*    */       
/* 39 */       DFEncyclopediaEntry template = fruit.getRandomElements((World)context.getWorld());
/*    */       
/* 41 */       Optional<Integer> shape = Optional.empty();
/* 42 */       Optional<Color> baseColor = Optional.empty();
/* 43 */       Optional<Color> stemColor = Optional.empty();
/*    */       
/* 45 */       int rolls = context.getRandom().nextInt(2);
/*    */       
/* 47 */       for (int i = 0; i < rolls; i++) {
/*    */         
/* 49 */         int rand = context.getRandom().nextInt(3);
/* 50 */         if (rand == 0) {
/* 51 */           shape = template.getShape();
/* 52 */         } else if (rand == 1) {
/* 53 */           baseColor = template.getBaseColor();
/*    */         } else {
/* 55 */           stemColor = template.getStemColor();
/*    */         } 
/*    */       } 
/* 58 */       String key = DevilFruitHelper.getDevilFruitKey(fruit);
/* 59 */       CompoundNBT nbt = stack.getOrCreateChildTag("fruitClues");
/* 60 */       nbt.putString("key", key);
/* 61 */       if (shape.isPresent())
/* 62 */         nbt.putInt("shape", ((Integer)shape.get()).intValue()); 
/* 63 */       if (baseColor.isPresent())
/* 64 */         nbt.putInt("baseColor", ((Color)baseColor.get()).getRGB()); 
/* 65 */       if (stemColor.isPresent())
/* 66 */         nbt.putInt("stemColor", ((Color)stemColor.get()).getRGB()); 
/* 67 */       stack.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_FRUIT_CLUE, new Object[0]));
/* 68 */       stack.setCount(1);
/*    */     } 
/*    */     
/* 71 */     return stack;
/*    */   }
/*    */   
/*    */   public static class Serializer
/*    */     extends LootFunction.Serializer<SetFruitClueFunction>
/*    */   {
/*    */     public Serializer() {
/* 78 */       super(new ResourceLocation("mineminenomi:fruit_clue"), SetFruitClueFunction.class);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void serialize(JsonObject object, SetFruitClueFunction functionClazz, JsonSerializationContext serializationContext) {
/* 84 */       super.serialize(object, functionClazz, serializationContext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public SetFruitClueFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
/* 90 */       return new SetFruitClueFunction(conditionsIn);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\data\functions\SetFruitClueFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */