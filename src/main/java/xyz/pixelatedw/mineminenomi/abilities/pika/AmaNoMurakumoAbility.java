/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
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
/*    */ public class AmaNoMurakumoAbility extends ItemAbility implements IParallelContinuousAbility {
/* 15 */   public static final Ability INSTANCE = (Ability)new AmaNoMurakumoAbility();
/*    */ 
/*    */   
/*    */   public AmaNoMurakumoAbility() {
/* 19 */     super("Ama no Murakumo", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Focuses light in the user's hand to create a sword");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeActive(PlayerEntity player) {
/* 26 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 27 */     return devilFruitProps.getDevilFruit().equalsIgnoreCase("pika_pika");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack(PlayerEntity player) {
/* 33 */     return new ItemStack((IItemProvider)ModWeapons.AMA_NO_MURAKUMO);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\AmaNoMurakumoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */