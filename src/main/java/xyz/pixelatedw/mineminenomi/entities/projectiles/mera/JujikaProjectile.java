/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.projectile.ThrowableEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.mera.JujikaParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class JujikaProjectile extends AbilityProjectileEntity {
/* 19 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new JujikaParticleEffect();
/*    */ 
/*    */   
/*    */   public JujikaProjectile(World world) {
/* 23 */     super(MeraProjectiles.JUJIKA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public JujikaProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public JujikaProjectile(World world, double x, double y, double z) {
/* 33 */     super(MeraProjectiles.JUJIKA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public JujikaProjectile(World world, LivingEntity player) {
/* 38 */     super(MeraProjectiles.JUJIKA, world, player);
/* 39 */     setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
/* 40 */     setDamage(25.0F);
/*    */     
/* 42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 48 */     if (areEyesInFluid(FluidTags.WATER)) {
/*    */       
/* 50 */       remove();
/* 51 */       getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 54 */     if (!this.world.isRemote && this.ticksExisted > 0) {
/*    */       
/* 56 */       Direction dir = Direction.fromAngle((getThrower()).rotationYaw);
/* 57 */       PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, dir.ordinal());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 63 */     if (CommonConfig.INSTANCE.isAbilityGriefingEnabled())
/*    */     {
/* 65 */       for (int j = -2; j <= 2; j++) {
/*    */         int i;
/* 67 */         for (i = -5; i <= 5; i++) {
/* 68 */           if (this.world.isAirBlock(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ())))
/* 69 */             this.world.setBlockState(new BlockPos(hit.getX() + i, hit.getY() + j, hit.getZ()), Blocks.FIRE.getDefaultState()); 
/*    */         } 
/* 71 */         for (i = -5; i <= 5; i++) {
/* 72 */           if (this.world.isAirBlock(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i)))
/* 73 */             this.world.setBlockState(new BlockPos(hit.getX(), hit.getY() + j, hit.getZ() + i), Blocks.FIRE.getDefaultState()); 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mera\JujikaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */