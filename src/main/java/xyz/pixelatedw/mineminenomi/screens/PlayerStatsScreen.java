package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.events.devilfruits.RandomFruitEvents;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncAbilityDataPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.packets.client.CRequestSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.packets.client.ui.COpenChallengesScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

@OnlyIn(Dist.CLIENT)
public class PlayerStatsScreen
  extends Screen {
  private final PlayerEntity player;
  private ExtendedWorldData worldProps;
  private IEntityStats entityStatsProps;
  private IDevilFruit devilFruitProps;
  private IChallengesData challengesProps;
  private final boolean hasQuests;
  private final boolean hasChallenges;
  
  public PlayerStatsScreen(boolean hasQuests, boolean hasChallenges) {
    super((ITextComponent)new StringTextComponent(""));
    this.player = (PlayerEntity)(Minecraft.getInstance()).player;
    this.hasQuests = hasQuests;
    this.hasChallenges = hasChallenges;
    this.worldProps = ExtendedWorldData.get(this.player.world);
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    
    int posX = (this.width - 256) / 2;
    int posY = (this.height - 256) / 2;
    
    String colaLabel = I18n.format(ModI18n.GUI_COLA, new Object[0]);
    String dorikiLabel = I18n.format(ModI18n.GUI_DORIKI, new Object[0]);
    String factionLabel = I18n.format(ModI18n.FACTION_NAME, new Object[0]);
    String raceLabel = I18n.format(ModI18n.RACE_NAME, new Object[0]);
    String styleLabel = I18n.format(ModI18n.STYLE_NAME, new Object[0]);
    
    String faction = WyHelper.getResourceName(this.entityStatsProps.getFaction());
    if (WyHelper.isNullOrEmpty(faction)) {
      faction = "empty";
    }
    String race = WyHelper.getResourceName(this.entityStatsProps.getRace().toLowerCase());
    if (WyHelper.isNullOrEmpty(race)) {
      race = "empty";
    }
    String style = WyHelper.getResourceName(this.entityStatsProps.getFightingStyle().toLowerCase());
    if (WyHelper.isNullOrEmpty(style)) {
      style = "empty";
    }
    String actualRank = "";
    if (this.entityStatsProps.isMarine()) {
      
      FactionHelper.MarineRank marineRank = this.entityStatsProps.getMarineRank();
      actualRank = (marineRank != null) ? (" - " + marineRank.getLocalizedName()) : "";
    }
    else if (this.entityStatsProps.isRevolutionary()) {
      
      FactionHelper.RevolutionaryRank revoRank = this.entityStatsProps.getRevolutionaryRank();
      actualRank = (revoRank != null) ? (" - " + revoRank.getLocalizedName()) : "";
    } 
    
    String factionActual = I18n.format("faction." + faction, new Object[0]) + actualRank;
    String raceActual = I18n.format("race." + race, new Object[0]);
    String styleActual = I18n.format("style." + style, new Object[0]);
    
    if (this.entityStatsProps.isCyborg())
      WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + colaLabel + ": " + TextFormatting.RESET + this.entityStatsProps.getCola() + " / " + this.entityStatsProps.getMaxCola(), posX - 50, posY + 50, -1); 
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + dorikiLabel + ": " + TextFormatting.RESET + this.entityStatsProps.getDoriki(), posX - 50, posY + 70, -1);
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + factionLabel + ": " + TextFormatting.RESET + factionActual, posX - 50, posY + 90, -1);
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + raceLabel + ": " + TextFormatting.RESET + raceActual, posX - 50, posY + 110, -1);
    WyHelper.drawStringWithBorder(this.font, TextFormatting.BOLD + styleLabel + ": " + TextFormatting.RESET + styleActual, posX - 50, posY + 130, -1);
    
    if (this.entityStatsProps.getBelly() > 0L) {
      
      WyHelper.drawStringWithBorder(this.font, "" + this.entityStatsProps.getBelly(), posX + 215, posY + 72, -1);
      this.minecraft.textureManager.bindTexture(ModResources.CURRENCIES);
      blit(posX + 190, posY + 60, 0, 32, 32, 64);
    } 
    
    if (this.entityStatsProps.getExtol() > 0L) {
      
      WyHelper.drawStringWithBorder(this.font, "" + this.entityStatsProps.getExtol(), posX + 215, posY + 102, -1);
      this.minecraft.textureManager.bindTexture(ModResources.CURRENCIES);
      blit(posX + 190, posY + 89, 34, 32, 64, 86);
    } 
    
    if (!WyHelper.isNullOrEmpty(this.devilFruitProps.getDevilFruit())) {
      
      ItemStack yamiFruit = new ItemStack((IItemProvider)ModAbilities.YAMI_YAMI_NO_MI);
      
      if (this.devilFruitProps.hasDevilFruit(ModAbilities.YAMI_YAMI_NO_MI)) {
        
        ItemStack df = DevilFruitHelper.getDevilFruitItem(this.devilFruitProps.getDevilFruit());
        String dfKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)df.getItem());
        
        boolean dual = (this.devilFruitProps.hasYamiPower() && !dfKey.equalsIgnoreCase("yami_yami"));
        
        if (dual) {
          this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText() + " + " + df.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
        } else {
          this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
        } 
        if (dual)
          drawItemStack(df, posX - 56, posY + 187, ""); 
        drawItemStack(yamiFruit, posX - 50, posY + 190, "");
      }
      else {
        
        ItemStack df = DevilFruitHelper.getDevilFruitItem(this.devilFruitProps.getDevilFruit());
        String fruitName = df.getDisplayName().getFormattedText();
        if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT) {
          
          AkumaNoMiItem item = ((AkumaNoMiItem)df.getItem()).getReverseShiftedFruit(this.player.world);
          df = new ItemStack((IItemProvider)item);
        } 
        boolean doubleYamiCheck = false;
        
        String dfKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)df.getItem());
        if (dfKey.equalsIgnoreCase("yami_yami") && this.devilFruitProps.hasYamiPower()) {
          doubleYamiCheck = true;
        }
        if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck) {
          this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + yamiFruit.getDisplayName().getFormattedText() + " + " + df.getDisplayName().getFormattedText(), (posX - 28), (posY + 194), -1);
        } else {
          this.minecraft.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "" + fruitName, (posX - 28), (posY + 194), -1);
        } 
        if (this.devilFruitProps.hasYamiPower() && !doubleYamiCheck)
          drawItemStack(yamiFruit, posX - 56, posY + 187, ""); 
        drawItemStack(df, posX - 50, posY + 190, "");
      } 
    } 




    
    super.render(x, y, f);
  }




  
  public void init() {
    WyNetwork.sendToServer(new CRequestSyncWorldDataPacket());
    
    this.entityStatsProps = EntityStatsCapability.get((LivingEntity)this.player);
    this.devilFruitProps = DevilFruitCapability.get((LivingEntity)this.player);
    this.challengesProps = ChallengesDataCapability.get(this.player);
    IQuestData questProps = QuestDataCapability.get(this.player);
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)this.player);
    
    int posX = (this.width - 256) / 2 - 110;
    int posY = (this.height - 256) / 2;
    
    boolean hasAbilities = (abilityProps.countUnlockedAbilities(APIConfig.AbilityCategory.ALL) > 0);
    posX += 80;
    Button abilitiesButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_ABILITIES, new Object[0]), b -> WyNetwork.sendToServer(new CRequestSyncAbilityDataPacket(true)));


    
    if (!hasAbilities)
      abilitiesButton.active = false; 
    addButton((Widget)abilitiesButton);
    
    if (this.hasQuests) {
      
      boolean hasQuests = (questProps.countInProgressQuests() > 0);
      posX += 80;
      Button questsButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_QUESTS, new Object[0]), b -> WyNetwork.sendToServer(new CRequestSyncQuestDataPacket(true)));


      
      if (!hasQuests)
        questsButton.active = false; 
      addButton((Widget)questsButton);
    } 
    
    if (this.entityStatsProps.isPirate()) {
      
      boolean hasCrew = (this.worldProps.getCrewWithMember(this.player.getUniqueID()) != null);
      posX += 80;
      Button crewButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_CREW, new Object[0]), b -> Minecraft.getInstance().displayGuiScreen(new CrewDetailsScreen()));


      
      if (!hasCrew)
        crewButton.active = false; 
      addButton((Widget)crewButton);
    } 
    
    if (this.hasChallenges) {
      
      boolean hasChallenges = (this.challengesProps.countChallenges() > 0);
      posX += 80;
      Button challengesButton = new Button(posX, posY + 210, 70, 20, I18n.format(ModI18n.GUI_CHALLENGES, new Object[0]), b -> WyNetwork.sendToServer(new COpenChallengesScreenPacket()));


      
      if (this.player.world.dimension.getType() == DimensionType.byName(ModResources.DIMENSION_TYPE_CHALLENGES))
        challengesButton.active = false; 
      if (!hasChallenges)
        challengesButton.active = false; 
      addButton((Widget)challengesButton);
    } 
  }


  
  public boolean isPauseScreen() {
    return false;
  }

  
  private void drawItemStack(ItemStack itemStack, int x, int y, String string) {
    GL11.glTranslatef(0.0F, 0.0F, 32.0F);
    this.itemRenderer.zLevel = 200.0F;
    FontRenderer font = null;
    if (itemStack != null)
      font = itemStack.getItem().getFontRenderer(itemStack); 
    if (font == null)
      font = this.font; 
    this.itemRenderer.renderItemAndEffectIntoGUI(itemStack, x, y);
    this.itemRenderer.zLevel = 0.0F;
  }

  
  public static void open(boolean hasQuests, boolean hasChallenges) {
    Minecraft.getInstance().displayGuiScreen(new PlayerStatsScreen(hasQuests, hasChallenges));
  }
}


