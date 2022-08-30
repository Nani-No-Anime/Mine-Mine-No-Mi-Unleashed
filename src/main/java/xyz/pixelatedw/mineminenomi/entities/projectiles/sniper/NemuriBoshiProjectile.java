/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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
/*    */ public class NemuriBoshiProjectile extends AbilityProjectileEntity {
/*    */   public NemuriBoshiProjectile(World world) {
/* 16 */     super(SniperProjectiles.NEMURI_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NemuriBoshiProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NemuriBoshiProjectile(World world, double x, double y, double z) {
/* 26 */     super(SniperProjectiles.NEMURI_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public NemuriBoshiProjectile(World world, LivingEntity player) {
/* 31 */     super(SniperProjectiles.NEMURI_BOSHI, world, player);
/*    */     
/* 33 */     setDamage(2.0F);
/* 34 */     setPhysical(false);
/* 35 */     setAffectedByImbuing();
/*    */     
/* 37 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 42 */     EffectInstance effect = new EffectInstance(ModEffects.UNCONSCIOUS, 120, 1);
/* 43 */     hitEntity.addPotionEffect(effect);
/* 44 */     if (this.owner instanceof ServerPlayerEntity)
/* 45 */       ((ServerPlayerEntity)this.owner).connection.sendPacket((IPacket)new SPlayEntityEffectPacket(hitEntity.getEntityId(), effect)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\NemuriBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */