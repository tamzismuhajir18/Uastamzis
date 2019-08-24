package com.uasjsaonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listView;
    private ActionMode actionMode;
    private ActionMode.Callback amCallback;
    private List<Contact> listhp;
    private ListAdapterContact adapter;
    private Contact selectedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview_main);


        listhp = new ArrayList<Contact>();
        loadDataHP();
    }

    private void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete " + selectedList.getName() + " ?");
        builder.setTitle("Delete");
        builder.setPositiveButton("Yes", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        listhp.remove(listhp.indexOf(selectedList));
                        Toast.makeText(getApplicationContext(), "deleted",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("No", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setIcon(android.R.drawable.ic_menu_delete);
        alert.show();
    }


    private void processResponse(String response) {
        try {
            JSONObject jsonObj = new JSONObject(response);
            JSONArray jsonArray = jsonObj.getJSONArray("contact");
            Log.d(TAG, "data length: " + jsonArray.length());
            Contact contact = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                contact = new Contact();
                contact.setName(obj.getString("name"));
                contact.setEmail(obj.getString("email"));
                contact.setAddress(obj.getString("address"));
                contact.setGender(obj.getString("gender"));
                contact.setMobile(obj.getString("mobile"));
                contact.setHome(obj.getString("home"));
                contact.setOffice(obj.getString("office"));

                this.listhp.add(contact);
            }
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
        }
    }
    public void loadDataHP() {
        try {
            ArrayList<NameValuePair> nameValuePairs = new
                    ArrayList<NameValuePair>(0);
            AsyncInvokeURLTask task = new
                    AsyncInvokeURLTask(nameValuePairs,
                    new AsyncInvokeURLTask.OnPostExecuteListener() {
                        @Override
                        public void onPostExecute(String result) {
// TODO Auto-generated method stub
                            Log.d("TAG", "Login:" + result);
                            if (result.equals("timeout") ||
                                    result.trim().equalsIgnoreCase("Tidak dapat Terkoneksi ke Data Base")) {
                                Toast.makeText(getBaseContext(), "Tidak Dapat Terkoneksi dengn Server", Toast.LENGTH_SHORT).show();
                            } else {
                                processResponse(result);
                                populateListView();
                            }
                        }
                    });
            task.showdialog = true;
            task.message = "Load Data Contact Please Wait...";
            task.applicationContext = MainActivity.this;
            task.mNoteItWebUrl = "list_contact.php";
            task.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateListView() {
        adapter = new ListAdapterContact(getApplicationContext(), this.listhp);
        listView.setAdapter(adapter);

    }
}

