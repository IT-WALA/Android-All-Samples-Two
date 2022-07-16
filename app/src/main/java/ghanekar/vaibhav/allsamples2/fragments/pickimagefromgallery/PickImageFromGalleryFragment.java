package ghanekar.vaibhav.allsamples2.fragments.pickimagefromgallery;


import android.app.Activity;
import android.app.Fragment;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.utils.BaseFragment;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class PickImageFromGalleryFragment extends BaseFragment {

    @InjectView(R.id.img1)
    ImageView img1;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.img2)
    ImageView img2;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.img3)
    ImageView img3;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.img4)
    ImageView img4;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.img5)
    ImageView img5;
    @InjectView(R.id.btn5)
    Button btn5;
    @InjectView(R.id.img6)
    ImageView img6;
    @InjectView(R.id.btn6)
    Button btn6;
    @InjectView(R.id.img7)
    ImageView img7;
    @InjectView(R.id.btn7)
    Button btn7;
    @InjectView(R.id.img8)
    ImageView img8;
    @InjectView(R.id.btn8)
    Button btn8;
    @InjectView(R.id.img9)
    ImageView img9;
    @InjectView(R.id.btn9)
    Button btn9;
    @InjectView(R.id.img10)
    ImageView img10;
    @InjectView(R.id.btn10)
    Button btn10;
    @InjectView(R.id.btnPickMultipleImages)
    Button btnPickMultipleImages;

    public PickImageFromGalleryFragment() {
        // Required empty public constructor
    }

    public static final String TAG = PickImageFromGalleryFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final int PICK_A_PHOTO = 100;
    private static final int PICK_MULTIPLE_PHOTOS = 200;
    private int btnId = 0;
    int picSize = 256;//size;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pick_image_from_gallery, container, false);
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
        setToolBar(activity, Constants.TITLE_PICK_IMAGE_FROM_GALLERY, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnPickMultipleImages, R.id.img1, R.id.btn1, R.id.img2, R.id.btn2, R.id.img3, R.id.btn3, R.id.img4, R.id.btn4, R.id.img5, R.id.btn5, R.id.img6, R.id.btn6, R.id.img7, R.id.btn7, R.id.img8, R.id.btn8, R.id.img9, R.id.btn9, R.id.img10, R.id.btn10})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.btnPickMultipleImages:
                    pickMultipleImages();
                    break;
                case R.id.img1:
                    break;
                case R.id.btn1:
                    btnId = 1;
                    pickImage();
                    break;
                case R.id.img2:
                    break;
                case R.id.btn2:
                    btnId = 2;
                    pickImage();
                    break;
                case R.id.img3:
                    break;
                case R.id.btn3:
                    btnId = 3;
                    pickImage();
                    break;
                case R.id.img4:
                    break;
                case R.id.btn4:
                    btnId = 4;
                    pickImage();
                    break;
                case R.id.img5:
                    break;
                case R.id.btn5:
                    btnId = 5;
                    pickImage();
                    break;
                case R.id.img6:
                    break;
                case R.id.btn6:
                    btnId = 6;
                    pickImage();
                    break;
                case R.id.img7:
                    break;
                case R.id.btn7:
                    btnId = 7;
                    pickImage();
                    break;
                case R.id.img8:
                    break;
                case R.id.btn8:
                    btnId = 8;
                    pickImage();
                    break;
                case R.id.img9:
                    break;
                case R.id.btn9:
                    btnId = 9;
                    pickImage();
                    break;
                case R.id.img10:
                    break;
                case R.id.btn10:
                    btnId = 10;
                    pickImage();
                    break;
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void pickMultipleImages() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_MULTIPLE_PHOTOS);
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_A_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_A_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            try {
                Uri uri = data.getData();
                //InputStream inputStream = activity.getContentResolver().openInputStream(data.getData());
                //Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                setImageInImageView(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_MULTIPLE_PHOTOS && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            String imageEncoded;
            ArrayList<String> imagesEncodedList = new ArrayList<String>();

            if (nullCheck(data.getData())) {
                loadSingleImage(data);
            } else if (nullCheck(data.getClipData())) {
                loadMultipleImages(data);
            } else {
                Toaster.showDefaultToast(activity, "You haven't picked Image", Gravity.CENTER, Toast.LENGTH_LONG);
            }

        }
    }

    private void loadMultipleImages(Intent data) {
        ClipData mClipData = data.getClipData();
        ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
        for (int i = 0; i < mClipData.getItemCount(); i++) {

            ClipData.Item item = mClipData.getItemAt(i);
            Uri uri = item.getUri();
            mArrayUri.add(uri);
        }
        ImageView[] imageViews = {img1, img2, img3, img4, img5, img6, img7, img8, img9, img10};

        for (int i = 0; i < mArrayUri.size(); i++) {
            Uri uri = mArrayUri.get(i);
            Glide.with(activity).load(uri).apply(new RequestOptions()
                    .fitCenter()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(picSize)).into(imageViews[i]);

        }
    }

    private void loadSingleImage(Intent data) {
        Uri uri = data.getData();
        Glide.with(activity).load(uri).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(picSize)).into(img1);
    }

    private void setImageInImageView(Uri inputStream) {
        switch (btnId) {
            case 1:
                loadImageUsingGlide(inputStream, img1);
                break;
            case 2:
                loadImageUsingGlide(inputStream, img2);
                break;
            case 3:
                loadImageUsingGlide(inputStream, img3);
                break;
            case 4:
                loadImageUsingGlide(inputStream, img4);
                break;
            case 5:
                loadImageUsingGlide(inputStream, img5);
                break;
            case 6:
                loadImageUsingGlide(inputStream, img6);
                break;
            case 7:
                loadImageUsingGlide(inputStream, img7);
                break;
            case 8:
                loadImageUsingGlide(inputStream, img8);
                break;
            case 9:
                loadImageUsingGlide(inputStream, img9);
                break;
            case 10:
                loadImageUsingGlide(inputStream, img10);
                break;
        }
    }

    private void loadImageUsingGlide(Uri inputStream, ImageView imageView) {
        Glide.with(activity).load(inputStream).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(picSize)).into(imageView);
    }
}
