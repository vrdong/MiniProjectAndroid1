package com.example.hp.miniproject1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class UniversityAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<University> universityList;

    public UniversityAdapter(Context context, int layout, List<University> universityList) {
        this.context = context;
        this.layout = layout;
        this.universityList = universityList;
    }

    @Override
    public int getCount() {
        return universityList.size();
    }

    @Override
    public Object getItem(int position) {
        return universityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen,txtMota;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_university, null);
            //Anh xa view
            holder = new ViewHolder();

            holder.txtTen = (TextView) convertView.findViewById(R.id.textViewTen);
            holder.txtMota = (TextView) convertView.findViewById(R.id.textViewMoTa);
            holder.imgHinh = (ImageView) convertView.findViewById(R.id.imageViewHinh);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }


        // Gan gia tri

        University university = this.universityList.get(position);
        holder.txtTen.setText(university.getTen());
        holder.txtMota.setText(university.getLocation());
        holder.imgHinh.setImageResource(university.getImage());

        return convertView;
    }
}
