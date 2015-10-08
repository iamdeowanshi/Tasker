package com.android.tasker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tasker.model.User;
import com.android.tasker.repository.RepoCallBack;
import com.android.tasker.repository.RepositoryFactory;
import com.android.tasker.repository.TaskRepoInterface;

public class LoginActivity extends Activity {

    private TextView registerLink;
    private Button login;
    private EditText email;
    private EditText password;
    private Handler handler;

    RepoCallBack<User> loginCallback = new RepoCallBack<User>() {
        @Override
        public void onSuccess(final User data) {
            handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    RepositoryFactory.getTaskRepo().setUserId(data.getId());
                    Intent intent = new Intent(getApplicationContext(), TaskListActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onFailure(Throwable tr) {
            handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Email or Password Incorrect", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerLink = (TextView) findViewById(R.id.textRegisterLink);
        login = (Button) findViewById(R.id.buttonLogin);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskRepoInterface taskRepo = RepositoryFactory.getTaskRepo();

                String textEmail = email.getText().toString();
                String textPassword = password.getText().toString();

                taskRepo.login(textEmail, textPassword, loginCallback);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
}
