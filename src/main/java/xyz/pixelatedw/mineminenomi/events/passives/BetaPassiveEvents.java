/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.beta.BetaCoatingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class BetaPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 24 */     if (event.getEntityLiving() instanceof net.minecraft.entity.player.PlayerEntity) {
/*    */       
/* 26 */       LivingEntity entity = event.getEntityLiving();
/* 27 */       IAbilityData abilityProps = AbilityDataCapability.get(event.getEntityLiving());
/* 28 */       Ability BetaCoating = abilityProps.getEquippedAbility((Ability)BetaCoatingAbility.INSTANCE);
/* 29 */       boolean hasBetaCoatingEnabled = (BetaCoating != null && BetaCoating.isContinuous());
/* 30 */       if (hasBetaCoatingEnabled) {
/*    */         
/* 32 */         boolean isNearBlock = false;
/* 33 */         AxisAlignedBB bb = entity.getBoundingBox().grow(1.0D, 1.0D, 1.0D);
/* 34 */         int mX = MathHelper.floor(bb.minX);
/* 35 */         int mY = MathHelper.floor(bb.minY);
/* 36 */         int mZ = MathHelper.floor(bb.minZ);
/* 37 */         for (int y2 = mY; y2 < bb.maxY; y2++) {
/*    */           
/* 39 */           for (int x2 = mX; x2 < bb.maxX; x2++) {
/*    */             
/* 41 */             for (int z2 = mZ; z2 < bb.maxZ; z2++) {
/*    */               
/* 43 */               BlockPos tmp = new BlockPos(x2, y2, z2);
/* 44 */               BlockState state = entity.world.getBlockState(tmp);
/* 45 */               if (state.isSolid()) {
/*    */                 
/* 47 */                 isNearBlock = true;
/*    */                 
/*    */                 break;
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/* 54 */         if ((entity.collidedHorizontally && !entity.collidedVertically) || (entity.isCrouching() && isNearBlock)) {
/*    */           
/* 56 */           double climbSpeed = Math.min(0.1D, (entity.getLookVec()).y * 0.5D);
/* 57 */           if (entity.isCrouching()) {
/* 58 */             entity.setMotion((entity.getMotion()).x, 0.0D, (entity.getMotion()).z);
/*    */           } else {
/* 60 */             entity.setMotion((entity.getMotion()).x, climbSpeed, (entity.getMotion()).z);
/* 61 */           }  entity.fallDistance = 0.0F;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\BetaPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */