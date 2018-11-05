package tw.com.atromoby.rootactivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public abstract class RootActivity extends AppCompatActivity implements View.OnClickListener{

    private SparseArray<CmdView> cmds = new SparseArray<>();
    private Handler handler;
    private List<BroadcastReceiver> broadcastReceivers = new ArrayList<>();

    protected void registerReceiver(String filterStr, BroadcastReceiver broadcastReceiver){
        LocalBroadcastManager.getInstance(this).registerReceiver(
                broadcastReceiver, new IntentFilter(filterStr));
        broadcastReceivers.add(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        handler = new Handler();
    }

    public final void clicked(int id, CmdView cd){
        findViewById(id).setOnClickListener(this);
        cmds.put(id,cd);
    }

    protected final void delay(int milsec, final Cmd cmd){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cmd.exec();
            }
        }, milsec);
    }

    public final void clicked(View v, CmdView cd){
        v.setOnClickListener(this);
        cmds.put(v.getId(),cd);
    }

    public final void pushActivity(Class actClass){
        Intent intent = new Intent(this, actClass);
        startActivity(intent);
    }

    public final void pushActivity(Class actClass, int anime){
        Intent intent = new Intent(this, actClass);
        startActivity(intent);
        switch (anime) {
            case 1:
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case 2:
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;
        }
    }

    protected void alert(String mess){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show();
    }

    public final void toActivity(Class actClass){
        Intent intent = new Intent(this, actClass);
        startActivity(intent);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 500);
    }

    public final void toActivity(Class actClass, int anime){
        Intent intent = new Intent(this, actClass);
        startActivity(intent);
        switch (anime) {
            case 1:
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                break;
            case 2:
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                break;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        cmds.get(v.getId()).exec(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        for(BroadcastReceiver broadcastReceiver: broadcastReceivers){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(
                    broadcastReceiver);
        }
    }
}
