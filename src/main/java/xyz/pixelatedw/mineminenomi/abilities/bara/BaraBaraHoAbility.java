/*     */ package xyz.pixelatedw.mineminenomi.abilities.bara;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMorphAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bara.BaraBaraHoProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.BaraHoZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class BaraBaraHoAbility extends Ability implements IMorphAbility {
/*  25 */   public static final BaraBaraHoAbility INSTANCE = new BaraBaraHoAbility();
/*     */   
/*     */   private BaraBaraHoProjectile projectile;
/*     */ 
/*     */   
/*     */   public BaraBaraHoAbility() {
/*  31 */     super("Bara Bara Ho", AbilityHelper.getDevilFruitCategory());
/*  32 */     setDescription("Launches the user's fist towards the enemy\nIf the user holds a weapon in hand this will increase the fist's damage as well");
/*  33 */     setMaxCooldown(4.0D);
/*     */     
/*  35 */     this.onUseEvent = this::onUseEvent;
/*  36 */     this.duringCooldownEvent = this::duringCooldownEvent;
/*  37 */     this.onEndCooldownEvent = this::onEndCooldownEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  42 */     BaraBaraFestivalAbility ability = (BaraBaraFestivalAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)BaraBaraFestivalAbility.INSTANCE);
/*  43 */     if (ability != null && ability.isContinuous()) {
/*     */       
/*  45 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_BARA_FESTIVAL, new Object[] { getDisplayName() }));
/*  46 */       return false;
/*     */     } 
/*     */     
/*  49 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  50 */     if (props.getZoanPoint().isEmpty()) {
/*  51 */       props.setZoanPoint("");
/*     */     }
/*  53 */     BaraBaraHoProjectile proj = new BaraBaraHoProjectile(player.world, (LivingEntity)player);
/*     */     
/*  55 */     ItemStack stack = player.getHeldItemMainhand();
/*     */     
/*  57 */     float extraDamage = 0.0F;
/*  58 */     if (ItemsHelper.isSword(stack)) {
/*  59 */       extraDamage = ItemsHelper.getItemDamage(stack);
/*     */     }
/*  61 */     proj.setDamage(proj.getDamage() + extraDamage);
/*     */     
/*  63 */     player.world.addEntity((Entity)proj);
/*  64 */     proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*  65 */     this.projectile = proj;
/*     */     
/*  67 */     props.setZoanPoint(getTransformation().getForm());
/*  68 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/*     */     
/*  70 */     player.addPotionEffect(new EffectInstance(ModEffects.NO_HANDS, 12, 0));
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldownEvent(PlayerEntity player, int cd) {
/*  77 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  78 */     if (this.projectile == null || !this.projectile.isAlive()) {
/*     */       
/*  80 */       props.setZoanPoint("");
/*  81 */       WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void onEndCooldownEvent(PlayerEntity player) {
/*  87 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)player);
/*  88 */     props.setZoanPoint("");
/*  89 */     WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo getTransformation() {
/*  95 */     return (ZoanInfo)BaraHoZoanInfo.INSTANCE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformationActive(LivingEntity entity) {
/* 101 */     return DevilFruitCapability.get(entity).getZoanPoint().equals(getTransformation().getForm());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bara\BaraBaraHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */