package almacenPalabras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

//import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class GestionBaseDatos extends SQLiteOpenHelper {
	
	private static String KEY_CAMPO1		= "tema";
	private static String KEY_CAMPO2		= "palabra";
	private static String KEY_ID			= "ID";
	private static String KEY_TABLA			= "sopas";
	private static String CREAR_BASEDATOS	= "CREATE TABLE " + KEY_TABLA + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CAMPO1 + " TEXT, " + KEY_CAMPO2 + " TEXT)";
	private static String ELIMINAR_BASEDATOS= "DROP TABLE IF EXISTS " + KEY_TABLA;

	public GestionBaseDatos(Context ctx, String nb) {
		super(ctx, nb, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREAR_BASEDATOS);
//		bd = db;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		db.execSQL(ELIMINAR_BASEDATOS);
		onCreate(db);
	}

	public void anadirNotas(String tema, String palabra) {
		ContentValues vals = new ContentValues();
		vals.put(KEY_CAMPO1, tema);
		vals.put(KEY_CAMPO2, palabra);

		this.getWritableDatabase().insert(KEY_TABLA, null, vals);
	}

	public Cursor getNotas() {
		/**
		 * Cursor nos sirve para obtener los valores de los campos.
		 * */
		String[] columnas = { KEY_ID, KEY_CAMPO1, KEY_CAMPO2 };
		Cursor c = this.getReadableDatabase().query(KEY_TABLA, columnas, null,
				null, null, null, null);
		return c;
	}

//	public void eliminarNota(int ID) {
//		this.getWritableDatabase().delete(KEY_TABLA, "ID = " + ID, null);
//	}
	public void eliminarNota(int id) {
		this.getWritableDatabase().delete(KEY_TABLA, "ID = " + id, null);
	}
//	public void eliminaTabla(int id){
//		bd.delete(KEY_TABLA, "ID=" + id,null);
//	}

	public void cerrar() {
		this.close();
	}
}
