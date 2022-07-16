package ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.listeners;


import ghanekar.vaibhav.allsamples2.fragments.recyclerview.expandable.ExpandableRecyclerViewLibrary.com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

public interface GroupExpandCollapseListener {

  /**
   * Called when a group is expanded
   * @param group the {@link ExpandableGroup} being expanded
   */
  void onGroupExpanded(ExpandableGroup group);

  /**
   * Called when a group is collapsed
   * @param group the {@link ExpandableGroup} being collapsed
   */
  void onGroupCollapsed(ExpandableGroup group);
}
