package lemons.combustible.passmaterial;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bustiblelemons.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import lemons.combustible.passmaterial.passphrases.NewPassPhraseAdapter;
import lemons.combustible.passmaterial.passphrases.OnCopyToClipBoard;
import lemons.combustible.passmaterial.passphrases.PassPhrase;
import lemons.combustible.passmaterial.passphrases.PassPhraseGenerator;
import lemons.combustible.passmaterial.passphrases.generators.PassphraseGenerators;
import lemons.combustible.passmaterial.passphrases.generators.PreferredGenerator;
import rx.Observer;


public class PassmaterialActivity extends ActionBarActivity implements OnCopyToClipBoard,
                                                                       Observer<PassPhrase> {

    private static final Logger log = new Logger(PassmaterialActivity.class);
    @Optional
    @InjectView(R.id.recycler)
    RecyclerView mRecyclerView;
    private PreferredGenerator mPreferredGenerator = PreferredGenerator.Wordnik;
    private PassPhraseGenerator  mGenerator;
    private NewPassPhraseAdapter mNewPassPhraseAdapter;
    private ClipboardManager mClipboardManager;

    @Optional
    @OnClick(android.R.id.button1)
    public void onGenerateNewBundle() {
        mGenerator.generateBundleAsync(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passmaterial);
        ButterKnife.inject(this);
        if (mRecyclerView != null) {
            mGenerator = PassphraseGenerators.getGenerator(mPreferredGenerator);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_passmaterial, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGenerator != null) {
            mGenerator.unsubscribe();
        }
    }

    @Override
    public void onCopyToClipboard(CharSequence passphrase) {
        if (mClipboardManager == null) {
            mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(getString(R.string.clipboard_label), passphrase);
            mClipboardManager.setPrimaryClip(clip);
            Toast.makeText(this, R.string.copied_to_clirboard, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(PassPhrase passPhrase) {
        log.d("onNext %s", passPhrase);
        if (mRecyclerView != null) {
            if (mNewPassPhraseAdapter == null) {
                mNewPassPhraseAdapter = new NewPassPhraseAdapter()
                        .withOnCopyToClipboard(this);
                mRecyclerView.setAdapter(mNewPassPhraseAdapter);
            }
            mNewPassPhraseAdapter.refreshData(passPhrase);
        }
    }
}
