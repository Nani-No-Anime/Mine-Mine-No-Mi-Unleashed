package xyz.pixelatedw.mineminenomi.datagen;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

import java.util.function.Consumer;

public class ModRecipesDataGen
  extends RecipeProvider
{
  public ModRecipesDataGen(DataGenerator generator) {
    super(generator);
  }



  
  public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.BULLET)
      .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
      .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
      .patternLine("ic ")
      .patternLine("ci ")
      .patternLine("   ")
      .setGroup("mineminenomi")
      .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.CANNON_BALL)
      .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
      .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
      .patternLine("ccc")
      .patternLine("cic")
      .patternLine("ccc")
      .setGroup("mineminenomi")
      .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.CANNON)
      .key(Character.valueOf('s'), (IItemProvider)Items.STRING)
      .key(Character.valueOf('i'), (IItemProvider)Blocks.IRON_BLOCK)
      .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
      .patternLine("s  ")
      .patternLine("iii")
      .patternLine("ccc")
      .setGroup("mineminenomi")
      .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.KEY)
      .key(Character.valueOf('g'), (IItemProvider)Items.GOLD_INGOT)
      .patternLine(" g ")
      .patternLine(" g ")
      .patternLine(" g ")
      .setGroup("mineminenomi")
      .addCriterion("has_gold_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.SAKE_CUP)
      .key(Character.valueOf('c'), (IItemProvider)Items.CLAY_BALL)
      .patternLine("   ")
      .patternLine("c c")
      .patternLine(" c ")
      .setGroup("mineminenomi")
      .addCriterion("has_clay_ball", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.CLAY_BALL
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.VIVRE_CARD)
      .key(Character.valueOf('p'), (IItemProvider)Items.PAPER)
      .patternLine("   ")
      .patternLine(" p ")
      .patternLine("   ")
      .setGroup("mineminenomi")
      .addCriterion("has_paper", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.PAPER
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModArmors.MEDIC_BAG)
      .key(Character.valueOf('s'), (IItemProvider)Items.STRING)
      .key(Character.valueOf('l'), (IItemProvider)Items.LEATHER)
      .key(Character.valueOf('b'), (IItemProvider)Items.LAPIS_LAZULI)
      .patternLine("s s")
      .patternLine("lbl")
      .patternLine("lll")
      .setGroup("mineminenomi")
      .addCriterion("has_leather", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LEATHER
          })).addCriterion("has_lapis_lazuli", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI
          })).build(consumer);

    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.MACE)
      .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
      .key(Character.valueOf('i'), (IItemProvider)Blocks.IRON_BLOCK)
      .patternLine(" i ")
      .patternLine(" i ")
      .patternLine(" s ")
      .setGroup("mineminenomi")
      .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.CLIMA_TACT)
      .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
      .key(Character.valueOf('b'), (IItemProvider)Items.LAPIS_LAZULI)
      .patternLine("bsb")
      .patternLine("bsb")
      .patternLine("bsb")
      .setGroup("mineminenomi")
      .addCriterion("has_lapis_lazuli", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT)
      .key(Character.valueOf('c'), (IItemProvider)ModWeapons.CLIMA_TACT)
      .key(Character.valueOf('b'), (IItemProvider)ModBlocks.BREATH_DIAL)
      .key(Character.valueOf('f'), (IItemProvider)ModBlocks.FLAME_DIAL)
      .key(Character.valueOf('e'), (IItemProvider)ModBlocks.EISEN_DIAL)
      .key(Character.valueOf('l'), (IItemProvider)ModBlocks.FLASH_DIAL)
      .key(Character.valueOf('m'), (IItemProvider)ModBlocks.MILKY_DIAL)
      .patternLine("eme")
      .patternLine("bcf")
      .patternLine("ele")
      .setGroup("mineminenomi")
      .addCriterion("has_clima_tact", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModWeapons.CLIMA_TACT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.SCISSORS)
      .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
      .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
      .patternLine(" ii")
      .patternLine("cii")
      .patternLine("cc ")
      .setGroup("mineminenomi")
      .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.UMBRELLA)
      .key(Character.valueOf('w'), (IItemProvider)Blocks.ORANGE_WOOL)
      .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
      .patternLine("www")
      .patternLine(" s ")
      .patternLine(" s ")
      .setGroup("mineminenomi")
      .addCriterion("has_sticks", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.STICK
          })).build(consumer);

    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.COLA)
      .key(Character.valueOf('s'), (IItemProvider)Items.SUGAR)
      .key(Character.valueOf('b'), (IItemProvider)Items.GLASS_BOTTLE)
      .patternLine(" s ")
      .patternLine(" s ")
      .patternLine(" b ")
      .setGroup("mineminenomi")
      .addCriterion("has_sugar", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.SUGAR
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.ULTRA_COLA)
      .key(Character.valueOf('s'), (IItemProvider)Items.SUGAR)
      .key(Character.valueOf('c'), (IItemProvider)ModItems.COLA)
      .patternLine("sss")
      .patternLine("sss")
      .patternLine("scs")
      .setGroup("mineminenomi")
      .addCriterion("has_cola", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.COLA
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModArmors.COLA_BACKPACK)
      .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
      .key(Character.valueOf('u'), (IItemProvider)ModItems.ULTRA_COLA)
      .patternLine("u u")
      .patternLine("uiu")
      .patternLine("u u")
      .setGroup("mineminenomi")
      .addCriterion("has_ultra_cola", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.ULTRA_COLA
          })).build(consumer);

    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.DENSE_KAIROSEKI)
      .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
      .patternLine("kk ")
      .patternLine("kk ")
      .patternLine("   ")
      .setGroup("mineminenomi")
      .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
          })).build(consumer);
    
    ShapelessRecipeBuilder.shapelessRecipe((IItemProvider)ModItems.KAIROSEKI)
      .addIngredient((IItemProvider)ModBlocks.KAIROSEKI)
      .setGroup("mineminenomi")
      .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
          })).build(consumer);

    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.KAIROSEKI_BARS)
      .key(Character.valueOf('k'), (IItemProvider)ModItems.DENSE_KAIROSEKI)
      .patternLine("kkk")
      .patternLine("kkk")
      .patternLine("   ")
      .setGroup("mineminenomi")
      .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.KAIROSEKI)
      .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
      .patternLine("kkk")
      .patternLine("kkk")
      .patternLine("kkk")
      .setGroup("mineminenomi")
      .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
          })).build(consumer);
    
    ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.KAIROSEKI_BULLET)
      .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
      .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
      .patternLine("kc ")
      .patternLine("ck ")
      .patternLine("   ")
      .setGroup("mineminenomi")
      .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
          })).build(consumer);
  }
}


