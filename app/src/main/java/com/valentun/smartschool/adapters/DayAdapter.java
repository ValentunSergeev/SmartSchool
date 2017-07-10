package com.valentun.smartschool.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.R;

import java.util.ArrayList;

/**
 * Created by Valentun on 02.07.2017.
 */

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    // Will be used later to identify which text will be placed to teacherGroupView
    public enum Type {
        GROUP, TEACHER
    }

    private ArrayList<Lesson> lessons;
    private Type type;

    public DayAdapter(ArrayList<Lesson> lessons, Type type) {
        this.lessons = lessons;
        this.type = type;
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.day_list_item, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView numberView, nameView, startTimeView,
                endTimeView, teacherGroupView, roomView;

        DayViewHolder(View itemView) {
            super(itemView);

            numberView = (TextView) itemView.findViewById(R.id.lesson_number);
            nameView = (TextView) itemView.findViewById(R.id.lesson_name);
            startTimeView = (TextView) itemView.findViewById(R.id.lesson_start_time);
            endTimeView = (TextView) itemView.findViewById(R.id.lesson_end_time);
            teacherGroupView = (TextView) itemView.findViewById(R.id.lesson_teacher_group);
            roomView = (TextView) itemView.findViewById(R.id.lesson_room_number);
        }

        void bind(int position) {
            // TODO set strings from bd instead
            if (type == Type.GROUP) {
                teacherGroupView.setText("Kozlova T.A.");
            } else {
                teacherGroupView.setText("312");
            }
        }
    }
}
