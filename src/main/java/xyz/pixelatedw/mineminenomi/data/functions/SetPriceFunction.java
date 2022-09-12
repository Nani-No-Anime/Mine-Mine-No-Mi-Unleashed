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
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;


public class SetPriceFunction
  extends LootFunction
{
  private IRandomRange range;
  
  protected SetPriceFunction(ILootCondition[] conditionsIn, IRandomRange rang) {
    super(conditionsIn);
    this.range = rang;
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    stack.getOrCreateTag().putInt("price", WyHelper.round(this.range.generateInt(context.getRandom())));
    return stack;
  }

  
  public static LootFunction.Builder<?> builder(IRandomRange range) {
    return builder(condition -> new SetPriceFunction(condition, range));
  }



  
  public static class Serializer
    extends LootFunction.Serializer<SetPriceFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:set_price"), SetPriceFunction.class);
    }


    
    public void serialize(JsonObject object, SetPriceFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
      object.add("price_range", RandomRanges.serialize(functionClazz.range, serializationContext));
    }


    
    public SetPriceFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      IRandomRange range = RandomRanges.deserialize(object.get("price_range"), deserializationContext);
      return new SetPriceFunction(conditionsIn, range);
    }
  }
}


