package tw.com.atromoby.utils;

import android.widget.TextView;


import tw.com.atromoby.collectionview.CollectionView;
import tw.com.atromoby.collectionview.Item;
import tw.com.atromoby.collectionview.ItemHolder;

public class RecordHolder extends ItemHolder {

    public static final int type = 1;
    private TextView ttt, fff;

    public RecordHolder(CollectionView cv) {
        super(cv, R.layout.carpark_card);
        ttt = findView(R.id.serveTime);
    }


    @Override
    public void init(Item item) {
        ttt.setText(item.title);
    }

    @Override
    public void cleanUp() {

    }
}
