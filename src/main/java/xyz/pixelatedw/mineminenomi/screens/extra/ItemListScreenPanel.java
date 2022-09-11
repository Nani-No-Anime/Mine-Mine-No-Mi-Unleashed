package xyz.pixelatedw.mineminenomi.screens.extra;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.gui.ScrollPanel;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ItemListScreenPanel
  extends ScrollPanel
{
  private TraderScreen parent;
  private List<TradeEntry> entries = new ArrayList<>();
  
  private static final int ENTRY_HEIGHT = 20;
  
  public ItemListScreenPanel(TraderScreen parent, List<TradeEntry> list) {
    super(parent.getMinecraft(), 215, 140, parent.height / 2 - 50, parent.width / 2 - 109);
    
    this.parent = parent;
    this.entries = list;
    
    for (TradeEntry entry : this.entries) {
      
      if (entry.getItemStack().getOrCreateTag().getBoolean("isClone") && !entry.getItemStack().getOrCreateTag().getBoolean("hasCloneTag")) {
        
        entry.getItemStack().setDisplayName((ITextComponent)new StringTextComponent(TextFormatting.RESET + entry.getItemStack().getDisplayName().getFormattedText() + " (Replica)"));
        entry.getItemStack().getOrCreateTag().putBoolean("hasCloneTag", true);
      } 
    } 
  }


  
  public boolean mouseReleased(double mouseX, double mouseY, int partialTicks) {
    return true;
  }


  
  protected int getContentHeight() {
    return (int)(this.entries.size() * 25.0D + 2.0D);
  }


  
  protected int getScrollAmount() {
    return 10;
  }


  
  protected void drawPanel(int entryRight, int relativeY, Tessellator tess, int mouseX, int mouseY) {
    for (TradeEntry entry : this.entries) {
      
      int y = relativeY;
      int x = this.parent.width / 2 - 109 + 40;
      
      this.parent.renderItem(entry.getItemStack(), x - 30, y - 1);
      if (this.parent.getSelectedStack() != null && entry.getItemStack().hashCode() == this.parent.getSelectedStack().getItemStack().hashCode()) {
        WyHelper.drawColourOnScreen(Color.WHITE.getRGB(), 100, (x - 40), (y - 4), this.width, 24.0D, 0.0D);
      }
      this.parent.drawSizedString(entry.getItemStack().getDisplayName().getFormattedText(), x + 50, y + 4, 0.8F, -1);
      this.parent.drawSizedString(entry.getPrice() + "", x + 122, y + 4, 0.8F, -1);
      relativeY = (int)(relativeY + 25.0D);
    } 
  }

  
  public TradeEntry findStackEntry(int mouseX, int mouseY) {
    double offset = ((mouseY - this.top) + this.scrollDistance);
    boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < this.left + this.width - 5 && mouseY < this.top + this.height);
    
    if (offset <= 0.0D || !isHovered) {
      return null;
    }
    int lineIdx = (int)(offset / 25.0D);
    if (lineIdx >= this.entries.size()) {
      return null;
    }
    TradeEntry entry = this.entries.get(lineIdx);
    if (entry != null && mouseX >= this.left && mouseX <= this.right && mouseY <= this.bottom) {
      return entry;
    }
    return null;
  }


  
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    TradeEntry entry = findStackEntry((int)mouseX, (int)mouseY);
    
    boolean isHovered = (mouseX >= this.left && mouseY >= this.top && mouseX < (this.left + this.width - 5) && mouseY < (this.top + this.height));
    
    if (isHovered && entry != null) {
      
      this.parent.setSelectedStack(entry);
      this.parent.setWantedAmount(1);
    } 
    
    return super.mouseClicked(mouseX, mouseY, button);
  }

  
  public void removeEntry(ItemStack stack) {
    for (int i = 0; i < this.entries.size(); i++) {
      
      if (((TradeEntry)this.entries.get(i)).getItemStack().getItem() == stack.getItem())
      {
        this.entries.remove(i);
      }
    } 
  }
}


