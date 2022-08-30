/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.container.Container;
/*    */ import net.minecraft.util.IWorldPosCallable;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.baku.BakuFactoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ @Mixin({Container.class})
/*    */ public class ContainerMixin
/*    */ {
/*    */   @Inject(method = {"isWithinUsableDistance"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void isWithinUsableDistance(IWorldPosCallable worldPos, PlayerEntity player, Block targetBlock, CallbackInfoReturnable<Boolean> callback) {
/* 24 */     if (targetBlock == Blocks.CRAFTING_TABLE.getBlock()) {
/*    */       
/* 26 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/* 27 */       Ability ability = abilityData.getEquippedAbility((Ability)BakuFactoryAbility.INSTANCE);
/* 28 */       boolean hasAbility = (ability != null && ability.isContinuous());
/* 29 */       if (hasAbility)
/* 30 */         callback.setReturnValue(Boolean.valueOf(true)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\mixins\ContainerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */