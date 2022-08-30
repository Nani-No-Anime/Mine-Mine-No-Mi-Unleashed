/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.math.AxisAlignedBB;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BombEntity
/*     */   extends Entity
/*     */ {
/*     */   public BombEntity(World world) {
/*  25 */     super(ModEntities.BOMB, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  31 */     this.prevPosX = getPosX();
/*  32 */     this.prevPosY = getPosY();
/*  33 */     this.prevPosZ = getPosZ();
/*     */     
/*  35 */     if (this.ticksExisted % 3 == 0) {
/*  36 */       this.world.addParticle((IParticleData)ParticleTypes.FLAME, getPosX(), getPosY() + 1.0D, getPosZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  38 */     if (!this.world.isRemote) {
/*     */       
/*  40 */       if (this.ticksExisted % 10 == 0) {
/*     */         
/*  42 */         List<PlayerEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 2.0D, new Class[] { PlayerEntity.class });
/*  43 */         if (targets.size() > 0)
/*     */         {
/*  45 */           remove();
/*     */         }
/*     */       } 
/*     */       
/*  49 */       if (this.ticksExisted >= 300) {
/*  50 */         remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove() {
/*  57 */     if (!this.world.isRemote) {
/*     */       
/*  59 */       ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, getPosX(), getPosY(), getPosZ(), 4.0F);
/*  60 */       explosion.setExplosionSound(true);
/*  61 */       explosion.setDamageOwner(false);
/*  62 */       explosion.setDestroyBlocks(false);
/*  63 */       explosion.setFireAfterExplosion(false);
/*  64 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*  65 */       explosion.setDamageEntities(true);
/*  66 */       explosion.doExplosion();
/*     */     } 
/*  68 */     super.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {}
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AxisAlignedBB getCollisionBox(Entity entity) {
/*  80 */     return entity.getBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox() {
/*  86 */     return getBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditional(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeAdditional(CompoundNBT compound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 114 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\BombEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */