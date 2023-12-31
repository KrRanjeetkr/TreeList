package com.example.treelist;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.treelist.base.BaseNodeViewFactory;
import com.example.treelist.base.SelectableTreeAction;
import com.example.treelist.helper.TreeHelper;

import java.util.List;

public class TreeView implements SelectableTreeAction {

    private TreeNode root;

    private Context context;

    private BaseNodeViewFactory baseNodeViewFactory;

    private RecyclerView rootView;

    private TreeViewAdapter adapter;

    private boolean itemSelectable = true;

    public TreeView(@NonNull TreeNode root, @NonNull Context context, @NonNull BaseNodeViewFactory baseNodeViewFactory) {
        this.root = root;
        this.context = context;
        this.baseNodeViewFactory = baseNodeViewFactory;
        if (baseNodeViewFactory == null) {
            throw new IllegalArgumentException("You must assign a BaseNodeViewFactory!");
        }
    }

    public View getView() {
        if (rootView == null) {
            this.rootView = buildRootView();
        }
        return rootView;
    }

    @NonNull
    private RecyclerView buildRootView() {
        RecyclerView recyclerView = new RecyclerView(context);
        /**
         * disable multi touch event to prevent terrible data set error when calculate list.
         */
        recyclerView.setMotionEventSplittingEnabled(false);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TreeViewAdapter(context, root, baseNodeViewFactory);
        adapter.setTreeView(this);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    @Override
    public void expandAll() {
        if (root == null) {
            return;
        }
        TreeHelper.expandAll(root);
        refreshTreeView();
    }


    public void refreshTreeView() {
        if (rootView != null) {
            ((TreeViewAdapter) rootView.getAdapter()).refreshView();
        }
    }

    public void updateTreeView() {
        if (rootView != null) {
            rootView.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public void expandNode(TreeNode treeNode) {
        adapter.expandNode(treeNode);
    }

    @Override
    public void expandLevel(int level) {
        TreeHelper.expandLevel(root, level);
        refreshTreeView();
    }

    @Override
    public void collapseAll() {
        if (root == null) {
            return;
        }
        TreeHelper.collapseAll(root);

        refreshTreeView();
    }

    @Override
    public void collapseNode(TreeNode treeNode) {
        adapter.collapseNode(treeNode);
    }

    @Override
    public void collapseLevel(int level) {
        TreeHelper.collapseLevel(root, level);

        refreshTreeView();
    }

    @Override
    public void toggleNode(TreeNode treeNode) {
        if (treeNode.isExpanded()) {
            collapseNode(treeNode);
        } else {
            expandNode(treeNode);
        }
    }

    @Override
    public void deleteNode(TreeNode node) {
        adapter.deleteNode(node);
    }

    @Override
    public void addNode(TreeNode parent, TreeNode treeNode) {
        parent.addChild(treeNode);

        refreshTreeView();
    }

    @Override
    public List<TreeNode> getAllNodes() {
        return TreeHelper.getAllNodes(root);
    }

    @Override
    public void selectNode(TreeNode treeNode) {
        if (treeNode != null) {
            adapter.selectNode(true, treeNode);
        }
    }

    @Override
    public void deselectNode(TreeNode treeNode) {
        if (treeNode != null) {
            adapter.selectNode(false, treeNode);
        }
    }

    @Override
    public void selectAll() {
        TreeHelper.selectNodeAndChild(root, true);

        refreshTreeView();
    }

    @Override
    public void deselectAll() {
        TreeHelper.selectNodeAndChild(root, false);

        refreshTreeView();
    }

    @Override
    public List<TreeNode> getSelectedNodes() {
        return TreeHelper.getSelectedNodes(root);
    }

    public boolean isItemSelectable() {
        return itemSelectable;
    }

    public void setItemSelectable(boolean itemSelectable) {
        this.itemSelectable = itemSelectable;
    }

}
