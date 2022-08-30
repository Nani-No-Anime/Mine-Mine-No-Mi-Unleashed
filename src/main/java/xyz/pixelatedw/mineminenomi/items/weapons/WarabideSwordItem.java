/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.wara.StrawProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class WarabideSwordItem extends AbilitySwordItem {
/*    */   public WarabideSwordItem(Ability instance, int damage) {
/* 15 */     super(instance, damage);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 21 */     ActionResult<ItemStack> result = super.onItemRightClick(world, player, hand);
/*    */     
/* 23 */     if (!world.isRemote) {
/*    */       
/* 25 */       StrawProjectile projectile = new StrawProjectile(player.world, (LivingEntity)player);
/* 26 */       player.world.addEntity((Entity)projectile);
/* 27 */       projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.5F);
/* 28 */       player.getCooldownTracker().setCooldown((Item)this, 25);
/*    */     } 
/*    */     
/* 31 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\weapons\WarabideSwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */