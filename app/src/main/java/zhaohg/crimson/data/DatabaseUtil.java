package zhaohg.crimson.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseUtil {

    public static final int PAGE_SIZE = 50;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static SQLiteDatabase getDatabase(Context context) {
        return context.openOrCreateDatabase("tomato.db", Context.MODE_PRIVATE, null);
    }

    public static boolean isTableExisted(Context context, String tableName) {
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query(false, "sqlite_master", null, "tbl_name='" + tableName + "'", null, null, null, null, null);
        boolean existed = false;
        if (cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return existed;
    }

    public static void dropTable(Context context, String tableName) {
        SQLiteDatabase db = getDatabase(context);
        db.execSQL("DROP TABLE " + tableName + ";");
        db.close();
    }

    public static String formatDate(Date date) {
        return format.format(date);
    }

    public static Date parseDate(String date) {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cursor selectById(SQLiteDatabase db, String tableName, int id) {
        return db.query(false, tableName, null, "id=" + id, null, null, null, null, null);
    }

    public static Cursor getPageSortedByKey(SQLiteDatabase db, String tableName, String keyColumn, boolean desc, int pageNum) {
        return db.rawQuery(
                "SELECT * " +
                "FROM " + tableName + " " +
                "ORDER BY " + keyColumn + " " + (desc ? "DESC" : "") + " " +
                "LIMIT " + PAGE_SIZE + " " +
                "OFFSET " + (PAGE_SIZE * pageNum) + ";", null);
    }

    public static Cursor getPageSortedById(SQLiteDatabase db, String tableName, boolean desc, int pageNum) {
        return getPageSortedByKey(db, tableName, "id", desc, pageNum);
    }

    public static Cursor getPageSortedWithCondition(SQLiteDatabase db, String tableName, String condition, String keyColumn, boolean desc, int pageNum) {
        return db.rawQuery(
                "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE " + condition + " " +
                "ORDER BY " + keyColumn + " " + (desc ? "DESC" : "") + " " +
                "LIMIT " + PAGE_SIZE + " " +
                "OFFSET " + (PAGE_SIZE * pageNum) + ";", null);

    }

    public static Cursor getPageSortedByIdWithCondition(SQLiteDatabase db, String tableName, String condition, boolean desc, int pageNum) {
        return getPageSortedWithCondition(db, tableName, condition, "id", desc, pageNum);
    }

    public static void deleteAll(Context context, String tableName) {
        SQLiteDatabase db = getDatabase(context);
        db.delete(tableName, null, null);
        db.close();
    }

    public static void deleteById(Context context, String tableName, int id) {
        SQLiteDatabase db = getDatabase(context);
        db.delete(tableName, "id=" + id, null);
        db.close();
    }

    public static String sqliteEscape(String str){
        str = str.replace("/", "//");
        str = str.replace("'", "''");
        return str;
    }

}
