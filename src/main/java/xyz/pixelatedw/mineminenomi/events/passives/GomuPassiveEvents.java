/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class GomuPassiveEvents
/*     */ {
/*     */   @SubscribeEvent
/*     */   public static void onEntityHurt(LivingHurtEvent event) {
/*  35 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  38 */     DamageSource source = event.getSource();
/*  39 */     Entity instantSource = source.getImmediateSource();
/*  40 */     Entity trueSource = source.getTrueSource();
/*  41 */     PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
/*  42 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)attacked);
/*     */     
/*  44 */     if (!props.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI) || source.isMagicDamage()) {
/*     */       return;
/*     */     }
/*  47 */     float reduction = 0.0F;
/*  48 */     ArrayList<String> instantSources = new ArrayList<>(Arrays.asList(new String[] { "mob", "player" }));
/*     */     
/*  50 */     boolean a = false;
/*  51 */     if (instantSource instanceof LivingEntity) {
/*     */       
/*  53 */       ItemStack mainhandGear = ((LivingEntity)instantSource).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
/*  54 */       a = (trueSource instanceof LivingEntity && !HakiHelper.hasHardeningActive((LivingEntity)instantSource) && instantSources.contains(source.getDamageType()) && !source.isProjectile() && getGomuDamagingItems(mainhandGear.getItem()) && !ItemsHelper.isKairosekiWeapon(mainhandGear));
/*     */     } 
/*     */     
/*  57 */     boolean b = (source.isProjectile() && instantSource instanceof AbilityProjectileEntity && ((AbilityProjectileEntity)instantSource).isPhysical() && !((AbilityProjectileEntity)instantSource).isAffectedByHaki());
/*     */     
/*  59 */     if ((a || b) && !source.isExplosion()) {
/*  60 */       reduction = 0.75F;
/*     */     }
/*  62 */     if (source.getDamageType().equals(DamageSource.LIGHTNING_BOLT.getDamageType())) {
/*  63 */       reduction = 1.0F;
/*     */     }
/*  65 */     event.setAmount(event.getAmount() * (1.0F - reduction));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityAttackEvent(LivingAttackEvent event) {
/*  71 */     if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  74 */     PlayerEntity attacked = (PlayerEntity)event.getEntityLiving();
/*  75 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)attacked);
/*     */     
/*  77 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.GOMU_GOMU_NO_MI)) {
/*     */       return;
/*     */     }
/*  80 */     DamageSource source = event.getSource();
/*  81 */     Entity instantSource = source.getImmediateSource();
/*     */     
/*  83 */     if (instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile || instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.CannonBallProjectile || instantSource instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile) {
/*     */ 
/*     */ 
/*     */       
/*  87 */       event.setCanceled(true);
/*  88 */       ((AbilityProjectileEntity)instantSource).setThrower((LivingEntity)attacked);
/*  89 */       ((AbilityProjectileEntity)instantSource).shoot(-(instantSource.getMotion()).x, -(instantSource.getMotion()).y, -(instantSource.getMotion()).z, 0.8F, 20.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean getGomuDamagingItems(Item item) {
/*  95 */     if ((item instanceof net.minecraft.item.SwordItem && !(item instanceof CoreSwordItem)) || item instanceof net.minecraft.item.PickaxeItem || item instanceof net.minecraft.item.AxeItem || item instanceof net.minecraft.item.TridentItem || item instanceof xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem) {
/*  96 */       return false;
/*     */     }
/*  98 */     if (item instanceof CoreSwordItem) {
/*  99 */       return ((CoreSwordItem)item).isBlunt();
/*     */     }
/* 101 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\passives\GomuPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */