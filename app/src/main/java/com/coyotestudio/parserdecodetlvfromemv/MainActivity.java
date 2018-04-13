package com.coyotestudio.parserdecodetlvfromemv;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.coyotestudio.parserdecodetlvfromemv.basicdecoder.TlvObj;
import com.coyotestudio.parserdecodetlvfromemv.basicdecoder.TlvParser;
import com.coyotestudio.parserdecodetlvfromemv.basicdecoder.TlvElements;
import com.coyotestudio.parserdecodetlvfromemv.basicdecoder.Utilities;

import com.coyotestudio.parserdecodetlvfromemv.basicdecoder.TagsEMV;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView mTextDecode;
    ListView lstInfoCard;
    ArrayList<String> arrInfoCard;
    TlvElements tlvs;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextDecode = findViewById(R.id.tv_tlv_code);
        lstInfoCard = findViewById(R.id.lst_info_card);
        mTextDecode.setMovementMethod(new ScrollingMovementMethod());
        arrInfoCard = new ArrayList<>();

        textView = new TextView(this);
        textView.setText(R.string.header_list_text_parser);
        textView.setPadding(26, 0, 5, 0);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        lstInfoCard.addHeaderView(textView);

    }

    public void lauchDecode(View view) {
        processHexString();
    }

    public void processHexString() {

        try {
            byte[] bytes = Utilities.parseHex(getString(R.string.tlv_default));
            TlvParser parser = new TlvParser();
            tlvs = parser.parse(bytes, 0, bytes.length);
            printData(tlvs);
        } catch (Exception e) {
            simpleDialogError();
        }
    }

    public void printData(TlvElements oTlvs) {
        if (oTlvs == null) {
            Log.d(TAG, "aTLV: is null");
            return;
        }

        if (arrInfoCard.size() > 0) arrInfoCard.clear();


        for (TlvObj tlv : oTlvs.getList()) {
            showInfo(tlv);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrInfoCard);



        lstInfoCard.setAdapter(adapter);

    }

    public void showInfo(TlvObj datTlv) {
        if (datTlv.isConstructed()) {
            //aLogger.debug("{} [{}]", aPadding, HexUtil.toHexString(aTlv.getTag().bytes));
            for (TlvObj child : datTlv.getValues()) {
                Log.d(TAG, "showInfoChild: " + child);
            }
        } else {

            String hexToStringTag = Utilities.toHexString(datTlv.getTag().bytes);
            TagsEMV tagElemt = new TagsEMV();
            String tag = !hexToStringTag.isEmpty() ? hexToStringTag : "";
            String tagContext = tagElemt.getTagInfo(tag);

            tag += " " + tagContext + "\n";
            String getValue = datTlv.getHexValue(tagContext);
            String value = getValue != null ? getValue : "";

            String result = tag + value;


            arrInfoCard.add(result);
        }
    }

    public void simpleDialogError() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Error!");
        alertDialog.setMessage("Something with the code was incorrec. Please verify the information.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
