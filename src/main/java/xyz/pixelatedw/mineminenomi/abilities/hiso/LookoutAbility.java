/*    */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.passive.AnimalEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class LookoutAbility extends Ability {
/* 20 */   public static final LookoutAbility INSTANCE = new LookoutAbility();
/*    */ 
/*    */   
/*    */   public LookoutAbility() {
/* 24 */     super("Animal Lookout", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("Allows the user to communicate with nearby animals and learn if other players passed near them");
/* 26 */     setMaxCooldown(10.0D);
/* 27 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 32 */     List<AnimalEntity> around = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { AnimalEntity.class });
/*    */     
/* 34 */     around.forEach(entity -> {
/*    */           if (!(entity instanceof AnimalEntity)) {
/*    */             return;
/*    */           }
/*    */ 
/*    */           
/*    */           if (entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == Items.COBBLESTONE) {
/*    */             CompoundNBT tag = entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getTag();
/*    */ 
/*    */             
/*    */             ListNBT seen = (ListNBT)tag.get("seen");
/*    */ 
/*    */             
/*    */             player.sendMessage((ITextComponent)new StringTextComponent("Recently:"));
/*    */             //[todo : figure this out]
/*    */             //seen.forEach(()->{});
/*    */           } 
/*    */         });
/*    */     
/* 53 */     if (around.size() > 0) {
/* 54 */       ((AnimalEntity)around.get(0)).addPotionEffect(new EffectInstance(ModEffects.ANIMAL_LOOKOUT, 9999));
/*    */     }
/* 56 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\LookoutAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */