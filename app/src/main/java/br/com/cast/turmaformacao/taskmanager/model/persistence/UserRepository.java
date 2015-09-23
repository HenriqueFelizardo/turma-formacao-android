package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;

public final class UserRepository {
    private UserRepository() {
        super();
    }

    public static void save(User user) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, values);
        } else {
            String where = UserContract.ID + " = ? ";
            String[] params = {user.getId().toString()};
            db.update(UserContract.TABLE, values, where, params);
        }
        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = UserContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(UserContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static User login(User user) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = UserContract.USER + " = ? and " + UserContract.SENHA + " = ? ";
        String[] params = {user.getUsuario(), user.getSenha()};

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, where, params, null, null, UserContract.ID);

        user = UserContract.getUser(cursor);

        db.close();
        databaseHelper.close();

        return user;
    }

    public static List<User> getAll() {

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, null, null, null, null, UserContract.ID);
        List<User> values = UserContract.getUsers(cursor);

        while (cursor.moveToNext()) {
            User user = new User();
            values.add(user);
        }

        db.close();
        databaseHelper.close();
        return values;
    }
}
