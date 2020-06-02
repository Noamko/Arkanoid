public interface HitListener {

    /**
     * this method id called whenever the beingHit object is hit.
     * the hitter parameter is the ball that's doing the hitting.
     * @param beingHit tag
     * @param hitter tag
     */
    void hitEvent(Block beingHit, Ball hitter);
}
