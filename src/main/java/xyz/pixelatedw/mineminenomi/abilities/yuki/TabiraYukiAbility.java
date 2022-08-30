/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class TabiraYukiAbility extends ItemAbility implements IParallelContinuousAbility {
/* 14 */   public static final TabiraYukiAbility INSTANCE = new TabiraYukiAbility();
/*    */ 
/*    */   
/*    */   public TabiraYukiAbility() {
/* 18 */     super("Tabira Yuki", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("The user creates a sword made of solid hardened snow. Will inflict §2Frostbite§r");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 25 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 26 */     return devilFruitProps.getDevilFruit().equalsIgnoreCase("yuki_yuki");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 32 */     return new ItemStack((IItemProvider)ModWeapons.TABIRA_YUKI);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\TabiraYukiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */