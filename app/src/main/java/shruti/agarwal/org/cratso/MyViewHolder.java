package shruti.agarwal.org.cratso;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Agarwal's on 7/27/2017.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv_name;
    TextView tv_userid;
    TextView tv_rank;
    ImageView profile_photo;


    public MyViewHolder(View row) {
        super(row);
        tv_name=(TextView)row.findViewById(R.id.name);
        tv_userid=(TextView)row.findViewById(R.id.userid);
        tv_rank=(TextView)row.findViewById(R.id.rank);
        profile_photo=(ImageView)row.findViewById(R.id.photo);
    }
}
