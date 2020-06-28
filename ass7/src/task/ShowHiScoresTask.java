package task;

import animation.Animation;
import animation.AnimationRunner;

public class ShowHiScoresTask implements Task<Void> {
   AnimationRunner runner;
   Animation highScoresAnimation;

   public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
      this.runner = runner;
      this.highScoresAnimation = highScoresAnimation;
   }
   public Void run() {
      this.runner.run(this.highScoresAnimation);
      return null;
   }
}
