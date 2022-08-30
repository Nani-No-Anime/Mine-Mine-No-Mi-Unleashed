/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bara.KuchuKirimomiDaiCircusAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class DaiCircusProjectile extends AbilityProjectileEntity {
/*    */   public DaiCircusProjectile(World world) {
/* 16 */     super(BaraProjectiles.DAI_CIRCUS, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DaiCircusProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DaiCircusProjectile(World world, double x, double y, double z) {
/* 26 */     super(BaraProjectiles.DAI_CIRCUS, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DaiCircusProjectile(World world, LivingEntity player) {
/* 31 */     super(BaraProjectiles.DAI_CIRCUS, world, player);
/*    */     
/* 33 */     setDamage(4.0F);
/* 34 */     setMaxLife(30);
/* 35 */     setPhysical(true);
/* 36 */     setCollisionSize(2.0D);
/*    */     
/* 38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 43 */     LivingEntity owner = getThrower();
/*    */     
/* 45 */     IAbilityData abilityProps = AbilityDataCapability.get(owner);
/* 46 */     KuchuKirimomiDaiCircusAbility ability = (KuchuKirimomiDaiCircusAbility)abilityProps.getEquippedAbility((Ability)KuchuKirimomiDaiCircusAbility.INSTANCE);
/* 47 */     if (ability != null && ability.isContinuous() && FactionHelper.getOutsideGroupPredicate(owner).test(hitEntity))
/* 48 */       ability.grabEntity(hitEntity); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\DaiCircusProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */