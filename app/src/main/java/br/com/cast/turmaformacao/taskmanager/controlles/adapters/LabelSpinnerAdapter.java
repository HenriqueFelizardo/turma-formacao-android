package br.com.cast.turmaformacao.taskmanager.controlles.adapters;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;


public class LabelSpinnerAdapter extends BaseAdapter {

    private Activity context;
    private List<Label> labels;

    public LabelSpinnerAdapter(Activity context, List<Label> labels) {
        this.context = context;
        this.labels = labels;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Label getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Label label = getItem(position);
        View labelListItemView = context.getLayoutInflater().inflate(R.layout.list_item_spinner_label, parent, false);

        TextView textViewName = (TextView) labelListItemView.findViewById(R.id.viewTextName);
        textViewName.setText(label.getName());

        int hexColor = android.graphics.Color.parseColor(label.getColor().getHex());
        labelListItemView.findViewById(R.id.viewLabelItem).getBackground().setColorFilter(hexColor, PorterDuff.Mode.SRC);

        return labelListItemView;
    }
}
