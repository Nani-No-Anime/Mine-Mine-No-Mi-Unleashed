package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.api.enums.Currency;
import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.trade.CBuyFromTraderPacket;
import xyz.pixelatedw.mineminenomi.packets.client.trade.CSellToTraderPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.*;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends Screen {
  private int guiState = 0;
  private int wantedAmount = 1;
  
  private ItemListScreenPanel listPanel;
  private TradeEntry selectedStack;
  private TradeEntry hoveredStack;
  private PlayerEntity player;
  private TraderEntity trader;
  private IEntityStats props;
  private SequencedString startMessage;
  private FlickeringString skipMessage;
  private ToggleWidget vearthAmountUp;
  private ToggleWidget vearthAmountDown;
  private int dirtBlocksAvailable;
  protected static final ResourceLocation RECIPE_BOOK = new ResourceLocation("textures/gui/recipe_book.png");

  
  public TraderScreen(TraderEntity entity) {
    super((ITextComponent)new StringTextComponent(""));
    this.trader = entity;
    this.player = (PlayerEntity)(Minecraft.getInstance()).player;
    this.props = EntityStatsCapability.get((LivingEntity)this.player);
  }


  
  public void render(int mouseX, int mouseY, float partialTicks) {
    renderBackground();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    switch (this.guiState) {
      
      case 0:
        renderMenu(mouseX, mouseY);
        break;
      case 1:
        renderSellShop(mouseX, mouseY, partialTicks);
        break;
      case 2:
        renderBuyShop(mouseX, mouseY, partialTicks);
        break;
    } 
    
    super.render(mouseX, mouseY, partialTicks);
  }

  
  public void renderMenu(int x, int y) {
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    if (!this.trader.canBuyFromPlayers()) {
      
      this.startMessage.render(posX - 200, posY - 50);
      this.skipMessage.render(posX - 100, posY + 60);
      
      if (this.startMessage.ticksExisted > this.startMessage.delayTicks) {
        
        this.guiState = 1;
        init(getMinecraft(), this.width, this.height);
        if (this.trader.canTrade(this.player)) {
          
          this.guiState = 1;
          init(getMinecraft(), this.width, this.height);
        } else {
          
          onClose();
        } 
      } 
    } else {
      
      this.startMessage.render(posX - 150, posY - 105);
    } 

    
    RenderSystem.pushMatrix();
    
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableBlend();
    InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, this.trader);
    
    RenderSystem.popMatrix();
  }


  
  public void renderSellShop(int mouseX, int mouseY, float partialTicks) {
    getMinecraft().getTextureManager().bindTexture(ModResources.BLANK2);
    
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    GuiUtils.drawTexturedModalRect(posX - 128, posY - 110, 0, 0, 256, 256, 0.0F);
    
    renderUpperColumn();
    
    drawSizedString((new TranslationTextComponent(ModI18n.GUI_NAME, new Object[0])).getFormattedText(), posX - 20, posY - 63, 0.9F, -1);
    drawSizedString((new TranslationTextComponent(ModI18n.GUI_PRICE, new Object[0])).getFormattedText(), posX + 50, posY - 63, 0.9F, -1);
    getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
    int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
    GuiUtils.drawTexturedModalRect(posX + 53, posY - 76, type, 32, 32, 64, 1.0F);
    
    this.listPanel.render(mouseX, mouseY, partialTicks);
    
    hover(mouseX, mouseY);
    
    if (this.selectedStack != null)
    {
      this.buttons.forEach(button -> button.render(mouseX, mouseY, partialTicks));
    }
  }



  
  public void renderBuyShop(int mouseX, int mouseY, float partialTicks) {
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    this.startMessage.render(posX - 150, posY - 105);
    
    posX -= 110;
    if (this.trader instanceof SkypieanTraderEntity && ((SkypieanTraderEntity)this.trader).getTradesLeft() > 0) {
      
      renderItem(new ItemStack((IItemProvider)Blocks.DIRT.asItem()), posX, posY - 30);
      String amount = this.wantedAmount + "";
      WyHelper.drawStringWithBorder(this.font, amount, posX + 8 - this.font.getStringWidth(amount) / 2, posY - 7, -1);
      WyHelper.drawStringWithBorder(this.font, "=", posX + 60, posY - 7, -1);
      WyHelper.drawStringWithBorder(this.font, CurrencyHelper.getExtolFromBelly(this.wantedAmount) + "", posX + 100, posY - 7, -1);
      getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
      GuiUtils.drawTexturedModalRect(posX + 75, posY - 21, 34, 32, 32, 64, 1.0F);
    } 
    posX += 110;
    
    RenderSystem.pushMatrix();
    
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableBlend();
    InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trader);
    
    RenderSystem.popMatrix();
  }

  
  public void renderUpperColumn() {
    int posX = this.width / 2;
    int posY = this.height / 2;
    String amount = "";
    
    if (this.hoveredStack != null) {
      
      WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
      renderItem(this.hoveredStack.getItemStack(), posX - 110, posY - 100);
      amount = getWantedAmount() + "/" + this.hoveredStack.getCount();
      if (this.hoveredStack.hasInfiniteStock()) {
        amount = getWantedAmount() + "/∞";
      }
    } else if (getSelectedStack() != null) {
      
      WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
      renderItem(getSelectedStack().getItemStack(), posX - 110, posY - 100);
      amount = getWantedAmount() + "/" + getSelectedStack().getCount();
      if (getSelectedStack().hasInfiniteStock()) {
        amount = getWantedAmount() + "/∞";
      }
    } 
    long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
    drawSizedString(amount, posX - 70, posY - 100, 0.9F, -1);
    drawSizedString(currency + "", posX + 85, posY - 95, 0.9F, -1);
    getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
    int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
    GuiUtils.drawTexturedModalRect(posX + 102, posY - 108, type, 32, 32, 64, 1.0F);
  }


  
  public void init(Minecraft mc, int width, int height) {
    super.init(mc, width, height);
    
    int posX = this.width / 2;
    int posY = this.height / 2;
    
    this.startMessage = new SequencedString("", 0, 0);
    
    if (this.skipMessage == null) {
      this.skipMessage = new FlickeringString("- " + (new TranslationTextComponent(ModI18n.GUI_CLICK_TO_SKIP, new Object[0])).getFormattedText() + " -", 20);
    }
    if (this.guiState == 0) {
      
      this.wantedAmount = 1;
      
      String message = "";
      if (this.trader.canTrade(this.player)) {
        message = (new TranslationTextComponent(ModI18n.TRADER_WELCOME_MESSAGE, new Object[0])).getFormattedText();
      } else {
        message = this.trader.getTradeFailMessage();
      } 
      this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
      
      if (this.trader.canBuyFromPlayers())
      {
        NewButton buyButton = new NewButton(posX - 180, posY - 50, 100, 20, I18n.format("gui.buy", new Object[0]), btn -> {
              this.guiState = 1;
              
              init(getMinecraft(), this.width, this.height);
            });
        addButton((Widget)buyButton);
        
        NewButton sellButton = new NewButton(posX - 180, posY - 20, 100, 20, I18n.format("gui.sell", new Object[0]), btn -> {
              this.guiState = 2;
              
              init(getMinecraft(), this.width, this.height);
            });
        addButton((Widget)sellButton);
      }
    
    } else if (this.guiState == 1) {
      
      this.listPanel = new ItemListScreenPanel(this, this.trader.getTradingItems());
      this.children.add(this.listPanel);

      
      TexturedIconButton incQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW, posX - 50, posY - 105, 10, 32, "", this::onIncreaseQuantity);
      incQtyBtn = incQtyBtn.setTextureInfo(posX - 62, posY - 105, 32, 32);
      addButton((Widget)incQtyBtn);
      
      TexturedIconButton decQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_DOWN, posX - 30, posY - 109, 10, 32, "", this::onDecreaseQuantity);
      decQtyBtn = decQtyBtn.setTextureInfo(posX - 42, posY - 109, 32, 32);
      addButton((Widget)decQtyBtn);
      
      TexturedIconButton buyBtn = new TexturedIconButton(ModResources.BLANK, posX - 10, posY - 100, 64, 22, (new TranslationTextComponent(ModI18n.GUI_BUY, new Object[0])).getFormattedText(), this::onBuy);
      buyBtn = buyBtn.setTextureInfo(posX - 10, posY - 100, 64, 32).setTextInfo(posX + 10, posY - 95, 1.0D);
      addButton((Widget)buyBtn);
      
      if (this.trader.canBuyFromPlayers())
      {
        NewButton backButton = new NewButton(posX - 200, posY + 78, 70, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
              this.guiState = 0;
              
              init(getMinecraft(), this.width, this.height);
            });
        addButton((Widget)backButton);
      }
    
    } else if (this.guiState == 2 && this.trader instanceof SkypieanTraderEntity) {

      
      this.wantedAmount = 0;
      SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
      String message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_VEARTH, new Object[] { Integer.valueOf(10000), Long.valueOf(skypieanTrader.getExtolLeftInStock()) })).getFormattedText();
      if (skypieanTrader.getTradesLeft() <= 0)
        message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_NO_EXTOL, new Object[0])).getFormattedText(); 
      this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 3, 5000000);
      
      if (skypieanTrader.getTradesLeft() > 0) {
        
        NewButton tradeButton = new NewButton(posX - 80, posY + 20, 70, 20, I18n.format("gui.sell", new Object[0]), btn -> {
              WyNetwork.sendToServer(new CSellToTraderPacket(this.trader.getEntityId(), this.wantedAmount));
              
              Minecraft.getInstance().displayGuiScreen(null);
            });
        addButton((Widget)tradeButton);
      } 
      
      NewButton backButton = new NewButton(posX - 200, posY + 78, 70, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
            this.guiState = 0;
            
            init(getMinecraft(), this.width, this.height);
          });
      addButton((Widget)backButton);
      
      this.dirtBlocksAvailable = 0;
      for (int i = 0; i < this.player.inventory.getSizeInventory(); i++) {
        
        ItemStack stack = this.player.inventory.getStackInSlot(i);
        if (stack != null && !stack.isEmpty() && stack.getItem() == Blocks.DIRT.asItem())
        {
          this.dirtBlocksAvailable += stack.getCount();
        }
      } 
      
      this.vearthAmountUp = new ToggleWidget(posX - 80, posY - 11, 12, 17, false);
      this.vearthAmountUp.initTextureValues(1, 208, 13, 18, RECIPE_BOOK);
      
      this.vearthAmountDown = new ToggleWidget(posX - 135, posY - 11, 12, 17, true);
      this.vearthAmountDown.initTextureValues(1, 208, 13, 18, RECIPE_BOOK);
      
      if (skypieanTrader.getTradesLeft() > 0) {
        
        addButton((Widget)this.vearthAmountUp);
        addButton((Widget)this.vearthAmountDown);
      } 
    } 
  }

  
  public void onBuy(Button btn) {
    if (getSelectedStack() == null) {
      return;
    }
    if (getWantedAmount() > getSelectedStack().getCount() && !getSelectedStack().hasInfiniteStock()) {
      return;
    }
    if (getEmptySlots() < calculateSlotsFromCount(getWantedAmount())) {
      return;
    }
    int totalPrice = getSelectedStack().getPrice() * getWantedAmount();
    
    long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
    
    if (currency < totalPrice) {
      return;
    }
    WyNetwork.sendToServer(new CBuyFromTraderPacket(this.trader.getEntityId(), getSelectedStack().getItemStack(), getWantedAmount()));

    
    if (!getSelectedStack().hasInfiniteStock()) {
      
      int count = getSelectedStack().getCount() - this.wantedAmount;
      
      if (count <= 0) {
        this.trader.getTradingItems().remove(getSelectedStack());
      } else {
        getSelectedStack().setCount(count);
      } 
    } 
    setSelectedStack((TradeEntry)null);
  }

  
  public void onIncreaseQuantity(Button btn) {
    if (getSelectedStack() != null && (getWantedAmount() < getSelectedStack().getCount() || getSelectedStack().hasInfiniteStock())) {
      setWantedAmount(getWantedAmount() + 1);
    }
  }
  
  public void onDecreaseQuantity(Button btn) {
    if (getSelectedStack() != null && getWantedAmount() > 1) {
      setWantedAmount(getWantedAmount() - 1);
    }
  }
  
  public void renderItem(ItemStack stack, int posX, int posY) {
    Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(stack, posX, posY);
  }

  
  public void drawSizedString(String txt, int x, int y, float scale, int color) {
    RenderSystem.pushMatrix();
    RenderSystem.translated(x, y, 0.0D);
    RenderSystem.scalef(scale, scale, scale);
    
    if (color == -1) {
      color = WyHelper.hexToRGB("#FFFFFF").getRGB();
    }
    drawCenteredString(txt, 0, 0, color);
    
    RenderSystem.popMatrix();
  }

  
  public void hover(int mouseX, int mouseY) {
    TradeEntry entry = this.listPanel.findStackEntry(mouseX, mouseY);
    if (entry != null) {
      
      this.hoveredStack = entry;
      setWantedAmount(1);
    }
    else {
      
      this.hoveredStack = null;
    } 
  }

  
  public int getEmptySlots() {
    int i = 0;
    for (ItemStack stack : this.player.inventory.mainInventory) {
      
      if (stack.isEmpty())
      {
        i++;
      }
    } 
    return i;
  }

  
  public int calculateSlotsFromCount(int count) {
    double val = count / 64.0D;
    return MathHelper.ceil(val);
  }

  
  public List<Integer> getStacks(int count) {
    List<Integer> list = new ArrayList<>();
    int j = 0;
    for (int i = 0; i < count; i += 64) {
      
      if (count - 64 * j < 64) {
        
        list.add(Integer.valueOf(count - 64 * j));
      }
      else {
        
        list.add(Integer.valueOf(64));
      } 
      j++;
    } 
    return list;
  }

  
  public void drawCenteredString(String txt, int posX, int posY, int color) {
    WyHelper.drawStringWithBorder(this.font, txt, posX - this.font.getStringWidth(txt) / 2, posY, color);
  }


  
  public boolean mouseClicked(double mouseX, double mouseY, int partialTicks) {
    boolean flag = super.mouseClicked(mouseX, mouseY, partialTicks);
    
    if (this.guiState == 0 && !this.trader.canBuyFromPlayers()) {
      
      if (this.startMessage.ticksExisted < this.startMessage.maxTicks) {
        this.startMessage.ticksExisted = this.startMessage.maxTicks;
      
      }
      else if (this.trader.canTrade(this.player)) {
        
        this.guiState = 1;
        init(getMinecraft(), this.width, this.height);
      }
      else {
        
        onClose();
      }
    
    }
    else if (this.guiState == 2) {
      
      SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
      if (this.vearthAmountUp.mouseClicked(mouseX, mouseY, partialTicks)) {
        
        int increaseAmount = 1;
        
        if (ModKeybindings.isSneaking()) {
          increaseAmount = 10;
        }
        if (this.wantedAmount + increaseAmount < skypieanTrader.getTradesLeft()) {
          
          if (this.wantedAmount + increaseAmount <= this.dirtBlocksAvailable) {
            this.wantedAmount += increaseAmount;
          } else {
            this.wantedAmount = 0;
          } 
        } else {
          this.wantedAmount = skypieanTrader.getTradesLeft();
        } 
      } else if (this.vearthAmountDown.mouseClicked(mouseX, mouseY, partialTicks)) {
        
        int decreaseAmount = 1;
        
        if (ModKeybindings.isSneaking()) {
          decreaseAmount = 10;
        }
        if (this.wantedAmount - decreaseAmount >= 0) {
          this.wantedAmount -= decreaseAmount;
        } else {
          this.wantedAmount = this.dirtBlocksAvailable;
        } 
      } 
    } 
    return flag;
  }

  
  public TradeEntry getSelectedStack() {
    return this.selectedStack;
  }

  
  public void setSelectedStack(TradeEntry selectedStack) {
    this.selectedStack = selectedStack;
  }

  
  public int getWantedAmount() {
    return this.wantedAmount;
  }

  
  public void setWantedAmount(int wantedAmount) {
    this.wantedAmount = wantedAmount;
  }


  
  public boolean isPauseScreen() {
    return false;
  }

  
  public static void open(TraderEntity entity) {
    Minecraft.getInstance().displayGuiScreen(new TraderScreen(entity));
  }
}


