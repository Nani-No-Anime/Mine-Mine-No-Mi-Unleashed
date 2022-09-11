package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SetFruitClueFunction
  extends LootFunction
{
  protected SetFruitClueFunction(ILootCondition[] conditionsIn) {
    super(conditionsIn);
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    AkumaNoMiItem fruit = (AkumaNoMiItem)WyHelper.shuffle(new ArrayList(ModValues.devilfruits)).stream().findFirst().orElse(null);
    if (fruit != null) {
      
      DFEncyclopediaEntry template = fruit.getRandomElements((World)context.getWorld());
      
      Optional<Integer> shape = Optional.empty();
      Optional<Color> baseColor = Optional.empty();
      Optional<Color> stemColor = Optional.empty();
      
      int rolls = context.getRandom().nextInt(2);
      
      for (int i = 0; i < rolls; i++) {
        
        int rand = context.getRandom().nextInt(3);
        if (rand == 0) {
          shape = template.getShape();
        } else if (rand == 1) {
          baseColor = template.getBaseColor();
        } else {
          stemColor = template.getStemColor();
        } 
      } 
      String key = DevilFruitHelper.getDevilFruitKey(fruit);
      CompoundNBT nbt = stack.getOrCreateChildTag("fruitClues");
      nbt.putString("key", key);
      if (shape.isPresent())
        nbt.putInt("shape", ((Integer)shape.get()).intValue()); 
      if (baseColor.isPresent())
        nbt.putInt("baseColor", ((Color)baseColor.get()).getRGB()); 
      if (stemColor.isPresent())
        nbt.putInt("stemColor", ((Color)stemColor.get()).getRGB()); 
      stack.setDisplayName((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_FRUIT_CLUE, new Object[0]));
      stack.setCount(1);
    } 
    
    return stack;
  }
  
  public static class Serializer
    extends LootFunction.Serializer<SetFruitClueFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:fruit_clue"), SetFruitClueFunction.class);
    }


    
    public void serialize(JsonObject object, SetFruitClueFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
    }


    
    public SetFruitClueFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      return new SetFruitClueFunction(conditionsIn);
    }
  }
}


