package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.User;

public final class UserContract {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String USER = "usuario";
    public static final String SENHA = "senha";
    public static final String[] COLUNS = {ID, USER, SENHA};

    private UserContract() {
        super();
    }

    public static String getCreateTableUser() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(USER + " TEXT NOT NULL, ");
        create.append(SENHA + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(UserContract.ID, user.getId());
        values.put(UserContract.USER, user.getUsuario());
        values.put(UserContract.SENHA, user.getSenha());

        return values;
    }

    public static User getUser(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndex(UserContract.ID)));
            user.setUsuario(cursor.getString(cursor.getColumnIndex(UserContract.USER)));
            user.setSenha(cursor.getString(cursor.getColumnIndex(UserContract.SENHA)));

            return user;
        }
        return null;
    }

    public static List<User> getUsers(Cursor cursor) {
        ArrayList<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            users.add(getUser(cursor));
        }
        return users;
    }
}
