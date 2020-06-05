package collision;

/**
 * Noam Koren.
 * 308192871
 * ass5
 */
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
