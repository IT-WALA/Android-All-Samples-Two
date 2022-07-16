package ghanekar.vaibhav.allsamples2.fragments.fileoperation;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ghanekar.vaibhav.allsamples2.R;
import ghanekar.vaibhav.allsamples2.fragments.fileoperation.util.Storage;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.FragmentManagePermission;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionResult;
import ghanekar.vaibhav.allsamples2.fragments.permissions.utils.PermissionUtils;
import ghanekar.vaibhav.allsamples2.utils.Constants;
import ghanekar.vaibhav.allsamples2.utils.Toaster;

/**
 * A simple {@link Fragment} subclass.
 */
public class FileOpFragment extends FragmentManagePermission {

    @InjectView(R.id.btnInternalStorage)
    Button btnInternalStorage;
    @InjectView(R.id.btnExternalStorage)
    Button btnExternalStorage;

    public FileOpFragment() {
        // Required empty public constructor
    }

    public static final String TAG = FileOpFragment.class.getSimpleName() + "_";
    private Activity activity;
    private static final String INTERNAL_STORAGE = "INTERNAL_STORAGE";
    private static final String EXTERNAL_STORAGE = "EXTERNAL_STORAGE";

    private String internalStorageRootPath = "/sdcard/";
    private String environmentInternalStorageRootPath = Environment.getExternalStorageDirectory().getPath();
    private String publicDirectoryInternalStorageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_op, container, false);
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
        setToolBar(activity, Constants.TITLE_FILE_OP, true, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.btnInternalStorage, R.id.btnExternalStorage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnInternalStorage:
                checkPermissions(INTERNAL_STORAGE);
                break;
            case R.id.btnExternalStorage:
                checkPermissions(EXTERNAL_STORAGE);
                break;
        }
    }

    private void checkPermissions(final String storageType) {
        String[] permissions = {PermissionUtils.Manifest_READ_EXTERNAL_STORAGE, PermissionUtils.Manifest_WRITE_EXTERNAL_STORAGE};
        askCompactPermissions(permissions, new PermissionResult() {
            @Override
            public void permissionGranted() {
                if (storageType.equals(INTERNAL_STORAGE)) {
                    saveFileToInternalStorage(3);
                } else {
                    saveFileToExternalStorage();
                }
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

    private void saveFileToInternalStorage(int caseID) {
        switch (caseID) {
            case 1:
                normalInternalStorage();
                break;
            case 2:
                environmentInternalStorage();
                break;
            case 3:
                publicDirectoryInternalStorage();
                break;
        }

    }

    private void publicDirectoryInternalStorage() {
        String path = publicDirectoryInternalStorageRootPath + "/Myfolder3";
        Storage storage = new Storage(activity);
        boolean isSaved = storage.createDirectory(path, true);

        if (isSaved) {
            Toaster.showDefaultToast(activity, "Folder Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
            String filePath = path + "/Myfile3.txt";
            boolean isFileSaved = storage.createFile(filePath, "My Content");

            if (isFileSaved) {
                Toaster.showDefaultToast(activity, "File Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            } else {
                Toaster.showDefaultToast(activity, "File Not Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        } else {
            Toaster.showDefaultToast(activity, "Folder Not Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }

    private void environmentInternalStorage() {
        String path = environmentInternalStorageRootPath + "/Myfolder2";
        Storage storage = new Storage(activity);
        boolean isSaved = storage.createDirectory(path, true);

        if (isSaved) {
            Toaster.showDefaultToast(activity, "Folder Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
            String filePath = path + "/Myfile2.txt";
            boolean isFileSaved = storage.createFile(filePath, "My Content");

            if (isFileSaved) {
                Toaster.showDefaultToast(activity, "File Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            } else {
                Toaster.showDefaultToast(activity, "File Not Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        } else {
            Toaster.showDefaultToast(activity, "Folder Not Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }

    private void normalInternalStorage() {
        String path = internalStorageRootPath + "/Myfolder1";
        Storage storage = new Storage(activity);
        boolean isSaved = storage.createDirectory(path, true);

        if (isSaved) {
            Toaster.showDefaultToast(activity, "Folder Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
            String filePath = path + "/Myfile1.txt";
            boolean isFileSaved = storage.createFile(filePath, "My Content");

            if (isFileSaved) {
                Toaster.showDefaultToast(activity, "File Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            } else {
                Toaster.showDefaultToast(activity, "File Not Saved.", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        } else {
            Toaster.showDefaultToast(activity, "Folder Not Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }


    /***External storage***/
    private void saveFileToExternalStorage() {
        if (isExternalStorageWritable()) {
            String path = activity.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/MyFolder6/";
            Storage storage = new Storage(activity);
            boolean isFolderCreated = storage.createDirectory(path, true);
            if (isFolderCreated) {
                Toaster.showDefaultToast(activity, "Folder Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
                boolean isFileCreated = storage.createFile(path + "/MyFile6.txt", "My Content");

                if (isFileCreated) {
                    Toaster.showDefaultToast(activity, "File Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
                } else {
                    Toaster.showDefaultToast(activity, "File Not Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
                }
            } else {
                Toaster.showDefaultToast(activity, "Folder Not Created.", Gravity.CENTER, Toast.LENGTH_SHORT);
            }
        } else {
            Toaster.showDefaultToast(activity, "SD card is not available.", Gravity.CENTER, Toast.LENGTH_SHORT);
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
