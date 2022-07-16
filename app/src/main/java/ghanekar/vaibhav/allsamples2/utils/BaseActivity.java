package ghanekar.vaibhav.allsamples2.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ghanekar.vaibhav.allsamples2.activities.ErrorActivity;
import ghanekar.vaibhav.allsamples2.R;


/**
 * Created by Vaibhav on 07/04/17.
 */

public class BaseActivity extends AppCompatActivity {

    private String TAG = BaseActivity.class.getSimpleName();
    private boolean doubleBackToExitPressedOnce = false;
    String title;

    protected void gotoActivity(Activity activity1, Class activity2, Bundle bundle, boolean finishPrevActivity, String Tag, boolean isAnimateTransition, String animationType) {
        try {
            Intent intent = new Intent(activity1, activity2);
            if (null != bundle) {
                intent.putExtras(bundle);
            }

            startActivity(intent);

            if (isAnimateTransition) {
                AnimateTo.onActivityTransition(animationType, activity1);
            }

            if (finishPrevActivity) {
                finish();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void gotoFragment(Fragment fragment, int container, Bundle bundle, String Tag, Fragment currentFragment) {

        try {
            if (nullCheck(bundle)) {
                fragment.setArguments(bundle);
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

            if (nullCheck(currentFragment)) {
                transaction.addToBackStack(currentFragment.getTag());
            }

            transaction.replace(container, fragment, Tag);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            handleException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onSuperBackPressed(Activity activity) {
        try {
            if (doubleBackToExitPressedOnce) {
                finishAffinity();
            }

            this.doubleBackToExitPressedOnce = true;

            Toaster.showDefaultToast(activity, getString(R.string.please_press_back_twice_to_exit), Gravity.BOTTOM, Toast.LENGTH_LONG);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /***toolbar***/
    protected void setToolbar(Toolbar toolbar, String title, boolean isSetNavigationButton) {
        try {
            this.title = title;
            toolbar.setTitle(title);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

            if (isSetNavigationButton) {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    //encryption
    protected byte[] encrypt(String plainText, String key) throws Exception {
        try {
            byte[] clean = plainText.getBytes();

            // Generating IV.
            int ivSize = 16;
            byte[] iv = new byte[ivSize];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Hashing key.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(key.getBytes("UTF-8"));
            byte[] keyBytes = new byte[16];
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Encrypt.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(clean);

            // Combine IV and encrypted part.
            byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
            System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);

            return encryptedIVAndText;
        } catch (Exception e) {
            handleException(e);
        }
        return "".getBytes();
    }

    //decryption
    protected String decrypt(byte[] encryptedIvTextBytes, String key) throws Exception {
        try {
            int ivSize = 16;
            int keySize = 16;

            // Extract IV.
            byte[] iv = new byte[ivSize];
            System.arraycopy(encryptedIvTextBytes, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Extract encrypted part.
            int encryptedSize = encryptedIvTextBytes.length - ivSize;
            byte[] encryptedBytes = new byte[encryptedSize];
            System.arraycopy(encryptedIvTextBytes, ivSize, encryptedBytes, 0, encryptedSize);

            // Hash key.
            byte[] keyBytes = new byte[keySize];
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key.getBytes());
            System.arraycopy(md.digest(), 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Decrypt.
            Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);

            return new String(decrypted);
        } catch (Exception e) {
            handleException(e);
        }
        return "";
    }

    protected String trimmer(String str) {
        return str.replaceAll("\\s{2,}", " ");
    }

    protected boolean nullCheck(Object obj) {
        try {
            if (null == obj) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            handleException(e);
        }
        return false;
    }

    protected void handleException(Exception e) {
        try {
            Log.e("Error", "/******************************************************************/");
            e.printStackTrace();
            Bundle bundle = new Bundle();
            bundle.putSerializable("ex", e);
            gotoActivity(BaseActivity.this, ErrorActivity.class, bundle, true, TAG, false, "");
        } catch (Exception ex) {
            e.printStackTrace();
        }
    }

    //if the current frag is the last frag remained in the stack then just finish the app on backpress.
    //if there are more than 1 frags in the stack then; remove the current frag and go back to the previous frag, on backpress.
    private void goBack() {
        try {
            int numOfFragments = getSupportFragmentManager().getBackStackEntryCount();
            if (numOfFragments == 0) {
                finish();
            } else if (numOfFragments > 0) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
                getSupportFragmentManager().beginTransaction().remove(currentFragment).commitAllowingStateLoss();
                getSupportFragmentManager().popBackStack();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }
}
