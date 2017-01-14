package co.zipperstudios.kfctimer.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import co.zipperstudios.kfctimer.AlertReceivers.AlertReceiver;
import co.zipperstudios.kfctimer.AlertReceivers.AlertReceiverB;
import co.zipperstudios.kfctimer.Objects.Product;
import co.zipperstudios.kfctimer.R;


/**
 *
 */
public class MenuFragment extends Fragment {
    private FrameLayout mRoot;
    private EditText mText;
    private Context mContext;
    private RadioGroup mRadioGroup;

    private TextClicked mCallback;

    public interface TextClicked{
        void sendProducts(Product p);
    }

    @Override
    public void onDetach() {
        mCallback = null; // => avoid leaking
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        mRoot = (FrameLayout) v.findViewById(R.id.fm_fl_layoutFrameMenu);
        mText = (EditText) v.findViewById(R.id.fm_et_editTextProduct);
        mContext = getContext();
        mRadioGroup = (RadioGroup) v.findViewById(R.id.fm_eg_radioGroup);
        mCallback = (TextClicked) getActivity();

        Button puiBtn = (Button) v.findViewById(R.id.buttonPui);
        puiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmPui(v);
            }
        });

        Button filetBBtn = (Button) v.findViewById(R.id.buttonFiletB);
        filetBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmFiletB(v);
            }
        });

        Button fileBtn = (Button) v.findViewById(R.id.buttonFile);
        fileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmFile(v);
            }
        });

        Button pulpeBtn = (Button) v.findViewById(R.id.buttonPulpe);
        pulpeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmPulpe(v);
            }
        });

        Button pulpePBtn = (Button) v.findViewById(R.id.buttonPulpeP);
        pulpePBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmPulpeP(v);
            }
        });

        Button aripaBtn = (Button) v.findViewById(R.id.buttonAripa);
        aripaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmAripa(v);
            }
        });

        Button stripsBtn = (Button) v.findViewById(R.id.buttonStrips);
        stripsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmStrips(v);
            }
        });

        Button zingerBtn = (Button) v.findViewById(R.id.buttonZinger);
        zingerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmZinger(v);
            }
        });

        Button ouBtn = (Button) v.findViewById(R.id.buttonOu);
        ouBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmOu(v);
            }
        });

        Button porumbBtn = (Button) v.findViewById(R.id.buttonPorumb);
        porumbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmPorumb(v);
            }
        });

        Button masaBtn = (Button) v.findViewById(R.id.buttonMasa);
        masaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmMasa(v);
            }
        });

        Button salateBtn = (Button) v.findViewById(R.id.buttonSalate);
        salateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmSalate(v);
            }
        });

        Button orezBtn = (Button) v.findViewById(R.id.buttonOrez);
        orezBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmOrez(v);
            }
        });

        return v;
    }

    public static MenuFragment newInstance() {
        MenuFragment f = new MenuFragment();
        return f;
    }

    private void addAlarmPui(View v) {
        String product = getResources().getString(R.string.pui);

        int alertTime = 90;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmFiletB(View v) {
        String product = getResources().getString(R.string.filet_bites);

        int alertTime = 40;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmFile(View v) {
        String product = getResources().getString(R.string.file);

        int alertTime = 60;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmPulpe(View v) {
        String product = getResources().getString(R.string.pulpe_nepicante);

        int alertTime = 45;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmPulpeP(View v) {
        String product = getResources().getString(R.string.pulpe_picante);

        int alertTime = 45;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmAripa(View v) {
        String product = getResources().getString(R.string.aripa);

        int alertTime = 90;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmStrips(View v) {
        String product = getResources().getString(R.string.strips);

        int alertTime = 45;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmZinger(View v) {
        String product = getResources().getString(R.string.zinger);

        int alertTime = 60;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmOu(View v) {
        String product = getResources().getString(R.string.ou);

        int alertTime = 30;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmPorumb(View v) {
        String product = getResources().getString(R.string.porumb);

        int alertTime = 180;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmMasa(View v) {
        String product = getResources().getString(R.string.masa_rece);

        int alertTime = 240;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmSalate(View v) {
        String product = getResources().getString(R.string.salate);

        int alertTime = 12 * 60;

        addAlarm(v, product, alertTime);
    }

    private void addAlarmOrez(View v) {
        String product = getResources().getString(R.string.orez);

        int alertTime = 12 * 60;

        addAlarm(v, product, alertTime);
    }

    public void sendProduct(Product p){
        mCallback.sendProducts(p);
    }

    private void addAlarm(View v, String product, int alertTime) {
        if (mText.getText().length() > 0 && !mText.getText().toString().equals("0")) {
            int checkedRadioButton = mRadioGroup.getCheckedRadioButtonId();
            String choice;
            if (checkedRadioButton == R.id.fm_rb_cap) {
                choice = getResources().getString(R.string.choicePiece);
            }
            else {
                choice = getResources().getString(R.string.choicePunga);
            }
            String quantity = mText.getText().toString();
            String text = getResources().getString(R.string.addedSnackBar).concat(" ")
                    .concat(quantity).concat(choice).concat(" ")
                    .concat(product).concat("!");

            Snackbar.make(mRoot, text, Snackbar.LENGTH_SHORT).show();

            Intent alertIntentExpire = new Intent(getActivity(), AlertReceiver.class);
            Intent alertIntentBeforeExpire = new Intent(getActivity(), AlertReceiverB.class);

            Long x = System.currentTimeMillis() % 1000000000;
            String uniqueID = x.toString();

            Bundle bundle = new Bundle();

            bundle.putString("quantity", quantity);
            bundle.putString("product", product);
            bundle.putString("choice", choice);
            bundle.putString("id", uniqueID);
            alertIntentExpire.putExtras(bundle);
            alertIntentBeforeExpire.putExtras(bundle);

            AlarmManager alarmManager = (AlarmManager)
                    getContext().getSystemService(Context.ALARM_SERVICE);

            long countdown = alertTime * 60000;

            Product p = new Product(product, Integer.parseInt(quantity), choice, countdown, System.currentTimeMillis(), Integer.parseInt(uniqueID) - 1, Integer.parseInt(uniqueID));
            sendProduct(p);
            long currentTime = SystemClock.elapsedRealtime();
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTime + (alertTime * 60000) - 60*1000*15 - 1500,
                    PendingIntent.getBroadcast(getActivity(), Integer.parseInt(uniqueID) - 1, alertIntentBeforeExpire, PendingIntent.FLAG_UPDATE_CURRENT));
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTime + (alertTime * 60000) - 1500,
                    PendingIntent.getBroadcast(getActivity(), Integer.parseInt(uniqueID), alertIntentExpire, PendingIntent.FLAG_UPDATE_CURRENT));
        }
        else {
            Snackbar.make(mRoot, getResources().getString(R.string.invalidQuantity), Snackbar.LENGTH_SHORT).show();
        }
    }
}

