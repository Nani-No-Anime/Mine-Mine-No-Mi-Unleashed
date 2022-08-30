/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;
/*    */ 
/*    */ public class MirageTempoCloudEntity
/*    */   extends EntityCloud {
/* 10 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new KemuriBoshiParticleEffect();
/*    */ 
/*    */   
/*    */   public MirageTempoCloudEntity(World world) {
/* 14 */     super(world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 20 */     super.tick();
/* 21 */     if (!this.world.isRemote)
/*    */     {
/* 23 */       if (this.ticksExisted % 2 == 0)
/* 24 */         PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\MirageTempoCloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */