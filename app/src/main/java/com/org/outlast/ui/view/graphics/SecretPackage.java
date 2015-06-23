package com.org.outlast.ui.view.graphics;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;
import com.org.outlast.ui.view.MainActivity;

public class SecretPackage extends Activity implements NumberPicker.OnValueChangeListener,NumberPicker.OnScrollListener,NumberPicker.Formatter{

    private NumberPicker number_1;
    private NumberPicker number_2;
    private NumberPicker number_3;
    private Intent intent;
    private Button bt;
    private ImageView back;
    /**密码*/
    private int s1 = 4;
    private int s2 = 2;
    private int s3 = 9;
    //formatter
    public String format(int value){
        String tmpStr = String.valueOf(value);
        if(value < 0){
            tmpStr = "0" + tmpStr;
        }
        return tmpStr;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_package);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.back);
        UIDecoder.setBackground(view, getResources(), R.drawable.secret_package, 300, 300);
        number_1 = (NumberPicker) findViewById(R.id.number_1);
        number_2 = (NumberPicker) findViewById(R.id.number_2);
        number_3 = (NumberPicker) findViewById(R.id.number_3);
        bt = (Button) findViewById(R.id.confirm);
        back = (ImageView) findViewById(R.id.home);

        intent = new Intent();
        init(number_1);
        init(number_2);
        init(number_3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number_1.getValue() == s1 && number_2.getValue() == s2 && number_3.getValue() == s3){
                    intent.setClass(SecretPackage.this, Drier.class);
                    startActivity(intent);
                    finish();
                    onDestroy();

                }else {
                    intent.setClass(SecretPackage.this,SecretError.class);
                    startActivity(intent);

                }
            }
        });

    }

    @Override
    public void onScrollStateChange(NumberPicker view, int scrollState) {

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
    private void init(NumberPicker number){
        number.setFormatter(this);
        number.setOnValueChangedListener(this);
        number.setOnScrollListener(this);
        number.setMaxValue(9);
        number.setMinValue(0);
        number.setValue(3);
    }
}
