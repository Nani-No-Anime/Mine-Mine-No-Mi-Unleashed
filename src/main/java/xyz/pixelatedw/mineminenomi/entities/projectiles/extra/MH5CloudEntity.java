/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.extra.MH5GasParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class MH5CloudEntity
/*    */   extends EntityCloud {
/* 15 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new MH5GasParticleEffect();
/*    */ 
/*    */   
/*    */   public MH5CloudEntity(World world) {
/* 19 */     super(world);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove() {
/* 25 */     for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 100.0D)) {
/*    */       
/* 27 */       ItemStack stack = target.getItemStackFromSlot(EquipmentSlotType.HEAD);
/* 28 */       if (stack == null || stack.getItem() != ModArmors.MH5_GAS_MASK)
/*    */       {
/* 30 */         target.attackEntityFrom(DamageSource.MAGIC, target.getHealth());
/*    */       }
/*    */     } 
/*    */     
/* 34 */     super.remove();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 40 */     super.tick();
/* 41 */     if (!this.world.isRemote)
/*    */     {
/* 43 */       if (this.ticksExisted % 2 == 0)
/* 44 */         PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\MH5CloudEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */