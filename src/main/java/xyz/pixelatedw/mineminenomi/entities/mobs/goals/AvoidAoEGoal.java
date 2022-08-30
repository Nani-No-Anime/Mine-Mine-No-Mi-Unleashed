/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class AvoidAoEGoal
/*    */   extends Goal
/*    */ {
/*    */   private OPEntity entity;
/*    */   private double speed;
/*    */   private double responseTime;
/*    */   
/*    */   public AvoidAoEGoal(OPEntity entity, double speed, double responseTime) {
/* 24 */     this.entity = entity;
/* 25 */     this.speed = speed;
/* 26 */     this.responseTime = responseTime;
/* 27 */     setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 33 */     List<PlayerEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 20.0D, new Class[] { PlayerEntity.class });
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
/*    */ 
/*    */     
/* 50 */     boolean hasTargetNearby = (((List)targets.stream().filter(player -> { IAbilityData props = AbilityDataCapability.get((LivingEntity)player); boolean flag = false; for (Ability abl : props.getEquippedAbilities()) { if (abl instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility && abl.isCharging()) { flag = true; break; }  }  return flag; }).collect(Collectors.toList())).size() > 0);
/*    */     
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\AvoidAoEGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */