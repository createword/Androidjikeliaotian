package com.jikexueyuan.jike_chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        Button btn_add = (Button)findViewById(R.id.btn_add);
        final EditText et_username = (EditText)findViewById(R.id.et_username);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                if(!username.equals("")) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("username", App.username);
                    params.add("target", username);
                    client.post("http://192.168.56.1/chat/addfriend.php", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            String response = new String(bytes);
                            Log.e("debug", response);
                            JSONObject object = null;
                            try {
                                object = new JSONObject(response);
                                String status = object.getString("status");
                                if(status.equals("success"))
                                {
                                    Toast.makeText(AddFriendActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                                    AddFriendActivity.this.finish();
                                }
                                else
                                {
                                    Toast.makeText(AddFriendActivity.this, "用户名不存在", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            Toast.makeText(AddFriendActivity.this, "网络错误，请稍后重试", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(AddFriendActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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
}
