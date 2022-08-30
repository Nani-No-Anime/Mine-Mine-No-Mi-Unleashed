/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class KagemushaAbility extends Ability {
/* 12 */   public static final KagemushaAbility INSTANCE = new KagemushaAbility();
/*    */ 
/*    */   
/*    */   public KagemushaAbility() {
/* 16 */     super("Kagemusha", AbilityHelper.getDevilFruitCategory());
/* 17 */     setDescription("Allows the user to change its position with that of the Doppelman.");
/* 18 */     setMaxCooldown(5.0D);
/*    */     
/* 20 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*    */     
/* 27 */     DoppelmanAbility ability = (DoppelmanAbility)props.getEquippedAbility((Ability)DoppelmanAbility.INSTANCE);
/* 28 */     boolean isActive = (ability != null && ability.isContinuous());
/*    */     
/* 30 */     if (isActive && ability.getDoppelman() != null) {
/*    */       
/* 32 */       BlockPos temp = player.getPosition();
/* 33 */       player.setPositionAndUpdate(ability.getDoppelman().getPosX(), ability.getDoppelman().getPosY(), ability.getDoppelman().getPosZ());
/* 34 */       ability.getDoppelman().setPositionAndUpdate(temp.getX(), temp.getY(), temp.getZ());
/* 35 */       return true;
/*    */     } 
/*    */     
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KagemushaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */