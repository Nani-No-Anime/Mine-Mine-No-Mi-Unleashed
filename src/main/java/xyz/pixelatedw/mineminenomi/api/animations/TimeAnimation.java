/*    */ package xyz.pixelatedw.mineminenomi.api.animations;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ 
/*    */ public abstract class TimeAnimation
/*    */   implements IAnimation<BipedModel> {
/*    */   private long time;
/*  9 */   private State state = State.STOP;
/*    */ 
/*    */   
/*    */   public void start() {
/* 13 */     this.time = 0L;
/* 14 */     this.state = State.PLAY;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 19 */     this.time = 0L;
/* 20 */     this.state = State.STOP;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 25 */     if (Minecraft.getInstance().isGamePaused()) {
/*    */       return;
/*    */     }
/* 28 */     if (this.state != State.PLAY) {
/*    */       return;
/*    */     }
/* 31 */     this.time++;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getTime() {
/* 36 */     return this.time;
/*    */   }
/*    */ 
/*    */   
/*    */   public State getState() {
/* 41 */     return this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPlaying() {
/* 46 */     return (this.state == State.PLAY);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStopped() {
/* 51 */     return (this.state == State.STOP);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPaused() {
/* 56 */     return (this.state == State.PAUSE);
/*    */   }
/*    */   
/*    */   public enum State
/*    */   {
/* 61 */     PLAY,
/* 62 */     STOP,
/* 63 */     PAUSE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\animations\TimeAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */