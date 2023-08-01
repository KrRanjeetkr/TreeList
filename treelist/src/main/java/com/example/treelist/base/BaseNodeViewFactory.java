package com.example.treelist.base;

import android.view.View;

import com.example.treelist.TreeNode;

public abstract class BaseNodeViewFactory {
    /**
 * The default implementation below behaves as in previous version when TreeViewAdapter.getItemViewType always returned the level,
 * but you can override it if you want some other viewType value to become the parameter to the method getNodeViewBinder.
 * @param treeNode
 * @return
 */
public int getViewType(TreeNode treeNode) {
    return treeNode.getLevel();
}

    /**
     * If you want build a tree view,you must implement this factory method
     *
     * @param view  The parameter for BaseNodeViewBinder's constructor, do not use this for other
     *              purpose!
     * @param viewType The viewType value is the treeNode level in the default implementation.
     * @return BaseNodeViewBinder
     */
    public abstract BaseNodeViewBinder getNodeViewBinder(View view, int viewType);


    /**
     * If you want build a tree view,you must implement this factory method
     *
     * @param level Level of view, returned from {@link #getViewType}
     * @return node layout id
     */
    public abstract int getNodeLayoutId(int level);

}
