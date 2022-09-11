package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.functions.ILootFunction;


public class FakeWeaponFunction
  extends LootFunction
{
  protected FakeWeaponFunction(ILootCondition[] conditionsIn) {
    super(conditionsIn);
  }


  
  protected ItemStack doApply(ItemStack stack, LootContext context) {
    stack.getOrCreateTag().putBoolean("isClone", true);
    return stack;
  }

  
  public static LootFunction.Builder<?> builder() {
    return builder(condition -> new FakeWeaponFunction(condition));
  }



  
  public static class Serializer
    extends LootFunction.Serializer<FakeWeaponFunction>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi:fake_weapon"), FakeWeaponFunction.class);
    }


    
    public void serialize(JsonObject object, FakeWeaponFunction functionClazz, JsonSerializationContext serializationContext) {
      super.serialize(object, functionClazz, serializationContext);
    }


    
    public FakeWeaponFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn) {
      return new FakeWeaponFunction(conditionsIn);
    }
  }
}


