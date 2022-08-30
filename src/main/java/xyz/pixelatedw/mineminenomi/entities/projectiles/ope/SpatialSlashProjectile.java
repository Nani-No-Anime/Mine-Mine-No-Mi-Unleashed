/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IndirectEntityDamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class SpatialSlashProjectile extends AbilityProjectileEntity {
/*    */   public SpatialSlashProjectile(World world) {
/* 23 */     super(OpeProjectiles.SPATIAL_SLASH, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpatialSlashProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpatialSlashProjectile(World world, double x, double y, double z) {
/* 33 */     super(OpeProjectiles.SPATIAL_SLASH, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpatialSlashProjectile(World world, LivingEntity player) {
/* 38 */     super(OpeProjectiles.SPATIAL_SLASH, world, player);
/* 39 */     setDamageSource((new IndirectEntityDamageSource("ability_projectile", (Entity)this, null)).setProjectile());
/* 40 */     setDamage(5.0F);
/* 41 */     setPhysical(false);
/* 42 */     setMaxLife(20);
/*    */     
/* 44 */     setCanGetStuckInGround();
/* 45 */     this.onBlockImpactEvent = this::onBlockImpact;
/* 46 */     this.onEntityImpactEvent = this::onEntityImpact;
/*    */   }
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {
/* 50 */     boolean hakiCondition = (HakiHelper.getBusoHardeningHakiExp(entity) + WyHelper.randomWithRange(0, 5) >= HakiHelper.getBusoHardeningHakiExp(getThrower()));
/* 51 */     boolean specialCondition = (DevilFruitCapability.get(entity).isLogia() && !HakiHelper.hasHardeningActive(entity));
/* 52 */     boolean hpCondition = (getThrower().getHealth() >= entity.getHealth() + 10.0F);
/* 53 */     boolean blocking = AbilityHelper.isTargetBlockingAbility(getThrower(), entity);
/*    */     
/* 55 */     if (!hakiCondition && !specialCondition && !hpCondition && !blocking)
/*    */     {
/*    */       
/* 58 */       entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 200, 0, false, false));
/*    */     }
/*    */   }
/*    */   
/*    */   private void onBlockImpact(BlockPos blockPos) {
/* 63 */     this.world.removeBlock(blockPos, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 69 */     super.tick();
/* 70 */     if (this.world.isRemote)
/*    */       return; 
/* 72 */     if (getThrower() != null && this.ticksExisted > 0 && (getThrower()).ticksExisted > 0) {
/*    */       
/* 74 */       RoomAbility a = (RoomAbility)AbilityDataCapability.get(getThrower()).getEquippedAbility((Ability)RoomAbility.INSTANCE);
/* 75 */       if (a == null) {
/* 76 */         remove();
/*    */       }
/* 78 */       if (!a.isEntityInThisRoom((Entity)this))
/* 79 */         remove(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\SpatialSlashProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */