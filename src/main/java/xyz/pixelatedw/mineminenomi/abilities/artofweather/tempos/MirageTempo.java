/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.MirageCloneEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
/*    */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*    */ 
/*    */ public class MirageTempo extends TempoAbility {
/* 14 */   public static final MirageTempo INSTANCE = new MirageTempo();
/*    */ 
/*    */   
/*    */   public MirageTempo() {
/* 18 */     super("Mirage Tempo", AbilityHelper.getStyleCategory());
/* 19 */     setDescription("2 Charged Cool Ball + Charged Heat Ball\nCreates several clones of the user");
/* 20 */     setCustomTexture("tempo");
/* 21 */     setMaxCooldown(10.0D);
/*    */     
/* 23 */     this.onUseEvent = this::onUseEvent;
/* 24 */     this.canUseCheck = this::canUseCheck;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) {
/* 29 */     if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
/*    */       
/* 31 */       ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem();
/* 32 */       String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
/* 33 */       return tempoCombo.equalsIgnoreCase("CCH");
/*    */     } 
/*    */     
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 41 */     MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
/* 42 */     smokeCloud.setLife(50);
/* 43 */     smokeCloud.setLocationAndAngles(player.getPosX(), player.getPosY() + 1.0D, player.getPosZ(), 0.0F, 0.0F);
/* 44 */     smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
/* 45 */     smokeCloud.setThrower((LivingEntity)player);
/* 46 */     player.world.addEntity((Entity)smokeCloud);
/*    */     
/* 48 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 50 */       MirageCloneEntity mirageClone = new MirageCloneEntity(player.world);
/* 51 */       mirageClone.setPositionAndRotation(player.getPosX(), player.getPosY(), player.getPosZ(), 180.0F, 0.0F);
/* 52 */       mirageClone.setOwner(player.getUniqueID());
/* 53 */       for (EquipmentSlotType slot : EquipmentSlotType.values()) {
/*    */         
/* 55 */         ItemStack stack = player.getItemStackFromSlot(slot);
/* 56 */         mirageClone.setItemStackToSlot(slot, stack);
/*    */       } 
/* 58 */       player.world.addEntity((Entity)mirageClone);
/*    */     } 
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\MirageTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */