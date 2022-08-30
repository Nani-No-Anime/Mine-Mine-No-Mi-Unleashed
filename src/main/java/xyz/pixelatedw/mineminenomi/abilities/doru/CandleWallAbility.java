/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class CandleWallAbility extends Ability {
/* 15 */   public static final Ability INSTANCE = new CandleWallAbility();
/*    */   
/* 17 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public CandleWallAbility() {
/* 21 */     super("Candle Wall", AbilityHelper.getDevilFruitCategory());
/* 22 */     setMaxCooldown(6.0D);
/* 23 */     setDescription("Creates a wax wall in front of the user");
/*    */     
/* 25 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 30 */     Direction dir = Direction.getFacingDirections((Entity)player)[0];
/*    */     
/* 32 */     int thiccness = 1;
/* 33 */     Ability ability = AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)CandleChampionAbility.INSTANCE);
/* 34 */     if (ability != null && ability.isContinuous()) {
/* 35 */       thiccness = 2;
/*    */     }
/* 37 */     if (dir == Direction.NORTH)
/* 38 */       AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, thiccness, ModBlocks.WAX, GRIEF_RULE); 
/* 39 */     if (dir == Direction.SOUTH)
/* 40 */       AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, thiccness, ModBlocks.WAX, GRIEF_RULE); 
/* 41 */     if (dir == Direction.EAST)
/* 42 */       AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), thiccness, 4, 3, ModBlocks.WAX, GRIEF_RULE); 
/* 43 */     if (dir == Direction.WEST) {
/* 44 */       AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), thiccness, 4, 3, ModBlocks.WAX, GRIEF_RULE);
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\CandleWallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */