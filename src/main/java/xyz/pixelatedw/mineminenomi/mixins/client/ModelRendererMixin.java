/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectList;
/*    */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ModelRenderer.class})
/*    */ public class ModelRendererMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private ObjectList<ModelRenderer.ModelBox> cubeList;
/*    */   @Shadow
/*    */   @Final
/*    */   private ObjectList<ModelRenderer> childModels;
/*    */   
/*    */   @Inject(method = {"getRandomCube"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void getRandomCube(Random random, CallbackInfoReturnable<ModelRenderer.ModelBox> callback) {
/* 39 */     if (this.cubeList.isEmpty())
/*    */     {
/* 41 */       if (!this.childModels.isEmpty())
/*    */       {
/* 43 */         for (ObjectListIterator<ModelRenderer> objectListIterator = this.childModels.iterator(); objectListIterator.hasNext(); ) { ModelRenderer renderer = objectListIterator.next();
/*    */           
/* 45 */           ModelRenderer.ModelBox box = renderer.getRandomCube(random);
/* 46 */           if (box != null) {
/*    */             
/* 48 */             callback.setReturnValue(box);
/*    */             return;
/*    */           }  }
/*    */       
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\client\ModelRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */