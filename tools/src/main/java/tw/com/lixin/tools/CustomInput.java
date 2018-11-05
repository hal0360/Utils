package tw.com.lixin.tools;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.util.AttributeSet;


public class CustomInput extends AppCompatEditText {

    public CustomInput(Context context)
    {
        super(context);
    }

    public CustomInput(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomInput(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void error(String s){
        requestFocus();
        setError(s);
    }

    public String getRawText(){
        Editable editable = getText();
        if(editable != null) return getText().toString().trim();
        return null;
    }
}
