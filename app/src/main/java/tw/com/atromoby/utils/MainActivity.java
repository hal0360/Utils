package tw.com.atromoby.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tw.com.atromoby.collectionview.CollectionView;
import tw.com.atromoby.collectionview.Item;
import tw.com.atromoby.collectionview.ItemHolder;
import tw.com.atromoby.collectionview.Provider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CollectionView.holders.put(RecordHolder.type, new Provider() {
            @Override
            public ItemHolder getHolder(CollectionView v) {
                return new RecordHolder(v);
            }
        });

        List<Item> items = new ArrayList<>();
        items.add(new Item(RecordHolder.type,"blue"));
        items.add(new Item(RecordHolder.type,"red"));
        items.add(new Item(RecordHolder.type,"yellow"));

        CollectionView collectionView = findViewById(R.id.recordCollView);
        collectionView.init(items);

    }
}
