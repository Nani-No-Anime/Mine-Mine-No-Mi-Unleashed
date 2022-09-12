package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.items.DFEncyclopediaItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class EncyclopediaCompletionFunction
  extends LootFunction
{
  protected EncyclopediaCompletionFunction(ILootCondition[] conditionsIn) {
    super(conditionsIn);
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    int attempts = context.getRandom().nextInt(5) + 1;
    for (int i = 0; i < attempts; i++) {
      
      AkumaNoMiItem fruit = (AkumaNoMiItem)WyHelper.shuffle(new ArrayList(ModValues.devilfruits)).stream().findFirst().orElse(null);
      if (fruit != null) {

        
        DFEncyclopediaEntry template = fruit.getRandomElements((World)context.getWorld());
        
        Optional<Integer> shape = Optional.empty();
        Optional<Color> baseColor = Optional.empty();
        Optional<Color> stemColor = Optional.empty();
        
        int rolls = context.getRandom().nextInt(2);
        if (context.getRandom().nextInt(128) == 0)
          rolls = 3; 
        if (rolls < 3) {

          
          for (int j = 0; j < rolls; j++) {
            
            int rand = context.getRandom().nextInt(3);
            if (rand == 0) {
              shape = template.getShape();
            } else if (rand == 1) {
              baseColor = template.getBaseColor();
            } else {
              stemColor = template.getStemColor();
            } 
          } 
        } else {
          
          shape = template.getShape();
          baseColor = template.getBaseColor();
          stemColor = template.getStemColor();
        } 
        
        if (shape.isPresent() || baseColor.isPresent() || stemColor.isPresent()) {

          
          String key = DevilFruitHelper.getDevilFruitKey(fruit);
          DFEncyclopediaEntry clue = new DFEncyclopediaEntry(shape, baseColor, stemColor);
          DFEncyclopediaItem.addFruitClues(stack, key, clue);
          stack.setCount(1);
        } 
      } 
    }  return stack;
  }
  
  public static class Serializer
    extends LootFunction.Serializer<EncyclopediaCompletionFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:encyclopedia_completion"), EncyclopediaCompletionFunction.class);
    }


    
    public void serialize(JsonObject object, EncyclopediaCompletionFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
    }


    
    public EncyclopediaCompletionFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      return new EncyclopediaCompletionFunction(conditionsIn);
    }
  }
}


