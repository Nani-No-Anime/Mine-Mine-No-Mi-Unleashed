/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayEntityEffectPacket;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BlackBoxProjectile extends AbilityProjectileEntity {
/*    */   public BlackBoxProjectile(World world) {
/* 16 */     super(KageProjectiles.BLACK_BOX, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackBoxProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackBoxProjectile(World world, double x, double y, double z) {
/* 26 */     super(KageProjectiles.BLACK_BOX, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlackBoxProjectile(World world, LivingEntity player) {
/* 31 */     super(KageProjectiles.BLACK_BOX, world, player);
/*    */     
/* 33 */     setDamage(5.0F);
/* 34 */     setMaxLife(15);
/* 35 */     setPhysical(false);
/* 36 */     setAffectedByImbuing();
/*    */     
/* 38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 43 */     EffectInstance instance = new EffectInstance(ModEffects.BLACK_BOX, 160, 0);
/* 44 */     target.addPotionEffect(instance);
/* 45 */     if (this.owner instanceof ServerPlayerEntity)
/* 46 */       ((ServerPlayerEntity)this.owner).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(target.getEntityId(), instance)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\BlackBoxProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */