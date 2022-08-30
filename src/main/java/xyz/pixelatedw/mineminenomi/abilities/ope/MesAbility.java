/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.CreatureAttribute;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.items.HeartItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class MesAbility extends PunchAbility implements IParallelContinuousAbility {
/* 22 */   public static final Ability INSTANCE = (Ability)new MesAbility();
/*    */ 
/*    */   
/*    */   public MesAbility() {
/* 26 */     super("MES", AbilityHelper.getDevilFruitCategory());
/* 27 */     setMaxCooldown(30.0D);
/* 28 */     setDescription("Removes the heart of the user's target which they can then damage to hurt the opponent");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 32 */     this.onHitEntityEvent = this::onHitEntity;
/* 33 */     this.onHitEffectEvent = this::onHitEffect;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 38 */     if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
/* 39 */       return false;
/*    */     }
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 46 */     if (!OpeHelper.hasRoomActive(player, (Ability)this)) {
/* 47 */       endContinuity(player);
/*    */     }
/*    */   }
/*    */   
/*    */   private void onHitEffect(PlayerEntity player, LivingEntity target) {
/* 52 */     IDevilFruit props = DevilFruitCapability.get(target);
/* 53 */     IEntityStats targetProps = EntityStatsCapability.get(target);
/*    */     
/* 55 */     boolean targetNoHeart = (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || target.getCreatureAttribute().equals(CreatureAttribute.UNDEAD));
/*    */     
/* 57 */     if (targetProps.hasHeart() && !targetNoHeart) {
/*    */       
/* 59 */       ItemStack heart = new ItemStack((IItemProvider)ModItems.HEART);
/* 60 */       ((HeartItem)heart.getItem()).setHeartOwner(heart, target);
/* 61 */       heart.setDisplayName((ITextComponent)new StringTextComponent(target.getDisplayName().getFormattedText() + "'s Heart"));
/* 62 */       player.inventory.addItemStackToInventory(heart);
/* 63 */       targetProps.setHeart(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 69 */     IDevilFruit props = DevilFruitCapability.get(target);
/*    */     
/* 71 */     if (props.hasDevilFruit(ModAbilities.WARA_WARA_NO_MI))
/*    */     {
/* 73 */       for (int i = 0; i < ((PlayerEntity)target).inventory.mainInventory.size(); i++) {
/* 74 */         if (((PlayerEntity)target).inventory.getStackInSlot(i).getItem() == ModItems.STRAW_DOLL)
/* 75 */           return 0.0F; 
/*    */       } 
/*    */     }
/* 78 */     if (AbilityHelper.isTargetBlockingAbility((LivingEntity)player, target)) {
/* 79 */       return 0.0F;
/*    */     }
/* 81 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\MesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */