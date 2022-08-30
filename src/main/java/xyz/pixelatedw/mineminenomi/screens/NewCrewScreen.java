/*    */ package xyz.pixelatedw.mineminenomi.screens;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.IGuiEventListener;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.client.gui.widget.TextFieldWidget;
/*    */ import net.minecraft.client.gui.widget.Widget;
/*    */ import net.minecraft.client.gui.widget.button.Button;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import xyz.pixelatedw.mineminenomi.packets.client.crew.CCreateCrewPacket;
/*    */ import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class NewCrewScreen extends Screen {
/*    */   private PlayerEntity player;
/*    */   private TextFieldWidget nameEdit;
/*    */   
/*    */   public NewCrewScreen() {
/* 25 */     super((ITextComponent)new StringTextComponent(""));
/* 26 */     this.player = (PlayerEntity)(Minecraft.getInstance()).player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int x, int y, float f) {
/* 32 */     renderBackground();
/* 33 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 35 */     this.nameEdit.render(x, y, f);
/*    */     
/* 37 */     super.render(x, y, f);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void init() {
/* 43 */     this.minecraft.keyboardListener.enableRepeatEvents(true);
/* 44 */     int posX = this.width / 2;
/* 45 */     int posY = this.height / 2;
/*    */     
/* 47 */     this.nameEdit = new TextFieldWidget(this.font, posX - 152, posY - 10, 300, 20, "");
/* 48 */     this.nameEdit.setMaxStringLength(255);
/* 49 */     this.nameEdit.setText(this.player.getName().getFormattedText() + "'s Crew");
/* 50 */     this.children.add(this.nameEdit);
/* 51 */     setFocusedDefault((IGuiEventListener)this.nameEdit);
/*    */     
/* 53 */     String createString = "Create";
/* 54 */     NoTextureButton createCrewButton = new NoTextureButton(posX - 30, posY + 50, 60, 16, createString, btn -> createCrew());
/* 55 */     addButton((Widget)createCrewButton);
/*    */   }
/*    */ 
/*    */   
/*    */   private void createCrew() {
/* 60 */     WyNetwork.sendToServer(new CCreateCrewPacket(this.nameEdit.getText()));
/* 61 */     onClose();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resize(Minecraft minecraft, int x, int y) {
/* 67 */     String crewName = this.nameEdit.getText();
/* 68 */     init(minecraft, x, y);
/* 69 */     this.nameEdit.setText(crewName);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 75 */     this.nameEdit.tick();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void open() {
/* 80 */     Minecraft.getInstance().displayGuiScreen(new NewCrewScreen());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\screens\NewCrewScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */