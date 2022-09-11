package xyz.pixelatedw.mineminenomi.data.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;


public class RandomizedFruitsCondition
  implements ILootCondition
{
  private static final RandomizedFruitsCondition INSTANCE = new RandomizedFruitsCondition();

  
  public static ILootCondition.IBuilder builder() {
    return () -> INSTANCE;
  }


  
  public boolean test(LootContext context) {
    return CommonConfig.INSTANCE.getRandomizedFruits();
  }
  
  public static class Serializer
    extends ILootCondition.AbstractSerializer<RandomizedFruitsCondition>
  {
    public Serializer() {
      super(new ResourceLocation("mineminenomi", "randomized_fruits"), RandomizedFruitsCondition.class);
    }



    
    public void serialize(JsonObject json, RandomizedFruitsCondition value, JsonSerializationContext context) {}


    
    public RandomizedFruitsCondition deserialize(JsonObject json, JsonDeserializationContext context) {
      return RandomizedFruitsCondition.INSTANCE;
    }
  }
}


