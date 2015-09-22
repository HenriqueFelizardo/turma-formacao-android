package br.com.cast.turmaformacao.taskmanager.controlles.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Color;

public class ColorListAdapter extends BaseAdapter {

    private Color[] colors;
    private Activity context;

    public ColorListAdapter(Activity context, Color[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return this.colors.length;
    }

    @Override
    public Color getItem(int position) {
        return this.colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(R.layout.list_item_color, parent, false);

        int hexColor = android.graphics.Color.parseColor(getItem(position).getHex());
        view.findViewById(R.id.viewColor).setBackgroundColor(hexColor);

        return view;
    }
}

