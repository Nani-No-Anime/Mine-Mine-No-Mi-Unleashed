/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class BeroCannonAbility extends Ability {
/* 17 */   public static final BeroCannonAbility INSTANCE = new BeroCannonAbility();
/*    */ 
/*    */   
/*    */   public BeroCannonAbility() {
/* 21 */     super("Bero Cannon", AbilityHelper.getDevilFruitCategory());
/* 22 */     setMaxCooldown(5.0D);
/* 23 */     setDescription("Transforms the user's tongue into a cannon and fires a random block from their inventory");
/*    */     
/* 25 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 30 */     List<ItemStack> projectiles = new ArrayList<>();
/* 31 */     for (ItemStack item : player.inventory.mainInventory) {
/*    */       
/* 33 */       if (item != null && item.getItem() instanceof BlockItem && BakuMunchAbility.GRIEF_RULE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.getItem()).getBlock()))) {
/*    */         
/* 35 */         projectiles.add(item);
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 40 */     if (!projectiles.isEmpty()) {
/*    */       
/* 42 */       BeroCannonProjectile proj = new BeroCannonProjectile(player.world, (LivingEntity)player);
/* 43 */       player.world.addEntity((Entity)proj);
/* 44 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.0F);
/* 45 */       ItemStack s = projectiles.stream().findFirst().orElse(null);
/* 46 */       if (s.getCount() > 1) {
/* 47 */         s.setCount(s.getCount() - 1);
/*    */       } else {
/* 49 */         player.inventory.deleteStack(s);
/*    */       } 
/* 51 */       return true;
/*    */     } 
/*    */     
/* 54 */     player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS, new Object[0]));
/*    */     
/* 56 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BeroCannonAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */