package com.example.florsangama.flor;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText dato1;
    private EditText dato2;
    private EditText dato3;
    private EditText dato4;
    private EditText dato5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dato1 = (EditText)findViewById(R.id.pedidos);
        dato2 = (EditText)findViewById(R.id.cliente);
        dato3 = (EditText)findViewById(R.id.cantidad);
        dato4 = (EditText)findViewById(R.id.cobrar);
        dato5 = (EditText)findViewById(R.id.direccion);
    }
    public void eliminarpedido(View v){
        try {
            basededatos admin = new basededatos(this,
            "administracion", null, 1);
            SQLiteDatabase bd= admin.getWritableDatabase();
            String pedidos=dato1.getText().toString();
            int cant=bd.delete("pedisostabla","pedidos="+ pedidos,null);
            dato1.setText("");
            dato2.setText("");
            dato3.setText("");
            dato4.setText("");
            dato5.setText("");
            if (cant ==1)
                Toast.makeText(this,"se borro correctamente",
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this,"no existe",
                        Toast.LENGTH_LONG).show();


        }catch (Exception e){
            Toast toast= Toast.makeText(this,"el error es: "+ e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }

    }
public void modificacionpedido(View v){
    try {
        basededatos admin = new basededatos(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String pedidos = dato1.getText().toString();
        String cliente = dato2.getText().toString();
        String cantidad = dato3.getText().toString();
        String cobrar = dato4.getText().toString();
        String direccion = dato5.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("cliente",cliente);
        registro.put("cantidad",cantidad);
        registro.put("cobrar",cobrar);
        registro.put("direccion",direccion);
        int cant =bd.update("pedidostabla", registro, "pedidos=" + pedidos, null);
        if (cant ==1)
            Toast.makeText(this,"se modificaron los datos correctamente", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "no existe numero de cliente",
                    Toast.LENGTH_LONG).show();

        dato1.setText("");
        dato2.setText("");
        dato3.setText("");
        dato4.setText("");
        dato5.setText("");




    }catch (Exception e){
        Toast toast=Toast.makeText(this,"el error es:"+ e.getMessage(), Toast.LENGTH_LONG);
        toast.show();

    }
}
    public void  guardarpedido(View v){
        try {
            basededatos admin = new basededatos(this,"administracion",null,1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            String pedidos = dato1.getText().toString();
            String cliente = dato2.getText().toString();
            String cantidad = dato3.getText().toString();
            String cobrar = dato4.getText().toString();
            String direccion = dato5.getText().toString();


            ContentValues registro = new ContentValues();
            registro.put("cliente",cliente);
            registro.put("cantidad",cantidad);
            registro.put("cobrar",cobrar);
            registro.put("direccion",direccion);

            bd.insert("pedidostabla", null, registro);

            dato1.setText("");
            dato2.setText("");
            dato3.setText("");
            dato4.setText("");
            dato5.setText("");
        }catch (Exception e){
            Toast toast=Toast.makeText(this,"el error es:"+ e.getMessage(), Toast.LENGTH_LONG);
            toast.show();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
