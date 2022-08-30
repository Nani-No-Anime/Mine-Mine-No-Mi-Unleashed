/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.dimension.DimensionType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncAbilityDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncWorldDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class PlayerStatsScreen
/*     */   extends Screen {
/*     */   private final PlayerEntity player;
/*     */   private ExtendedWorldData worldProps;
/*     */   private IEntityStats entityStatsProps;
/*     */   private IDevilFruit devilFruitProps;
/*     */   private IChallengesData challengesProps;
/*     */   private final boolean hasQuests;
/*     */   private final boolean hasChallenges;
/*     */   
/*     */   public PlayerStatsScreen(boolean hasQuests, boolean hasChallenges) {
/*  59 */     super((ITextComponent)new StringTextComponent(""));
/*  60 */     this.player = (PlayerEntity)(Minecraft.getInstance()).player;
/*  61 */     this.hasQuests = hasQuests;
/*  62 */     this.hasChallenges = hasChallenges;
/*  63 */     this.worldProps = ExtendedWorldData.get(this.player.world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  69 */     renderBackground();
/*     */     
/*  71 */     RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  73 */     int posX = (this.width - 256) / 2;
/*  74 */     int posY = (this.height - 256) / 2;
/*     */     
/*  76 */     String colaLabel = I18n.format(ModI18n.GUI_COLA, new Object[0]);
/*  77 */     String dorikiLabel = I18n.format(ModI18n.GUI_DORIKI, new Object[0]);
/*  78 */     String factionLabel = I18n.format(ModI18n.FACTION_NAME, new Object[0]);
/*  79 */     String raceLabel = I18n.format(ModI18n.RACE_NAME, new Object[0]);
/*  80 */     String styleLabel = I18n.format(ModI18n.STYLE_NAME, new Object[0]);
/*     */     
/*  82 */     String faction = WyHelper.getResourceName(this.entityStatsProps.getFaction());
/*  83 */     if (WyHelper.isNullOrEmpty(faction)) {
/*  84 */       faction = "empty";
/*     */     }
/*  86 */     String race = WyHelper.getResourceName(this.entityStatsProps.getRace().toLowerCase());
/*  87 */     if (WyHelper.isNullOrEmpty(race)) {
/*  88 */       race = "empty";
/*     */     }
/*  90 */     String style = WyHelper.getResourceName(this.entityStatsProps.getFightingStyle().toLowerCase());
/*  91 */     if (WyHelper.isNullOrEmpty(style)) {
/*  92 */       style = "empty";
/*     */     }
/*  94 */     String actualRank = "";
/*  95 */     if (this.entityStatsProps.isMarine()) {
/*     */       
/*  97 */       FactionHelper.MarineRank marineRank = this.entityStatsProps.getMarineRank();
/*  98 */       actualRank = (marineRank != null) ? (" - " + marineRank.getLocalizedName()) : "";
/*     */     }
/* 100 */     else if (this.entityStatsProps.isRevolutionary()) {
/*     */       
/* 102 */       FactionHelper.RevolutionaryRank revoRank = this.entityStatsProps.getRevolutionaryRank();
/* 103 */       actualRank = (revoRank != null) ? (" - " + revoRank.getLocalizedName()) : "";
/*     */     } 
/*     */     
/* 106 */     String factionActual = I18n.format("faction." + faction, new Object[0]) + actualRank;
/* 107 */     String raceActual = I18n.format("race." + race, new Object[0]);
/* 108 */     String styleActual = I18n.format("style." + style, new Object[0]);
/*     */     
/* 110 */     if (this.entityStatsProps.isCyborg())
/* 111 */       WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + colaLabel + ": " + TextFormatting.RESET + this.entityStatsProps.getCola() + " / " + this.entityStatsProps.getMaxCola(), posX - 50, posY + 50, -1); 
/* 112 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + dorikiLabel + ": " + TextFormatting.RESET + this.entityStatsProps.getDoriki(), posX - 50, posY + 70, -1);
/* 113 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + factionLabel + ": " + TextFormatting.RESET + factionActual, posX - 50, posY + 90, -1);
/* 114 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + raceLabel + ": " + TextFormatting.RESET + raceActual, posX - 50, posY + 110, -1);
/* 115 */     WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + styleLabel + ": " + TextFormatting.RESET + styleActual, posX - 50, posY + 130, -1);
/*     */     
/* 117 */     if (this.entityStatsProps.getBelly() > 0L) {
/*     */       
/* 119 */       WyHelper.drawStringWithBorder(this.font, "" + this.entityStatsProps.getBelly(), posX + 215, posY + 72, -1);
/* 120 */       this.minecraft.textureManager.bindTexture(ModResources.CURRENCIES);
/* 121 */       blit(posX + 190, posY + 60, 0, 32, 32, 64);
/*     */     } 
/*     */     
/* 124 */     if (this.entityStatsProps.getExtol() > 0L) {
/*     */       
/* 126 */       WyHelper.drawStringWithBorder(this.font, "" + this.entityStatsProps.getExtol(), posX + 215, posY + 102, -1);
/* 127 */       this.minecraft.textureManager.bindTexture(ModResources.CURRENCIES);
/* 128 */       blit(posX + 190, posY + 89, 34, 32, 64, 86);
/*     */     } 
/*     */     
/* 131 */     if (!WyHelper.isNullOrEmpty(this.devilFruitProps.getDevilFruit())) {
/*     */       
/* 133 */       ItemStack yamiFruit = new ItemStack((IItemProvider)ModAbilities.YAMI_YAMI_NO_MI);
/*     */       
/* 135 */       if (this.devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI)) {
/*     */         
/* 137 */         ItemStack df = DevilFruitHelper.getDevilFruitItem(this.devilFruitProps.getDevilFruit());
/* 138 */         String dfKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)df.getItem());
/*     */         
/* 140 */         boolean dual = (this.devilFruitProps.hasYamiPower() && !dfKey.equalsIgnoreCase("yami_yami"));
/*     */         
/* 142 */         if (dual) {
/* 143 */           this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText() + " + " + df.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
/*     */         } else {
/* 145 */           this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
/*     */         } 
/* 147 */         if (dual)
/* 148 */           drawItemStack(df, posX - 56, posY + 187, ""); 
/* 149 */         drawItemStack(yamiFruit, posX - 50, posY + 190, "");
/*     */       }
/*     */       else {
/*     */         
/* 153 */         ItemStack df = DevilFruitHelper.getDevilFruitItem(this.devilFruitProps.getDevilFruit());
/* 154 */         String fruitName = df.getDisplayName().getFormattedText();
/* 155 */         if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
/*     */           
/* 157 */           AkumaNoMiItem item = ((AkumaNoMiItem)df.getItem()).getReverseShiftedFruit(this.player.world);
/* 158 */           df = new ItemStack((IItemProvider)item);
/*     */         } 
/* 160 */         boolean doubleYamiCheck = false;
/*     */         
/* 162 */         String dfKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)df.getItem());
/* 163 */         if (dfKey.equalsIgnoreCase("yami_yami") && this.devilFruitProps.hasYamiPower()) {
/* 164 */           doubleYamiCheck = true;
/*     */         }
/* 166 */         if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck) {
/* 167 */           this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText() + " + " + df.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
/*     */         } else {
/* 169 */           this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + fruitName, (posX - 28), (posY + 194), -1);
/*     */         } 
/* 171 */         if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck)
/* 172 */           drawItemStack(yamiFruit, posX - 56, posY + 187, ""); 
/* 173 */         drawItemStack(df, posX - 50, posY + 190, "");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 189 */     WyNetwork.sendToServer(new CRequestSyncWorldDataPacket());
/*     */     
/* 191 */     this.entityStatsProps = EntityStatsCapability.get((LivingEntity)this.player);
/* 192 */     this.devilFruitProps = DevilFruitCapability.get((LivingEntity)this.player);
/* 193 */     this.challengesProps = ChallengesDataCapability.get(this.player);
/* 194 */     IQuestData questProps = QuestDataCapability.get(this.player);
/* 195 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)this.player);
/*     */     
/* 197 */     int posX = (this.width - 256) / 2 - 110;
/* 198 */     int posY = (this.height - 256) / 2;
/*     */     
/* 200 */     boolean hasAbilities = (abilityProps.countUnlockedAbilities(APIConfig.AbilityCategory.ALL) > 0);
/* 201 */     posX += 80;
/* 202 */     Button abilitiesButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_ABILITIES, new Object[0]), b -> WyNetwork.sendToServer(new CRequestSyncAbilityDataPacket(true)));
/*     */ 
/*     */ 
/*     */     
/* 206 */     if (!hasAbilities)
/* 207 */       abilitiesButton.active = false; 
/* 208 */     addButton((Widget)abilitiesButton);
/*     */     
/* 210 */     if (this.hasQuests) {
/*     */       
/* 212 */       boolean hasQuests = (questProps.countInProgressQuests() > 0);
/* 213 */       posX += 80;
/* 214 */       Button questsButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_QUESTS, new Object[0]), b -> WyNetwork.sendToServer(new CRequestSyncQuestDataPacket(true)));
/*     */ 
/*     */ 
/*     */       
/* 218 */       if (!hasQuests)
/* 219 */         questsButton.active = false; 
/* 220 */       addButton((Widget)questsButton);
/*     */     } 
/*     */     
/* 223 */     if (this.entityStatsProps.isPirate()) {
/*     */       
/* 225 */       boolean hasCrew = (this.worldProps.getCrewWithMember(this.player.getUniqueID()) != null);
/* 226 */       posX += 80;
/* 227 */       Button crewButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_CREW, new Object[0]), b -> Minecraft.getInstance().displayGuiScreen(new CrewDetailsScreen()));
/*     */ 
/*     */ 
/*     */       
/* 231 */       if (!hasCrew)
/* 232 */         crewButton.active = false; 
/* 233 */       addButton((Widget)crewButton);
/*     */     } 
/*     */     
/* 236 */     if (this.hasChallenges) {
/*     */       
/* 238 */       boolean hasChallenges = (this.challengesProps.countChallenges() > 0);
/* 239 */       posX += 80;
/* 240 */       Button challengesButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_CHALLENGES, new Object[0]), b -> WyNetwork.sendToServer(new COpenChallengesScreenPacket()));
/*     */ 
/*     */ 
/*     */       
/* 244 */       if (this.player.world.dimension.getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES))
/* 245 */         challengesButton.active = false; 
/* 246 */       if (!hasChallenges)
/* 247 */         challengesButton.active = false; 
/* 248 */       addButton((Widget)challengesButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawItemStack(ItemStack itemStack, int x, int y, String string) {
/* 260 */     GL11.glTranslatef(0.0F, 0.0F, 32.0F);
/* 261 */     this.itemRenderer.zLevel = 200.0F;
/* 262 */     FontRenderer font = null;
/* 263 */     if (itemStack != null)
/* 264 */       font = itemStack.getItem().getFontRenderer(itemStack); 
/* 265 */     if (font == null)
/* 266 */       font = this.font; 
/* 267 */     this.itemRenderer.renderItemAndEffectIntoGUI(itemStack, x, y);
/* 268 */     this.itemRenderer.zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void open(boolean hasQuests, boolean hasChallenges) {
/* 273 */     Minecraft.getInstance().displayGuiScreen(new PlayerStatsScreen(hasQuests, hasChallenges));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\PlayerStatsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */