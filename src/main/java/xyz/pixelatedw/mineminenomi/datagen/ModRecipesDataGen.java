/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ 
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.advancements.ICriterionInstance;
/*     */ import net.minecraft.advancements.criterion.InventoryChangeTrigger;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.data.IFinishedRecipe;
/*     */ import net.minecraft.data.RecipeProvider;
/*     */ import net.minecraft.data.ShapedRecipeBuilder;
/*     */ import net.minecraft.data.ShapelessRecipeBuilder;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class ModRecipesDataGen
/*     */   extends RecipeProvider
/*     */ {
/*     */   public ModRecipesDataGen(DataGenerator generator) {
/*  23 */     super(generator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
/*  30 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.BULLET)
/*  31 */       .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
/*  32 */       .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
/*  33 */       .patternLine("ic ")
/*  34 */       .patternLine("ci ")
/*  35 */       .patternLine("   ")
/*  36 */       .setGroup("mineminenomi")
/*  37 */       .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
/*  38 */           })).build(consumer);
/*     */     
/*  40 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.CANNON_BALL)
/*  41 */       .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
/*  42 */       .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
/*  43 */       .patternLine("ccc")
/*  44 */       .patternLine("cic")
/*  45 */       .patternLine("ccc")
/*  46 */       .setGroup("mineminenomi")
/*  47 */       .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
/*  48 */           })).build(consumer);
/*     */     
/*  50 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.CANNON)
/*  51 */       .key(Character.valueOf('s'), (IItemProvider)Items.STRING)
/*  52 */       .key(Character.valueOf('i'), (IItemProvider)Blocks.IRON_BLOCK)
/*  53 */       .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
/*  54 */       .patternLine("s  ")
/*  55 */       .patternLine("iii")
/*  56 */       .patternLine("ccc")
/*  57 */       .setGroup("mineminenomi")
/*  58 */       .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
/*  59 */           })).build(consumer);
/*     */     
/*  61 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.KEY)
/*  62 */       .key(Character.valueOf('g'), (IItemProvider)Items.GOLD_INGOT)
/*  63 */       .patternLine(" g ")
/*  64 */       .patternLine(" g ")
/*  65 */       .patternLine(" g ")
/*  66 */       .setGroup("mineminenomi")
/*  67 */       .addCriterion("has_gold_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.GOLD_INGOT
/*  68 */           })).build(consumer);
/*     */     
/*  70 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.SAKE_CUP)
/*  71 */       .key(Character.valueOf('c'), (IItemProvider)Items.CLAY_BALL)
/*  72 */       .patternLine("   ")
/*  73 */       .patternLine("c c")
/*  74 */       .patternLine(" c ")
/*  75 */       .setGroup("mineminenomi")
/*  76 */       .addCriterion("has_clay_ball", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.CLAY_BALL
/*  77 */           })).build(consumer);
/*     */     
/*  79 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.VIVRE_CARD)
/*  80 */       .key(Character.valueOf('p'), (IItemProvider)Items.PAPER)
/*  81 */       .patternLine("   ")
/*  82 */       .patternLine(" p ")
/*  83 */       .patternLine("   ")
/*  84 */       .setGroup("mineminenomi")
/*  85 */       .addCriterion("has_paper", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.PAPER
/*  86 */           })).build(consumer);
/*     */     
/*  88 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModArmors.MEDIC_BAG)
/*  89 */       .key(Character.valueOf('s'), (IItemProvider)Items.STRING)
/*  90 */       .key(Character.valueOf('l'), (IItemProvider)Items.LEATHER)
/*  91 */       .key(Character.valueOf('b'), (IItemProvider)Items.LAPIS_LAZULI)
/*  92 */       .patternLine("s s")
/*  93 */       .patternLine("lbl")
/*  94 */       .patternLine("lll")
/*  95 */       .setGroup("mineminenomi")
/*  96 */       .addCriterion("has_leather", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LEATHER
/*  97 */           })).addCriterion("has_lapis_lazuli", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI
/*  98 */           })).build(consumer);
/*     */ 
/*     */     
/* 101 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.MACE)
/* 102 */       .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
/* 103 */       .key(Character.valueOf('i'), (IItemProvider)Blocks.IRON_BLOCK)
/* 104 */       .patternLine(" i ")
/* 105 */       .patternLine(" i ")
/* 106 */       .patternLine(" s ")
/* 107 */       .setGroup("mineminenomi")
/* 108 */       .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
/* 109 */           })).build(consumer);
/*     */     
/* 111 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.CLIMA_TACT)
/* 112 */       .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
/* 113 */       .key(Character.valueOf('b'), (IItemProvider)Items.LAPIS_LAZULI)
/* 114 */       .patternLine("bsb")
/* 115 */       .patternLine("bsb")
/* 116 */       .patternLine("bsb")
/* 117 */       .setGroup("mineminenomi")
/* 118 */       .addCriterion("has_lapis_lazuli", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.LAPIS_LAZULI
/* 119 */           })).build(consumer);
/*     */     
/* 121 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT)
/* 122 */       .key(Character.valueOf('c'), (IItemProvider)ModWeapons.CLIMA_TACT)
/* 123 */       .key(Character.valueOf('b'), (IItemProvider)ModBlocks.BREATH_DIAL)
/* 124 */       .key(Character.valueOf('f'), (IItemProvider)ModBlocks.FLAME_DIAL)
/* 125 */       .key(Character.valueOf('e'), (IItemProvider)ModBlocks.EISEN_DIAL)
/* 126 */       .key(Character.valueOf('l'), (IItemProvider)ModBlocks.FLASH_DIAL)
/* 127 */       .key(Character.valueOf('m'), (IItemProvider)ModBlocks.MILKY_DIAL)
/* 128 */       .patternLine("eme")
/* 129 */       .patternLine("bcf")
/* 130 */       .patternLine("ele")
/* 131 */       .setGroup("mineminenomi")
/* 132 */       .addCriterion("has_clima_tact", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModWeapons.CLIMA_TACT
/* 133 */           })).build(consumer);
/*     */     
/* 135 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.SCISSORS)
/* 136 */       .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
/* 137 */       .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
/* 138 */       .patternLine(" ii")
/* 139 */       .patternLine("cii")
/* 140 */       .patternLine("cc ")
/* 141 */       .setGroup("mineminenomi")
/* 142 */       .addCriterion("has_iron_ingot", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.IRON_INGOT
/* 143 */           })).build(consumer);
/*     */     
/* 145 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModWeapons.UMBRELLA)
/* 146 */       .key(Character.valueOf('w'), (IItemProvider)Blocks.ORANGE_WOOL)
/* 147 */       .key(Character.valueOf('s'), (IItemProvider)Items.STICK)
/* 148 */       .patternLine("www")
/* 149 */       .patternLine(" s ")
/* 150 */       .patternLine(" s ")
/* 151 */       .setGroup("mineminenomi")
/* 152 */       .addCriterion("has_sticks", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.STICK
/* 153 */           })).build(consumer);
/*     */ 
/*     */     
/* 156 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.COLA)
/* 157 */       .key(Character.valueOf('s'), (IItemProvider)Items.SUGAR)
/* 158 */       .key(Character.valueOf('b'), (IItemProvider)Items.GLASS_BOTTLE)
/* 159 */       .patternLine(" s ")
/* 160 */       .patternLine(" s ")
/* 161 */       .patternLine(" b ")
/* 162 */       .setGroup("mineminenomi")
/* 163 */       .addCriterion("has_sugar", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)Items.SUGAR
/* 164 */           })).build(consumer);
/*     */     
/* 166 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.ULTRA_COLA)
/* 167 */       .key(Character.valueOf('s'), (IItemProvider)Items.SUGAR)
/* 168 */       .key(Character.valueOf('c'), (IItemProvider)ModItems.COLA)
/* 169 */       .patternLine("sss")
/* 170 */       .patternLine("sss")
/* 171 */       .patternLine("scs")
/* 172 */       .setGroup("mineminenomi")
/* 173 */       .addCriterion("has_cola", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.COLA
/* 174 */           })).build(consumer);
/*     */     
/* 176 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModArmors.COLA_BACKPACK)
/* 177 */       .key(Character.valueOf('i'), (IItemProvider)Items.IRON_INGOT)
/* 178 */       .key(Character.valueOf('u'), (IItemProvider)ModItems.ULTRA_COLA)
/* 179 */       .patternLine("u u")
/* 180 */       .patternLine("uiu")
/* 181 */       .patternLine("u u")
/* 182 */       .setGroup("mineminenomi")
/* 183 */       .addCriterion("has_ultra_cola", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.ULTRA_COLA
/* 184 */           })).build(consumer);
/*     */ 
/*     */     
/* 187 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.DENSE_KAIROSEKI)
/* 188 */       .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
/* 189 */       .patternLine("kk ")
/* 190 */       .patternLine("kk ")
/* 191 */       .patternLine("   ")
/* 192 */       .setGroup("mineminenomi")
/* 193 */       .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
/* 194 */           })).build(consumer);
/*     */     
/* 196 */     ShapelessRecipeBuilder.shapelessRecipe((IItemProvider)ModItems.KAIROSEKI)
/* 197 */       .addIngredient((IItemProvider)ModBlocks.KAIROSEKI)
/* 198 */       .setGroup("mineminenomi")
/* 199 */       .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
/* 200 */           })).build(consumer);
/*     */ 
/*     */     
/* 203 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.KAIROSEKI_BARS)
/* 204 */       .key(Character.valueOf('k'), (IItemProvider)ModItems.DENSE_KAIROSEKI)
/* 205 */       .patternLine("kkk")
/* 206 */       .patternLine("kkk")
/* 207 */       .patternLine("   ")
/* 208 */       .setGroup("mineminenomi")
/* 209 */       .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
/* 210 */           })).build(consumer);
/*     */     
/* 212 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModBlocks.KAIROSEKI)
/* 213 */       .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
/* 214 */       .patternLine("kkk")
/* 215 */       .patternLine("kkk")
/* 216 */       .patternLine("kkk")
/* 217 */       .setGroup("mineminenomi")
/* 218 */       .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
/* 219 */           })).build(consumer);
/*     */     
/* 221 */     ShapedRecipeBuilder.shapedRecipe((IItemProvider)ModItems.KAIROSEKI_BULLET)
/* 222 */       .key(Character.valueOf('k'), (IItemProvider)ModItems.KAIROSEKI)
/* 223 */       .key(Character.valueOf('c'), (IItemProvider)Blocks.COBBLESTONE)
/* 224 */       .patternLine("kc ")
/* 225 */       .patternLine("ck ")
/* 226 */       .patternLine("   ")
/* 227 */       .setGroup("mineminenomi")
/* 228 */       .addCriterion("has_kairoseki", (ICriterionInstance)InventoryChangeTrigger.Instance.forItems(new IItemProvider[] { (IItemProvider)ModItems.KAIROSEKI
/* 229 */           })).build(consumer);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModRecipesDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */