package com.ogs.m_bus;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class buspass extends Fragment {
    private FirebaseDatabase db;
    String firstItem;
//
FirebaseAuth auth123;
    FirebaseDatabase fbdb;
    FirebaseUser user;
    DatabaseReference dbref;
    String name;
    int flag=0;int result;
String money1;

    //-------------
    String money;
    Spinner spinner;
    String[] bus_type = new String[100];
    String[] bus_t_rate = new String[100];
    String[] stages = new String[100];
    String[] Destinationarray = new String[100];
    String[] viaarray = new String[100];
    String[] b_price = new String[100];
    String routenos;
    String busno;
    String depature;
    String Typesbusp;
    int pos_to, pos_from;
    int cost1, cost2;
    int[] total1 = new int[100];
    int cost;

    public buspass() {
        // Required empty public constructor
    }

    String from_loc, to_loc;
    String[] values =
            {"Select time range", "1 Week", "1 Month", "3 Months", "6 Months"};
    Button btn_proceed_bp;
    EditText input_name, input_from, input_to, seats;

    String text;
    String s_name, s_from, s_to;
    //to check from and to in list
    ArrayList<String> routeno = new ArrayList<>();
    ArrayList<String> start = new ArrayList<>();
    ArrayList<String> Destination1 = new ArrayList<>();
    //String[] Destination1=new String[100];
//    ArrayList<String> Destination1 = new ArrayList<>();
    ArrayList<String> via1 = new ArrayList<>();
    ArrayList<String> Types1 = new ArrayList<>();
    ArrayList<String> Rates1 = new ArrayList<>();
    ArrayList<String> Stage = new ArrayList<>();
    ArrayList<String> price = new ArrayList<>();
    // String[] via1 = new String[100];
    int count = 0, total, total_price;

    //---------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_buspass, container, false);
        btn_proceed_bp = (Button) v.findViewById(R.id.btn_proceed_bp);
        db = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
        input_name = (EditText) v.findViewById(R.id.input_name);
        s_name = input_name.getText().toString();

        input_from = (EditText) v.findViewById(R.id.input_from);

        input_to = (EditText) v.findViewById(R.id.input_to);
        //    s_to=input_to.getText().toString();

        spinner = (Spinner) v.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = spinner.getSelectedItemPosition();
                // Toast.makeText(buspass.this.getContext(), "You have selected " + values[+i], Toast.LENGTH_LONG).show();
                Log.d("message ", values[+i]);
                text = values[+i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//--------------------


        //---------------------------

        btn_proceed_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable e = input_from.getText();
                String string123 = "";
                if (e != null) {
                    string123 = e.toString();
                }
                Log.d("String value", "String" + string123);
                Editable e1 = input_to.getText();
                String string1 = "";
                if (e1 != null) {
                    string1 = e1.toString();
                }
                Log.d("String value", "String to" + string1);

                total1 = jsonparse(string123, string1);
                for (int x = 0; x < total1.length; x++) {
                    if (total1[x] != 0) {
                        total = total1[x];
                        break;
                    }
                }
                if (total != 0) {
                    Log.d("total count : in fn ", "count " + total);
                    cost = calculate_price(total);


                    if (text.toString().equalsIgnoreCase(values[1])) {
                        cost1 = 7;
                        cost2 = cost * cost1;
                        Log.d("cost total", "cost:" + cost2);
                    }
                    if (text.toString().equalsIgnoreCase(values[2])) {
                        cost1 = 30;
                        cost2 = cost * cost1;
                        Log.d("cost total", "cost:" + cost2);
                    }
                    if (text.toString().equalsIgnoreCase(values[3])) {
                        cost1 = 90;
                        cost2 = cost * cost1;
                        Log.d("cost total", "cost:" + cost2);
                    }
                    if (text.toString().equalsIgnoreCase(values[4])) {
                        cost1 = 180;
                        cost2 = cost * cost1;
                        Log.d("cost total", "cost:" + cost2);
                    }

                    String s = String.valueOf(cost2);
                    Log.d("total cost: in fn ", "cost " + s);
             //       FetchUsermoney fu=new FetchUsermoney();
                    passdetails u = new passdetails();
                    Users usermoney=new Users();
                    DatabaseReference dr = db.getReference();
                    DatabaseReference dr1 = db.getReference();
                    u.setName(input_name.getText().toString());
                    u.setFrom(input_from.getText().toString());
                    u.setTo(input_to.getText().toString());
                    u.setTime(text.toString());
                    u.setPrice(s.toString());
//         usermoney.setMoney(result);
                    dr.child("Buspass").child("Route").push().setValue(u);
//              dr1.child("Users").push().setValue(usermoney);
                    //----------------
                    fetchmoney(cost2);
                    //Log.d("Wallet money","Wllet"+result);

                    //---------------------

                } else {
                    Toast.makeText(buspass.this.getContext(), "No Such routes are available", Toast.LENGTH_SHORT).show();
                }
            }

            private void fetchmoney(final int cost2) {

int flag=0;
                final String[] strresult = new String[1];
                auth123 = FirebaseAuth.getInstance();
                fbdb = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
                user = FirebaseAuth.getInstance().getCurrentUser();
                dbref = fbdb.getInstance().getReference();
                DatabaseReference ref = dbref.child("Users");
                Users usersinfo1 = new Users();
                name = usersinfo1.getName();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String strres;
                        Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();
                        Users usersinfo = new Users();

                        while (dataSnapshotsChat.hasNext()) {
                            DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();

                            usersinfo = dataSnapshotChild.getValue(Users.class);

                            money1=usersinfo.getMoney();
                            Log.d("Value", "Money : " + usersinfo.getMoney());
                            Log.d("Value", "Email : " + usersinfo.getEmail());
                            Log.d("Value", "Name : " + usersinfo.getName());
                            Log.d("Value", "password : " + usersinfo.getPassword());
                            Log.d("Value", "phone number : " + usersinfo.getPhone());
                        }
                        result = Integer.parseInt(String.valueOf(money1));

                        Log.d("messge:","wallet:"+result);
                        Log.d("FetchUserMoney:","money "+result);
                        int result1 = result - cost2;
                        Log.d("FetchUserMoney:","Result1 "+result1);

                       strres = Integer.toString(result1);
                        Log.d("FetchUserMoney:","String result "+strres);
                        strresult[0] =strres;
                        usersinfo.setMoney(strres);
                        Toast.makeText(buspass.this.getContext(),"Ticket amount is "+cost2,Toast.LENGTH_LONG).show();
                        payment prof= new payment();
                        FragmentManager manager =getFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putString("Money",Integer.toString(cost2));
                        prof.setArguments(bundle);
                        manager.beginTransaction().replace(R.id.relativelayout_frag,prof,prof.getTag()).commit();
//                        //     dbref.child("Users").push().setValue(usersinfo);
//                        getFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.relativelayout_frag, new payment())
//                                .commit();
                   }
                    @Override

                    public void onCancelled(DatabaseError databaseError) {
                        //  Toast.makeText(buspass.this.getContext(),"Money is  not deduccted from wwallet",Toast.LENGTH_SHORT).show();

                        Log.d("messsge:","Money is  not deducted from wallet");}
                });
