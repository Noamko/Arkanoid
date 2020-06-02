public interface HitNotifier {

    /**
     * adds a hit listener.
     * @param hl tag
     */
    void addHitListener(HitListener hl);

    /**
     * removes a hit listener.
     * @param hl tag
     */
    void removeHitListener(HitListener hl);
}
