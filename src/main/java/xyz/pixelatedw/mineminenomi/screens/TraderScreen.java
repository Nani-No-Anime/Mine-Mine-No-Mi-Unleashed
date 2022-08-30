/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.screen.inventory.InventoryScreen;
/*     */ import net.minecraft.client.gui.widget.ToggleWidget;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModKeybindings;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CBuyFromTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.trade.CSellToTraderPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.FlickeringString;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.ItemListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.NewButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.SequencedString;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class TraderScreen extends Screen {
/*  47 */   private int guiState = 0;
/*  48 */   private int wantedAmount = 1;
/*     */   
/*     */   private ItemListScreenPanel listPanel;
/*     */   private TradeEntry selectedStack;
/*     */   private TradeEntry hoveredStack;
/*     */   private PlayerEntity player;
/*     */   private TraderEntity trader;
/*     */   private IEntityStats props;
/*     */   private SequencedString startMessage;
/*     */   private FlickeringString skipMessage;
/*     */   private ToggleWidget vearthAmountUp;
/*     */   private ToggleWidget vearthAmountDown;
/*     */   private int dirtBlocksAvailable;
/*  61 */   protected static final ResourceLocation RECIPE_BOOK = new ResourceLocation("textures/gui/recipe_book.png");
/*     */ 
/*     */   
/*     */   public TraderScreen(TraderEntity entity) {
/*  65 */     super((ITextComponent)new StringTextComponent(""));
/*  66 */     this.trader = entity;
/*  67 */     this.player = (PlayerEntity)(Minecraft.getInstance()).player;
/*  68 */     this.props = EntityStatsCapability.get((LivingEntity)this.player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int mouseX, int mouseY, float partialTicks) {
/*  74 */     renderBackground();
/*  75 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  77 */     switch (this.guiState) {
/*     */       
/*     */       case 0:
/*  80 */         renderMenu(mouseX, mouseY);
/*     */         break;
/*     */       case 1:
/*  83 */         renderSellShop(mouseX, mouseY, partialTicks);
/*     */         break;
/*     */       case 2:
/*  86 */         renderBuyShop(mouseX, mouseY, partialTicks);
/*     */         break;
/*     */     } 
/*     */     
/*  90 */     super.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderMenu(int x, int y) {
/*  95 */     int posX = this.width / 2;
/*  96 */     int posY = this.height / 2;
/*     */     
/*  98 */     if (!this.trader.canBuyFromPlayers()) {
/*     */       
/* 100 */       this.startMessage.render(posX - 200, posY - 50);
/* 101 */       this.skipMessage.render(posX - 100, posY + 60);
/*     */       
/* 103 */       if (this.startMessage.ticksExisted > this.startMessage.delayTicks) {
/*     */         
/* 105 */         this.guiState = 1;
/* 106 */         init(getMinecraft(), this.width, this.height);
/* 107 */         if (this.trader.canTrade(this.player)) {
/*     */           
/* 109 */           this.guiState = 1;
/* 110 */           init(getMinecraft(), this.width, this.height);
/*     */         } else {
/*     */           
/* 113 */           onClose();
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 118 */       this.startMessage.render(posX - 150, posY - 105);
/*     */     } 
/*     */ 
/*     */     
/* 122 */     RenderSystem.pushMatrix();
/*     */     
/* 124 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 125 */     RenderSystem.enableBlend();
/* 126 */     InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, this.trader);
/*     */     
/* 128 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderSellShop(int mouseX, int mouseY, float partialTicks) {
/* 134 */     getMinecraft().getTextureManager().bindTexture(ModResources.BLANK2);
/*     */     
/* 136 */     int posX = this.width / 2;
/* 137 */     int posY = this.height / 2;
/*     */     
/* 139 */     GuiUtils.drawTexturedModalRect(posX - 128, posY - 110, 0, 0, 256, 256, 0.0F);
/*     */     
/* 141 */     renderUpperColumn();
/*     */     
/* 143 */     drawSizedString((new TranslationTextComponent(ModI18n.GUI_NAME, new Object[0])).getFormattedText(), posX - 20, posY - 63, 0.9F, -1);
/* 144 */     drawSizedString((new TranslationTextComponent(ModI18n.GUI_PRICE, new Object[0])).getFormattedText(), posX + 50, posY - 63, 0.9F, -1);
/* 145 */     getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
/* 146 */     int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
/* 147 */     GuiUtils.drawTexturedModalRect(posX + 53, posY - 76, type, 32, 32, 64, 1.0F);
/*     */     
/* 149 */     this.listPanel.render(mouseX, mouseY, partialTicks);
/*     */     
/* 151 */     hover(mouseX, mouseY);
/*     */     
/* 153 */     if (this.selectedStack != null)
/*     */     {
/* 155 */       this.buttons.forEach(button -> button.render(mouseX, mouseY, partialTicks));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderBuyShop(int mouseX, int mouseY, float partialTicks) {
/* 163 */     int posX = this.width / 2;
/* 164 */     int posY = this.height / 2;
/*     */     
/* 166 */     this.startMessage.render(posX - 150, posY - 105);
/*     */     
/* 168 */     posX -= 110;
/* 169 */     if (this.trader instanceof SkypieanTraderEntity && ((SkypieanTraderEntity)this.trader).getTradesLeft() > 0) {
/*     */       
/* 171 */       renderItem(new ItemStack((IItemProvider)Blocks.DIRT.asItem()), posX, posY - 30);
/* 172 */       String amount = this.wantedAmount + "";
/* 173 */       WyHelper.drawStringWithBorder(this.font, amount, posX + 8 - this.font.getStringWidth(amount) / 2, posY - 7, -1);
/* 174 */       WyHelper.drawStringWithBorder(this.font, "=", posX + 60, posY - 7, -1);
/* 175 */       WyHelper.drawStringWithBorder(this.font, CurrencyHelper.getExtolFromBelly(this.wantedAmount) + "", posX + 100, posY - 7, -1);
/* 176 */       getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
/* 177 */       GuiUtils.drawTexturedModalRect(posX + 75, posY - 21, 34, 32, 32, 64, 1.0F);
/*     */     } 
/* 179 */     posX += 110;
/*     */     
/* 181 */     RenderSystem.pushMatrix();
/*     */     
/* 183 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 184 */     RenderSystem.enableBlend();
/* 185 */     InventoryScreen.drawEntityOnScreen(posX + 150, posY + 150, 100, 40.0F, 5.0F, (LivingEntity)this.trader);
/*     */     
/* 187 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderUpperColumn() {
/* 192 */     int posX = this.width / 2;
/* 193 */     int posY = this.height / 2;
/* 194 */     String amount = "";
/*     */     
/* 196 */     if (this.hoveredStack != null) {
/*     */       
/* 198 */       WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
/* 199 */       renderItem(this.hoveredStack.getItemStack(), posX - 110, posY - 100);
/* 200 */       amount = getWantedAmount() + "/" + this.hoveredStack.getCount();
/* 201 */       if (this.hoveredStack.hasInfiniteStock()) {
/* 202 */         amount = getWantedAmount() + "/∞";
/*     */       }
/* 204 */     } else if (getSelectedStack() != null) {
/*     */       
/* 206 */       WyHelper.drawIcon(ModResources.BLANK, posX - 117, posY - 105, 32, 42);
/* 207 */       renderItem(getSelectedStack().getItemStack(), posX - 110, posY - 100);
/* 208 */       amount = getWantedAmount() + "/" + getSelectedStack().getCount();
/* 209 */       if (getSelectedStack().hasInfiniteStock()) {
/* 210 */         amount = getWantedAmount() + "/∞";
/*     */       }
/*     */     } 
/* 213 */     long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
/* 214 */     drawSizedString(amount, posX - 70, posY - 100, 0.9F, -1);
/* 215 */     drawSizedString(currency + "", posX + 85, posY - 95, 0.9F, -1);
/* 216 */     getMinecraft().getTextureManager().bindTexture(ModResources.CURRENCIES);
/* 217 */     int type = (this.trader.getCurrency() == Currency.BELLY) ? 0 : 34;
/* 218 */     GuiUtils.drawTexturedModalRect(posX + 102, posY - 108, type, 32, 32, 64, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init(Minecraft mc, int width, int height) {
/* 224 */     super.init(mc, width, height);
/*     */     
/* 226 */     int posX = this.width / 2;
/* 227 */     int posY = this.height / 2;
/*     */     
/* 229 */     this.startMessage = new SequencedString("", 0, 0);
/*     */     
/* 231 */     if (this.skipMessage == null) {
/* 232 */       this.skipMessage = new FlickeringString("- " + (new TranslationTextComponent(ModI18n.GUI_CLICK_TO_SKIP, new Object[0])).getFormattedText() + " -", 20);
/*     */     }
/* 234 */     if (this.guiState == 0) {
/*     */       
/* 236 */       this.wantedAmount = 1;
/*     */       
/* 238 */       String message = "";
/* 239 */       if (this.trader.canTrade(this.player)) {
/* 240 */         message = (new TranslationTextComponent(ModI18n.TRADER_WELCOME_MESSAGE, new Object[0])).getFormattedText();
/*     */       } else {
/* 242 */         message = this.trader.getTradeFailMessage();
/*     */       } 
/* 244 */       this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 2);
/*     */       
/* 246 */       if (this.trader.canBuyFromPlayers())
/*     */       {
/* 248 */         NewButton buyButton = new NewButton(posX - 180, posY - 50, 100, 20, I18n.format("gui.buy", new Object[0]), btn -> {
/*     */               this.guiState = 1;
/*     */               
/*     */               init(getMinecraft(), this.width, this.height);
/*     */             });
/* 253 */         addButton((Widget)buyButton);
/*     */         
/* 255 */         NewButton sellButton = new NewButton(posX - 180, posY - 20, 100, 20, I18n.format("gui.sell", new Object[0]), btn -> {
/*     */               this.guiState = 2;
/*     */               
/*     */               init(getMinecraft(), this.width, this.height);
/*     */             });
/* 260 */         addButton((Widget)sellButton);
/*     */       }
/*     */     
/* 263 */     } else if (this.guiState == 1) {
/*     */       
/* 265 */       this.listPanel = new ItemListScreenPanel(this, this.trader.getTradingItems());
/* 266 */       this.children.add(this.listPanel);
/*     */ 
/*     */       
/* 269 */       TexturedIconButton incQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW, posX - 50, posY - 105, 10, 32, "", this::onIncreaseQuantity);
/* 270 */       incQtyBtn = incQtyBtn.setTextureInfo(posX - 62, posY - 105, 32, 32);
/* 271 */       addButton((Widget)incQtyBtn);
/*     */       
/* 273 */       TexturedIconButton decQtyBtn = new TexturedIconButton(ModResources.BRIGHT_WOOD_ARROW_DOWN, posX - 30, posY - 109, 10, 32, "", this::onDecreaseQuantity);
/* 274 */       decQtyBtn = decQtyBtn.setTextureInfo(posX - 42, posY - 109, 32, 32);
/* 275 */       addButton((Widget)decQtyBtn);
/*     */       
/* 277 */       TexturedIconButton buyBtn = new TexturedIconButton(ModResources.BLANK, posX - 10, posY - 100, 64, 22, (new TranslationTextComponent(ModI18n.GUI_BUY, new Object[0])).getFormattedText(), this::onBuy);
/* 278 */       buyBtn = buyBtn.setTextureInfo(posX - 10, posY - 100, 64, 32).setTextInfo(posX + 10, posY - 95, 1.0D);
/* 279 */       addButton((Widget)buyBtn);
/*     */       
/* 281 */       if (this.trader.canBuyFromPlayers())
/*     */       {
/* 283 */         NewButton backButton = new NewButton(posX - 200, posY + 78, 70, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
/*     */               this.guiState = 0;
/*     */               
/*     */               init(getMinecraft(), this.width, this.height);
/*     */             });
/* 288 */         addButton((Widget)backButton);
/*     */       }
/*     */     
/* 291 */     } else if (this.guiState == 2 && this.trader instanceof SkypieanTraderEntity) {
/*     */ 
/*     */       
/* 294 */       this.wantedAmount = 0;
/* 295 */       SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
/* 296 */       String message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_VEARTH, new Object[] { Integer.valueOf(10000), Long.valueOf(skypieanTrader.getExtolLeftInStock()) })).getFormattedText();
/* 297 */       if (skypieanTrader.getTradesLeft() <= 0)
/* 298 */         message = (new TranslationTextComponent(ModI18n.TRADER_SKYPIEAN_NO_EXTOL, new Object[0])).getFormattedText(); 
/* 299 */       this.startMessage = new SequencedString(message, 250, this.font.getStringWidth(message) / 3, 5000000);
/*     */       
/* 301 */       if (skypieanTrader.getTradesLeft() > 0) {
/*     */         
/* 303 */         NewButton tradeButton = new NewButton(posX - 80, posY + 20, 70, 20, I18n.format("gui.sell", new Object[0]), btn -> {
/*     */               WyNetwork.sendToServer(new CSellToTraderPacket(this.trader.getEntityId(), this.wantedAmount));
/*     */               
/*     */               Minecraft.getInstance().displayGuiScreen(null);
/*     */             });
/* 308 */         addButton((Widget)tradeButton);
/*     */       } 
/*     */       
/* 311 */       NewButton backButton = new NewButton(posX - 200, posY + 78, 70, 20, I18n.format("gui.cancel", new Object[0]), btn -> {
/*     */             this.guiState = 0;
/*     */             
/*     */             init(getMinecraft(), this.width, this.height);
/*     */           });
/* 316 */       addButton((Widget)backButton);
/*     */       
/* 318 */       this.dirtBlocksAvailable = 0;
/* 319 */       for (int i = 0; i < this.player.inventory.getSizeInventory(); i++) {
/*     */         
/* 321 */         ItemStack stack = this.player.inventory.getStackInSlot(i);
/* 322 */         if (stack != null && !stack.isEmpty() && stack.getItem() == Blocks.DIRT.asItem())
/*     */         {
/* 324 */           this.dirtBlocksAvailable += stack.getCount();
/*     */         }
/*     */       } 
/*     */       
/* 328 */       this.vearthAmountUp = new ToggleWidget(posX - 80, posY - 11, 12, 17, false);
/* 329 */       this.vearthAmountUp.initTextureValues(1, 208, 13, 18, RECIPE_BOOK);
/*     */       
/* 331 */       this.vearthAmountDown = new ToggleWidget(posX - 135, posY - 11, 12, 17, true);
/* 332 */       this.vearthAmountDown.initTextureValues(1, 208, 13, 18, RECIPE_BOOK);
/*     */       
/* 334 */       if (skypieanTrader.getTradesLeft() > 0) {
/*     */         
/* 336 */         addButton((Widget)this.vearthAmountUp);
/* 337 */         addButton((Widget)this.vearthAmountDown);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBuy(Button btn) {
/* 344 */     if (getSelectedStack() == null) {
/*     */       return;
/*     */     }
/* 347 */     if (getWantedAmount() > getSelectedStack().getCount() && !getSelectedStack().hasInfiniteStock()) {
/*     */       return;
/*     */     }
/* 350 */     if (getEmptySlots() < calculateSlotsFromCount(getWantedAmount())) {
/*     */       return;
/*     */     }
/* 353 */     int totalPrice = getSelectedStack().getPrice() * getWantedAmount();
/*     */     
/* 355 */     long currency = (this.trader.getCurrency() == Currency.BELLY) ? this.props.getBelly() : this.props.getExtol();
/*     */     
/* 357 */     if (currency < totalPrice) {
/*     */       return;
/*     */     }
/* 360 */     WyNetwork.sendToServer(new CBuyFromTraderPacket(this.trader.getEntityId(), getSelectedStack().getItemStack(), getWantedAmount()));
/*     */ 
/*     */     
/* 363 */     if (!getSelectedStack().hasInfiniteStock()) {
/*     */       
/* 365 */       int count = getSelectedStack().getCount() - this.wantedAmount;
/*     */       
/* 367 */       if (count <= 0) {
/* 368 */         this.trader.getTradingItems().remove(getSelectedStack());
/*     */       } else {
/* 370 */         getSelectedStack().setCount(count);
/*     */       } 
/*     */     } 
/* 373 */     setSelectedStack((TradeEntry)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onIncreaseQuantity(Button btn) {
/* 378 */     if (getSelectedStack() != null && (getWantedAmount() < getSelectedStack().getCount() || getSelectedStack().hasInfiniteStock())) {
/* 379 */       setWantedAmount(getWantedAmount() + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void onDecreaseQuantity(Button btn) {
/* 384 */     if (getSelectedStack() != null && getWantedAmount() > 1) {
/* 385 */       setWantedAmount(getWantedAmount() - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void renderItem(ItemStack stack, int posX, int posY) {
/* 390 */     Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(stack, posX, posY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawSizedString(String txt, int x, int y, float scale, int color) {
/* 395 */     RenderSystem.pushMatrix();
/* 396 */     RenderSystem.translated(x, y, 0.0D);
/* 397 */     RenderSystem.scalef(scale, scale, scale);
/*     */     
/* 399 */     if (color == -1) {
/* 400 */       color = WyHelper.hexToRGB("#FFFFFF").getRGB();
/*     */     }
/* 402 */     drawCenteredString(txt, 0, 0, color);
/*     */     
/* 404 */     RenderSystem.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void hover(int mouseX, int mouseY) {
/* 409 */     TradeEntry entry = this.listPanel.findStackEntry(mouseX, mouseY);
/* 410 */     if (entry != null) {
/*     */       
/* 412 */       this.hoveredStack = entry;
/* 413 */       setWantedAmount(1);
/*     */     }
/*     */     else {
/*     */       
/* 417 */       this.hoveredStack = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEmptySlots() {
/* 423 */     int i = 0;
/* 424 */     for (ItemStack stack : this.player.inventory.mainInventory) {
/*     */       
/* 426 */       if (stack.isEmpty())
/*     */       {
/* 428 */         i++;
/*     */       }
/*     */     } 
/* 431 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int calculateSlotsFromCount(int count) {
/* 436 */     double val = count / 64.0D;
/* 437 */     return MathHelper.ceil(val);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Integer> getStacks(int count) {
/* 442 */     List<Integer> list = new ArrayList<>();
/* 443 */     int j = 0;
/* 444 */     for (int i = 0; i < count; i += 64) {
/*     */       
/* 446 */       if (count - 64 * j < 64) {
/*     */         
/* 448 */         list.add(Integer.valueOf(count - 64 * j));
/*     */       }
/*     */       else {
/*     */         
/* 452 */         list.add(Integer.valueOf(64));
/*     */       } 
/* 454 */       j++;
/*     */     } 
/* 456 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredString(String txt, int posX, int posY, int color) {
/* 461 */     WyHelper.drawStringWithBorder(this.font, txt, posX - this.font.getStringWidth(txt) / 2, posY, color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(double mouseX, double mouseY, int partialTicks) {
/* 467 */     boolean flag = super.mouseClicked(mouseX, mouseY, partialTicks);
/*     */     
/* 469 */     if (this.guiState == 0 && !this.trader.canBuyFromPlayers()) {
/*     */       
/* 471 */       if (this.startMessage.ticksExisted < this.startMessage.maxTicks) {
/* 472 */         this.startMessage.ticksExisted = this.startMessage.maxTicks;
/*     */       
/*     */       }
/* 475 */       else if (this.trader.canTrade(this.player)) {
/*     */         
/* 477 */         this.guiState = 1;
/* 478 */         init(getMinecraft(), this.width, this.height);
/*     */       }
/*     */       else {
/*     */         
/* 482 */         onClose();
/*     */       }
/*     */     
/*     */     }
/* 486 */     else if (this.guiState == 2) {
/*     */       
/* 488 */       SkypieanTraderEntity skypieanTrader = (SkypieanTraderEntity)this.trader;
/* 489 */       if (this.vearthAmountUp.mouseClicked(mouseX, mouseY, partialTicks)) {
/*     */         
/* 491 */         int increaseAmount = 1;
/*     */         
/* 493 */         if (ModKeybindings.isSneaking()) {
/* 494 */           increaseAmount = 10;
/*     */         }
/* 496 */         if (this.wantedAmount + increaseAmount < skypieanTrader.getTradesLeft()) {
/*     */           
/* 498 */           if (this.wantedAmount + increaseAmount <= this.dirtBlocksAvailable) {
/* 499 */             this.wantedAmount += increaseAmount;
/*     */           } else {
/* 501 */             this.wantedAmount = 0;
/*     */           } 
/*     */         } else {
/* 504 */           this.wantedAmount = skypieanTrader.getTradesLeft();
/*     */         } 
/* 506 */       } else if (this.vearthAmountDown.mouseClicked(mouseX, mouseY, partialTicks)) {
/*     */         
/* 508 */         int decreaseAmount = 1;
/*     */         
/* 510 */         if (ModKeybindings.isSneaking()) {
/* 511 */           decreaseAmount = 10;
/*     */         }
/* 513 */         if (this.wantedAmount - decreaseAmount >= 0) {
/* 514 */           this.wantedAmount -= decreaseAmount;
/*     */         } else {
/* 516 */           this.wantedAmount = this.dirtBlocksAvailable;
/*     */         } 
/*     */       } 
/*     */     } 
/* 520 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public TradeEntry getSelectedStack() {
/* 525 */     return this.selectedStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedStack(TradeEntry selectedStack) {
/* 530 */     this.selectedStack = selectedStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWantedAmount() {
/* 535 */     return this.wantedAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWantedAmount(int wantedAmount) {
/* 540 */     this.wantedAmount = wantedAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 546 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(TraderEntity entity) {
/* 551 */     Minecraft.getInstance().displayGuiScreen(new TraderScreen(entity));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\TraderScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */