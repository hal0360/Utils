package tw.com.atromoby.collectionview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.Toast;


public abstract class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private SparseArray<Cmd> cmds = new SparseArray<>();
    protected Context context;
    private CollectionView collectionView;

    public ItemHolder(CollectionView cv, int Rid) {
        super(LayoutInflater.from(cv.tempVG.getContext()).inflate(Rid, cv.tempVG, false));
        context = cv.context;
        collectionView = cv;
    }

    protected void alert(String mess){
        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }


    protected final <T extends View & Checkable> T findView(int Rid){
        return itemView.findViewById(Rid);
    }

    protected final void delete(){
        collectionView.delete(getAdapterPosition());
    }


    protected final void clicked(View v, Cmd cd){
        v.setOnClickListener(this);
        cmds.put(v.getId(),cd);
    }

    protected final void clicked(int id, Cmd cd){
        findView(id).setOnClickListener(this);
        cmds.put(id,cd);
    }

    public abstract void init(Item item);

    public abstract void cleanUp();

    @Override
    public final void onClick(View v) {
        cmds.get(v.getId()).exec();
    }
}
