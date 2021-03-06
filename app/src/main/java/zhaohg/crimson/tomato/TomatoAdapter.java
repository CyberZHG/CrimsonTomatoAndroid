package zhaohg.crimson.tomato;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhaohg.crimson.R;
import zhaohg.crimson.data.DatabaseUtil;


public class TomatoAdapter extends RecyclerView.Adapter<TomatoAdapter.ViewHolder> {

    private final List<Tomato> tomatoes;

    private final Context context;

    public TomatoAdapter(Context context) {
        this.context = context;
        this.tomatoes = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tomato, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Tomato tomato = tomatoes.get(i);
        viewHolder.setId(tomato.getId());
        viewHolder.textViewTitle.setText(tomato.getTitle());
        viewHolder.textViewDate.setText(DatabaseUtil.formatDate(tomato.getBegin()) +
                " / " +
                DatabaseUtil.formatDate(tomato.getEnd()));
        if (tomato.isSynced()) {
            viewHolder.textViewSync.setText(context.getString(R.string.tomato_synced));
        } else {
            viewHolder.textViewSync.setText(context.getString(R.string.tomato_unsynced));
        }
    }

    @Override
    public int getItemCount() {
        return tomatoes.size();
    }

    private boolean isExisted(Tomato newTomato) {
        for (Tomato tomato : tomatoes) {
            if (tomato.getId() == newTomato.getId()) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        tomatoes.clear();
        notifyDataSetChanged();
    }

    public Tomato getAt(int position) {
        if (0 <= position && position < tomatoes.size()) {
            return tomatoes.get(position);
        }
        return null;
    }

    public void removeAt(int position) {
        tomatoes.remove(position);
        notifyItemRemoved(position);
    }

    private void append(Tomato tomato) {
        if (!isExisted(tomato)) {
            int pos = tomatoes.size();
            tomatoes.add(tomato);
            notifyItemInserted(pos);
            notifyItemRangeChanged(pos, 1);
        }
    }

    public void append(List<Tomato> tomatoes) {
        for (Tomato tomato : tomatoes) {
            append(tomato);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private int id;

        public final TextView textViewTitle;
        public final TextView textViewDate;
        public final TextView textViewSync;

        public ViewHolder(View view) {
            super(view);
            textViewTitle = (TextView) view.findViewById(R.id.text_view_title);
            textViewDate = (TextView) view.findViewById(R.id.text_view_date);
            textViewSync = (TextView) view.findViewById(R.id.text_view_sync);
            view.setOnClickListener(new OnPostClickerListener());
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        private class OnPostClickerListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
            }
        }
    }

}
