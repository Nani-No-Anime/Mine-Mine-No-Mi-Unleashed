/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class CigarItem extends Item {
/* 28 */   private int smokeFreqency = 1;
/*    */ 
/*    */   
/*    */   public CigarItem(int smokeIn) {
/* 32 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(200));
/* 33 */     this.smokeFreqency = smokeIn;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 39 */     if (player.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty()) {
/*    */       
/* 41 */       ItemStack stack = new ItemStack((IItemProvider)player.getHeldItem(hand).getItem(), 1);
/* 42 */       player.setItemStackToSlot(EquipmentSlotType.HEAD, stack);
/* 43 */       player.playSound(SoundEvents.ITEM_FLINTANDSTEEL_USE, 1.0F, 1.0F);
/* 44 */       player.getHeldItem(hand).shrink(1);
/*    */     } 
/*    */     
/* 47 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
/* 53 */     return armorType.equals(EquipmentSlotType.HEAD);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
/* 59 */     if (player.ticksExisted % this.smokeFreqency == 0 && !player.canSwim()) {
/*    */       
/* 61 */       Vec3d vec = player.getLookVec().scale(0.5D + (player.getWidth() / 2.0F)).rotateYaw((float)Math.toRadians(-20.0D));
/* 62 */       world.addParticle((IParticleData)ParticleTypes.CAMPFIRE_COSY_SMOKE, player.getPosX() + vec.x, vec.y + player.getPosYEye(), player.getPosZ() + vec.z, 0.0D, 0.04D, 0.0D);
/*    */       
/* 64 */       if (!world.isRemote) {
/*    */         
/* 66 */         IDevilFruit d = DevilFruitCapability.get((LivingEntity)player);
/* 67 */         if (d.hasDevilFruit(ModAbilities.MOKU_MOKU_NO_MI) || d.hasDevilFruit(ModAbilities.GASU_GASU_NO_MI))
/*    */           return; 
/* 69 */         stack.damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.HEAD));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addInformation(ItemStack stack, @Nullable World p_77624_2_, List<ITextComponent> a, ITooltipFlag p_77624_4_) {
/* 80 */     super.addInformation(stack, p_77624_2_, a, p_77624_4_);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\CigarItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */