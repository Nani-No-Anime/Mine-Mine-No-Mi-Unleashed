/*    */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.LivingHealByEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.doctor.FirstAidParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class FirstAidAbility extends PunchAbility {
/* 20 */   public static final FirstAidAbility INSTANCE = new FirstAidAbility();
/*    */   
/* 22 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new FirstAidParticleEffect();
/*    */ 
/*    */   
/*    */   public FirstAidAbility() {
/* 26 */     super("First Aid", AbilityHelper.getStyleCategory());
/* 27 */     setMaxCooldown(10.0D);
/* 28 */     setDescription("Allows the user to heal their target");
/*    */     
/* 30 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 31 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
/* 37 */     boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */     
/* 39 */     if (!hasMedicBag) {
/*    */       
/* 41 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
/* 42 */       return false;
/*    */     } 
/*    */     
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 50 */     ItemStack medicBag = (ItemStack)player.inventory.armorInventory.get(2);
/* 51 */     boolean hasMedicBag = (medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG);
/*    */     
/* 53 */     if (!hasMedicBag) {
/*    */       
/* 55 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, new Object[] { getName() }));
/* 56 */       return -1.0F;
/*    */     } 
/*    */     
/* 59 */     int heal = (int)(8.0D + WyHelper.percentage(20.0D, target.getMaxHealth()));
/* 60 */     LivingHealByEvent event = new LivingHealByEvent((LivingEntity)player, target, heal);
/* 61 */     MinecraftForge.EVENT_BUS.post(event);
/*    */     
/* 63 */     target.heal(heal);
/* 64 */     PARTICLES.spawn(player.world, target.getPosX(), target.getPosY(), target.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 66 */     int damage = (medicBag.getDamage() + 10 <= medicBag.getMaxDamage()) ? 10 : (medicBag.getMaxDamage() - medicBag.getDamage());
/* 67 */     medicBag.damageItem(damage, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/* 68 */     if (medicBag.getDamage() >= medicBag.getMaxDamage())
/*    */     {
/*    */       
/* 71 */       medicBag.shrink(1);
/*    */     }
/*    */     
/* 74 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\FirstAidAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */