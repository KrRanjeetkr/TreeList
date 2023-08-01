package com.example.treelist;

import android.view.View;

import com.example.treelist.base.BaseNodeViewBinder;
import com.example.treelist.base.BaseNodeViewFactory;

class MyNodeViewFactory extends BaseNodeViewFactory {

    @Override
    public BaseNodeViewBinder getNodeViewBinder(View view, int level) {
        switch (level) {
            case 0:
                return new FirstLevelNodeViewBinder(view);
            case 1:
                return new SecondLevelNodeViewBinder(view);
            case 2:
                return new ThreeLevelNodeViewBinder(view);
            case 3:
                return new ThirdLevelNodeViewBinder(view);
            default:
                return null;
        }
    }

    @Override
    public int getNodeLayoutId(int level) {
        switch (level) {
            case 0:
                return R.layout.item_first_level;
            case 1:
                return R.layout.item_second_level;
            case 3:
                return R.layout.item_third_level;
            case 2:
                return R.layout.item_three_level;

            default:
                return 0;
        }
    }

}
