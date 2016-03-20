package rs.ivsoft.logintest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rs.ivsoft.logintest.Adapteri.KonobariAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    private JSONArray mJSONArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycler = (RecyclerView) findViewById(R.id.konobari_login);
        mRecycler.setHasFixedSize(true);
        mlayoutManager=new LinearLayoutManager(this);
        mRecycler.setLayoutManager(mlayoutManager);
        new GetAllTask().execute(new APIConnector());
        //mRecycler.setAdapter(mAdapter);
    //    mAdapter=new KonobariAdapter(fillDataSet(mJSONArray));
    //    mRecycler.setAdapter(mAdapter);
    }




    public void setAdapter(JSONArray jsonArray){


        //this.mJSONArray=jsonArray;
        ArrayList<Konobar> results= new ArrayList<Konobar>();
        try{
            int i=0;

            while (i<jsonArray.length()) {
                Konobar obj = new Konobar();
                JSONObject object = jsonArray.getJSONObject(i);
                obj.setFullname(object.getString("fullName"));
                obj.setUsername(object.getString("user"));
                obj.setPassword(object.getString("pass"));
                obj.setPutanja(object.getString("slika"));
                results.add(obj);
                i++;
            }
            this.mAdapter=new KonobariAdapter(MainActivity.this,results);
            this.mRecycler.setAdapter(mAdapter);
        }catch (JSONException e){
            e.printStackTrace();
        }

    }
    private class GetAllTask extends AsyncTask<APIConnector,Long,JSONArray>{

        @Override
        protected JSONArray doInBackground(APIConnector... params) {
            return params[0].GetAll();
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            setAdapter(jsonArray);
        }
    }


}
