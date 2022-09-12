package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;


public class SetInfiniteStockFunction
  extends LootFunction
{
  protected SetInfiniteStockFunction(ILootCondition[] conditionsIn) {
    super(conditionsIn);
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    stack.getOrCreateTag().putBoolean("isInfinite", true);
    stack.setCount(1);
    return stack;
  }

  
  public static LootFunction.Builder<?> builder() {
    return builder(condition -> new SetInfiniteStockFunction(condition));
  }



  
  public static class Serializer
    extends LootFunction.Serializer<SetInfiniteStockFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:infinite_stock"), SetInfiniteStockFunction.class);
    }


    
    public void serialize(JsonObject object, SetInfiniteStockFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
    }


    
    public SetInfiniteStockFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      return new SetInfiniteStockFunction(conditionsIn);
    }
  }
}


