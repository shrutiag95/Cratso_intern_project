package shruti.agarwal.org.cratso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Agarwal's on 7/27/2017.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    List<String> name;
    List<String> user_id;
    List<String> photo;
    List<String> rank;
    public MyRecyclerAdapter(Context applicationContext, List<String> name, List<String> user_id, List<String> photo, List<String> rank) {
        this.context=applicationContext;
        this.name=name;
        this.user_id=user_id;
        this.photo=photo;
        this.rank=rank;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        MyViewHolder holder =new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
          holder.tv_name.setText(name.get(position));
          holder.tv_userid.setText("Userid: "+user_id.get(position));
          holder.tv_rank.setText("Rank:"+rank.get(position));
          Glide.with(context).load(photo.get(position)).into(holder.profile_photo);


    }

    @Override
    public int getItemCount() {
        return name.size();
    }
}
