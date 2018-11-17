package com.example.xqlim.secondlife.ChatbotFolder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.xqlim.secondlife.R;
import com.example.xqlim.secondlife.SidebarFolder.Sidebar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FieldValue;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.FirebaseFirestoreSettings;
//import com.google.firebase.firestore.Query;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//import me.eugenekoh.skylightapp.utils.Tools;

import com.ibm.watson.developer_cloud.assistant.v2.Assistant;
import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.Context;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.http.ServiceCallback;
import com.example.xqlim.secondlife.R;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//import android.content.Context;
//firestore
//chatbot

/**
 * Control class for ChatManager which controls the ChatBot function
 */


public class ChatManager extends Fragment{

    final String TAG = "Chatbot";

    /**
     * IBM Watson API Key
     */

    final Conversation myConversationService =
            new Conversation(
                    "2018-09-20",
                    "3fedf5c4-fffc-41d2-bf74-3b27e9aca9bc",
                    "cPjgN6dSCn0H"
            );

    /**
     * Context of Activity
     */
    Context context = new Context();
    android.content.Context mContext = this.getContext();

    /**
     * Handler for ListView
     */
    private Handler handler = new Handler();

    /**
     * ListView to display messages
     */
    public ListView msgView;

    /**
     * List of Hashmaps to oontain all the configurations for the Listview display
     */
    public List<HashMap<String, String>> aList;
    /**
     * Array to contain all the messages
     */
    public ArrayAdapter<String> msgList;
    /**
     * Button to send message
     */
    private Button conv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);


        View view = inflater.inflate(R.layout.activity_chatbot, container, false);

        msgView = (ListView) view.findViewById(R.id.listview);
        msgList = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);
        msgView.setAdapter(msgList);
        aList = new ArrayList<HashMap<String, String>>();
        //final TextView conversation = (TextView)findViewById(R.id.conversation);
        final EditText userInput = (EditText)view.findViewById(R.id.user_input);
        conv = (Button)view.findViewById(R.id.button);

        conversationAPI("", context, getString(R.string.workspace));
        //destroyChat("Bob Silvers");
        conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageResponse response = null;
                final String currentinput = String.valueOf(userInput.getText());
                displayMsg(currentinput);

                conversationAPI(currentinput, context, getString(R.string.workspace));
                userInput.setText("");

            }
        });


        return view;
    }

    /**
     * Resumes Fragment activity
     */
    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((Sidebar) getActivity())
                .setActionBarTitle("Chatbot");

    }

    /**
     * Takes in the user Input and sends it to IBM Watson API. If IBM Watson API understands the intent,
     * it would return the appropriate response. Else, the chatbot would print the default response.
     *
     * @param input User's input chat message
     * @param context Context of activity
     * @param workspaceId IBM Watson API key
     */
    public void conversationAPI(String input, Context context, String workspaceId){

        MessageOptions newMessage = new MessageOptions.Builder().workspaceId(workspaceId)
                .input(new InputData.Builder(input).build()).context(context).build();

        myConversationService.message(newMessage).enqueue(new ServiceCallback<MessageResponse>() {
            @Override
            public void onResponse(MessageResponse response) {
                displayMsg(response);
                if (response.getIntents().get(0).getIntent().endsWith("checkflight")){
                    //checkFlight();
                    Log.d(TAG, "checkflight");
                    displayMsg("You can recycle it at E-waste recycling Centres!");

                }
                else if (response.getIntents().get(0).getIntent().endsWith("checkhotel")){
                    displayMsg("hotel maps api");

                }
                else if (response.getIntents().get(0).getIntent().endsWith("directions")){
                    //displayDirections();
                    Log.d(TAG, "directions");
                }
                else if (response.getIntents().get(0).getIntent().endsWith("recycletoaster")){
                    //displayMsg("You can recycle it at E-waste Recycling Centres!");

                }
            }
            @Override
            public void onFailure(Exception e) {
            }
        });
    }

    /**
     * Setups the ListView for the Bot reply and prints the message.
     * @param msg Message to tbe printed
     */

    public void displayMsg(MessageResponse msg){
        final MessageResponse mssg = msg;
        handler.post(new Runnable(){
            @Override
            public void run() {
                String text = mssg.getOutput().getText().get(0);
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("listview_title", "Bot");
                hm.put("listview_discription", text);
                hm.put("listview_image", Integer.toString(R.drawable.chatbot));
                aList.add(hm);
                String[] from = {"listview_image", "listview_title", "listview_discription"};
                int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
                SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_activity, from, to);
                msgList.add("Bot: " + text);
                msgView.setAdapter(simpleAdapter);
                msgView.setSelection(msgList.getCount()-1);
                msgView.smoothScrollToPosition(msgList.getCount() - 1);
                context = mssg.getContext();
            }
        });
    }

    /**
     * Setups the Listview to display User's input message.
     * Prints User's input message
     * @param msg User's input message
     */
    public void displayMsg(String msg) {
        final String mssg = msg;
        handler.post(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("listview_title", "You");
                hm.put("listview_discription", mssg);
                hm.put("listview_image", Integer.toString(R.drawable.profile_pc));
                aList.add(hm);
                String[] from = {"listview_image", "listview_title", "listview_discription"};
                int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
                SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_activity, from, to);
                msgList.add(mssg);
                msgView.setAdapter(simpleAdapter);
                msgView.setSelection(msgList.getCount()-1);
                msgView.smoothScrollToPosition(msgList.getCount() - 1);
            }
        });
    }

//
}