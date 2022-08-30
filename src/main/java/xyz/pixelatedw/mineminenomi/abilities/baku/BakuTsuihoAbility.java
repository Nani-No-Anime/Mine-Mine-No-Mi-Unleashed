/*     */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.BlockItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.baku.BeroCannonProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ 
/*     */ public class BakuTsuihoAbility extends ChargeableAbility {
/*  19 */   public static final BakuTsuihoAbility INSTANCE = new BakuTsuihoAbility();
/*     */   
/*  21 */   private List<ItemStack> projectiles = new ArrayList<>();
/*  22 */   private List<Block> loadedProjectiles = new ArrayList<>();
/*  23 */   private int limit = 0;
/*     */ 
/*     */   
/*     */   public BakuTsuihoAbility() {
/*  27 */     super("Baku Tsuiho", AbilityHelper.getDevilFruitCategory());
/*  28 */     setMaxCooldown(8.0D);
/*  29 */     setMaxChargeTime(4.0D);
/*  30 */     setCancelable();
/*  31 */     setDescription("Allows the user to charge multiple blocks from their inventory in their mouth and shoot them all at the same time");
/*     */     
/*  33 */     this.onStartChargingEvent = this::onStartChargingEvent;
/*  34 */     this.duringChargingEvent = this::duringChargingEvent;
/*  35 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*  36 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int cooldown) {
/*  41 */     int projectileSpace = 2;
/*  42 */     if (this.limit > 0 && (this.cooldown - 10.0D) % 2.0D == 0.0D) {
/*     */       
/*  44 */       BeroCannonProjectile proj = new BeroCannonProjectile(player.world, (LivingEntity)player);
/*  45 */       proj.setLocationAndAngles(player
/*  46 */           .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
/*  47 */           .getPosY() + 0.3D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
/*  48 */           .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
/*     */       
/*  50 */       player.world.addEntity((Entity)proj);
/*  51 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F, 3.0F);
/*  52 */       this.limit--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onEndChargingEvent(PlayerEntity player) {
/*  58 */     this.limit = 1 + this.loadedProjectiles.size();
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringChargingEvent(PlayerEntity player, int chargeTime) {
/*  65 */     if (!this.projectiles.isEmpty())
/*     */     {
/*  67 */       if (chargeTime % 10 == 0) {
/*     */         
/*  69 */         ItemStack stack = this.projectiles.stream().findAny().orElse(null);
/*  70 */         if (stack != null) {
/*     */           
/*  72 */           if (stack.getCount() > 1) {
/*  73 */             stack.shrink(1);
/*     */           } else {
/*     */             
/*  76 */             player.inventory.deleteStack(stack);
/*  77 */             this.projectiles.remove(stack);
/*     */           } 
/*  79 */           this.loadedProjectiles.add(((BlockItem)stack.getItem()).getBlock());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartChargingEvent(PlayerEntity player) {
/*  87 */     this.loadedProjectiles.clear();
/*  88 */     this.projectiles.clear();
/*     */     
/*  90 */     for (ItemStack item : player.inventory.mainInventory) {
/*     */       
/*  92 */       if (item != null && item.getItem() instanceof BlockItem && BakuMunchAbility.GRIEF_RULE.getApprovedBlocks().stream().anyMatch(p -> (p == ((BlockItem)item.getItem()).getBlock()))) {
/*  93 */         this.projectiles.add(item);
/*     */       }
/*     */     } 
/*  96 */     if (!this.projectiles.isEmpty()) {
/*  97 */       return true;
/*     */     }
/*  99 */     player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS, new Object[0]));
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuTsuihoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */