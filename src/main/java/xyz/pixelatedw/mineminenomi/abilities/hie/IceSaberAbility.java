/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ItemAbility;
/*    */ 
/*    */ public class IceSaberAbility extends ItemAbility implements IParallelContinuousAbility {
/* 16 */   public static final Ability INSTANCE = (Ability)new IceSaberAbility();
/*    */ 
/*    */   
/*    */   public IceSaberAbility() {
/* 20 */     super("Ice Saber", AbilityHelper.getDevilFruitCategory());
/* 21 */     setDescription("Creates a sharp blade made of compressed ice");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 27 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 28 */     return devilFruitProps.hasDevilFruit(ModAbilities.HIE_HIE_NO_MI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 34 */     return new ItemStack((IItemProvider)ModWeapons.ICE_SABER);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceSaberAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */