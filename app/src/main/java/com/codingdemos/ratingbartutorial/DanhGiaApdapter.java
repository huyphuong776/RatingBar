package com.codingdemos.ratingbartutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class DanhGiaApdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<PhanHoi> phanHoiList;

    public DanhGiaApdapter(Context context, int layout, List<PhanHoi> phanHoiList) {
        this.context = context;
        this.layout = layout;
        this.phanHoiList = phanHoiList;
    }

    @Override
    public int getCount() {
        return phanHoiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        // Ánh xạ view
        TextView txtUser = (TextView) view.findViewById(R.id.textviewTen);
        TextView txtPhanHoi = (TextView) view.findViewById(R.id.textviewPhanhoi);

        //Gán giá trị
        PhanHoi phanHoi = phanHoiList.get(i);
        txtUser.setText(phanHoi.getUserName());
        txtPhanHoi.setText(phanHoi.getDanhGia());


        return view;
    }
}
