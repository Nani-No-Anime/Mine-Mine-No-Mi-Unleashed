/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.command.impl.FillCommand;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({FillCommand.class})
/*    */ public class FillCommandMixin
/*    */ {
/*    */   @ModifyVariable(method = {"doFill"}, at = @At("STORE"), ordinal = 0)
/*    */   private static int areaSize(int x) {
/* 20 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\FillCommandMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */