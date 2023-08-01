package com.example.treelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private TreeNode root;
    private TreeView treeView;
    private RelativeLayout relativeLayout;
    private ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewGroup = (RelativeLayout) findViewById(R.id.container);
        root = TreeNode.root();
        buildTree();
        treeView = new TreeView(root, this, new MyNodeViewFactory());
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(view);

    }

    private void buildTree() {

        for (int i = 0; i < 20; i++) {
            TreeNode treeNode = new TreeNode("Parent  " + "No." + i, 0);
            if(i != 3) { // avoids creating child nodes for "parent" 3 (which then is not a parent, so the semantic in the displayed text becomes incorrect)
                for (int j = 0; j < 10; j++) {
                    TreeNode treeNode1 = new TreeNode("Child " + "No." + j, 1);
                    if(j != 5) { // avoids creating grand child nodes for child node 5
                        // For the child node without grand children there should not be any arrow displayed.
                        // In the demo code this can be handled in method 'SecondLevelNodeViewBinder.bindView' like this:
                        // imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
                        for (int k = 0; k < 5; k++) {
                            TreeNode treeNode2 = new TreeNode("Grand Child " + "No." + k, 2);
                            treeNode1.addChild(treeNode2);
                        }
                    }
                    treeNode.addChild(treeNode1);
                }
            }
            root.addChild(treeNode);
        }

    }

//    private void buildTree() {
//
//    }
}