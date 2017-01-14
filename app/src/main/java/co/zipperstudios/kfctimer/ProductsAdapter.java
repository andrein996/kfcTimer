package co.zipperstudios.kfctimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.zipperstudios.kfctimer.AlertReceivers.AlertReceiver;
import co.zipperstudios.kfctimer.AlertReceivers.AlertReceiverB;
import co.zipperstudios.kfctimer.Objects.Product;
import co.zipperstudios.kfctimer.ui.FragmentIstoric;

/**
 * Created by Andrei on 7/22/2016.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder>{

    private List<Product> mProductList;
    private DeleteFromRealmListener mDeleteFromRealmListener;
    private ImageView mRemoveButton;

    // The code block to be executed

    public void deleteAll(int position, long systemTime) {
        cancelAlarm(position);
        deleteItemFromLocalList(position);
        if (mDeleteFromRealmListener != null) {
            mDeleteFromRealmListener.delete(position, systemTime);
        }
    }

    private void cancelAlarm(int position) {
        Product thisProduct = mProductList.get(position);

        AlarmManager alarmManager = (AlarmManager) FragmentIstoric.getAppContext().getSystemService(Context.ALARM_SERVICE);

        Intent alertIntentExpire = new Intent(FragmentIstoric.getAppContext(), AlertReceiver.class);
        Intent alertIntentBeforeExpire = new Intent(FragmentIstoric.getAppContext(), AlertReceiverB.class);

        PendingIntent intentExpire = PendingIntent.getBroadcast(FragmentIstoric.getAppContext(), thisProduct.getSecondAlarm(), alertIntentExpire, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent intentBeforeExpire = PendingIntent.getBroadcast(FragmentIstoric.getAppContext(), thisProduct.getFirstAlarm(), alertIntentBeforeExpire, PendingIntent.FLAG_UPDATE_CURRENT);

        intentBeforeExpire.cancel();
        alarmManager.cancel(intentBeforeExpire);
        intentExpire.cancel();
        alarmManager.cancel(intentExpire);
    }


    public ProductsAdapter(List<Product> prodList) {
        this.mProductList = prodList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }



    public void deleteItemFromLocalList(int position) {//removes the row at position and the alarms
        mProductList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Product product = mProductList.get(position);
        holder.mProductName.setText(product.getProduct());
        holder.mProductQuantity.setText(product.getQuantityString());
        holder.mProductCountdown.setText(product.getCountdownString());
        //holder.setExpirationTimestamp(product.getExpirationTimeStamp());

        holder.setCountdown(product.getExpirationTimeStamp());
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public void setListener(DeleteFromRealmListener deleteFromRealmListener) {
        mDeleteFromRealmListener = deleteFromRealmListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mProductName, mProductQuantity, mProductCountdown;
        private CountDownTimer mTimer;

        private long expirationTimestamp;

        public MyViewHolder(View view) {
            super(view);
            mProductName = (TextView) view.findViewById(R.id.plr_tv_productName);
            mProductQuantity = (TextView) view.findViewById(R.id.plr_tv_productQuantity);
            mProductCountdown = (TextView) view.findViewById(R.id.plr_tv_productCountdown);
            mRemoveButton = (ImageView) view.findViewById(R.id.plr_btn_remove);

            mRemoveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int thisPosition = getAdapterPosition();
                    deleteAll(thisPosition, mProductList.get(thisPosition).getSystemtime());
                    mTimer.cancel();
                }
            });

        }

        public void setCountdown(final long expirationTime) {
            expirationTimestamp = expirationTime;

            if (mTimer != null) {
                mTimer.cancel();
            }

            mTimer = new CountDownTimer(expirationTimestamp, 1000) {

                public void onTick(long millisUntilFinished) {
                    long newTime = millisUntilFinished / 60000;
                    String timer = (Long.toString(newTime)).concat("'");
                    mProductCountdown.setText(timer);
                }

                public void onFinish() {
                    int thisPosition = getAdapterPosition();
                    deleteAll(thisPosition, mProductList.get(thisPosition).getSystemtime());
                }
            }.start();
        }

//        public void setExpirationTimestamp(long expirationTimestamp) {
//            this.expirationTimestamp = expirationTimestamp;
//        }
    }

    public interface DeleteFromRealmListener {
        void delete(int position, long systemTime);
    }
}
