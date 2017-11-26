package com.todo.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todo.R;
import com.todo.model.Task;
import com.todo.model.realm.RealmController;
import com.todo.view.utils.ItemTouchHelperAdapter;
import com.todo.view.utils.ItemTouchHelperViewHolder;

import io.realm.Realm;
import io.realm.RealmObject;


public class ToDoAdapter extends RealmRecyclerAdapter<Task, ToDoAdapter.ViewHolder> implements
        ItemTouchHelperAdapter {
    private Realm mRealm;
    private OnItemActionListener mItemActionListener;

    public ToDoAdapter(@NonNull OnItemActionListener mItemActionListener) {
        this.mItemActionListener = mItemActionListener;
    }

    @Override
    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoAdapter.ViewHolder holder, int position) {
        mRealm = RealmController.getInstance().getRealm();
        final Task task = getItem(position);
        holder.titleTextView.setText(task.getTitle());
        holder.descriptionTextView.setText(task.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemActionListener.onItemClick(task);
            }
        });
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //TODO: Implement onItemMove() for a task.
        /*String prev = mItems.remove(fromPosition);
        mItems.add(toPosition > fromPosition + 1 ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);*/
    }

    @Override
    public void onItemDismiss(int position) {
        //TODO: Implement onItemDismiss() for a task.
        mRealm = RealmController.getInstance().getRealm();
        RealmObject obj = getItem(position);

        mRealm.beginTransaction();
        obj.deleteFromRealm();
        mRealm.commitTransaction();
        notifyItemRemoved(position);
    }

    public void addItem(RealmObject item, int position) {
        //TODO: Implement addItem() for a task.
        /*mItems.add(position, item);
        notifyItemInserted(position);*/
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.text);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.YELLOW);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public interface OnItemActionListener<T extends RealmObject> {
        void onItemClick(T item);

        void onItemDismiss(T item, int position);
    }
}
