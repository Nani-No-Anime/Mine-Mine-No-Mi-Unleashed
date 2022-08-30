/*    */ package xyz.pixelatedw.mineminenomi.entities;
/*    */ 
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ 
/*    */ public class WantedPosterPackageEntity
/*    */   extends MobEntity
/*    */ {
/*    */   public WantedPosterPackageEntity(World world) {
/* 15 */     super(ModEntities.WANTED_POSTER_PACKAGE, world);
/* 16 */     setHealth(1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void spawnDrops(DamageSource cause) {
/* 22 */     if (!this.onGround) {
/* 23 */       ItemsHelper.dropWantedPosters(this.world, (int)getPosX(), (int)getPosY(), (int)getPosZ());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 29 */     setMotion(getMotion().getX(), getMotion().getY() / (1.5D + this.world.rand.nextDouble()), getMotion().getZ());
/* 30 */     this.fallDistance = 0.0F;
/* 31 */     BlockPos pos = new BlockPos(getPosX(), getPosY(), getPosZ());
/*    */     
/* 33 */     if (this.onGround && !this.world.isRemote)
/*    */     {
/* 35 */       if (this.world.isAirBlock(pos)) {
/*    */         
/* 37 */         this.world.setBlockState(pos, ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
/* 38 */         remove();
/*    */       }
/* 40 */       else if (this.world.isAirBlock(pos.up())) {
/*    */         
/* 42 */         this.world.setBlockState(pos.up(), ModBlocks.WANTED_POSTER_PACKAGE.getDefaultState());
/* 43 */         remove();
/*    */       } 
/*    */     }
/*    */     
/* 47 */     if (isInWater() || isInLava()) {
/* 48 */       onKillCommand();
/*    */     }
/* 50 */     super.tick();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\WantedPosterPackageEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */