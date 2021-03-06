package sim.ssn.com.todo.fragment.todo;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sim.ssn.com.todo.listener.CustomListener;
import sim.ssn.com.todo.resource.Todo;

public class AdapterTodoList extends RecyclerView.Adapter<ViewHolderTodoList>{
    private List<Todo> todoList;
    private int layout;
    private Activity activity;
    private View inflatedView;
    private CustomListener customListener;

    public AdapterTodoList(int layout, Activity activity, List<Todo> todoList){
        this.customListener = (CustomListener) activity;
        this.todoList = todoList;
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public ViewHolderTodoList onCreateViewHolder(ViewGroup viewGroup, int position) {
        inflatedView = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        return new ViewHolderTodoList(activity, inflatedView);
    }

    @Override
    public void onBindViewHolder(ViewHolderTodoList holder, final int position) {
        final Todo todo = todoList.get(position);
        holder.assignData(todo);
        inflatedView.setOnLongClickListener(customListener.handleEditTodo(todoList.get(position)));
        inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customListener.handleCardClick(todo);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
