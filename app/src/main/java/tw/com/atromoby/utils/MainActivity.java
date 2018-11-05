package tw.com.atromoby.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import tw.com.atromoby.rootactivity.Animate;
import tw.com.atromoby.rootactivity.CmdView;
import tw.com.atromoby.rootactivity.RootActivity;

public class MainActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clicked(R.id.testButt, new CmdView() {
            @Override
            public void exec(View v) {
                toActivity(TestActivity.class, Animate.FADE);
            }
        });

    }
}
