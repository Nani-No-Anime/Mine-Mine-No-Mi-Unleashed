/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.ILivingEntityData;
/*    */ import net.minecraft.entity.IRangedAttackMob;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.SpawnReason;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class MarineBomberEntity extends AbstractMarineEntity implements IRangedAttackMob {
/* 29 */   private static final String[] DEFAULT_TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5" };
/*    */ 
/*    */   
/*    */   public MarineBomberEntity(World world) {
/* 33 */     super(ModEntities.MARINE_BOMBER, world, DEFAULT_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerGoals() {
/* 39 */     super.registerGoals();
/* 40 */     this.goalSelector.addGoal(1, (Goal)new RangedAttackGoal(this, 1.0D, 200, 30.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerAttributes() {
/* 46 */     super.registerAttributes();
/* 47 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(15.0D);
/* 48 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20999999344348907D);
/* 49 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/* 50 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/* 51 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*    */     
/* 53 */     setDoriki(20.0D + WyHelper.randomWithRange(0, 10));
/* 54 */     setBelly(5.0D + WyHelper.randomWithRange(0, 10));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerData() {
/* 60 */     super.registerData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 67 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*    */     
/* 69 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.BAZOOKA));
/*    */     
/* 71 */     return spawnData;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void attackEntityWithRangedAttack(LivingEntity target, float distance) {
/* 77 */     if (getHeldItemMainhand() == null || !getHeldItemMainhand().getItem().equals(ModWeapons.BAZOOKA)) {
/*    */       return;
/*    */     }
/* 80 */     CannonBallProjectile cannonBallProjectile = new CannonBallProjectile(this.world, (LivingEntity)this);
/* 81 */     cannonBallProjectile.setDamage(cannonBallProjectile.getDamage() * 0.8F);
/* 82 */     ((AbilityProjectileEntity)cannonBallProjectile).onBlockImpactEvent = (hit -> {
/*    */         if (this.ticksExisted < 0) {
/*    */           return;
/*    */         }
/*    */         AbilityProjectileEntity proj = cannonBallProjectile;
/*    */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)proj, this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
/*    */         explosion.setStaticDamage(5.0F);
/*    */         explosion.setDestroyBlocks(false);
/*    */         explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/*    */         explosion.doExplosion();
/*    */       });
/* 93 */     double velX = target.getPosX() - getPosX();
/* 94 */     double velY = (target.getBoundingBox()).minY - cannonBallProjectile.getPosY();
/* 95 */     double velZ = target.getPosZ() - getPosZ();
/* 96 */     double x = MathHelper.sqrt(velX * velX + velZ * velZ);
/*    */     
/* 98 */     cannonBallProjectile.shoot(velX, velY + x * 0.10000000149011612D, velZ, 2.0F, MathHelper.clamp(12 - this.world.getDifficulty().getId() * 4, 0, 100));
/* 99 */     this.world.addEntity((Entity)cannonBallProjectile);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MarineBomberEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */