/*     */ package xyz.pixelatedw.mineminenomi.datagen;
/*     */ 
/*     */ import net.minecraft.data.DataGenerator;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.generators.ExistingFileHelper;
/*     */ import net.minecraftforge.client.model.generators.ItemModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.ModelBuilder;
/*     */ import net.minecraftforge.client.model.generators.ModelFile;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.datagen.ItemModelsDataGen;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModItemModelsDataGen
/*     */   extends ItemModelsDataGen
/*     */ {
/*     */   public ModItemModelsDataGen(DataGenerator generator, ExistingFileHelper existingFileHelper) {
/*  28 */     super(generator, existingFileHelper);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerModels() {
/*  34 */     for (RegistryObject<Item> obj : (Iterable<RegistryObject<Item>>)WyRegistry.ITEMS.getEntries()) {
/*     */       
/*  36 */       if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem || ((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.SakeCupItem || ((Item)obj
/*  37 */         .get()).asItem() instanceof xyz.pixelatedw.mineminenomi.items.BellyPouchItem || ((Item)obj.get()).asItem() instanceof net.minecraft.item.BowItem) {
/*     */         continue;
/*     */       }
/*  40 */       if (((Item)obj.get()).asItem() instanceof xyz.pixelatedw.mineminenomi.wypi.ModdedSpawnEggItem) {
/*  41 */         spawnEggItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
/*  42 */       }  if (((Item)obj.get()).asItem() instanceof net.minecraft.item.BlockItem) {
/*  43 */         blockItem(((Item)obj.get()).asItem().getRegistryName().getPath()); continue;
/*     */       } 
/*  45 */       item(((Item)obj.get()).asItem().getRegistryName().getPath());
/*     */     } 
/*     */ 
/*     */     
/*  49 */     modItem((IItemProvider)ModItems.SAKE_CUP).filledItem().asCup();
/*  50 */     modItem((IItemProvider)ModItems.BELLY_POUCH, "belly_pouch_0").size3Item();
/*     */ 
/*     */     
/*  53 */     modItem((IItemProvider)ModWeapons.MARINE_SWORD).hakiWeapon().asSmallSword();
/*  54 */     modItem((IItemProvider)ModWeapons.PIRATE_CUTLASS).hakiWeapon().asSmallSword();
/*  55 */     modItem((IItemProvider)ModWeapons.PIPE).hakiWeapon().asMediumPipe();
/*  56 */     modItem((IItemProvider)ModWeapons.SCISSORS).hakiWeapon().asMediumSword();
/*  57 */     modItem((IItemProvider)ModWeapons.KIKOKU).hakiWeapon().asLongSword();
/*  58 */     modItem((IItemProvider)ModWeapons.KIRIBACHI).hakiWeapon().asLongSword();
/*  59 */     modItem((IItemProvider)ModWeapons.YORU).hakiWeapon().asLongSword();
/*  60 */     modItem((IItemProvider)ModWeapons.MURAKUMOGIRI).hakiWeapon().asLongPipe();
/*  61 */     modItem((IItemProvider)ModWeapons.HOOK).sheathedWeapon().hakiWeapon().asHook();
/*  62 */     modItem((IItemProvider)ModWeapons.JITTE).hakiWeapon().asMediumSword();
/*  63 */     modItem((IItemProvider)ModWeapons.NONOSAMA_STAFF).hakiWeapon().asMediumPipe();
/*  64 */     modItem((IItemProvider)ModWeapons.NONOSAMA_TRIDENT).hakiWeapon().asMediumPipe();
/*  65 */     modItem((IItemProvider)ModWeapons.HAMMER_5T).hakiWeapon().asSmallSword();
/*  66 */     modItem((IItemProvider)ModWeapons.TONFA).hakiWeapon().asTonfa();
/*  67 */     modItem((IItemProvider)ModWeapons.BANDIT_KNIFE).hakiWeapon().asSmallSword();
/*  68 */     modItem((IItemProvider)ModWeapons.WADO_ICHIMONJI).sheathedWeapon().hakiWeapon().asMediumSword();
/*  69 */     modItem((IItemProvider)ModWeapons.SANDAI_KITETSU).sheathedWeapon().hakiWeapon().asMediumSword();
/*  70 */     modItem((IItemProvider)ModWeapons.NIDAI_KITETSU).sheathedWeapon().hakiWeapon().asMediumSword();
/*  71 */     modItem((IItemProvider)ModWeapons.SHUSUI).sheathedWeapon().hakiWeapon().asMediumSword();
/*  72 */     modItem((IItemProvider)ModWeapons.SOUL_SOLID).sheathedWeapon().hakiWeapon().asMediumSword();
/*  73 */     modItem((IItemProvider)ModWeapons.DURANDAL).hakiWeapon().asMediumSword();
/*  74 */     modItem((IItemProvider)ModWeapons.MACE).hakiWeapon().asLongSword();
/*  75 */     modItem((IItemProvider)ModWeapons.UMBRELLA).openWeapon().asMediumSword();
/*  76 */     modItem((IItemProvider)ModWeapons.CLIMA_TACT).asMediumPipe();
/*  77 */     modItem((IItemProvider)ModWeapons.PERFECT_CLIMA_TACT).asMediumPipe();
/*  78 */     modItem((IItemProvider)ModWeapons.SORCERY_CLIMA_TACT).openWeapon().asMediumPipe();
/*     */ 
/*     */     
/*  81 */     modItem((IItemProvider)ModWeapons.FLINTLOCK).asPistol();
/*  82 */     modItem((IItemProvider)ModWeapons.SENRIKU).asRifle();
/*  83 */     modItem((IItemProvider)ModWeapons.GINGA_PACHINKO).pullWeapon().asSmallSlingshot();
/*  84 */     modItem((IItemProvider)ModWeapons.KABUTO).pullWeapon().asLongSlingshot();
/*  85 */     modItem((IItemProvider)ModWeapons.BLACK_KABUTO).pullWeapon().asLongSlingshot();
/*  86 */     modItem((IItemProvider)ModWeapons.GREEN_KUJA_BOW).pullWeapon().asBow();
/*     */ 
/*     */     
/*  89 */     modItem((IItemProvider)ModWeapons.ICE_SABER).asSmallSword();
/*  90 */     modItem((IItemProvider)ModWeapons.AMA_NO_MURAKUMO).asSmallSword();
/*  91 */     modItem((IItemProvider)ModWeapons.NORO_NORO_BEAM_SWORD).asSmallSword();
/*  92 */     modItem((IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN).asSmallSword();
/*  93 */     modItem((IItemProvider)ModWeapons.BLUE_SWORD).asSmallSword();
/*  94 */     modItem((IItemProvider)ModWeapons.TABIRA_YUKI).asSmallSword();
/*  95 */     modItem((IItemProvider)ModWeapons.WARABIDE_SWORD).asSmallSword();
/*  96 */     modItem((IItemProvider)ModWeapons.DORU_PICKAXE).asSmallSword();
/*     */   }
/*     */ 
/*     */   
/*     */   public ModItemModelBuilder modItem(IItemProvider itemHandler) {
/* 101 */     String name = itemHandler.asItem().getRegistryName().getPath();
/* 102 */     return modItem(itemHandler, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModItemModelBuilder modItem(IItemProvider itemHandler, String texture) {
/* 107 */     String name = itemHandler.asItem().getRegistryName().getPath();
/*     */ 
/*     */     
/* 110 */     ItemModelBuilder builder = (ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated"))).texture("layer0", "items/" + texture);
/* 111 */     return new ModItemModelBuilder(name, builder, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModItemModelBuilder modItemWithOverlay(IItemProvider itemHandler) {
/* 116 */     String name = itemHandler.asItem().getRegistryName().getPath();
/*     */ 
/*     */ 
/*     */     
/* 120 */     ItemModelBuilder builder = (ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)((ItemModelBuilder)getBuilder(name)).parent((ModelFile)new ModelFile.UncheckedModelFile("item/generated"))).texture("layer0", "items/" + name)).texture("layer1", "items/" + name + "_overlay");
/* 121 */     return new ModItemModelBuilder(name, builder, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ModItemModelBuilder
/*     */   {
/*     */     private String name;
/*     */     private ItemModelBuilder builder;
/*     */     private ModItemModelsDataGen parent;
/*     */     
/*     */     public ModItemModelBuilder(String name, ItemModelBuilder builder, ModItemModelsDataGen modItemModelsDataGen) {
/* 132 */       this.name = name;
/* 133 */       this.builder = builder;
/* 134 */       this.parent = modItemModelsDataGen;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asSmallSword() {
/* 139 */       this.builder
/* 140 */         .transforms()
/* 141 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 142 */         .rotation(0.0F, -90.0F, 55.0F)
/* 143 */         .translation(0.0F, 7.5F, -0.5F)
/* 144 */         .scale(1.6F)
/* 145 */         .end()
/* 146 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 147 */         .rotation(0.0F, 90.0F, -55.0F)
/* 148 */         .translation(0.0F, 7.5F, -0.5F)
/* 149 */         .scale(1.6F)
/* 150 */         .end()
/* 151 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 152 */         .rotation(0.0F, -90.0F, 40.0F)
/* 153 */         .translation(1.13F, 4.5F, 1.13F)
/* 154 */         .scale(1.0F)
/* 155 */         .end()
/* 156 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 157 */         .rotation(0.0F, 90.0F, -40.0F)
/* 158 */         .translation(1.13F, 4.5F, 1.13F)
/* 159 */         .scale(1.0F)
/* 160 */         .end()
/* 161 */         .end();
/* 162 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asMediumSword() {
/* 167 */       this.builder
/* 168 */         .transforms()
/* 169 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 170 */         .rotation(0.0F, -90.0F, 55.0F)
/* 171 */         .translation(0.0F, 9.5F, -0.5F)
/* 172 */         .scale(1.6F)
/* 173 */         .end()
/* 174 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 175 */         .rotation(0.0F, 90.0F, -55.0F)
/* 176 */         .translation(0.0F, 9.5F, -0.5F)
/* 177 */         .scale(1.6F)
/* 178 */         .end()
/* 179 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 180 */         .rotation(0.0F, -90.0F, 40.0F)
/* 181 */         .translation(1.13F, 4.5F, 1.13F)
/* 182 */         .scale(1.0F)
/* 183 */         .end()
/* 184 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 185 */         .rotation(0.0F, 90.0F, -40.0F)
/* 186 */         .translation(1.13F, 4.5F, 1.13F)
/* 187 */         .scale(1.0F)
/* 188 */         .end()
/* 189 */         .end();
/* 190 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asLongSword() {
/* 195 */       this.builder
/* 196 */         .transforms()
/* 197 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 198 */         .rotation(15.0F, -90.0F, 55.0F)
/* 199 */         .translation(0.0F, 16.0F, -0.5F)
/* 200 */         .scale(2.2F)
/* 201 */         .end()
/* 202 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 203 */         .rotation(15.0F, 90.0F, -55.0F)
/* 204 */         .translation(0.0F, 16.0F, -0.5F)
/* 205 */         .scale(2.2F)
/* 206 */         .end()
/* 207 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 208 */         .rotation(0.0F, -90.0F, 40.0F)
/* 209 */         .translation(1.13F, 4.5F, 1.13F)
/* 210 */         .scale(1.0F)
/* 211 */         .end()
/* 212 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 213 */         .rotation(0.0F, 90.0F, -40.0F)
/* 214 */         .translation(1.13F, 4.5F, 1.13F)
/* 215 */         .scale(1.0F)
/* 216 */         .end()
/* 217 */         .end();
/* 218 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asMediumPipe() {
/* 223 */       this.builder
/* 224 */         .transforms()
/* 225 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 226 */         .rotation(0.0F, -90.0F, 5.0F)
/* 227 */         .translation(0.0F, 6.5F, 8.0F)
/* 228 */         .scale(2.0F)
/* 229 */         .end()
/* 230 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 231 */         .rotation(0.0F, 90.0F, -5.0F)
/* 232 */         .translation(0.0F, 6.5F, 8.0F)
/* 233 */         .scale(2.0F)
/* 234 */         .end()
/* 235 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 236 */         .rotation(0.0F, -90.0F, 40.0F)
/* 237 */         .translation(1.13F, 4.5F, 1.13F)
/* 238 */         .scale(1.0F)
/* 239 */         .end()
/* 240 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 241 */         .rotation(0.0F, 90.0F, -40.0F)
/* 242 */         .translation(1.13F, 4.5F, 1.13F)
/* 243 */         .scale(1.0F)
/* 244 */         .end()
/* 245 */         .end();
/* 246 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asLongPipe() {
/* 251 */       this.builder
/* 252 */         .transforms()
/* 253 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 254 */         .rotation(0.0F, -90.0F, 5.0F)
/* 255 */         .translation(0.0F, 7.5F, 8.0F)
/* 256 */         .scale(3.6F)
/* 257 */         .end()
/* 258 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 259 */         .rotation(0.0F, 90.0F, -5.0F)
/* 260 */         .translation(0.0F, 7.5F, 8.0F)
/* 261 */         .scale(3.6F)
/* 262 */         .end()
/* 263 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 264 */         .rotation(0.0F, -90.0F, 40.0F)
/* 265 */         .translation(1.13F, 4.5F, 1.13F)
/* 266 */         .scale(1.0F)
/* 267 */         .end()
/* 268 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 269 */         .rotation(0.0F, 90.0F, -40.0F)
/* 270 */         .translation(1.13F, 4.5F, 1.13F)
/* 271 */         .scale(1.0F)
/* 272 */         .end()
/* 273 */         .end();
/* 274 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asTonfa() {
/* 279 */       this.builder
/* 280 */         .transforms()
/* 281 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 282 */         .rotation(0.0F, -90.0F, -145.0F)
/* 283 */         .translation(0.0F, -5.5F, 6.5F)
/* 284 */         .scale(1.1F, 1.1F, 1.5F)
/* 285 */         .end()
/* 286 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 287 */         .rotation(0.0F, 90.0F, 145.0F)
/* 288 */         .translation(0.0F, -5.5F, 6.5F)
/* 289 */         .scale(1.1F, 1.1F, 1.5F)
/* 290 */         .end()
/* 291 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 292 */         .rotation(0.0F, -90.0F, 40.0F)
/* 293 */         .translation(1.13F, 4.5F, 1.13F)
/* 294 */         .scale(1.0F)
/* 295 */         .end()
/* 296 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 297 */         .rotation(0.0F, 90.0F, -40.0F)
/* 298 */         .translation(1.13F, 4.5F, 1.13F)
/* 299 */         .scale(1.0F)
/* 300 */         .end()
/* 301 */         .end();
/* 302 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asHook() {
/* 307 */       this.builder
/* 308 */         .transforms()
/* 309 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 310 */         .rotation(0.0F, -90.0F, 135.0F)
/* 311 */         .translation(0.0F, -2.0F, -2.0F)
/* 312 */         .scale(0.9F, 0.9F, 3.9F)
/* 313 */         .end()
/* 314 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 315 */         .rotation(0.0F, 90.0F, -135.0F)
/* 316 */         .translation(0.0F, -2.0F, -2.0F)
/* 317 */         .scale(0.9F, 0.9F, 3.9F)
/* 318 */         .end()
/* 319 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 320 */         .rotation(0.0F, -90.0F, 40.0F)
/* 321 */         .translation(1.13F, 4.5F, 1.13F)
/* 322 */         .scale(1.0F)
/* 323 */         .end()
/* 324 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 325 */         .rotation(0.0F, 90.0F, -40.0F)
/* 326 */         .translation(1.13F, 4.5F, 1.13F)
/* 327 */         .scale(1.0F)
/* 328 */         .end()
/* 329 */         .end();
/* 330 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asPistol() {
/* 335 */       this.builder
/* 336 */         .transforms()
/* 337 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 338 */         .rotation(0.0F, -90.0F, 45.0F)
/* 339 */         .translation(0.0F, 1.0F, -2.5F)
/* 340 */         .scale(0.8F)
/* 341 */         .end()
/* 342 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 343 */         .rotation(0.0F, 90.0F, -45.0F)
/* 344 */         .translation(0.0F, 1.0F, -2.5F)
/* 345 */         .scale(0.8F)
/* 346 */         .end()
/* 347 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 348 */         .rotation(0.0F, -90.0F, 40.0F)
/* 349 */         .translation(1.13F, 4.5F, 1.13F)
/* 350 */         .scale(0.7F)
/* 351 */         .end()
/* 352 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 353 */         .rotation(0.0F, 90.0F, -40.0F)
/* 354 */         .translation(1.13F, 4.5F, 1.13F)
/* 355 */         .scale(0.7F)
/* 356 */         .end()
/* 357 */         .end();
/* 358 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asRifle() {
/* 363 */       this.builder
/* 364 */         .transforms()
/* 365 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 366 */         .rotation(0.0F, -90.0F, 35.0F)
/* 367 */         .translation(0.0F, 1.0F, -4.0F)
/* 368 */         .scale(1.7F)
/* 369 */         .end()
/* 370 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 371 */         .rotation(0.0F, 90.0F, -35.0F)
/* 372 */         .translation(0.0F, 1.0F, -4.0F)
/* 373 */         .scale(1.7F)
/* 374 */         .end()
/* 375 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 376 */         .rotation(0.0F, -90.0F, 30.0F)
/* 377 */         .translation(2.0F, 2.5F, -2.5F)
/* 378 */         .scale(1.5F)
/* 379 */         .end()
/* 380 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 381 */         .rotation(0.0F, 90.0F, -30.0F)
/* 382 */         .translation(2.0F, 2.5F, -2.5F)
/* 383 */         .scale(1.5F)
/* 384 */         .end()
/* 385 */         .end();
/* 386 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asLongSlingshot() {
/* 391 */       this.builder
/* 392 */         .transforms()
/* 393 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 394 */         .rotation(-80.0F, 260.0F, -40.0F)
/* 395 */         .translation(-1.0F, 1.0F, 2.5F)
/* 396 */         .scale(1.9F)
/* 397 */         .end()
/* 398 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 399 */         .rotation(-80.0F, -260.0F, 40.0F)
/* 400 */         .translation(-1.0F, 1.0F, 2.5F)
/* 401 */         .scale(1.9F)
/* 402 */         .end()
/* 403 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 404 */         .rotation(0.0F, -90.0F, 25.0F)
/* 405 */         .translation(1.13F, 3.2F, -1.13F)
/* 406 */         .scale(0.68F)
/* 407 */         .end()
/* 408 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 409 */         .rotation(0.0F, 90.0F, -25.0F)
/* 410 */         .translation(1.13F, 3.2F, -1.13F)
/* 411 */         .scale(0.68F)
/* 412 */         .end()
/* 413 */         .end();
/* 414 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asSmallSlingshot() {
/* 419 */       this.builder
/* 420 */         .transforms()
/* 421 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 422 */         .rotation(-80.0F, 260.0F, -40.0F)
/* 423 */         .translation(-1.0F, 3.5F, 2.5F)
/* 424 */         .scale(1.0F)
/* 425 */         .end()
/* 426 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 427 */         .rotation(-80.0F, -260.0F, 40.0F)
/* 428 */         .translation(-1.0F, 3.5F, 2.5F)
/* 429 */         .scale(1.0F)
/* 430 */         .end()
/* 431 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 432 */         .rotation(0.0F, -90.0F, 25.0F)
/* 433 */         .translation(1.13F, 3.2F, -1.13F)
/* 434 */         .scale(0.68F)
/* 435 */         .end()
/* 436 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 437 */         .rotation(0.0F, 90.0F, -25.0F)
/* 438 */         .translation(1.13F, 3.2F, -1.13F)
/* 439 */         .scale(0.68F)
/* 440 */         .end()
/* 441 */         .end();
/* 442 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asBow() {
/* 447 */       this.builder
/* 448 */         .transforms()
/* 449 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 450 */         .rotation(-80.0F, 260.0F, -40.0F)
/* 451 */         .translation(-1.0F, -2.0F, 2.5F)
/* 452 */         .scale(1.3F, 1.3F, 1.1F)
/* 453 */         .end()
/* 454 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 455 */         .rotation(-80.0F, -260.0F, 40.0F)
/* 456 */         .translation(-1.0F, -2.0F, 2.5F)
/* 457 */         .scale(1.3F, 1.3F, 1.1F)
/* 458 */         .end()
/* 459 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 460 */         .rotation(0.0F, -90.0F, 25.0F)
/* 461 */         .translation(1.13F, 3.2F, -1.13F)
/* 462 */         .scale(0.68F)
/* 463 */         .end()
/* 464 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 465 */         .rotation(0.0F, 90.0F, -25.0F)
/* 466 */         .translation(1.13F, 3.2F, -1.13F)
/* 467 */         .scale(0.68F)
/* 468 */         .end()
/* 469 */         .end();
/* 470 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder asCup() {
/* 475 */       this.builder
/* 476 */         .transforms()
/* 477 */         .transform(ModelBuilder.Perspective.THIRDPERSON_RIGHT)
/* 478 */         .rotation(0.0F, 0.0F, 0.0F)
/* 479 */         .translation(0.0F, 2.0F, -0.1F)
/* 480 */         .scale(0.8F)
/* 481 */         .end()
/* 482 */         .transform(ModelBuilder.Perspective.THIRDPERSON_LEFT)
/* 483 */         .rotation(0.0F, 0.0F, 0.0F)
/* 484 */         .translation(0.0F, 2.0F, -0.1F)
/* 485 */         .scale(0.8F)
/* 486 */         .end()
/* 487 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_RIGHT)
/* 488 */         .rotation(0.0F, -90.0F, 40.0F)
/* 489 */         .translation(1.13F, 3.2F, -1.13F)
/* 490 */         .scale(0.7F)
/* 491 */         .end()
/* 492 */         .transform(ModelBuilder.Perspective.FIRSTPERSON_LEFT)
/* 493 */         .rotation(0.0F, 90.0F, -40.0F)
/* 494 */         .translation(1.13F, 3.2F, -1.13F)
/* 495 */         .scale(0.7F)
/* 496 */         .end()
/* 497 */         .end();
/* 498 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder sheathedWeapon() {
/* 503 */       this.builder
/* 504 */         .override()
/* 505 */         .predicate(new ResourceLocation("sheathed"), 1.0F)
/* 506 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_sheathed")))
/* 507 */         .end();
/*     */ 
/*     */       
/* 510 */       String name = this.name + "_sheathed";
/* 511 */       ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 512 */         .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 513 */         .texture("layer0", "items/" + name);
/*     */       
/* 515 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder hakiWeapon() {
/* 520 */       this.builder
/* 521 */         .override()
/* 522 */         .predicate(new ResourceLocation("haki"), 1.0F)
/* 523 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_haki")))
/* 524 */         .end();
/*     */ 
/*     */       
/* 527 */       String name = this.name + "_haki";
/* 528 */       ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 529 */         .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 530 */         .texture("layer0", "items/" + name);
/*     */       
/* 532 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder openWeapon() {
/* 537 */       this.builder
/* 538 */         .override()
/* 539 */         .predicate(new ResourceLocation("open"), 1.0F)
/* 540 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_open")))
/* 541 */         .end();
/*     */ 
/*     */       
/* 544 */       String name = this.name + "_open";
/* 545 */       ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 546 */         .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 547 */         .texture("layer0", "items/" + name);
/*     */       
/* 549 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder pullWeapon() {
/* 554 */       this.builder
/* 555 */         .override()
/* 556 */         .predicate(new ResourceLocation("pulling"), 1.0F)
/* 557 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_0")))
/* 558 */         .end()
/* 559 */         .override()
/* 560 */         .predicate(new ResourceLocation("pulling"), 1.0F)
/* 561 */         .predicate(new ResourceLocation("pull"), 0.65F)
/* 562 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_1")))
/* 563 */         .end()
/* 564 */         .override()
/* 565 */         .predicate(new ResourceLocation("pulling"), 1.0F)
/* 566 */         .predicate(new ResourceLocation("pull"), 0.9F)
/* 567 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_pulling_2")))
/* 568 */         .end();
/*     */ 
/*     */       
/* 571 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 573 */         String name = this.name + "_pulling_" + i;
/* 574 */         ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 575 */           .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 576 */           .texture("layer0", "items/" + name);
/*     */       } 
/*     */       
/* 579 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder filledItem() {
/* 584 */       this.builder
/* 585 */         .override()
/* 586 */         .predicate(new ResourceLocation("filled"), 1.0F)
/* 587 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_filled")))
/* 588 */         .end();
/*     */ 
/*     */       
/* 591 */       String name = this.name + "_filled";
/* 592 */       ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 593 */         .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 594 */         .texture("layer0", "items/" + name);
/*     */       
/* 596 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModItemModelBuilder size3Item() {
/* 601 */       this.builder
/* 602 */         .override()
/* 603 */         .predicate(new ResourceLocation("size"), 0.0F)
/* 604 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_0")))
/* 605 */         .end()
/* 606 */         .override()
/* 607 */         .predicate(new ResourceLocation("size"), 1.0F)
/* 608 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_1")))
/* 609 */         .end()
/* 610 */         .override()
/* 611 */         .predicate(new ResourceLocation("size"), 2.0F)
/* 612 */         .model((ModelFile)new ModelFile.UncheckedModelFile(new ResourceLocation("mineminenomi", "item/" + this.name + "_2")))
/* 613 */         .end();
/*     */ 
/*     */       
/* 616 */       for (int i = 0; i < 3; i++) {
/*     */         
/* 618 */         String name = this.name + "_" + i;
/* 619 */         ((ItemModelBuilder)((ItemModelBuilder)this.parent.getBuilder(name))
/* 620 */           .parent((ModelFile)new ModelFile.UncheckedModelFile("mineminenomi:item/" + this.name)))
/* 621 */           .texture("layer0", "items/" + name);
/*     */       } 
/*     */       
/* 624 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\datagen\ModItemModelsDataGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */