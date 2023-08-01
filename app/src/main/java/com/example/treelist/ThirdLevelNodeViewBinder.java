package com.example.treelist;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.treelist.base.CheckableNodeViewBinder;


public class ThirdLevelNodeViewBinder extends CheckableNodeViewBinder {

    CheckBox checkBox;

    public ThirdLevelNodeViewBinder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
    }

    @Override
    public int getCheckableViewId() {
        return R.id.checkBox;
    }

    @Override
    public void bindView(TreeNode treeNode) {

        checkBox.setText(treeNode.getValue().toString());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//                if (isChecked){
//                    checkBox.setChecked(isChecked);
//                    ResponseDataUtils.NetworkList.add(checkBox.getText().toString().trim());
//                }else {
//                    if (ResponseDataUtils.NetworkList.size() > 0){
//                        checkBox.setChecked(isChecked);
//                        ResponseDataUtils.NetworkList.remove(checkBox.getText().toString().trim());
//                    }
//                }

            }
        });

    }
}

