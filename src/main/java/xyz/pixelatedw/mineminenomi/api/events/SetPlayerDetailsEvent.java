/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ public class SetPlayerDetailsEvent
/*    */   extends PlayerEvent {
/*    */   private IEntityStats props;
/*    */   
/*    */   public SetPlayerDetailsEvent(PlayerEntity player) {
/* 14 */     super(player);
/* 15 */     this.props = EntityStatsCapability.get((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   public IEntityStats getEntityStats() {
/* 20 */     return this.props;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\events\SetPlayerDetailsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */