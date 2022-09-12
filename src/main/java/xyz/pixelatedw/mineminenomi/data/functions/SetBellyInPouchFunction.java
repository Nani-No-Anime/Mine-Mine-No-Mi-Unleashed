package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.IRandomRange;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.RandomRanges;
import net.minecraft.world.storage.loot.conditions.ILootCondition;


public class SetBellyInPouchFunction
  extends LootFunction
{
  private IRandomRange range;
  
  protected SetBellyInPouchFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
    super(conditionsIn);
    this.range = rang;
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    stack.getOrCreateTag().putInt("belly", this.range.generateInt(context.getRandom()));
    return stack;
  }

  
  public static LootFunction.Builder<?> builder(IRandomRange range) {
    return builder(condition -> new SetBellyInPouchFunction(condition, range));
  }



  
  public static class Serializer
    extends LootFunction.Serializer<SetBellyInPouchFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:set_belly_in_pouch"), SetBellyInPouchFunction.class);
    }


    
    public void serialize(JsonObject object, SetBellyInPouchFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
      object.add("belly_range", RandomRanges.serialize(functionClazz.range, serializationContext));
    }


    
    public SetBellyInPouchFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      IRandomRange range = RandomRanges.deserialize(object.get("belly_range"), deserializationContext);
      return new SetBellyInPouchFunction(conditionsIn, range);
    }
  }
}


