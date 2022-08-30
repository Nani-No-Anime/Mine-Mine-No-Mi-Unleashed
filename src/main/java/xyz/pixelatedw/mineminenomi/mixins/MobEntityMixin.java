/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*    */ 
/*    */ @Mixin({MobEntity.class})
/*    */ public abstract class MobEntityMixin
/*    */ {
/*    */   @Inject(method = {"updateEntityActionState"}, at = {@At("HEAD")}, cancellable = true)
/*    */   protected void updateEntityActionState(CallbackInfo callback) {
/* 19 */     MobEntity entity = (MobEntity)(Object)this;
/*    */ 
/*    */ 
/*    */     
/* 23 */     Objects.requireNonNull(IBindHandsEffect.class); entity.getActivePotionEffects().stream().map(EffectInstance::getPotion).filter(IBindHandsEffect.class::isInstance)
/* 24 */       .filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings())
/* 25 */       .forEach(eff -> {
/*    */           entity.goalSelector.disableFlag(Goal.Flag.JUMP);
/*    */           entity.goalSelector.disableFlag(Goal.Flag.MOVE);
/*    */           entity.goalSelector.disableFlag(Goal.Flag.TARGET);
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\MobEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */