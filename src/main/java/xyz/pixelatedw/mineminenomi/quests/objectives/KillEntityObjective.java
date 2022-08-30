/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IKillEntityObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KillEntityObjective
/*    */   extends Objective
/*    */   implements IKillEntityObjective
/*    */ {
/*    */   protected ICheckKill killEvent;
/*    */   
/*    */   public KillEntityObjective(String title, int count) {
/* 22 */     this(title, count, (ICheckKill)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public KillEntityObjective(String title, int count, EntityType entityType) {
/* 27 */     this(title, count, (player, target, source) -> (target.getType() == entityType));
/*    */   }
/*    */ 
/*    */   
/*    */   public KillEntityObjective(String title, int count, ICheckKill check) {
/* 32 */     super(title); this.killEvent = ((player, target, source) -> { IAttributeInstance attackAttribute = target.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE); return (attackAttribute != null && attackAttribute.getValue() > 0.0D);
/* 33 */       }); setMaxProgress(count);
/* 34 */     if (check != null) {
/* 35 */       this.killEvent = check;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkKill(PlayerEntity player, LivingEntity target, DamageSource source) {
/* 41 */     return this.killEvent.test(player, target, source);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ICheckKill
/*    */   {
/*    */     default ICheckKill and(ICheckKill check) {
/* 51 */       return (player, target, source) -> 
/* 52 */         (test(player, target, source) && check.test(player, target, source));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     default ICheckKill or(ICheckKill check) {
/* 58 */       return (player, target, source) -> 
/* 59 */         (test(player, target, source) || check.test(player, target, source));
/*    */     }
/*    */     
/*    */     boolean test(PlayerEntity param1PlayerEntity, LivingEntity param1LivingEntity, DamageSource param1DamageSource);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\KillEntityObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */