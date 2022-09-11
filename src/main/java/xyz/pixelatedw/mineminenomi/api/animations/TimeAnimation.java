package xyz.pixelatedw.mineminenomi.api.animations;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;

public abstract class TimeAnimation
  implements IAnimation<BipedModel> {
  private long time;
  private State state = State.STOP;

  
  public void start() {
    this.time = 0L;
    this.state = State.PLAY;
  }

  
  public void stop() {
    this.time = 0L;
    this.state = State.STOP;
  }

  
  public void tick() {
    if (Minecraft.getInstance().isGamePaused()) {
      return;
    }
    if (this.state != State.PLAY) {
      return;
    }
    this.time++;
  }

  
  public long getTime() {
    return this.time;
  }

  
  public State getState() {
    return this.state;
  }

  
  public boolean isPlaying() {
    return (this.state == State.PLAY);
  }

  
  public boolean isStopped() {
    return (this.state == State.STOP);
  }

  
  public boolean isPaused() {
    return (this.state == State.PAUSE);
  }
  
  public enum State
  {
    PLAY,
    STOP,
    PAUSE;
  }
}


