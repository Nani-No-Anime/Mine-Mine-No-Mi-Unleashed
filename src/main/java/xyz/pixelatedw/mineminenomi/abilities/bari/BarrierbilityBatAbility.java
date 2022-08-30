/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class BarrierbilityBatAbility extends ItemAbility implements IParallelContinuousAbility {
/* 15 */   public static final BarrierbilityBatAbility INSTANCE = new BarrierbilityBatAbility();
/*    */ 
/*    */   
/*    */   public BarrierbilityBatAbility() {
/* 19 */     super("Barrierbility Bat", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Shapes the barriers in the form of a bat that the user can hold");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 26 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 27 */     return devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 33 */     return new ItemStack((IItemProvider)ModWeapons.BARRIERBILITY_BAT);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierbilityBatAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */