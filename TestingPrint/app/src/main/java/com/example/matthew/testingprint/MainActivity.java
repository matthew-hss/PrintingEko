package com.example.matthew.testingprint;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import comm.CommEntity;
import comm.entity.BasePrinter;
import common.utils.IOUtils;

public class MainActivity extends AppCompatActivity {

    private boolean inited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String textModel = new String(IOUtils.readResource("assets/model/voucher_inst.txt"));
//        HashMap<String, String> hsmpParam = new HashMap<String, String>();
//        //EditText textToPrint = (EditText) findViewById(R.id.txtToPrint);
//        hsmpParam.put("$BARCODE$","123456789012");
//        //hsmpParam.put("$BARCODE$", textToPrint.toString());
//        BasePrinter basePrinter = CommEntity.getPrinter(1);
//        basePrinter.print(textModel, hsmpParam);

        CommEntity.init();

        Button btnPrint = (Button) findViewById(R.id.btnPrint);
        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //try {
                String textModel = new String(IOUtils.readResource("assets/model/voucher_inst.txt"));
                HashMap<String, String> hsmpParam = new HashMap<String, String>();
                //EditText textToPrint = (EditText) findViewById(R.id.txtToPrint);
                hsmpParam.put("$BARCODE$", "123456789012");
                //hsmpParam.put("$BARCODE$", textToPrint.toString());
                BasePrinter basePrinter = CommEntity.getPrinter(1);
                basePrinter.print(textModel, hsmpParam);
                //} catch (Exception e) {
                //    Log.e("Impresora: ", "Error", e);
                //}
            }

        });
    }


}
