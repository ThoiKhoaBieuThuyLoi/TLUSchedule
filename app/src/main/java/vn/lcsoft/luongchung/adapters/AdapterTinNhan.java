package vn.lcsoft.luongchung.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.lcsoft.luongchung.models.TinNhan;
import vn.lcsoft.luongchung.tluschedule.ChatMain;
import vn.lcsoft.luongchung.tluschedule.R;

public class AdapterTinNhan extends BaseAdapter {
    private String ID;
    private Activity context;
    private ArrayList<TinNhan> objects;
    public AdapterTinNhan(ChatMain chatMain, ArrayList<TinNhan> arrTinNhan, String s) {
        this.context=chatMain;
        this.objects=arrTinNhan;
        this.ID=s;
    }
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        return (objects.get(position).getId().equals(ID)) ? 0 : 1;
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public TinNhan getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/fontmain.ttf");
        final TinNhan tinNhan = objects.get(i);
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View v= inflater.inflate(R.layout.itemchatkhach, null);
        CircleImageView imgAva=v.findViewById(R.id.imgAVTKhach);
        TextView txtKhach=v.findViewById(R.id.txtKhach);
        TextView txtNgGui=v.findViewById(R.id.txtNguoiGui);
        Glide.with(context)
                .load("https://graph.facebook.com/" + tinNhan.getId()+ "/picture?type=small")
                .into(imgAva);
        txtKhach.setText(tinNhan.getNoiDung());
        txtKhach.setTypeface(font);
        txtNgGui.setTypeface(font);
        txtNgGui.setText(tinNhan.getName()+": ");
        if(tinNhan.getId().equals("1048254815322995")){
            txtNgGui.setTextColor(Color.parseColor("#D44A3C"));
        }


        imgAva.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+tinNhan.getId()));
                    context.startActivity(intent);
                } catch(Exception e) {
                    Toast.makeText(context, "Bạn phải cài Ứng dụng facebook mới sử dụng tính năng này !", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });



        return v;
    }
}
