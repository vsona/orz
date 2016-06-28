package net.vsona.baselibrary.refresh;

/**
 * Refresh interface.
 *
 * @author wichna
 * @date 10/13/15
 * @company xiaoyezi
 */
public interface RefreshableInterface {

    /**
     * Set refresh.
     */
    void onRefreshing();

    /**
     * Cancel refreshing.
     */
    void onRefreshCompleted();
}
