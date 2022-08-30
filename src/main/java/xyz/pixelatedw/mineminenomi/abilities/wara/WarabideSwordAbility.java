/*    */ package xyz.pixelatedw.mineminenomi.abilities.wara;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class WarabideSwordAbility extends ItemAbility {
/* 14 */   public static final WarabideSwordAbility INSTANCE = new WarabideSwordAbility();
/*    */ 
/*    */   
/*    */   public WarabideSwordAbility() {
/* 18 */     super("Warabide Sword", AbilityHelper.getDevilFruitCategory());
/* 19 */     setDescription("Creates a sword that can shoot long thin straw-like projectiles");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 25 */     return new ItemStack((IItemProvider)ModWeapons.WARABIDE_SWORD);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 31 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/* 32 */     return props.hasDevilFruit(ModAbilities.WARA_WARA_NO_MI);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\WarabideSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */