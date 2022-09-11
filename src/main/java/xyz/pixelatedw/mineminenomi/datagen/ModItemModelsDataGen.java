package xyz.pixelatedw.mineminenomi.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.RegistryObject;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
import xyz.pixelatedw.mineminenomi.wypi.datagen.ItemModelsDataGen;








public class ModItemModelsDataGen
  extends ItemModelsDataGen
{
  public ModItemModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
    super(generator, existingFileHelper);
  }


  
  protected void registerModels() {
    for (RegistryObject<Item> obj : (Iterable<RegistryObject<Item>>)WyRegistry.ITEMS.getEntries()) {
      
      if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem || ((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.SakeCupItem || ((Item)obj
        .get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.BellyPouchItem || ((Item)obj.get()).asItem() instanceof net.minecraft.item.BowItem) {
        continue;
      }
      if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem) {
        spawnEggItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
      }  if (((Item)obj.get()).asItem() instanceof net.minecraft.item.BlockItem) {
        blockItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
      } 
      item(((Item)obj.get()).asItem().getRegistryName().getPath());
    } 

    
    modItem((IItemProvider)ModItems.SAKE_CUP).filledItem().asCup();
    modItem((IItemProvider)ModItems.BELLY_POUCH, "belly_pouch_0").size3Item();

    
    modItem((IItemProvider)ModWeapons.MARINE_SWORD).hakiWeapon().asSmallSword();
    modItem((IItemProvider)ModWeapons.PIRATE_CUTLASS).hakiWeapon().asSmallSword();
    modItem((IItemProvider)ModWeapons.PIPE).hakiWeapon().asMediumPipe();
    modItem((IItemProvider)ModWeapons.SCISSORS).hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.KIKOKU).hakiWeapon().asLongSword();
    modItem((IItemProvider)ModWeapons.KIRIBACHI).hakiWeapon().asLongSword();
    modItem((IItemProvider)ModWeapons.YORU).hakiWeapon().asLongSword();
    modItem((IItemProvider)ModWeapons.MURAKUMOGIRI).hakiWeapon().asLongPipe();
    modItem((IItemProvider)ModWeapons.HOOK).sheathedWeapon().hakiWeapon().asHook();
    modItem((IItemProvider)ModWeapons.JITTE).hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.NONOSAMA_STAFF).hakiWeapon().asMediumPipe();
    modItem((IItemProvider)ModWeapons.NONOSAMA_TRIDENT).hakiWeapon().asMediumPipe();
    modItem((IItemProvider)ModWeapons.HAMMER_5T).hakiWeapon().asSmallSword();
    modItem((IItemProvider)ModWeapons.TONFA).hakiWeapon().asTonfa();
    modItem((IItemProvider)ModWeapons.BANDIT_KNIFE).hakiWeapon().asSmallSword();
    modItem((IItemProvider)ModWeapons.WADO_ICHIMONJI).sheathedWeapon().hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.SANDAI_KITETSU).sheathedWeapon().hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.NIDAI_KITETSU).sheathedWeapon().hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.SHUSUI).sheathedWeapon().hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.SOUL_SOLID).sheathedWeapon().hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.DURANDAL).hakiWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.MACE).hakiWeapon().asLongSword();
    modItem((IItemProvider)ModWeapons.UMBRELLA).openWeapon().asMediumSword();
    modItem((IItemProvider)ModWeapons.CLIMA_TACT).asMediumPipe();
    modItem((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT).asMediumPipe();
    modItem((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT).openWeapon().asMediumPipe();

    
    modItem((IItemProvider)ModWeapons.FLINTLOCK).asPistol();
    modItem((IItemProvider)ModWeapons.SENRIKU).asRifle();
    modItem((IItemProvider)ModWeapons.GINGA_PACHINKO).pullWeapon().asSmallSlingshot();
    modItem((IItemProvider)ModWeapons.KABUTO).pullWeapon().asLongSlingshot();
    modItem((IItemProvider)ModWeapons.BLACK_KABUTO).pullWeapon().asLongSlingshot();
    modItem((IItemProvider)ModWeapons.GREEN_KUJA_BOW).pullWeapon().asBow();

    
    modItem((IItemProvider)ModWeapons.ICE_SABER).asSmallSword();
    modItem((IItemProvider)ModWeapons.AMA_NO_MURAKUMO).asSmallSword();
    modItem((IItemProvider)ModWeapons.NORO_NORO_BEAM_SWORD).asSmallSword();
    modItem((IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN).asSmallSword();
    modItem((IItemProvider)ModWeapons.BLUE_SWORD).asSmallSword();
    modItem((IItemProvider)ModWeapons.TABIRA_YUKI).asSmallSword();
    modItem((IItemProvider)ModWeapons.WARABIDE_SWORD).asSmallSword();
    modItem((IItemProvider)ModWeapons.DORU_PICKAXE).asSmallSword();
  }

  
  public ModItemModelBuilder modItem(IItemProvider itemHandler) {
    String name = itemHandler.asItem().getRegistryName().getPath();
    return modItem(itemHandler, name);
  }

  
  public ModItemModelBuilder modItem(IItemProvider itemHandler, String texture) {
    String name = itemHandler.asItem().getRegistryName().getPath();

    
    ItemModelBuilder builder = (ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated"))).texture("layer0", "items/" + texture);
    return new ModItemModelBuilder(name, builder, this);
  }

  
  public ModItemModelBuilder modItemWithOverlay(IItemProvider itemHandler) {
    String name = itemHandler.asItem().getRegistryName().getPath();


    
    ItemModelBuilder builder = (ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated"))).texture("layer0", "items/" + name)).texture("layer1", "items/" + name + "_overlay");
    return new ModItemModelBuilder(name, builder, this);
  }

  
  public static class ModItemModelBuilder
  {
    private String name;
    private ItemModelBuilder builder;
    private ModItemModelsDataGen parent;
    
    public ModItemModelBuilder(String name, ItemModelBuilder builder, ModItemModelsDataGen modItemModelsDataGen) {
      this.name = name;
      this.builder = builder;
      this.parent = modItemModelsDataGen;
    }

    
    public ModItemModelBuilder asSmallSword() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 55.0F)
        .translation(0.0F, 7.5F, -0.5F)
        .scale(1.6F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -55.0F)
        .translation(0.0F, 7.5F, -0.5F)
        .scale(1.6F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asMediumSword() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 55.0F)
        .translation(0.0F, 9.5F, -0.5F)
        .scale(1.6F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -55.0F)
        .translation(0.0F, 9.5F, -0.5F)
        .scale(1.6F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asLongSword() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(15.0F, -90.0F, 55.0F)
        .translation(0.0F, 16.0F, -0.5F)
        .scale(2.2F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(15.0F, 90.0F, -55.0F)
        .translation(0.0F, 16.0F, -0.5F)
        .scale(2.2F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asMediumPipe() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 5.0F)
        .translation(0.0F, 6.5F, 8.0F)
        .scale(2.0F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -5.0F)
        .translation(0.0F, 6.5F, 8.0F)
        .scale(2.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asLongPipe() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 5.0F)
        .translation(0.0F, 7.5F, 8.0F)
        .scale(3.6F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -5.0F)
        .translation(0.0F, 7.5F, 8.0F)
        .scale(3.6F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asTonfa() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, -145.0F)
        .translation(0.0F, -5.5F, 6.5F)
        .scale(1.1F, 1.1F, 1.5F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, 145.0F)
        .translation(0.0F, -5.5F, 6.5F)
        .scale(1.1F, 1.1F, 1.5F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asHook() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 135.0F)
        .translation(0.0F, -2.0F, -2.0F)
        .scale(0.9F, 0.9F, 3.9F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -135.0F)
        .translation(0.0F, -2.0F, -2.0F)
        .scale(0.9F, 0.9F, 3.9F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(1.0F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asPistol() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 45.0F)
        .translation(0.0F, 1.0F, -2.5F)
        .scale(0.8F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -45.0F)
        .translation(0.0F, 1.0F, -2.5F)
        .scale(0.8F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(0.7F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 4.5F, 1.13F)
        .scale(0.7F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asRifle() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 35.0F)
        .translation(0.0F, 1.0F, -4.0F)
        .scale(1.7F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 90.0F, -35.0F)
        .translation(0.0F, 1.0F, -4.0F)
        .scale(1.7F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 30.0F)
        .translation(2.0F, 2.5F, -2.5F)
        .scale(1.5F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -30.0F)
        .translation(2.0F, 2.5F, -2.5F)
        .scale(1.5F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asLongSlingshot() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(-80.0F, 260.0F, -40.0F)
        .translation(-1.0F, 1.0F, 2.5F)
        .scale(1.9F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(-80.0F, -260.0F, 40.0F)
        .translation(-1.0F, 1.0F, 2.5F)
        .scale(1.9F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asSmallSlingshot() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(-80.0F, 260.0F, -40.0F)
        .translation(-1.0F, 3.5F, 2.5F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(-80.0F, -260.0F, 40.0F)
        .translation(-1.0F, 3.5F, 2.5F)
        .scale(1.0F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asBow() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(-80.0F, 260.0F, -40.0F)
        .translation(-1.0F, -2.0F, 2.5F)
        .scale(1.3F, 1.3F, 1.1F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(-80.0F, -260.0F, 40.0F)
        .translation(-1.0F, -2.0F, 2.5F)
        .scale(1.3F, 1.3F, 1.1F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -25.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.68F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder asCup() {
      this.builder
        .transforms()
        .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
        .rotation(0.0F, 0.0F, 0.0F)
        .translation(0.0F, 2.0F, -0.1F)
        .scale(0.8F)
        .end()
        .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
        .rotation(0.0F, 0.0F, 0.0F)
        .translation(0.0F, 2.0F, -0.1F)
        .scale(0.8F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
        .rotation(0.0F, -90.0F, 40.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.7F)
        .end()
        .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
        .rotation(0.0F, 90.0F, -40.0F)
        .translation(1.13F, 3.2F, -1.13F)
        .scale(0.7F)
        .end()
        .end();
      return this;
    }

    
    public ModItemModelBuilder sheathedWeapon() {
      this.builder
        .override()
        .predicate(new ResourceLocation("sheathed"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_sheathed")))
        .end();

      
      String name = this.name + "_sheathed";
      ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
        .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
        .texture("layer0", "items/" + name);
      
      return this;
    }

    
    public ModItemModelBuilder hakiWeapon() {
      this.builder
        .override()
        .predicate(new ResourceLocation("haki"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_haki")))
        .end();

      
      String name = this.name + "_haki";
      ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
        .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
        .texture("layer0", "items/" + name);
      
      return this;
    }

    
    public ModItemModelBuilder openWeapon() {
      this.builder
        .override()
        .predicate(new ResourceLocation("open"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_open")))
        .end();

      
      String name = this.name + "_open";
      ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
        .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
        .texture("layer0", "items/" + name);
      
      return this;
    }

    
    public ModItemModelBuilder pullWeapon() {
      this.builder
        .override()
        .predicate(new ResourceLocation("pulling"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_0")))
        .end()
        .override()
        .predicate(new ResourceLocation("pulling"), 1.0F)
        .predicate(new ResourceLocation("pull"), 0.65F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_1")))
        .end()
        .override()
        .predicate(new ResourceLocation("pulling"), 1.0F)
        .predicate(new ResourceLocation("pull"), 0.9F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_2")))
        .end();

      
      for (int i = 0; i < 3; i++) {
        
        String name = this.name + "_pulling_" + i;
        ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
          .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
          .texture("layer0", "items/" + name);
      } 
      
      return this;
    }

    
    public ModItemModelBuilder filledItem() {
      this.builder
        .override()
        .predicate(new ResourceLocation("filled"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_filled")))
        .end();

      
      String name = this.name + "_filled";
      ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
        .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
        .texture("layer0", "items/" + name);
      
      return this;
    }

    
    public ModItemModelBuilder size3Item() {
      this.builder
        .override()
        .predicate(new ResourceLocation("size"), 0.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_0")))
        .end()
        .override()
        .predicate(new ResourceLocation("size"), 1.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_1")))
        .end()
        .override()
        .predicate(new ResourceLocation("size"), 2.0F)
        .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_2")))
        .end();

      
      for (int i = 0; i < 3; i++) {
        
        String name = this.name + "_" + i;
        ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
          .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
          .texture("layer0", "items/" + name);
      } 
      
      return this;
    }
  }
}


