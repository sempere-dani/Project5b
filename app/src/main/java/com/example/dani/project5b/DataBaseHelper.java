package com.example.dani.project5b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    //Datos de la tabla
    final private static String NAME = "programas_db";
    final static String TABLE_NAME = "programas";
    final static String ID = "_id";
    final static String PROGRAM_NAME = "nombre";
    final static String IMG = "image";
    final static String COMENT = "Comentario";
    //Comandos

    final static String[] columns = { ID , PROGRAM_NAME};
    final static String[] columns2 = { COMENT, IMG } ;

    final private static String CREATE_CMD =
            "CREATE TABLE "+ TABLE_NAME +" (" + ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COMENT +"STRING, "
                    + IMG + "STRING, "
                    + PROGRAM_NAME + " TEXT NOT NULL)";
    final private static Integer VERSION = 1;
    final private Context mContext;
    //Modos edicion
    public static final String C_MODO = "modo" ;
    public static final int C_VISUALIZAR = 551 ;
    public static final int C_CREAR = 552 ;
    public static final int C_EDITAR = 553 ;
    //Constructor
    public DataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }
    //Creación de la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
//Creamos la base de datos
        Log.i(this.getClass().toString(), "Tabla LENGUAJES  creada");
        db.execSQL(CREATE_CMD);
//La rellenamos
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.PROGRAM_NAME, " JAVA");
        //values.put(DataBaseHelper.IMG,"");
        //values.put(DataBaseHelper.COMENT,"Orientado a Objetos");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();
        values.put(DataBaseHelper.PROGRAM_NAME, "ANDROID");
        //values.put(DataBaseHelper.IMG,"");
        //values.put(DataBaseHelper.COMENT,"");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);
        values.clear();
        values.put(DataBaseHelper.PROGRAM_NAME, "RUBY");
        //values.put(DataBaseHelper.IMG,"");
        //values.put(DataBaseHelper.COMENT,"");
        db.insert(DataBaseHelper.TABLE_NAME, null, values);


        Log.i(this.getClass().toString(), "Datos insertados");


    }
    //Actualización de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// N/A
    }
    //Borrando de la base de datos
    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
    //Lectura de la base de datos
    public Cursor readProgramas(SQLiteDatabase db) {
        return db.query(TABLE_NAME,
                columns, null, new String[] {}, null, null,
                null);
    }
    /**
     * Devuelve cursor con todos las columnas del registro
     */
    public Cursor getRegistro(long id) throws SQLException
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.query( true, TABLE_NAME, columns, ID + "=" + id, null, null, null, null, null);
//Nos movemos al primer registro de la consulta
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getRegistro2(long id) throws SQLException
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c1 = db.query( true, TABLE_NAME, columns2, ID + "=" + id, null, null, null, null, null);
//Nos movemos al primer registro de la consulta
        if (c1 != null) {
            c1.moveToFirst();
        }
        return c1;
    }
    /**
     * Inserta los valores en un registro de la tabla
     */
    public long insert(ContentValues reg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.insert(TABLE_NAME, null, reg);
    }
    /**
     * Inserta los valores en un registro de la tabla
     */
    public long update(ContentValues reg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        if (reg.containsKey(ID))
        {
//
// Obtenemos el id y lo borramos de los valores
//
            long id = reg.getAsLong(ID);
            reg.remove(ID);
//
// Actualizamos el registro con el identificador que hemos extraido
//
            return db.update(TABLE_NAME, reg, "_id=" + id, null);
        }
        return db.insert(TABLE_NAME, null, reg);
    }


/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_helper);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_base_helper, menu);
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
    }*/
}
