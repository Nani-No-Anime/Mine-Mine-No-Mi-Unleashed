/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityStairsAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ public class BarrierbilityStairsProjectile extends AbilityProjectileEntity {
/* 17 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(World world) {
/* 21 */     super(BariProjectiles.BARRIERBILITY_STAIRS, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(World world, double x, double y, double z) {
/* 31 */     super(BariProjectiles.BARRIERBILITY_STAIRS, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierbilityStairsProjectile(World world, LivingEntity player) {
/* 36 */     super(BariProjectiles.BARRIERBILITY_STAIRS, world, player);
/*    */     
/* 38 */     setMaxLife(40);
/* 39 */     setPhysical(false);
/* 40 */     setPassThroughEntities();
/* 41 */     setPassThroughBlocks();
/*    */     
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 48 */     IAbilityData abilityProps = AbilityDataCapability.get(getThrower());
/* 49 */     BarrierbilityStairsAbility ability = (BarrierbilityStairsAbility)abilityProps.getEquippedAbility((Ability)BarrierbilityStairsAbility.INSTANCE);
/*    */     
/* 51 */     if (ability != null && ability.isContinuous())
/* 52 */       ability.fillBlocksList(AbilityHelper.createFilledCube(this.world, getPosX(), getPosY() - 2.0D, getPosZ(), 1, 1, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BarrierbilityStairsProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */