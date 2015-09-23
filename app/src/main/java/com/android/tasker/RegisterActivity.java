package com.android.tasker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.tasker.model.User;
import com.android.tasker.repository.RepositoryFactory;
import com.android.tasker.repository.TaskRepoInterface;

public class RegisterActivity extends AppCompatActivity {

    private Button signUp;
    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private SharedPreferences userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name = (EditText)findViewById(R.id.textName);
        email = (EditText)findViewById(R.id.textEmail);
        password = (EditText)findViewById(R.id.textPassword);
        confirmPassword = (EditText)findViewById(R.id.textConfirmPassword);
        signUp = (Button)findViewById(R.id.buttonRegister);
        userData = getPreferences(MODE_PRIVATE);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userConfirmPassword = confirmPassword.getText().toString();
                boolean correctEmail = isValidEmail((CharSequence) userEmail);

                if(userName != null && correctEmail && userPassword != null && userConfirmPassword.equals(userPassword)) {

                    SharedPreferences.Editor editor = userData.edit();
                    editor.putString("name", userName);
                    editor.putString("email", userEmail);
                    editor.putString("password", userPassword);
                    editor.commit();


                    User user = new User(userName, userEmail, userPassword);

                    TaskRepoInterface taskRepo = RepositoryFactory.getTaskRepo();
                    taskRepo.register(user);

                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
/*
    void selectOption() {
            final CharSequence[] items = { "Take Photo", "Choose from Gallery", "Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (items[item].equals("Take Photo")) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                    } else if (items[item].equals("Choose from Library")) {
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(
                                Intent.createChooser(intent, "Select File"), MEDIA_TYPE_IMAGE);
                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
