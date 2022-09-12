package xyz.pixelatedw.mineminenomi.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.client.ability.CRemoveAbilityPacket;
import xyz.pixelatedw.mineminenomi.screens.extra.AbilitiesListScreenPanel;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.client.CSyncAbilityDataPacket;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SelectHotbarAbilitiesScreen extends Screen {
  protected PlayerEntity player;
  public int slotSelected = -1; private AbilitiesListScreenPanel abilitiesList;
  public int abilitySelected = -1;
  
  protected boolean forceUpdate = false;
  
  private IAbilityData abilityDataProps;
  
  public SelectHotbarAbilitiesScreen(PlayerEntity player) {
    super((ITextComponent)new StringTextComponent(""));
    this.player = player;
    this.minecraft = Minecraft.getInstance();
    
    this.abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
  }


  
  public void render(int x, int y, float f) {
    renderBackground();
    
    Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
    
    int posX = this.width;
    int posY = this.height;
    
    blit((posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256);
    blit((posX - 250) / 2, posY - 60, 0, 0, 256, 256);
    
    WyHelper.drawStringWithBorder(this.minecraft.fontRenderer, this.abilityDataProps.getCombatBarSet() + "", posX / 2 - 110, posY - 24, WyHelper.hexToRGB("#FFFFFF").getRGB());
    
    this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
    
    RenderSystem.enableBlend();
    int i;
    for (i = 0; i < 8; i++) {
      
      if (this.slotSelected == i + this.abilityDataProps.getCombatBarSet() * 8) {
        RenderSystem.color4f(0.0F, 0.0F, 1.0F, 1.0F);
      }
      blit(posX / 2 - 102 + i * 25, posY - 33, 0, 0, 23, 23);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    
    for (i = 0; i < 8; i++) {
      
      int j = i + this.abilityDataProps.getCombatBarSet() * 8;
      RenderSystem.blendFuncSeparate(770, 771, 1, 0);
      if (this.abilityDataProps.getEquippedAbility(j) != null) {
        RendererHelper.drawAbilityIcon(WyHelper.getResourceName(this.abilityDataProps.getEquippedAbility(j).getName()), posX / 2 - 98 + i * 25, posY - 29, 16, 16);
      }
    } 
    RenderSystem.disableBlend();


    
    if (this.abilitiesList != null) {
      this.abilitiesList.render(x, y, f);
    }

    
    super.render(x, y, f);
  }


  
  public void init() {
    int posX = this.width;
    int posY = this.height;
    
    int posX2 = this.width / 2;
    int posY2 = this.height / 2;

    
    int idx = 0;
    for (APIConfig.AbilityCategory category : APIConfig.AbilityCategory.values()) {
      
      if (category != APIConfig.AbilityCategory.ALL) {

        
        Ability abl = this.abilityDataProps.getUnlockedAbilities(category).stream().findFirst().orElse(null);
        if (abl != null) {
          
          if (idx == 4) {
            
            posX2 += 260;
            posY2 -= 140;
          } 
          
          int posY3 = posY2 - 100 + idx * 70 / 2;
          
          String iconName = abl.getName();
          ResourceLocation icon = category.getIcon(this.player);
          if (icon == null)
            icon = new ResourceLocation("mineminenomi", "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"); 
          TexturedIconButton button = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX2 - 145, posY3, 30, 30, "", btn -> updateListScreen(category));
          button = button.setTextureInfo(posX2 - 145, posY3, 30, 40).setIconInfo(icon, posX2 - 135, posY3 + 9, 1.45D);
          addButton((Widget)button);
          
          idx++;
        } 
      } 
    } 
    
    for (int i = 0; i < 8; i++) {
      
      RenderSystem.enableBlend();
      int id = i + this.abilityDataProps.getCombatBarSet() * 8;
      NoTextureButton slotButton = new NoTextureButton(posX / 2 - 101 + i * 25, posY - 31, 22, 21, "", btn -> {
            if (this.slotSelected != id) {
              Ability ability = this.abilityDataProps.getEquippedAbility(id);

              
              if (ability == null) {
                this.slotSelected = id;
              } else if (ability.isOnStandby()) {
                this.slotSelected = id;
              } 
            } else {
              Ability ability = this.abilityDataProps.getEquippedAbility(this.slotSelected);

              
              if (ability != null && ability.isOnStandby()) {
                WyNetwork.sendToServer(new CRemoveAbilityPacket(this.slotSelected));

                
                this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
              } 
            } 
          });
      
      slotButton.setFake();
      addButton((Widget)slotButton);
    } 
    
    updateListScreen(AbilityHelper.getDevilFruitCategory());
  }

  
  public void updateListScreen(APIConfig.AbilityCategory category) {
    this.children.remove(this.abilitiesList);
    
    List<Ability> list = this.abilityDataProps.getUnlockedAbilities(category);
    Ability[] arr = new Ability[list.size()];
    arr = list.<Ability>toArray(arr);
    
    this.abilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
    this.children.add(this.abilitiesList);
    setFocused((IGuiEventListener)this.abilitiesList);
  }


  
  public void onClose() {
    if (this.forceUpdate)
      WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityDataProps)); 
    super.onClose();
  }


  
  public boolean isPauseScreen() {
    return false;
  }

  
  public void markDirty() {
    this.forceUpdate = true;
  }
}


