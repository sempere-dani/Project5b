package com.example.dani.project5b;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Like extends ListActivity {

    private DataBaseHelper mDbHelper;
    private SQLiteDatabase db;
    private SimpleCursorAdapter mAdapter;
    private Cursor c;
    private static final String TAG = "Datos";
    public static final String C_MODO = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EDITAR = 553 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        // Creamos una nueva DataBase
        mDbHelper = new DataBaseHelper(this);
        db = mDbHelper.getWritableDatabase();

        Toast.makeText(getBaseContext(),"Base de datos preparada",Toast.LENGTH_LONG);


        //Leemos la base de datos y mostramos la informacion
        Cursor c = mDbHelper.readProgramas(db);
        mAdapter = new SimpleCursorAdapter(this, R.layout.activity_data_base_helper, c,
                DataBaseHelper.columns, new int[]{R.id._id, R.id.nombre},
                0);
        setListAdapter(mAdapter);
        //Añadimos el listener del boton
        final Button boton = (Button) findViewById(R.id.addBtn);
        boton.setOnClickListener(new Button.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent i = new Intent(Like.this, Formulario.class);
                                         i.putExtra(C_MODO, C_CREAR);
                                         startActivityForResult(i, C_CREAR);
                                     }
                                 }
        );


    }

    //Visualizar un dato en Formulario
   @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
// Llamamos a la Actividad Formulario indicando el modo visualización y el identificador del registro
        Intent i = new Intent(Like.this, Formulario.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(mDbHelper.ID, id);

        startActivityForResult(i, C_VISUALIZAR);
    }

    //CApturamos la respuesta a la creación de registro
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Nos aseguramos que es la petición que hemos realizado
//
        switch (requestCode) {
            case C_CREAR:
                if (resultCode == RESULT_OK)
//Leemos la base de datos y mostramos la informacion
                c = mAdapter.getCursor();
                c = mDbHelper.readProgramas(db);
                mAdapter.changeCursor(c);
                mAdapter.notifyDataSetChanged();

            case C_EDITAR:
                if (resultCode == RESULT_OK)
//Leemos la base de datos y mostramos la informacion
                c = mAdapter.getCursor();
                c = mDbHelper.readProgramas(db);
                mAdapter.changeCursor(c);
                mAdapter.notifyDataSetChanged();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }

    }
    public void editHandler(View v) {
//get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout)v.getParent();
        TextView id =(TextView) vwParentRow.findViewById(R.id._id);
        Intent i = new Intent(Like.this, Formulario.class);
        i.putExtra(C_MODO, C_EDITAR);
        i.putExtra(mDbHelper.ID, Long.valueOf((String)id.getText()));
        this.startActivityForResult(i, C_EDITAR);
    }
    public void viewHandler(View v) {
//get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout) v.getParent();
        TextView id = (TextView) vwParentRow.findViewById(R.id._id);
        Intent i = new Intent(Like.this, Formulario.class);
        i.putExtra(C_MODO, C_VISUALIZAR);
        i.putExtra(mDbHelper.ID, Long.valueOf((String) id.getText()));
        this.startActivityForResult(i, C_VISUALIZAR);
    }

}

