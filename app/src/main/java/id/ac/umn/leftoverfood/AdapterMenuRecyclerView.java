package id.ac.umn.leftoverfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterMenuRecyclerView extends RecyclerView.Adapter<AdapterMenuRecyclerView.ViewHolder> {
    private ArrayList<Menu> DaftarMenu;
    private Context context;

    public AdapterMenuRecyclerView(ArrayList<Menu> menus,Context context){
        this.DaftarMenu = menus;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String name = DaftarMenu.get(position).getNama();
        final String url  = DaftarMenu.get(position).getUrl();

        Glide.with(context).load(url).into(holder.imageView);
        holder.textView.setText(name);
    }

    @Override
    public int getItemCount() {
        return DaftarMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_namaMenu);
            imageView = (ImageView) itemView.findViewById(R.id.IV_menu);
        }
    }
}
