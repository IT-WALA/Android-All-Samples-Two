package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.listeners;


public interface ExpandCollapseListener {

  /**
   * Called when a group is expanded
   *
   */
  void onGroupExpanded(int positionStart, int itemCount);

  /**
   * Called when a group is collapsed
   *
   */
  void onGroupCollapsed(int positionStart, int itemCount);
}