//    usersinfo1.setMoney(strres);
              //  dbref.child("Users").push().setValue(usersinfo1);
                //flag=1;
                //return strresult[0];

            }

            private int calculate_price(int totalcount) {
                Log.d("Count:", "Count" + totalcount);
                try {
                    for (int p = 1; p <= stages.length; p++) {
                        if (totalcount == p) {

                            total_price = totalcount * Integer.parseInt(bus_t_rate[p]);
                        }
                    }
                    Log.d("tag", "Total price" + total_price);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return total_price;
            }


        });
        return v;
    }


    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is;
            is = getContext().getAssets().open("bus_routes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    int[] jsonparse(String from, String to) {

        s_from = from;
        s_to = to;
        int[] a=new int[100];
        Log.d("String value", "String from " + s_from);
        Log.d("String value", "String to" + s_to);
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray Types = obj.getJSONArray("Types");

            for (int i = 0; i < Types.length(); i++) {
                JSONObject typesobj = Types.getJSONObject(i);
                bus_type[i] = typesobj.getString("name").toString();
                bus_t_rate[i] = typesobj.getString("rate").toString();
                Types1.add(Types.getString(i));
                Log.d("Types array:", Types.getString(i));
            }
            Log.d("tag", "Types array:" + Types1);
            JSONArray Rates = obj.getJSONArray("Rates");

            for (int i = 0; i < Rates.length(); i++) {
                JSONObject stage = Rates.getJSONObject(i);
                Log.d("Rates array:", Rates.getString(i));
                stages[i] = stage.getString("Stage");
                Stage.add(stage.getString("Stage"));
                bus_t_rate[i] = stage.getString("price");
                price.add(stage.getString("price"));
                for (int j = 0; j < Rates.length(); j++) {
                    Rates1.add(Rates.getString(j));
                }
            }
// Log.d("tag","Rates array:"+Rates1);
//            Log.d("tag","Stage array:"+Stage);
//            Log.d("tag","Rates array:"+price);

            JSONArray userArray = obj.getJSONArray("Routes");

            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                routeno.add(userDetail.getString("route no"));
                routenos = routeno.get(i).toString();
                start.add(userDetail.getString("depature"));
                busno = userDetail.getString("bus no").toString();
                Typesbusp = userDetail.getString("Types").toString();
                depature = (userDetail.getString("depature")).toString();
                JSONArray Destination = userDetail.getJSONArray("Destination");
                Log.d("Route no : ", routeno.get(i));

                for (int j = 0; j < Destination.length(); j++) {
                    Destinationarray[j] = Destination.getString(j).toString();
                    Log.d("Destination array:", Destination.getString(j));
                    Destination1.add(Destination.getString(j));
                    Log.d("tag", "Destination array:" + Destination1);
                }
                JSONArray via = userDetail.getJSONArray("via");
                for (int k = 0; k < via.length(); k++) {

                    via1.add(via.getString(k));
                    viaarray[k] = via.getString(k).toString();


                    Log.d("Via route :", via.getString(k));

                }

                via1.add(Destination1.get(i));
                Log.d("Via : ", "Via array:" + via1);
                //------------------
                for (int q = 0; q < viaarray.length; q++) {
                    if (s_from.equalsIgnoreCase(viaarray[q])) {
                        pos_from = q;
                        Log.d("Tag", "From loc" + s_from + "position:" + pos_from);
                    }
                    if (s_to.equalsIgnoreCase(viaarray[q])) {
                        pos_to = q;
                        Log.d("Tag", "To loc" + s_to + "position:" + pos_to);
                    }
                }
                count =   pos_to-pos_from;
Log.d("Count:","Count"+count);


           a[i]=count;
              }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int x=0;x<a.length;x++){
            if(count != 0 ){
                Log.d("Count:","Count"+a[x]);
            }
        }

        return a; }

    }
