/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.awt.Color;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.ReadBookScreen;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.gui.widget.button.ChangePageButton;
/*     */ import net.minecraft.client.renderer.BufferBuilder;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class EncyclopediaScreen
/*     */   extends Screen {
/*     */   private int currPage;
/*     */   private ItemStack bookStack;
/*  40 */   private List<DFEncyclopediaEntry> entries = Lists.newArrayList();
/*     */   
/*  42 */   public static final ResourceLocation QUESTION_MARK = new ResourceLocation("mineminenomi", "textures/gui/icons/question-mark.png");
/*     */ 
/*     */   
/*     */   protected EncyclopediaScreen(ItemStack stack) {
/*  46 */     super((ITextComponent)new StringTextComponent(""));
/*  47 */     this.bookStack = stack;
/*  48 */     this.entries = getDefaultList(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*     */     ResourceLocation baseIcon;
/*  54 */     renderBackground();
/*  55 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  56 */     this.minecraft.getTextureManager().bindTexture(ReadBookScreen.BOOK_TEXTURES);
/*     */     
/*  58 */     int posX = (this.width - 192) / 2;
/*  59 */     int posY = 2;
/*     */     
/*  61 */     blit(posX, posY, 0, 0, 192, 192);
/*     */     
/*  63 */     DFEncyclopediaEntry entry = !this.entries.isEmpty() ? this.entries.get(this.currPage) : null;
/*     */     
/*  65 */     String currentPageLabel = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(Math.max(getPageCount(), 1)) });
/*  66 */     int textSize = getTextWidth(currentPageLabel);
/*  67 */     this.font.drawString(currentPageLabel, (posX - textSize + 192 - 44), 18.0F, 0);
/*     */     
/*  69 */     RenderSystem.pushMatrix();
/*     */     
/*  71 */     RenderSystem.enableBlend();
/*     */     
/*  73 */     ResourceLocation stemIcon = null;
/*     */     
/*  75 */     Color baseColor = (entry != null) ? entry.getBaseColor().orElse(Color.BLACK) : Color.BLACK;
/*  76 */     Color stemColor = (entry != null) ? entry.getStemColor().orElse(Color.BLACK) : Color.BLACK;
/*     */     
/*  78 */     if (entry == null || !entry.getShape().isPresent() || ((Integer)entry.getShape().get()).intValue() <= 0) {
/*  79 */       baseIcon = QUESTION_MARK;
/*     */     } else {
/*     */       
/*  82 */       baseIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + ".png");
/*  83 */       stemIcon = new ResourceLocation("mineminenomi", "textures/items/fruits/generic/generic_fruit_" + entry.getShape().get() + "_stem.png");
/*     */     } 
/*     */     
/*  86 */     RenderSystem.translated((posX + 55), (posY + 40), 2.0D);
/*  87 */     RenderSystem.translated(64.0D, 64.0D, 0.0D);
/*  88 */     RenderSystem.scaled(0.8D, 0.8D, 0.8D);
/*  89 */     RenderSystem.translated(-64.0D, -64.0D, 1.0D);
/*  90 */     this.minecraft.getTextureManager().bindTexture(baseIcon);
/*  91 */     if (baseIcon.equals(QUESTION_MARK)) {
/*     */       
/*  93 */       RenderSystem.color4f(stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
/*  94 */       drawUpperIcon(baseIcon, 0, 0, 64, 32);
/*  95 */       RenderSystem.color4f(baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
/*  96 */       drawLowerIcon(baseIcon, 0, 32, 64, 32);
/*     */     }
/*     */     else {
/*     */       
/* 100 */       RenderSystem.color4f(baseColor.getRed() / 255.0F, baseColor.getGreen() / 255.0F, baseColor.getBlue() / 255.0F, 1.0F);
/* 101 */       WyHelper.drawIcon(baseIcon, 0, 0, 64, 64);
/*     */     } 
/*     */     
/* 104 */     if (stemIcon != null) {
/*     */       
/* 106 */       this.minecraft.getTextureManager().bindTexture(stemIcon);
/* 107 */       RenderSystem.color4f(stemColor.getRed() / 255.0F, stemColor.getGreen() / 255.0F, stemColor.getBlue() / 255.0F, 1.0F);
/* 108 */       if (entry.getShape().isPresent() && ((Integer)entry.getShape().get()).intValue() > 0)
/*     */       {
/* 110 */         WyHelper.drawIcon(stemIcon, 0, 0, 64, 64);
/*     */       }
/*     */     } 
/*     */     
/* 114 */     RenderSystem.popMatrix();
/*     */     
/* 116 */     if (entry != null) {
/*     */       
/* 118 */       String fruitName = (new ItemStack((IItemProvider)entry.getDevilFruit())).getDisplayName().getFormattedText();
/* 119 */       fruitName = (entry.getCompletion() >= 3) ? (TextFormatting.GOLD + fruitName) : fruitName;
/* 120 */       List<String> splitText = WyHelper.splitString(this.font, fruitName, posX, 100);
/* 121 */       for (int j = 0; j < splitText.size(); j++)
/*     */       {
/* 123 */         WyHelper.drawStringWithBorder(this.font, splitText.get(j), posX + 93 - this.font.getStringWidth(splitText.get(j)) / 2, posY + 113 + j * 12, -1);
/*     */       }
/*     */     } 
/*     */     
/* 127 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init() {
/* 133 */     int posX = (this.width - 192) / 2;
/* 134 */     int posY = 2;
/*     */     
/* 136 */     addButton((Widget)new ChangePageButton(posX + 116, posY + 159, true, button -> nextPage(), true));
/*     */ 
/*     */ 
/*     */     
/* 140 */     addButton((Widget)new ChangePageButton(posX + 43, posY + 159, false, button -> previousPage(), true));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     addButton((Widget)new Button(this.width / 2 - 150, 196, 98, 20, "All", button -> {
/*     */             this.entries = getDefaultList(this.bookStack);
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 151 */     addButton((Widget)new Button(this.width / 2 - 50, 196, 98, 20, "Partially Complete", button -> {
                      //[todo : figure out what filter is on origional line ] this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter(()->{}).collect(Collectors.toList());
/*     */             this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter((e)->{return false;}).collect(Collectors.toList());
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 157 */     addButton((Widget)new Button(this.width / 2 + 50, 196, 98, 20, "Only Complete", button -> {
                      //[todo : figure out what filter is on origional line ] this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter(()->{}).collect(Collectors.toList());
/*     */             this.entries = (List<DFEncyclopediaEntry>)getDefaultList(this.bookStack).stream().filter((e)->{return false;}).collect(Collectors.toList());
/*     */             
/*     */             this.currPage = 0;
/*     */           }));
/*     */     
/* 163 */     super.init();
/*     */   }
/*     */ 
/*     */   
/*     */   private void previousPage() {
/* 168 */     if (this.currPage > 0) {
/*     */       
/* 170 */       this.currPage--;
/*     */     }
/* 172 */     else if (this.currPage == 0) {
/*     */       
/* 174 */       this.currPage = getPageCount() - 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void nextPage() {
/* 180 */     if (this.currPage < getPageCount() - 1) {
/*     */       
/* 182 */       this.currPage++;
/*     */     }
/* 184 */     else if (this.currPage == getPageCount() - 1) {
/*     */       
/* 186 */       this.currPage = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getPageCount() {
/* 192 */     return this.entries.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getTextWidth(String text) {
/* 197 */     return this.font.getStringWidth(this.font.getBidiFlag() ? this.font.bidiReorder(text) : text);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<DFEncyclopediaEntry> getDefaultList(ItemStack stack) {
/* 202 */     List<DFEncyclopediaEntry> list = Lists.newArrayList();
/* 203 */     CompoundNBT nbt = stack.getOrCreateChildTag("unlocked");
/* 204 */     for (AkumaNoMiItem fruit : ModValues.devilfruits) {
/*     */       
/* 206 */       String key = DevilFruitHelper.getDevilFruitKey(fruit);
/* 207 */       if (!nbt.isEmpty() && nbt.contains(key)) {
/*     */         
/* 209 */         DFEncyclopediaEntry dFEncyclopediaEntry = DFEncyclopediaEntry.of(nbt.getCompound(key));
/* 210 */         dFEncyclopediaEntry.setDevilFruit(fruit);
/* 211 */         list.add(dFEncyclopediaEntry);
/*     */         
/*     */         continue;
/*     */       } 
/* 215 */       DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(Optional.empty(), Optional.empty(), Optional.empty());
/* 216 */       entry.setDevilFruit(fruit);
/* 217 */       list.add(entry);
/*     */     } 
/*     */     
/* 220 */     Collections.reverse(list);
/* 221 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawUpperIcon(ResourceLocation rs, int x, int y, int u, int v) {
/* 232 */     Minecraft.getInstance().getTextureManager().bindTexture(rs);
/* 233 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/* 234 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 235 */     bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 0.5F).endVertex();
/* 236 */     bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 0.5F).endVertex();
/* 237 */     bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.0F).endVertex();
/* 238 */     bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.0F).endVertex();
/* 239 */     Tessellator.getInstance().draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawLowerIcon(ResourceLocation rs, int x, int y, int u, int v) {
/* 244 */     Minecraft.getInstance().getTextureManager().bindTexture(rs);
/* 245 */     BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
/* 246 */     bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
/* 247 */     bufferbuilder.pos(x, (y + v), 1.0D).tex(0.0F, 1.0F).endVertex();
/* 248 */     bufferbuilder.pos((x + u), (y + v), 1.0D).tex(1.0F, 1.0F).endVertex();
/* 249 */     bufferbuilder.pos((x + u), y, 1.0D).tex(1.0F, 0.5F).endVertex();
/* 250 */     bufferbuilder.pos(x, y, 1.0D).tex(0.0F, 0.5F).endVertex();
/* 251 */     Tessellator.getInstance().draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(ItemStack stack) {
/* 256 */     Minecraft.getInstance().displayGuiScreen(new EncyclopediaScreen(stack));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\EncyclopediaScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */