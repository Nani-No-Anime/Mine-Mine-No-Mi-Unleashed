/*     */ package xyz.pixelatedw.mineminenomi.screens;
/*     */ 
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.IGuiEventListener;
/*     */ import net.minecraft.client.gui.screen.Screen;
/*     */ import net.minecraft.client.gui.widget.Widget;
/*     */ import net.minecraft.client.gui.widget.button.Button;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.client.ability.CRemoveAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.AbilitiesListScreenPanel;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
/*     */ import xyz.pixelatedw.mineminenomi.screens.extra.TexturedIconButton;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.client.CSyncAbilityDataPacket;
/*     */ 
/*     */ @OnlyIn(Dist.CLIENT)
/*     */ public class SelectHotbarAbilitiesScreen extends Screen {
/*     */   protected PlayerEntity player;
/*  35 */   public int slotSelected = -1; private AbilitiesListScreenPanel abilitiesList;
/*  36 */   public int abilitySelected = -1;
/*     */   
/*     */   protected boolean forceUpdate = false;
/*     */   
/*     */   private IAbilityData abilityDataProps;
/*     */   
/*     */   public SelectHotbarAbilitiesScreen(PlayerEntity player) {
/*  43 */     super((ITextComponent)new StringTextComponent(""));
/*  44 */     this.player = player;
/*  45 */     this.minecraft = Minecraft.getInstance();
/*     */     
/*  47 */     this.abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(int x, int y, float f) {
/*  53 */     renderBackground();
/*     */     
/*  55 */     Minecraft.getInstance().getTextureManager().bindTexture(ModResources.BLANK);
/*     */     
/*  57 */     int posX = this.width;
/*  58 */     int posY = this.height;
/*     */     
/*  60 */     blit((posX - 250) / 2, (posY - 230) / 2, 0, 0, 256, 256);
/*  61 */     blit((posX - 250) / 2, posY - 60, 0, 0, 256, 256);
/*     */     
/*  63 */     WyHelper.drawStringWithBorder(this.minecraft.fontRenderer, this.abilityDataProps.getCombatBarSet() + "", posX / 2 - 110, posY - 24, WyHelper.hexToRGB("#FFFFFF").getRGB());
/*     */     
/*  65 */     this.minecraft.getTextureManager().bindTexture(ModResources.WIDGETS);
/*     */     
/*  67 */     RenderSystem.enableBlend();
/*     */     int i;
/*  69 */     for (i = 0; i < 8; i++) {
/*     */       
/*  71 */       if (this.slotSelected == i + this.abilityDataProps.getCombatBarSet() * 8) {
/*  72 */         RenderSystem.color4f(0.0F, 0.0F, 1.0F, 1.0F);
/*     */       }
/*  74 */       blit(posX / 2 - 102 + i * 25, posY - 33, 0, 0, 23, 23);
/*  75 */       RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */     
/*  78 */     for (i = 0; i < 8; i++) {
/*     */       
/*  80 */       int j = i + this.abilityDataProps.getCombatBarSet() * 8;
/*  81 */       RenderSystem.blendFuncSeparate(770, 771, 1, 0);
/*  82 */       if (this.abilityDataProps.getEquippedAbility(j) != null) {
/*  83 */         RendererHelper.drawAbilityIcon(WyHelper.getResourceName(this.abilityDataProps.getEquippedAbility(j).getName()), posX / 2 - 98 + i * 25, posY - 29, 16, 16);
/*     */       }
/*     */     } 
/*  86 */     RenderSystem.disableBlend();
/*     */ 
/*     */ 
/*     */     
/*  90 */     if (this.abilitiesList != null) {
/*  91 */       this.abilitiesList.render(x, y, f);
/*     */     }
/*     */ 
/*     */     
/*  95 */     super.render(x, y, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/* 101 */     int posX = this.width;
/* 102 */     int posY = this.height;
/*     */     
/* 104 */     int posX2 = this.width / 2;
/* 105 */     int posY2 = this.height / 2;
/*     */ 
/*     */     
/* 108 */     int idx = 0;
/* 109 */     for (APIConfig.AbilityCategory category : APIConfig.AbilityCategory.values()) {
/*     */       
/* 111 */       if (category != APIConfig.AbilityCategory.ALL) {
/*     */ 
/*     */         
/* 114 */         Ability abl = this.abilityDataProps.getUnlockedAbilities(category).stream().findFirst().orElse(null);
/* 115 */         if (abl != null) {
/*     */           
/* 117 */           if (idx == 4) {
/*     */             
/* 119 */             posX2 += 260;
/* 120 */             posY2 -= 140;
/*     */           } 
/*     */           
/* 123 */           int posY3 = posY2 - 100 + idx * 70 / 2;
/*     */           
/* 125 */           String iconName = abl.getName();
/* 126 */           ResourceLocation icon = category.getIcon(this.player);
/* 127 */           if (icon == null)
/* 128 */             icon = new ResourceLocation("mineminenomi", "textures/abilities/" + WyHelper.getResourceName(iconName) + ".png"); 
/* 129 */           TexturedIconButton button = new TexturedIconButton(ModResources.BLANK2_SIMPLE, posX2 - 145, posY3, 30, 30, "", btn -> updateListScreen(category));
/* 130 */           button = button.setTextureInfo(posX2 - 145, posY3, 30, 40).setIconInfo(icon, posX2 - 135, posY3 + 9, 1.45D);
/* 131 */           addButton((Widget)button);
/*     */           
/* 133 */           idx++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     for (int i = 0; i < 8; i++) {
/*     */       
/* 140 */       RenderSystem.enableBlend();
/* 141 */       int id = i + this.abilityDataProps.getCombatBarSet() * 8;
/* 142 */       NoTextureButton slotButton = new NoTextureButton(posX / 2 - 101 + i * 25, posY - 31, 22, 21, "", btn -> {
/*     */             if (this.slotSelected != id) {
/*     */               Ability ability = this.abilityDataProps.getEquippedAbility(id);
/*     */ 
/*     */               
/*     */               if (ability == null) {
/*     */                 this.slotSelected = id;
/*     */               } else if (ability.isOnStandby()) {
/*     */                 this.slotSelected = id;
/*     */               } 
/*     */             } else {
/*     */               Ability ability = this.abilityDataProps.getEquippedAbility(this.slotSelected);
/*     */ 
/*     */               
/*     */               if (ability != null && ability.isOnStandby()) {
/*     */                 WyNetwork.sendToServer(new CRemoveAbilityPacket(this.slotSelected));
/*     */ 
/*     */                 
/*     */                 this.abilityDataProps.setEquippedAbility(this.slotSelected, null);
/*     */               } 
/*     */             } 
/*     */           });
/*     */       
/* 165 */       slotButton.setFake();
/* 166 */       addButton((Widget)slotButton);
/*     */     } 
/*     */     
/* 169 */     updateListScreen(AbilityHelper.getDevilFruitCategory());
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateListScreen(APIConfig.AbilityCategory category) {
/* 174 */     this.children.remove(this.abilitiesList);
/*     */     
/* 176 */     List<Ability> list = this.abilityDataProps.getUnlockedAbilities(category);
/* 177 */     Ability[] arr = new Ability[list.size()];
/* 178 */     arr = list.<Ability>toArray(arr);
/*     */     
/* 180 */     this.abilitiesList = new AbilitiesListScreenPanel(this, this.abilityDataProps, arr);
/* 181 */     this.children.add(this.abilitiesList);
/* 182 */     setFocused((IGuiEventListener)this.abilitiesList);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClose() {
/* 188 */     if (this.forceUpdate)
/* 189 */       WyNetwork.sendToServer(new CSyncAbilityDataPacket(this.abilityDataProps)); 
/* 190 */     super.onClose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPauseScreen() {
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 201 */     this.forceUpdate = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\SelectHotbarAbilitiesScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */