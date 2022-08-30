/*    */ package xyz.pixelatedw.mineminenomi.abilities.saraaxolotl;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public class HeartRegenAbility extends PassiveAbility {
/* 11 */   public static final HeartRegenAbility INSTANCE = new HeartRegenAbility();
/*    */   
/* 13 */   private int ticksWithoutHeart = 0;
/*    */ 
/*    */   
/*    */   public HeartRegenAbility() {
/* 17 */     super("Heart Regen", AbilityHelper.getDevilFruitCategory());
/*    */     
/* 19 */     this.duringPassiveEvent = this::duringPassiveEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringPassiveEvent(PlayerEntity player) {
/* 24 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 25 */     if (!props.hasHeart()) {
/*    */       
/* 27 */       this.ticksWithoutHeart++;
/* 28 */       if (this.ticksWithoutHeart == 100) {
/*    */         
/* 30 */         props.setHeart(true);
/* 31 */         this.ticksWithoutHeart = 0;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\saraaxolotl\HeartRegenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */