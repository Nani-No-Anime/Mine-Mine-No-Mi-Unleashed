/*    */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*    */ 
/*    */ import com.google.common.collect.Iterables;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.math.Vec3i;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.jiki.AttractParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AttractAbility extends Ability {
/* 24 */   public static final AttractAbility INSTANCE = new AttractAbility();
/*    */   
/* 26 */   public static final AttractParticleEffect PARTICLES = new AttractParticleEffect();
/*    */   
/*    */   private static final int RADIUS = 40;
/*    */ 
/*    */   
/*    */   public AttractAbility() {
/* 32 */     super("Attract", AbilityHelper.getDevilFruitCategory());
/* 33 */     setDescription("§2Range:§r 40 blocks\nAttracts all nearby magnetic objects from the ground or enemy inventories");
/*    */ 
/*    */     
/* 36 */     setMaxCooldown(3.0D);
/*    */     
/* 38 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onUseEvent(PlayerEntity player) {
/* 43 */     List<Entity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { MobEntity.class, PlayerEntity.class });
/* 44 */     targets.remove(player);
/*    */     
/* 46 */     for (Entity target : targets) {
/*    */       
/* 48 */       Iterable<ItemStack> iter = target.getEquipmentAndArmor();
/* 49 */       if (target instanceof PlayerEntity) {
/*    */         
/* 51 */         PlayerInventory playerInv = ((PlayerEntity)target).inventory;
/* 52 */         iter = Iterables.concat((Iterable)playerInv.mainInventory, (Iterable)playerInv.armorInventory, (Iterable)playerInv.offHandInventory);
/*    */       } 
/*    */       
/* 55 */       for (ItemStack stack : iter) {
/*    */         
/* 57 */         if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.MAGNETIC)) {
/*    */           
/* 59 */           if (target instanceof MobEntity) {
/*    */             
/* 61 */             EquipmentSlotType slot = MobEntity.getSlotForItemStack(stack);
/* 62 */             ((MobEntity)target).setItemStackToSlot(slot, ItemStack.EMPTY);
/*    */           } 
/*    */           
/* 65 */           if (target instanceof PlayerEntity)
/*    */           {
/* 67 */             ((PlayerEntity)target).inventory.deleteStack(stack);
/*    */           }
/*    */           
/* 70 */           ItemEntity item = target.entityDropItem(stack, 1.0F);
/* 71 */           item.setPickupDelay(30);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 76 */     List<ItemEntity> items = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40.0D, new Class[] { ItemEntity.class });
/*    */     
/* 78 */     for (ItemEntity item : items) {
/*    */       
/* 80 */       if (item.getItem().isEmpty() || !item.getItem().getItem().isIn(ModTags.Items.MAGNETIC))
/*    */         continue; 
/* 82 */       Vec3d vec = item.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -3.0D, 0.0D);
/* 83 */       double speedReduction = 8.0D;
/* 84 */       double xSpeed = -vec.x / speedReduction;
/* 85 */       double ySpeed = -vec.y / speedReduction;
/* 86 */       double zSpeed = -vec.z / speedReduction;
/* 87 */       item.setMotion(xSpeed, ySpeed, zSpeed);
/* 88 */       item.velocityChanged = true;
/* 89 */       double dist = Math.sqrt(player.getPosition().distanceSq((Vec3i)item.getPosition()));
/* 90 */       int delay = (int)Math.max(5.0D, dist / 3.0D);
/* 91 */       item.setPickupDelay(delay);
/*    */       
/* 93 */       PARTICLES.spawn(player.world, item.getPosX(), item.getPosY(), item.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     } 
/*    */     
/* 96 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\AttractAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */