/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.netsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.netsu.NetsuEnhancementAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class NekkaiGyoraiProjectile extends AbilityProjectileEntity {
/* 22 */   private int damage = 10;
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiProjectile(World world) {
/* 26 */     super(NetsuProjectiles.NEKKAI_GYORAI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiProjectile(World world, LivingEntity player) {
/* 31 */     super(NetsuProjectiles.NEKKAI_GYORAI, world, player);
/*    */     
/* 33 */     IAbilityData abilityProps = AbilityDataCapability.get(player);
/* 34 */     NetsuEnhancementAbility ability = (NetsuEnhancementAbility)abilityProps.getEquippedAbility((Ability)NetsuEnhancementAbility.INSTANCE);
/* 35 */     if (ability != null && ability.isContinuous()) {
/* 36 */       this.damage += 5;
/*    */     }
/* 38 */     setDamage(this.damage);
/* 39 */     setDamageSource(DamageSource.IN_FIRE);
/* 40 */     setMaxLife(30);
/*    */     
/* 42 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 49 */     hitEntity.setFire(4);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 54 */     AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 59 */     if (!this.world.isRemote)
/*    */     {
/* 61 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 63 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 64 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 65 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 67 */         ParticleType<GenericParticleData> particle = ModParticleTypes.NETSU;
/* 68 */         if (i % 3 == 0)
/* 69 */           particle = ModParticleTypes.NETSU2; 
/* 70 */         if (i % 7 == 0) {
/* 71 */           particle = ModParticleTypes.MERA;
/*    */         }
/* 73 */         GenericParticleData data = new GenericParticleData(particle);
/* 74 */         data.setLife(10);
/* 75 */         data.setSize(1.3F);
/* 76 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\netsu\NekkaiGyoraiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */