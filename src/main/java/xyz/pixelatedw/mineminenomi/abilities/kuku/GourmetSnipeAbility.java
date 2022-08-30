/*     */ package xyz.pixelatedw.mineminenomi.abilities.kuku;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class GourmetSnipeAbility extends Ability implements IMultiTargetAbility {
/*  34 */   public static final GourmetSnipeAbility INSTANCE = new GourmetSnipeAbility();
/*     */   
/*  36 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
/*  37 */   private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
/*  38 */   private static final ArrayList<Item> FOODS = new ArrayList<>(Arrays.asList(new Item[] { Items.CAKE, Items.COOKED_CHICKEN, Items.COOKED_RABBIT, Items.COOKED_COD, Items.APPLE, Items.COOKED_MUTTON, Items.COOKED_SALMON, Items.HONEY_BOTTLE, Items.COOKED_PORKCHOP }));
/*     */   
/*     */   private int initialY;
/*     */   
/*     */   public GourmetSnipeAbility() {
/*  43 */     super("Gourmet Snipe", AbilityHelper.getDevilFruitCategory());
/*  44 */     setDescription("Launches the user forward and converts everything cut into food");
/*  45 */     setMaxCooldown(20.0D);
/*     */     
/*  47 */     this.onUseEvent = this::onUseEvent;
/*  48 */     this.duringCooldownEvent = this::duringCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  53 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*  54 */       return false;
/*     */     }
/*  56 */     if (!AbilityHelper.canUseSwordsmanAbilities((LivingEntity)player)) {
/*     */       
/*  58 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD, new Object[0]));
/*  59 */       return false;
/*     */     } 
/*     */     
/*  62 */     clearTargets();
/*  63 */     this.initialY = (int)player.getPosY();
/*     */     
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/*  70 */     if (canDealDamage() && player.getPosY() >= this.initialY) {
/*     */       
/*  72 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.6D, 1.6D);
/*  73 */       player.setMotion(speed.x, player.getMotion().getY(), speed.z);
/*  74 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*     */       
/*  76 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  77 */       list.remove(player);
/*     */       
/*  79 */       list.forEach(entity -> {
/*     */             if (isTarget(entity)) {
/*     */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10.0F);
/*     */             }
/*     */           });
/*     */       
/*  85 */       for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 2)) {
/*     */         
/*  87 */         if (location.getY() >= player.getPosY())
/*     */         {
/*  89 */           if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, GRIEF_RULE)) {
/*     */             
/*  91 */             if (player.world.rand.nextDouble() > 0.4D)
/*  92 */               player.world.addEntity((Entity)new ItemEntity(player.world, location.getX(), location.getY(), location.getZ(), new ItemStack((IItemProvider)FOODS.get((int)WyHelper.randomWithRange(0, FOODS.size() - 1))))); 
/*  93 */             PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDealDamage() {
/* 102 */     return (this.cooldown > getMaxCooldown() * 0.95D);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kuku\GourmetSnipeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */