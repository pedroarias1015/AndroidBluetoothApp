package com.example.perico.appbluetoothperico;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class ArduinoActivity extends AppCompatActivity {

    Button  btnDis,g1,g2,g3,g4,g5,g6,g7,g8,luces,musica;
    SeekBar brightness;
    TextView lumn;
    String address = null;

    BluetoothAdapter adapterBT = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    TextView consola;
    private static final String TAG = "BT";
    OutputStream out;
    RelativeLayout layout_joystick;
    String cad;


    JoyStickClass js;




    private StringBuilder recDataString = new StringBuilder();


    final int handlerState = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.segunda_act);
        address = getIntent().getExtras().getString("dir");
        consola=(TextView)findViewById(R.id.consola);
        g1=(Button)findViewById(R.id.g1);
        g2=(Button)findViewById(R.id.g2);
        g3=(Button)findViewById(R.id.g3);
        g4=(Button)findViewById(R.id.g4);
        g5=(Button)findViewById(R.id.g5);
        g6=(Button)findViewById(R.id.g6);
        g7=(Button)findViewById(R.id.g7);
        g8=(Button)findViewById(R.id.g8);
        musica=(Button)findViewById(R.id.musicaBtn);
        luces=(Button)findViewById(R.id.luces);
        layout_joystick = (RelativeLayout)findViewById(R.id.layout_joystick);
        js = new JoyStickClass(getApplicationContext(), layout_joystick,R.mipmap.cursor_opt);


        js.setStickSize(150, 150);
        js.setLayoutSize(500, 500);
        js.setLayoutAlpha(150);
        js.setStickAlpha(100);
        js.setOffset(90);
        js.setMinimumDistance(50);



        new ConnectBT().execute(); //Call the class to connect

        btnDis=(Button)findViewById(R.id.desconectar) ;

/*
        try {out= btSocket.getOutputStream();
            out.write(new byte[] { (byte) 0xa0, 0, 7, 16, 0, 4, 0 });
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        //////////////////////ZONA DE CODIGO ARDUINO ///////////////////////////////////











        g1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("UP");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("UP");
                        return true;

                    default:
                        return true;
                }
            }
        });













        /*


        g1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                                                   /// UP: codigo para ir hacia adelante
            try {
                write("UP");
            } catch (IOException e) {
                e.printStackTrace();
            }
            msg("UP");


        }
    });*/


        g2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("LF");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("LF");
                        return true;

                    default:
                        return true;
                }
            }
        });


/*
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                  /// LF: codigo para ir hacia la izquierda
                try {
                    write("LF");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("LF");

            }
        });*/


        g3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("RG");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("RG");
                        return true;

                    default:
                        return true;
                }
            }
        });
/*

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                    /// RG: codigo para ir hacia la derecha
                try {
                    write("RG");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("RG");}


        });*/



        g4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("DW");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("DW");
                        return true;

                    default:
                        return true;
                }
            }
        });

/*
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                             /// DW: codigo para ir hacia atrás
                try {
                    write("DW");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("DW");

            }
        });*/


        g5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("UL");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("UL");
                        return true;

                    default:
                        return true;
                }
            }
        });

/*
        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// DW: codigo para ir hacia atrás
                try {
                    write("UL");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("UL");

            }
        });*/



        g6.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("UR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("UR");
                        return true;

                    default:
                        return true;
                }
            }
        });

/*
        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// DW: codigo para ir hacia atrás
                try {
                    write("UR");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("UR");

            }
        });*/


        g7.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("DR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("DR");
                        return true;

                    default:
                        return true;
                }
            }
        });


/*
        g7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// DW: codigo para ir hacia atrás
                try {
                    write("DR");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("DR");

            }
        });*/

        g8.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        try {
                            write("DL");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        msg("DL");
                        return true;

                    default:
                        return true;
                }
            }
        });


/*
        g8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// DW: codigo para ir hacia atrás
                try {
                    write("DL");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("DL");

            }
        });*/


        luces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                                 /// LI: codigo para encender y apagar luces
                try {
                    write("LI");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("LI");

            }
        });



        musica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                              /// MU: codigo para encender y apagar la musica
                try {
                    write("MU");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg("MU");

            }
        });










        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect();
            }
        });










        layout_joystick.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1) {
                js.drawStick(arg1);
                if(arg1.getAction() == MotionEvent.ACTION_DOWN
                        || arg1.getAction() == MotionEvent.ACTION_MOVE) {

                    // cad=" (" + String.valueOf(js.getX())+", " + String.valueOf(js.getY())+")";
                    cad="";
                    try {
                        write(String.valueOf(js.getX())+String.valueOf(js.getY()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    int direction = js.get8Direction();
                    if(direction == JoyStickClass.STICK_UP) {
                        consola.setText("UP"+cad);


                    } else if(direction == JoyStickClass.STICK_UPRIGHT) {
                        consola.setText("UR"+cad);
                        try {
                            write("UR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if(direction == JoyStickClass.STICK_RIGHT) {
                        consola.setText("RG"+cad);
                        try {
                            write("RG");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_DOWNRIGHT) {
                        consola.setText("DR"+cad);
                        try {
                            write("DR");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_DOWN) {
                        consola.setText("DW"+cad); try {
                            write("DW");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_DOWNLEFT) {
                        consola.setText("DL"+cad);
                        try {
                            write("DL");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_LEFT) {
                        consola.setText("LF"+cad);
                        try {
                            write("LF");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_UPLEFT) {
                        consola.setText("UL"+cad);
                        try {
                            write("UL");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(direction == JoyStickClass.STICK_NONE) {
                        consola.setText(cad);

                    }
                } else if(arg1.getAction() == MotionEvent.ACTION_UP) {
                   consola.setText("");
                }
                return true;
            }
        });







    }


//////////////////////FIN----->ZONA DE CODIGO ARDUINO ///////////////////////////////////

















    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected


        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    adapterBT = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = adapterBT.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                    out=btSocket.getOutputStream();
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("No se encuentra el dispositivo");
                finish();
            }
            else
            {
                msg("CONECTADO");
                isBtConnected = true;
            }


        }
    }



    private void msg(String s)
    {
        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        consola.setText(s);
    }








  private void  write(String cadena) throws IOException{
        try{
        out.write(cadena.getBytes());}
        catch (IOException e){
            Toast.makeText(this,"error",Toast.LENGTH_LONG);
        }



  }








}
