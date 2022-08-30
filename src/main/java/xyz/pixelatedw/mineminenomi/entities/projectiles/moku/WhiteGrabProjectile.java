/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.moku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.moku.WhiteGrabAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class WhiteGrabProjectile extends AbilityProjectileEntity {
/*    */   public WhiteGrabProjectile(World world) {
/* 20 */     super(MokuProjectiles.WHITE_GRAB, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteGrabProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteGrabProjectile(World world, double x, double y, double z) {
/* 30 */     super(MokuProjectiles.WHITE_GRAB, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public WhiteGrabProjectile(World world, LivingEntity player) {
/* 35 */     super(MokuProjectiles.WHITE_GRAB, world, player);
/*    */     
/* 37 */     setDamage(4.0F);
/* 38 */     setMaxLife(30);
/* 39 */     setPhysical(true);
/* 40 */     setCollisionSize(2.0D);
/*    */     
/* 42 */     this.onTickEvent = this::onTickEvent;
/* 43 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 48 */     LivingEntity owner = getThrower();
/*    */     
/* 50 */     IAbilityData abilityProps = AbilityDataCapability.get(owner);
/* 51 */     WhiteGrabAbility ability = (WhiteGrabAbility)abilityProps.getEquippedAbility((Ability)WhiteGrabAbility.INSTANCE);
/* 52 */     if (ability != null && ability.isContinuous() && FactionHelper.getOutsideGroupPredicate(owner).test(hitEntity)) {
/* 53 */       ability.grabEntity(hitEntity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 58 */     if (!this.world.isRemote)
/*    */     {
/* 60 */       for (int i = 0; i < 10; i++) {
/*    */         
/* 62 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 63 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 64 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 66 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.MOKU);
/* 67 */         data.setLife(10);
/* 68 */         data.setSize(1.3F);
/* 69 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\moku\WhiteGrabProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */