package com.example.treelist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.treelist.base.CheckableNodeViewBinder;

public class FirstLevelNodeViewBinder extends CheckableNodeViewBinder {

    TextView textView;
    ImageView imageView;

    public FirstLevelNodeViewBinder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.node_name_view);
        imageView = itemView.findViewById(R.id.arrow_img);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public void bindView(final TreeNode treeNode) {
        textView.setText(treeNode.getValue().toString());
        imageView.setRotation(treeNode.isExpanded() ? 90 : 0);
        imageView.setVisibility(treeNode.hasChild() ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onNodeToggled(TreeNode treeNode, boolean expand) {
        if (expand) {
            imageView.animate().rotation(90).setDuration(200).start();

        } else {
            imageView.animate().rotation(0).setDuration(200).start();
        }
    }

}

