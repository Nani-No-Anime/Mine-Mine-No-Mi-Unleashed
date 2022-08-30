/*    */ package xyz.pixelatedw.mineminenomi.abilities.mato;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mato.TargetProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class Target1Ability extends Ability {
/* 14 */   public static final Ability INSTANCE = new Target1Ability();
/*    */ 
/*    */   
/*    */   public Target1Ability() {
/* 18 */     super("Target1", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("when activated, the user can drop the item he's holding, which will launch the item towards the marked mob");
/*    */     
/* 21 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 27 */     ItemStack it = player.getHeldItemMainhand();
/* 28 */     Item item = it.getItem();
/*    */ 
/*    */     
/* 31 */     if (it == null || it == new ItemStack((IItemProvider)Blocks.AIR) || it == new ItemStack(null)) {
/*    */ 
/*    */       
/* 34 */       player.sendMessage((ITextComponent)new StringTextComponent("You must be holding an object in your main hand to use the target ability !"));
/*    */     }
/* 36 */     else if (it != null || it != new ItemStack((IItemProvider)Blocks.AIR) || it != new ItemStack(null)) {
/*    */       
/* 38 */       player.inventory.clearMatchingItems(p -> ((new ItemStack((IItemProvider)item, 1)).getItem() == p.getItem()), 1);
/* 39 */       TargetProjectile projectile = new TargetProjectile(player.world, (LivingEntity)player);
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
/* 63 */       projectile.setDamage(1.0F);
/* 64 */       player.world.addEntity((Entity)projectile);
/* 65 */       projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 1.0F, 1.0F);
/*    */     } 
/*    */ 
/*    */     
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mato\Target1Ability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */