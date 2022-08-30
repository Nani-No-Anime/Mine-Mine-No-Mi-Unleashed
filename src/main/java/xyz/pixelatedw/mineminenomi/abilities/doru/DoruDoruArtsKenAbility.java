/*    */ package xyz.pixelatedw.mineminenomi.abilities.doru;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class DoruDoruArtsKenAbility extends ItemAbility implements IParallelContinuousAbility {
/* 19 */   public static final Ability INSTANCE = (Ability)new DoruDoruArtsKenAbility();
/*    */ 
/*    */   
/*    */   public DoruDoruArtsKenAbility() {
/* 23 */     super("Doru Doru Arts: Ken", AbilityHelper.getDevilFruitCategory());
/* 24 */     setDescription("The user uses hardened wax to create a sword");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 30 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 31 */     return devilFruitProps.hasDevilFruit(ModAbilities.DORU_DORU_NO_MI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 37 */     ItemStack itemStack = new ItemStack((IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN);
/* 38 */     float red = 1.0F;
/* 39 */     float green = 1.0F;
/* 40 */     float blue = 1.0F;
/* 41 */     if (player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.COLOR_PALETTE))) {
/*    */       
/* 43 */       red = this.random.nextFloat();
/* 44 */       green = this.random.nextFloat();
/* 45 */       blue = this.random.nextFloat();
/*    */     } 
/* 47 */     itemStack.getOrCreateChildTag("display").putInt("color", (new Color(red, green, blue)).getRGB());
/* 48 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doru\DoruDoruArtsKenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */