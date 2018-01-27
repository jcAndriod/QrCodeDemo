package code.qr.jc.qrcodedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 56553
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_sm)
    Button btnSm;
    @BindView(R.id.tv_jg)
    TextView tvJg;
    @BindView(R.id.et_sr)
    EditText etSr;
    @BindView(R.id.btn_sc)
    Button btnSc;
    @BindView(R.id.iv_sc)
    ImageView ivSc;
    @BindView(R.id.cb_dian)
    CheckBox cbDian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btnSm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
            }
        });
        btnSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etSr.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Bitmap qrcode = EncodingUtils.createQRCode(input, 500, 500
                            ,cbDian.isChecked()? BitmapFactory.decodeResource(getResources(),R.mipmap.logo):null);
                    ivSc.setImageBitmap(qrcode);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                tvJg.setText(result);
            }
        }
    }
}
