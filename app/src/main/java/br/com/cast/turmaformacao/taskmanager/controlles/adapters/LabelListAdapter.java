package br.com.cast.turmaformacao.taskmanager.controlles.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.cast.turmaformacao.taskmanager.R;
import br.com.cast.turmaformacao.taskmanager.model.entities.Label;

public class LabelListAdapter extends BaseAdapter {
    private List<Label> labelList;
    private Activity context;

    public LabelListAdapter(Activity context, List<Label> labelList) {
        this.context = context;
        this.labelList = labelList;
    }

    public void setDataValues(List<Label> values) {
        labelList.clear();
        labelList.addAll(values);
    }

    @Override
    public int getCount() {
        return labelList.size();
    }

    @Override
    public Label getItem(int position) {
        return labelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Label label = getItem(position);
        View labelListItemView = context.getLayoutInflater().inflate(R.layout.list_item_label, parent, false);

        TextView textViewId = (TextView) labelListItemView.findViewById(R.id.textViewId);
        textViewId.setText(label.getId().toString());

        TextView textViewName = (TextView) labelListItemView.findViewById(R.id.textViewName);
        textViewName.setText(label.getName());

        View viewTagColor = labelListItemView.findViewById(R.id.viewTagColor);
        int hexColor = android.graphics.Color.parseColor(label.getColor().getHex());
        viewTagColor.findViewById(R.id.viewTagColor).setBackgroundColor(hexColor);

        return labelListItemView;
    }
}
