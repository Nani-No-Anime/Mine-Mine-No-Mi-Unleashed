/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.DeathScreen;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ @Mixin({DeathScreen.class})
/*    */ public class DeathScreenMixin
/*    */   extends Screen
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   public boolean isHardcoreMode;
/*    */   
/*    */   public DeathScreenMixin(ITextComponent title) {
/* 29 */     super(title);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"init"}, at = {@At("HEAD")})
/*    */   public void init(CallbackInfo callback) {
/* 38 */     ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 39 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 41 */     if (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !props.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.INSTANCE.getForm()))
/*    */     {
/* 43 */       this.isHardcoreMode = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\DeathScreenMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */