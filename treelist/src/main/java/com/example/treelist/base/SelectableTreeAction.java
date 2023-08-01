package com.example.treelist.base;

import com.example.treelist.TreeNode;

import java.util.List;

public interface SelectableTreeAction extends BaseTreeAction {

    void selectNode(TreeNode treeNode);

    void deselectNode(TreeNode treeNode);

    void selectAll();

    void deselectAll();

    List<TreeNode> getSelectedNodes();
}

