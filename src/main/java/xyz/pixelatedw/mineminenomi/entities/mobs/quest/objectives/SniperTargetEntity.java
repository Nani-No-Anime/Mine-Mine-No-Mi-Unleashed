/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class SniperTargetEntity
/*    */   extends MobEntity
/*    */ {
/*    */   public SniperTargetEntity(World world) {
/* 12 */     super(ModEntities.SNIPER_TARGET, world);
/* 13 */     this.experienceValue = 0;
/* 14 */     setAIMoveSpeed(0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 20 */     super.registerAttributes();
/* 21 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 27 */     setMotion(0.0D, -0.1D, 0.0D);
/* 28 */     this.fallDistance = 0.0F;
/*    */     
/* 30 */     if ((this.onGround || isInWater() || isInLava()) && !this.world.isRemote) {
/* 31 */       remove();
/*    */     }
/* 33 */     super.tick();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\objectives\SniperTargetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */