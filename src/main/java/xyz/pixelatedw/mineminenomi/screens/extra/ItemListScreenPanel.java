/*     */ package xyz.pixelatedw.mineminenomi.screens.extra;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.client.gui.ScrollPanel;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ItemListScreenPanel
/*     */   extends ScrollPanel
/*     */ {
/*     */   private TraderScreen parent;
/*  20 */   private List<TradeEntry> entries = new ArrayList<>();
/*     */   
/*     */   private static final int ENTRY_HEIGHT = 20;
/*     */   
/*     */   public ItemListScreenPanel(TraderScreen parent, List<TradeEntry> list) {
/*  25 */     super(parent.getMinecraft(), 215, 140, parent.height / 2 - 50, parent.width / 2 - 109);
/*     */     
/*  27 */     this.parent = parent;
/*  28 */     this.entries = list;
/*     */     
/*  30 */     for (TradeEntry entry : this.entries) {
/*     */       
/*  32 */       if (entry.getItemStack().getOrCreateTag().getBoolean("isClone") && !entry.getItemStack().getOrCreateTag().getBoolean("hasCloneTag")) {
/*     */         
/*  34 */         entry.getItemStack().setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + entry.getItemStack().getDisplayName().getFormattedText() + " (Replica)"));
/*  35 */         entry.getItemStack().getOrCreateTag().putBoolean("hasCloneTag", true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseReleased(double mouseX, double mouseY, int partialTicks) {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  49 */     return (int)(this.entries.size() * 25.0D + 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getScrollAmount() {
/*  55 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
/*  61 */     for (TradeEntry entry : this.entries) {
/*     */       
/*  63 */       int y = relativeY;
/*  64 */       int x = this.parent.width / 2 - 109 + 40;
/*     */       
/*  66 */       this.parent.renderItem(entry.getItemStack(), x - 30, y - 1);
/*  67 */       if (this.parent.getSelectedStack() != null && entry.getItemStack().hashCode() == this.parent.getSelectedStack().getItemStack().hashCode()) {
/*  68 */         WyHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, (x - 40), (y - 4), this.width, 24.0D, 0.0D);
/*     */       }
/*  70 */       this.parent.drawSizedString(entry.getItemStack().getDisplayName().getFormattedText(), x + 50, y + 4, 0.8F, -1);
/*  71 */       this.parent.drawSizedString(entry.getPrice() + "", x + 122, y + 4, 0.8F, -1);
/*  72 */       relativeY = (int)(relativeY + 25.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TradeEntry findStackEntry(int mouseX, int mouseY) {
/*  78 */     double offset = ((mouseY - this.top) + this.scrollDistance);
/*  79 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
/*     */     
/*  81 */     if (offset <= 0.0D || !isHovered) {
/*  82 */       return null;
/*     */     }
/*  84 */     int lineIdx = (int)(offset / 25.0D);
/*  85 */     if (lineIdx >= this.entries.size()) {
/*  86 */       return null;
/*     */     }
/*  88 */     TradeEntry entry = this.entries.get(lineIdx);
/*  89 */     if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom) {
/*  90 */       return entry;
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int button) {
/*  98 */     TradeEntry entry = findStackEntry((int)mouseX, (int)mouseY);
/*     */     
/* 100 */     boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
/*     */     
/* 102 */     if (isHovered && entry != null) {
/*     */       
/* 104 */       this.parent.setSelectedStack(entry);
/* 105 */       this.parent.setWantedAmount(1);
/*     */     } 
/*     */     
/* 108 */     return super.mouseClicked(mouseX, mouseY, button);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntry(ItemStack stack) {
/* 113 */     for (int i = 0; i < this.entries.size(); i++) {
/*     */       
/* 115 */       if (((TradeEntry)this.entries.get(i)).getItemStack().getItem() == stack.getItem())
/*     */       {
/* 117 */         this.entries.remove(i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\extra\ItemListScreenPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */