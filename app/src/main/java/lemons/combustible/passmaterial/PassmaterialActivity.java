package lemons.combustible.passmaterial;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bustiblelemons.logging.Logger;
import com.bustiblelemons.model.OnlinePhotoUrl;

import java.io.File;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.Optional;
import lemons.combustible.passmaterial.google.ImagesAdapter;
import lemons.combustible.passmaterial.google.ImagesRetriever;
import lemons.combustible.passmaterial.passphrases.NewPassPhraseAdapter;
import lemons.combustible.passmaterial.passphrases.OnCopyToClipBoard;
import lemons.combustible.passmaterial.passphrases.PassPhraseGenerator;
import lemons.combustible.passmaterial.passphrases.Word;
import lemons.combustible.passmaterial.passphrases.generators.PassphraseGenerators;
import lemons.combustible.passmaterial.passphrases.generators.PreferredGenerator;
import lemons.combustible.passmaterial.passphrases.model.PassPhrase;
import lemons.combustible.passmaterial.passphrases.model.PassPhraseConfig;
import lemons.combustible.passmaterial.settings.OnOpenSettings;
import lemons.combustible.passmaterial.settings.SettingsDialog;
import rx.Observer;


public class PassmaterialActivity extends ActionBarActivity implements OnCopyToClipBoard,
                                                                       OnOpenSettings,
                                                                       OnPassphraseConfigChanged {

    private static final Logger log = new Logger(PassmaterialActivity.class);
    @Optional
    @InjectView(R.id.recycler)
    RecyclerView mRecyclerView;

    @Optional
    @InjectView(R.id.images_recycler)
    RecyclerView mImagesRecyclerView;


    private PreferredGenerator mPreferredGenerator = PreferredGenerator.Wordnik;
    private PassPhraseGenerator  mGenerator;
    private NewPassPhraseAdapter mNewPassPhraseAdapter;
    private ClipboardManager     mClipboardManager;
    private ImagesAdapter        mImagesAdapter;
    private GridLayoutManager    mGridLayoutManager;

    private Observer<Collection<OnlinePhotoUrl>> mImagesObserver       =
            new Observer<Collection<OnlinePhotoUrl>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Collection<OnlinePhotoUrl> onlinePhotoUrls) {
                    log.d("mImagesObserver onNext %s", onlinePhotoUrls);
                    if (mImagesRecyclerView != null) {
                        if (mImagesAdapter == null) {
                            int wordCount = PassPhraseConfig.getPassPhraseConfig().getWordCount();
                            mImagesAdapter = new ImagesAdapter()
                                    .withImageCountToShow(wordCount);
                            mImagesRecyclerView.setAdapter(mImagesAdapter);
                        }
                        int size = onlinePhotoUrls.size();
                        int span = size;
                        if (size > 3) {
                            span = (int) Math.sqrt(size);
                        }
                        mGridLayoutManager.setSpanCount(span);
                        mImagesAdapter.refreshData(onlinePhotoUrls);
                    }

                }
            };
    private Observer<? super PassPhrase>         mPassphrasesGenerated = new Observer<PassPhrase>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(PassPhrase passPhrase) {
            if (mRecyclerView != null) {
                if (mNewPassPhraseAdapter == null) {
                    mNewPassPhraseAdapter = new NewPassPhraseAdapter()
                            .withOnCopyToClipboard(PassmaterialActivity.this)
                            .withOnOpenSettings(PassmaterialActivity.this);
                    mRecyclerView.setAdapter(mNewPassPhraseAdapter);
                }
                mNewPassPhraseAdapter.refreshData(passPhrase);
                List<Word> words = passPhrase.getWords();
                ImagesRetriever.getImagesRetriever().getImagesFor(mImagesObserver, words);
            }
        }
    };

    private Observer<PassPhraseConfig> mConfigReadObserver = new Observer<PassPhraseConfig>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(PassPhraseConfig config) {
            if (config != null) {
                PassPhraseConfig.init(config);
                onGenerateNewBundle();
            }
        }
    };
    private File mConfigFile;
    private Observer<PassPhraseConfig> mReadConfigAndOpenSettings = new Observer<PassPhraseConfig>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(PassPhraseConfig config) {
            if (config != null) {
                openSettings(PassPhraseConfig.getPassPhraseConfig());
            }
        }
    };

    @Optional
    @OnClick(android.R.id.button1)
    public void onGenerateNewBundle() {
        mGenerator.generateBundleAsync(mPassphrasesGenerated, PassPhraseConfig.getPassPhraseConfig());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passmaterial);
        ButterKnife.inject(this);
        mConfigFile = PassPhraseConfig.getConfigFile(this);
        PassPhraseConfigFacade.getConfig(mConfigReadObserver, mConfigFile);
        if (mRecyclerView != null) {
            mGenerator = PassphraseGenerators.getGenerator(mPreferredGenerator);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }
        if (mImagesRecyclerView != null) {
            mImagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mGridLayoutManager = new GridLayoutManager(this, 2);
            mImagesRecyclerView.setLayoutManager(mGridLayoutManager);
            mImagesRecyclerView.setHasFixedSize(true);
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
        }
        ClipData clip = ClipData.newPlainText(getString(R.string.clipboard_label), passphrase);
        mClipboardManager.setPrimaryClip(clip);
        Toast.makeText(this, R.string.copied_to_clirboard, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onOpenSettings() {
        if (PassPhraseConfig.isInited()) {
            openSettings(PassPhraseConfig.getPassPhraseConfig());
        } else {
            if (mConfigFile == null) {
                mConfigFile = PassPhraseConfig.getConfigFile(this);
            }
            PassPhraseConfigFacade.getConfig(mReadConfigAndOpenSettings, mConfigFile);
        }
    }

    private void openSettings(PassPhraseConfig config) {
        SettingsDialog dialog = SettingsDialog.newInstance(config);
        dialog.show(getSupportFragmentManager(), SettingsDialog.TAG);
    }

    @Override
    public void onPassphraseConfigChanged(PassPhraseConfig config) {
        if (mNewPassPhraseAdapter != null) {
            mNewPassPhraseAdapter.notifyDataSetChanged();
        }
        if (mImagesAdapter != null) {
            int wordCount = PassPhraseConfig.getPassPhraseConfig().getWordCount();
            mImagesAdapter.withImageCountToShow(wordCount);
            mImagesAdapter.notifyDataSetChanged();
        }
    }
}
