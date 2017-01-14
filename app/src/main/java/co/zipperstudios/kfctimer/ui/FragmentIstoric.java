package co.zipperstudios.kfctimer.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import co.zipperstudios.kfctimer.Objects.Product;
import co.zipperstudios.kfctimer.ProductsAdapter;
import co.zipperstudios.kfctimer.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class FragmentIstoric extends Fragment implements ProductsAdapter.DeleteFromRealmListener {
    private List<Product> mProductsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ProductsAdapter mAdapter;
    private static Realm myRealm;
    private RealmConfiguration realmConfig;
    private static Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_istoric, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        
        FragmentIstoric.mContext = v.getContext();


        mAdapter = new ProductsAdapter(mProductsList);
        mAdapter.setListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(v.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        realmConfig = new RealmConfiguration.Builder(getActivity()).build();

        myRealm = Realm.getInstance(realmConfig);

        readFromRealm();

        return v;
    }

    public static FragmentIstoric newInstance() {
        FragmentIstoric f = new FragmentIstoric();
        return f;
    }

    public static Context getAppContext(){
        return FragmentIstoric.mContext;
    }

    public void updateProducts(Product p){
        // Here you have it

        myRealm.beginTransaction();
        Product historyTab = myRealm.createObject(Product.class);

        historyTab.setCountdown(p.getCountdown());
        historyTab.setProduct(p.getProduct());
        historyTab.setMyPackage(p.getMpackage());
        historyTab.setQuantity(p.getQuantity());
        historyTab.setSystemtime(p.getSystemtime());
        historyTab.setFirstAlarm(p.getFirstAlarm());
        historyTab.setSecondAlarm(p.getSecondAlarm());
        myRealm.commitTransaction();

        mProductsList.add(p);
        mAdapter.notifyDataSetChanged();
    }

    public void updateProductFromRealm(Product p){
        // Here you have it


    }

    public void readFromRealm() {
        RealmResults<Product> resultsFromRealm = myRealm.where(Product.class).findAll();

        ArrayList<Product> results = new ArrayList<>();
        results.addAll(myRealm.copyFromRealm(resultsFromRealm));

        if (results.size() != 0) {
            for (Product p : results) {
                if (p.getExpirationTimeStamp() > 0) {
                    mProductsList.add(p);
                }
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void delete(int position, long systemTime) {
        myRealm.beginTransaction();
        RealmQuery<Product> query = myRealm.where(Product.class);

        query.equalTo("systemtime", systemTime);

        RealmResults<Product> result = query.findAll();

        result.deleteFirstFromRealm();

        myRealm.commitTransaction();

        //mProductsList.remove(position);
        //mAdapter.notifyDataSetChanged();
    }
}
