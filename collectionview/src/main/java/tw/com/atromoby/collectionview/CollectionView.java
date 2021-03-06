package tw.com.atromoby.collectionview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

public class CollectionView extends RecyclerView {

    public Context context;
    private MyAdaptor myAdaptor;
    public ViewGroup tempVG;
    public static final SparseArray<Provider> holders = new SparseArray<>();

    public CollectionView(Context context)
    {
        super(context);
        this.context = context;
    }

    public CollectionView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
    }

    public CollectionView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void init(List<Item> items){
        myAdaptor = new MyAdaptor(items);
        setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(llm);
        setAdapter(myAdaptor);
    }

    public void add(int pos, Item item){
        myAdaptor.items.add(pos, item);
        myAdaptor.notifyItemInserted(pos);
    }

    public void delete(int index){
        myAdaptor.items.remove(index);
        myAdaptor.notifyItemRemoved(index);
    }

    public void refresh(List<Item> newItems){
        myAdaptor.items.clear();
        myAdaptor.items.addAll(newItems);
        myAdaptor.notifyDataSetChanged();
        scrollToPosition(0);
    }

    private CollectionView getThis(){
        return this;
    }

    private class MyAdaptor extends RecyclerView.Adapter<ItemHolder>{

        public List<Item> items;

        public MyAdaptor(List<Item> items) {
            this.items = items;
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int i) {
            holder.init(items.get(i));
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).type;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            tempVG = viewGroup;
            return holders.get(i).getHolder(getThis());
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull ItemHolder holder) {
            holder.cleanUp();
        }

        @Override
        public void onViewRecycled (@NonNull ItemHolder holder) {
            holder.cleanUp();
        }
    }
}

