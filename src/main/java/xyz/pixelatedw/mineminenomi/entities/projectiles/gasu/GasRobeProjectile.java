/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.ShinokuniZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class GasRobeProjectile extends AbilityProjectileEntity {
/*    */   boolean shinokuni;
/*    */   ShinokuniAbility ability;
/*    */   
/* 22 */   public GasRobeProjectile(World world) { super(GasuProjectiles.GAS_ROBE, world);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     this.shinokuni = false; } public GasRobeProjectile(EntityType type, World world) { super(type, world); this.shinokuni = false; } public GasRobeProjectile(World world, double x, double y, double z) { super(GasuProjectiles.GAS_ROBE, world, x, y, z); this.shinokuni = false; }
/*    */ 
/*    */   
/*    */   public GasRobeProjectile(World world, LivingEntity player) {
/* 40 */     super(GasuProjectiles.GAS_ROBE, world, player);
/*    */     this.shinokuni = false;
/* 42 */     setDamage(0.1F);
/* 43 */     setMaxLife(30);
/* 44 */     setPassThroughEntities();
/* 45 */     setChangeHurtTime(true);
/*    */     
/* 47 */     this.shinokuni = ShinokuniZoanInfo.INSTANCE.isActive(player);
/* 48 */     if (this.shinokuni) {
/*    */       
/* 50 */       this.ability = (ShinokuniAbility)AbilityDataCapability.get(player).getEquippedAbility((Ability)ShinokuniAbility.INSTANCE);
/*    */     } else {
/*    */       
/* 53 */       this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.WEAKNESS, 200, 2), new EffectInstance(Effects.BLINDNESS, 40, 0), new EffectInstance(Effects.POISON, 200, 6) });
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 60 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 61 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 66 */     if (this.shinokuni) {
/* 67 */       this.ability.applyEffects((PlayerEntity)getThrower(), entity);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onTickEvent() {
/* 72 */     if (!this.world.isRemote)
/*    */     {
/* 74 */       for (int i = 0; i < 5; i++) {
/*    */         
/* 76 */         double offsetX = WyHelper.randomDouble() / 3.0D;
/* 77 */         double offsetY = WyHelper.randomDouble() / 3.0D;
/* 78 */         double offsetZ = WyHelper.randomDouble() / 3.0D;
/*    */         
/* 80 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
/* 81 */         data.setLife(5);
/* 82 */         data.setSize(2.0F);
/* 83 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GasRobeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */