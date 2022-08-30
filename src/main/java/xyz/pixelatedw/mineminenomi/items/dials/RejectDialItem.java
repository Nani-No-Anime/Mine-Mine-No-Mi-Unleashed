/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.IndirectEntityDamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class RejectDialItem extends BlockItem {
/* 26 */   private static final DamageSource REJECT_DIAL = (new DamageSource("reject_dial")).setDamageBypassesArmor().setMagicDamage().setDamageIsAbsolute();
/*    */ 
/*    */   
/*    */   public RejectDialItem() {
/* 30 */     super(ModBlocks.REJECT_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 36 */     if (player.getCooldownTracker().getCooldown(getItem(), 0.0F) > 0.0F) {
/* 37 */       return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*    */     }
/* 39 */     if (!world.isRemote) {
/*    */       
/* 41 */       if (!CommonConfig.INSTANCE.isAbilityGriefingEnabled())
/* 42 */         return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand)); 
/* 43 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 5.0D);
/* 44 */       player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 2, false, false));
/* 45 */       player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1, false, false));
/* 46 */       player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1, false, false));
/*    */       
/* 48 */       player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2, 5));
/* 49 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, 
/* 50 */           (mop.getHitVec()).x, 
/* 51 */           (mop.getHitVec()).y, 
/* 52 */           (mop.getHitVec()).z, 12.0F);
/* 53 */       explosion.setStaticDamage(80.0F);
/* 54 */       explosion.setDamageOwner(false);
/* 55 */       explosion.setDamageSource((new IndirectEntityDamageSource("explosion", (Entity)player, null)).setDamageBypassesArmor().setDamageIsAbsolute().setExplosion());
/* 56 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(13));
/* 57 */       explosion.doExplosion();
/* 58 */       player.attackEntityFrom((new IndirectEntityDamageSource("explosion", (Entity)player, null)).setDamageBypassesArmor().setDamageIsAbsolute().setExplosion(), 50.0F);
/*    */ 
/*    */       
/* 61 */       player.getCooldownTracker().setCooldown(getItem(), 200);
/* 62 */       player.getHeldItem(hand).shrink(1);
/*    */     } 
/*    */     
/* 65 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hitEntity(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/* 71 */     if (!attacker.world.isRemote && attacker instanceof PlayerEntity) {
/*    */       
/* 73 */       PlayerEntity playerAttacker = (PlayerEntity)attacker;
/* 74 */       if (playerAttacker.getCooldownTracker().getCooldown(getItem(), 0.0F) > 0.0F) {
/* 75 */         return false;
/*    */       }
/* 77 */       playerAttacker.getCooldownTracker().setCooldown(getItem(), 400);
/* 78 */       playerAttacker.getHeldItem(playerAttacker.getActiveHand()).shrink(1);
/*    */       
/* 80 */       playerAttacker.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 600, 2, false, false));
/* 81 */       playerAttacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 1, false, false));
/* 82 */       playerAttacker.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 600, 1, false, false));
/*    */       
/* 84 */       if (!playerAttacker.isCreative())
/* 85 */         attacker.attackEntityFrom(REJECT_DIAL, attacker.getMaxHealth() - 1.0F); 
/* 86 */       target.attackEntityFrom(REJECT_DIAL, target.getMaxHealth() + 1.0F);
/* 87 */       return true;
/*    */     } 
/*    */     
/* 90 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 96 */     return super.onBlockPlaced(pos, world, player, itemStack, state);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\dials\RejectDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */