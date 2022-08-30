/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public interface IOutOfBodyAbility {
/*    */   public static final Predicate<Ability> IS_ACTIVE= (ability -> !(ability instanceof IOutOfBodyAbility) ? false : ability.isContinuous());
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
/*    */   default double getDistanceFromPivot(Entity entity) {
/* 31 */     if (getPivotPoint() == null)
/* 32 */       return -1.0D; 
/* 33 */     return Math.sqrt(entity.getDistanceSq(getPivotPoint().getX(), getPivotPoint().getY(), getPivotPoint().getZ()));
/*    */   }
/*    */ 
/*    */   
/*    */   default void startOutOfBody(PlayerEntity player) {
/* 38 */     player.onGround = false;
/* 39 */     player.abilities.isFlying = true;
/* 40 */     if (player instanceof ServerPlayerEntity) {
/* 41 */       ((ServerPlayerEntity)player).sendPlayerAbilities();
/*    */     }
/*    */   }
/*    */   
/*    */   default void stopOutOfBody(PlayerEntity player) {
/* 46 */     player.abilities.isFlying = false;
/* 47 */     if (player instanceof ServerPlayerEntity)
/* 48 */       ((ServerPlayerEntity)player).sendPlayerAbilities(); 
/*    */   }
/*    */   
/*    */   float getMaxRange();
/*    */   
/*    */   @Nullable
/*    */   BlockPos getPivotPoint();
/*    */   
/*    */   boolean isPhysical();
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IOutOfBodyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */