package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Color;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public final class LabelContract {

    public static final String TABLE = "label";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String COLOR = "color";
    public static final String ID = "id";
    public static final String[] COLUNS = {ID, NAME, DESCRIPTION, COLOR};

    private LabelContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT, ");
        create.append(COLOR + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Label label) {
        ContentValues values = new ContentValues();

        values.put(LabelContract.ID, label.getId());
        values.put(LabelContract.NAME, label.getName());
        values.put(LabelContract.COLOR, label.getColor().getHex());
        values.put(LabelContract.DESCRIPTION, label.getDescription());

        return values;
    }

    public static Label getLabel(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Label label = new Label();
            label.setId(cursor.getLong(cursor.getColumnIndex(LabelContract.ID)));
            label.setName(cursor.getString(cursor.getColumnIndex(LabelContract.NAME)));
            label.setDescription(cursor.getString(cursor.getColumnIndex(LabelContract.DESCRIPTION)));
            label.setColor(Color.getInstance(cursor.getString(cursor.getColumnIndex(LabelContract.COLOR))));

            return label;
        }
        return null;
    }

    public static List<Label> getLabels(Cursor cursor) {
        ArrayList<Label> labels = new ArrayList<>();
        while (cursor.moveToNext()) {
            labels.add(getLabel(cursor));
        }
        return labels;
    }

}
