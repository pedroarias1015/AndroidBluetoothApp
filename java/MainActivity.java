package com.example.perico.appbluetoothperico;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

                /*
      /////          VARIABLES  A EMPLEAR          /////
                 */

    private BluetoothAdapter adapterBT;
    private  ArrayList<String> emparejados,hcs;
    private  Button BT,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    private  Handler bluetoothIn;
    private   final int handlerState = 0;
    private StringBuilder recDataString = new StringBuilder();
    private HashMap<String,String> mapa2= new HashMap<String, String>();
    private HashMap<String,String> mapa= new HashMap<String, String>();
    // SPP UUID service - this should work for most devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket btSocket;
    private TextView consola;









                 /*
      /////          VARIABLES  A EMPLEAR -END       /////
                 */




                  /*
     /////           ON CREATE     /////
                 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inicializar();


        if(adapterBT==null){
            Toast.makeText(this,"No se soporta el bluetooth",Toast.LENGTH_LONG).show();}
        else{
            if(!adapterBT.isEnabled()){

                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);// Peticion de encendido de BT

            }
            BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getEmparejados();
                    asociar();
                    chekear();

                }
            });

        }
    }




                  /*
     /////           ON CREATE -END    /////
                 */












                  /*
     /////          FUNCIONES AUXILIARES PRIVATE; PROTECTED    /////
                 */




   /*
    ZONA DE INICIALIZACION
    */
   private void inicializar(){
       adapterBT=BluetoothAdapter.getDefaultAdapter();
       emparejados=new ArrayList<String>();
       hcs=new ArrayList<String>();
       BT=(Button)findViewById(R.id.BT);
       b1= (Button) findViewById(R.id.b1);
       b2= (Button) findViewById(R.id.b2);
       b3= (Button) findViewById(R.id.b3);
       b4= (Button) findViewById(R.id.b4);
       b5= (Button) findViewById(R.id.b5);
       b6= (Button) findViewById(R.id.b6);
       b7= (Button) findViewById(R.id.b7);
       b8= (Button) findViewById(R.id.b8);
       b9= (Button) findViewById(R.id.b9);
       consola=(TextView) findViewById(R.id.consola);



   }
 /*
   EMPAREJAR
    */

    private void getEmparejados(){

        Set<BluetoothDevice> peared= adapterBT.getBondedDevices();
        int a=0;
        for(BluetoothDevice device:peared ){

            emparejados.add(device.getName()+ " ("+device.getAddress()+")");
            mapa.put(a+"",device.getAddress());
            mapa2.put(a+"",device.getName());
            if((device.getName().substring(0,2)).equals("HC")){hcs.add(a+"");}
            a++;


        }

    }
    /*
   ASOCIAR
   */
    private void asociar(){

        switch (emparejados.size()){

            case 1:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);break;
            case 2:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);break;
            case 3:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);break;
            case 4:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);break;
            case 5:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);
                b5.setText(emparejados.get(4));b5.setVisibility(View.VISIBLE);break;
            case 6:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);
                b5.setText(emparejados.get(4));b5.setVisibility(View.VISIBLE);
                b6.setText(emparejados.get(5));b6.setVisibility(View.VISIBLE);break;
            case 7:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);
                b5.setText(emparejados.get(4));b5.setVisibility(View.VISIBLE);
                b6.setText(emparejados.get(5));b6.setVisibility(View.VISIBLE);
                b7.setText(emparejados.get(6));b7.setVisibility(View.VISIBLE);break;
            case 8:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);
                b5.setText(emparejados.get(4));b5.setVisibility(View.VISIBLE);
                b6.setText(emparejados.get(5));b6.setVisibility(View.VISIBLE);
                b7.setText(emparejados.get(6));b7.setVisibility(View.VISIBLE);
                b8.setText(emparejados.get(7));b8.setVisibility(View.VISIBLE);break;
            case 9:
                b1.setText(emparejados.get(0));b1.setVisibility(View.VISIBLE);
                b2.setText(emparejados.get(1));b2.setVisibility(View.VISIBLE);
                b3.setText(emparejados.get(2));b3.setVisibility(View.VISIBLE);
                b4.setText(emparejados.get(3));b4.setVisibility(View.VISIBLE);
                b5.setText(emparejados.get(4));b5.setVisibility(View.VISIBLE);
                b6.setText(emparejados.get(5));b6.setVisibility(View.VISIBLE);
                b7.setText(emparejados.get(6));b7.setVisibility(View.VISIBLE);
                b8.setText(emparejados.get(7));b8.setVisibility(View.VISIBLE);
                b9.setText(emparejados.get(8)); b9.setVisibility(View.VISIBLE); break;
            default:
                break;

        }

    }

     /*
    CHECK
    */

    private void chekear(){








        if(b1.getVisibility()==View.VISIBLE){



            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cambia(b1,1,view);
                }
            });
        }

        if(b2.getVisibility()==View.VISIBLE){

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b2,2,view);
                }
            });
        }

        if(b3.getVisibility()==View.VISIBLE){

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b3,3,view);
                }
            });
        }

        if(b4.getVisibility()==View.VISIBLE){

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b4,4,view);
                }
            });
        }

        if(b5.getVisibility()==View.VISIBLE){

            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b5,5,view);
                }
            });
        }

        if(b6.getVisibility()==View.VISIBLE){

            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b6,6,view);
                }
            });
        }

        if(b7.getVisibility()==View.VISIBLE){

            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b7,7,view);
                }
            });
        }

        if(b8.getVisibility()==View.VISIBLE){

            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b8,8,view);
                }
            });
        }

        if(b9.getVisibility()==View.VISIBLE){

            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cambia(b9,9,view);
                }
            });
        }



    }


 /*
    CAMBIA DE LAYOUT
    */


    private void cambia(Button b,int id,View v){
        String dir=mapa.get(String.valueOf(id-1));
        String name= mapa2.get(String.valueOf(id-1));

        if((name.substring(0,2)).equals("HC")){

        Intent ArduinoActivity = new Intent(v.getContext(), ArduinoActivity.class);
        ArduinoActivity.putExtra("dir",dir);
        startActivityForResult(ArduinoActivity,0); }


        else{
            Toast.makeText(getApplicationContext(),"APP  creada para conexion con Arduino.UUID no coincide \n Mediante HC-0*",Toast.LENGTH_SHORT).show();
      }



    }





}
