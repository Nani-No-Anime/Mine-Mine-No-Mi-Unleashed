/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.BlockParticleData;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.hana.CienFleurStompAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class HanaFeetEntity extends AbilityProjectileEntity {
/*    */   public HanaFeetEntity(World world) {
/* 28 */     super(HanaProjectiles.FEET, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HanaFeetEntity(World world, LivingEntity player) {
/* 33 */     super(HanaProjectiles.FEET, world, player);
/* 34 */     setMaxLife(35);
/* 35 */     setPassThroughEntities();
/*    */ 
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockImpactEvent(BlockPos pos) {
/* 43 */     if (getThrower() == null) {
/*    */       return;
/*    */     }
/* 46 */     List<LivingEntity> list = WyHelper.getEntitiesNear(pos, this.world, 5.0D, FactionHelper.getOutsideGroupPredicate(getThrower()), new Class[] { LivingEntity.class });
/* 47 */     list.remove(getThrower());
/* 48 */     Iterator<LivingEntity> iter = list.iterator();
/*    */     
/* 50 */     while (iter.hasNext()) {
/*    */       
/* 52 */       LivingEntity target = iter.next();
/* 53 */       target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage(getThrower(), (Ability)CienFleurStompAbility.INSTANCE), 10.0F);
/* 54 */       target.setMotion(target.getMotion().add(0.0D, 1.0D, 0.0D));
/* 55 */       target.velocityChanged = true;
/*    */     } 
/*    */     
/* 58 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks(getPosition(), (IWorld)this.world, 5, p -> FoliageBlockProtectionRule.INSTANCE.isPresent(this.world.getBlockState(p)), (List)ImmutableList.of(Blocks.AIR));
/* 59 */     for (BlockPos p : blocks) {
/*    */       
/* 61 */       BlockState blockState1 = this.world.getBlockState(new BlockPos(p.getX(), p.getY(), p.getZ()));
/* 62 */       for (int i = 0; i < 150; i++) {
/*    */         
/* 64 */         double offsetX = WyHelper.randomDouble();
/* 65 */         double offsetY = WyHelper.randomDouble();
/* 66 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 68 */         ((ServerWorld)this.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState1), p
/*    */             
/* 70 */             .getX() + offsetX, p
/* 71 */             .getY() + offsetY, p
/* 72 */             .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */       
/* 75 */       AbilityHelper.placeBlockIfAllowed(this.world, p.getX(), p.getY(), p.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
/*    */     } 
/*    */     
/* 78 */     BlockState blockState = this.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
/* 79 */     if (blockState.getMaterial().isSolid())
/*    */     {
/* 81 */       for (int i = 0; i < 150; i++) {
/*    */         
/* 83 */         double x = WyHelper.randomDouble();
/* 84 */         double z = WyHelper.randomDouble();
/*    */         
/* 86 */         ((ServerWorld)this.world).spawnParticle((IParticleData)new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
/* 87 */             .getX() + WyHelper.randomWithRange(-3, 3) + x, (pos
/* 88 */             .getY() + 1), pos
/* 89 */             .getZ() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hana\HanaFeetEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */