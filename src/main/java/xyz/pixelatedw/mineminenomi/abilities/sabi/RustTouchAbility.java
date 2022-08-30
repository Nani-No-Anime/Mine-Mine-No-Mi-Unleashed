/*    */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*    */ 
/*    */ import com.google.common.collect.Iterables;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class RustTouchAbility
/*    */   extends PunchAbility implements IPunchOverlayAbility {
/* 23 */   public static final RustTouchAbility INSTANCE = new RustTouchAbility();
/*    */   
/* 25 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new RustTouchParticleEffect();
/* 26 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.RUST_TOUCH_ARM);
/*    */ 
/*    */   
/*    */   public RustTouchAbility() {
/* 30 */     super("Rust Touch", AbilityHelper.getDevilFruitCategory());
/* 31 */     setMaxCooldown(15.0D);
/* 32 */     setDescription("Rusts the enemy and the items equipped on them");
/*    */     
/* 34 */     this.onHitEntityEvent = this::onHitEntity;
/* 35 */     this.onHitEffectEvent = this::onHitEffectEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
/* 40 */     target.addPotionEffect(new EffectInstance(Effects.WITHER, 160, 2));
/* 41 */     target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 160, 1));
/* 42 */     target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 160, 1));
/*    */     
/* 44 */     Iterable<ItemStack> iter = target.getEquipmentAndArmor();
/* 45 */     if (target instanceof PlayerEntity) {
/*    */       
/* 47 */       PlayerInventory playerInv = ((PlayerEntity)target).inventory;
/* 48 */       iter = Iterables.concat((Iterable)playerInv.mainInventory, (Iterable)playerInv.armorInventory, (Iterable)playerInv.offHandInventory);
/*    */     } 
/*    */     
/* 51 */     for (ItemStack stack : iter) {
/*    */       
/* 53 */       if (!stack.isEmpty() && stack.getItem().isIn(ModTags.Items.RUSTY)) {
/*    */         
/* 55 */         if (stack.isDamageable()) {
/* 56 */           stack.damageItem(stack.getMaxDamage() / 3 + 1 + 1, target, e -> e.sendBreakAnimation(EquipmentSlotType.MAINHAND)); continue;
/*    */         } 
/* 58 */         stack.shrink(1 + this.random.nextInt(4));
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity player, LivingEntity target) {
/* 67 */     return 8.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity entity) {
/* 73 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustTouchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */