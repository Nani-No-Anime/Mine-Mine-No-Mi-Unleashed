/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ItemStack.class})
/*    */ public class ItemStackMixin
/*    */ {
/*    */   @Inject(method = {"damageItem"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public <T extends LivingEntity> void damageItem(int amount, T entity, Consumer<T> onBroken, CallbackInfo callback) {
/* 20 */     boolean hakiActiveFlag = HakiHelper.hasImbuingActive((LivingEntity)entity);
/* 21 */     if (hakiActiveFlag)
/* 22 */       callback.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\ItemStackMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */