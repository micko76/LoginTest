package rs.ivsoft.logintest.Adapteri;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rs.ivsoft.logintest.Konobar;
import rs.ivsoft.logintest.R;

/**
 * Created by Milan on 20.3.2016..
 */
public class KonobariAdapter extends RecyclerView.Adapter<KonobariAdapter.KonobarHolder> {

    private ArrayList<Konobar> mData;
    private static MyClickListener myClickListener;
    private Context mContext;
    public static class KonobarHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        static ImageView mSlika;
        TextView mFullName;

        public KonobarHolder(View itemView) {
            super(itemView);
            mSlika=(ImageView) itemView.findViewById(R.id.slicica);
            mFullName=(TextView) itemView.findViewById(R.id.fullname);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(),v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public KonobariAdapter (Context context,ArrayList<Konobar> myData){
        this.mData=myData;
        mContext=context;
    }
    public KonobarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_element,parent,false);
        KonobarHolder dataObjectKonobarHolder = new KonobarHolder(view);
        return dataObjectKonobarHolder;
    }

    @Override
    public void onBindViewHolder(KonobarHolder konobarHolder, int position) {
        String putanja="http://192.168.1.2"+mData.get(position).getPutanja().replaceAll("\\\\","");
        konobarHolder.mFullName.setText(mData.get(position).getFullname());
        Picasso.with(mContext)
                .load(putanja)
                .error(R.drawable.glavonja)
                .placeholder(R.drawable.glavonja)
                .into(konobarHolder.mSlika);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
