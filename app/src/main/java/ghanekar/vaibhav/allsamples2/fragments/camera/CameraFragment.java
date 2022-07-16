package ghanekar.vaibhav.allsamples2.fragments.camera;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.camera.croputil.CropImage;
import ghanekar.vaibhav.allsamples2.fragments.camera.croputil.CropImageActivity;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.FragmentManagePermission;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionResult;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionUtils;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Logger;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

import static android.app.Activity.RESULT_OK;
import static ghanekar.vaibhav.allsamples2.fragments.camera.croputil.CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends FragmentManagePermission {

    @InjectView(R.id.btnClickImage)
    Button btnClickImage;
    @InjectView(R.id.btnClickAndCropImage)
    Button btnClickAndCropImage;
    @InjectView(R.id.imageView)
    ImageView imageView;

    public CameraFragment() {
        // Required empty public constructor
    }

    public static final String TAG = CameraFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final int CLICK_PHOTO = 100;
    private static final int CLICK_AND_CROP_PHOTO = 200;

    private String mCurrentPhotoPath;
    private static final String FILE_AUTORITY = "ghanekar.vaibhav.allsamples2";
    private File photoFile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
        ButterKnife.inject(this, view);
        try {
            init();
        } catch (Exception e) {
            handleException(e);
        }
        return view;
    }

    private void init() {
        activity = getActivity();
        setToolBar(activity, Constants.TITLE_CAMERA, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnClickImage, R.id.btnClickAndCropImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClickImage:
                checkPermissions();
                break;
            case R.id.btnClickAndCropImage:
                checkPermissionForCrop();
                break;
        }
    }

    private void checkPermissions() {
        String[] permissions = {PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE, PermissionUtils.Manifest_READ_EXTERNAL_STORAGE};
        askCompactPermissions(permissions, new
                PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        clickImage();
                    }

                    @Override
                    public void permissionDenied() {
                        openSettingsApp(activity);
                    }

                    @Override
                    public void permissionForeverDenied() {
                        openSettingsApp(activity);
                    }
                });
    }

    private void checkPermissionForCrop() {
        String[] permissions = {PermissionUtils.Manifest_CAMERA, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE, PermissionUtils.Manifest_READ_EXTERNAL_STORAGE};
        askCompactPermissions(permissions, new
                PermissionResult() {
                    @Override
                    public void permissionGranted() {
                        clickAndCropImage();
                    }

                    @Override
                    public void permissionDenied() {
                        openSettingsApp(activity);
                    }

                    @Override
                    public void permissionForeverDenied() {
                        openSettingsApp(activity);
                    }
                });
    }

    private void clickImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            //Thumbnail
            //startActivityForResult(intent, CLICK_PHOTO);

            //Fullsize image
            photoFile = createImageFile();
            if (nullCheck(photoFile)) {
                Uri photoUri = FileProvider.getUriForFile(activity, FILE_AUTORITY, photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, CLICK_PHOTO);
            }
        } else {
            Toaster.showDefaultToast(activity, "No app found.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }

    private void clickAndCropImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            photoFile = createImageFile();
            if (nullCheck(photoFile)) {
                Uri photoUri = FileProvider.getUriForFile(activity, FILE_AUTORITY, photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, CLICK_AND_CROP_PHOTO);
            }
        } else {
            Toaster.showDefaultToast(activity, "No app found.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }

    private File createImageFile() {
        // Create an image file name
        String timeStamp = String.valueOf(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri picUri = FileProvider.getUriForFile(activity, FILE_AUTORITY, photoFile);
        if (requestCode == CLICK_PHOTO && resultCode == RESULT_OK) {
            showFullImage(picUri);
        } else if (requestCode == CLICK_AND_CROP_PHOTO && resultCode == RESULT_OK) {
            cropTheImage(picUri);
        } else if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            picUri = result.getUri();
            showFullImage(picUri);
        }
    }

    private void cropTheImage(Uri picUri) {
        Intent intent = CropImage.activity(picUri)
                .getIntent(getContext());
        startActivityForResult(intent, CROP_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private void showFullImage(Uri picUri) {
        Glide.with(activity).load(picUri).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(512)).into(imageView);
        //addPicToGallery();
    }

    private void addPicToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        activity.sendBroadcast(mediaScanIntent);
    }

    private void showThumbnail(Bundle bundle) {
        //this is a thumbnail
        Bitmap bitmap = (Bitmap) bundle.get("data");
        imageView.setImageBitmap(bitmap);
    }
}
