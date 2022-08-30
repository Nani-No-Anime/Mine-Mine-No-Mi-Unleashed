/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class NoroNoroBeamSwordAbility extends ItemAbility implements IParallelContinuousAbility {
/* 15 */   public static final Ability INSTANCE = (Ability)new NoroNoroBeamSwordAbility();
/*    */ 
/*    */   
/*    */   public NoroNoroBeamSwordAbility() {
/* 19 */     super("Noro Noro Beam Sword", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Focuses photons inside a hilt to create a sword, which slows enemies upon hit");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 26 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 27 */     return devilFruitProps.getDevilFruit().equalsIgnoreCase("noro_noro");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 33 */     return new ItemStack((IItemProvider)ModWeapons.NORO_NORO_BEAM_SWORD);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\NoroNoroBeamSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */